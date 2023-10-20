import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class MyPlugin2 implements Plugin<Project> {
    @Override
    public void apply(Project target) {
        System.out.println("This my plugin2");
    }
}
