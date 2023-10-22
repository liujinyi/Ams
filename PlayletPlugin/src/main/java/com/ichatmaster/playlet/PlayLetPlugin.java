package com.ichatmaster.playlet;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class PlayLetPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        System.out.println("PlayLetPlugin apply success");
    }
}