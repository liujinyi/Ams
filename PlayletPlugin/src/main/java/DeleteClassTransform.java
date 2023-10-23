import com.android.build.api.transform.DirectoryInput;
import com.android.build.api.transform.Format;
import com.android.build.api.transform.JarInput;
import com.android.build.api.transform.QualifiedContent;
import com.android.build.api.transform.Transform;
import com.android.build.api.transform.TransformException;
import com.android.build.api.transform.TransformInput;
import com.android.build.api.transform.TransformInvocation;
import com.android.build.api.transform.TransformOutputProvider;
import com.android.build.gradle.internal.pipeline.TransformManager;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;

public class DeleteClassTransform extends Transform {
    @Override
    public String getName() {
        return "DeleteClassTransform";
    }

    @Override
    public Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_JARS;
    }

    @Override
    public Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT;
    }

    @Override
    public boolean isIncremental() {
        return false;
    }


    @Override
    public void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation);
        Collection<TransformInput> inputs = transformInvocation.getInputs();
        Collection<TransformInput> referenceInputs = transformInvocation.getReferencedInputs();
        TransformOutputProvider outPutProvider = transformInvocation.getOutputProvider();
        inputs.forEach(transformInput -> {
            transformInput.getJarInputs().forEach(jarInput -> {
                processJarInput(jarInput, outPutProvider);
            });
            transformInput.getDirectoryInputs().forEach(directoryInput -> {
                processDirectoryInput(directoryInput, outPutProvider);
            });
        });

    }

    void processJarInput(JarInput jarInput, TransformOutputProvider outputProvider) {
        // pangrowth-dpsdk-4.9.0.2-runtime.jar
        File dest = outputProvider.getContentLocation(jarInput.getFile().getAbsolutePath(), jarInput.getContentTypes(), jarInput.getScopes(), Format.JAR);
        //将修改过的字节码copy到dest,就可以实现编译期间干预字节码的目的
        if (jarInput.getFile().getName().equals("pangrowth-dpsdk-4.9.0.2-runtime.jar")) {

            try {
                File tempFile = new File(jarInput.getFile().getParent(), "temp.jar");
                JarOutputStream outputStream = new JarOutputStream(new FileOutputStream(tempFile));
                JarFile jarFile = new JarFile(jarInput.getFile());
                Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements()) {
                    JarEntry next = entries.nextElement();
                    String className = next.getName();
//                    System.out.println("拷贝文件 className name-----" + className);
                    if (className.equals("com/bytedance/sdk/dp/core/business/view/DPDrawSeekLayout.class") ||
                            className.equals("com/bytedance/sdk/dp/core/business/view/DPDrawSeekLayout$1.class") ||
                            className.equals("com/bytedance/sdk/dp/core/business/view/DPDrawSeekLayout$2.class")) {
                        System.out.println("not need copy " + className);
                    } else {
                        InputStream inputStream = jarFile.getInputStream(next);
                        outputStream.putNextEntry(new ZipEntry(className));
                        int len = inputStream.read();
                        while (len != -1) {
                            outputStream.write(len);
                            len = inputStream.read();
                        }
                    }
                }
                outputStream.flush();
                outputStream.close();
                FileUtils.copyFile(tempFile, dest);
                tempFile.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            try {
                FileUtils.copyFile(jarInput.getFile(), dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    void processDirectoryInput(DirectoryInput directoryInput, TransformOutputProvider outputProvider) {
        File dest = outputProvider.getContentLocation(directoryInput.getName(), directoryInput.getContentTypes(), directoryInput.getScopes(), Format.DIRECTORY);
        //将修改过的字节码copy到dest,就可以实现编译期间干预字节码的目的
        System.out.println("拷贝文件夹 dest -----" + dest.getAbsolutePath());
        try {
            FileUtils.copyDirectory(directoryInput.getFile(), dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
