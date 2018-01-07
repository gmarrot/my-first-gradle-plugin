package com.betomorrow.gradle.sample;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

class MyFirstPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println("Hello World")
    }

}
