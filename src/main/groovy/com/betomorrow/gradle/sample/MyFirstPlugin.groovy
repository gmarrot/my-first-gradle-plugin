package com.betomorrow.gradle.sample

import com.betomorrow.gradle.sample.extensions.InfoExtension
import com.betomorrow.gradle.sample.tasks.InfoTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class MyFirstPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.with {
            def info = extensions.create("info", InfoExtension, project)

            task("info", description: "Write project information", group: "other", type: InfoTask) {
                filePath = info.filename
                logSize = info.logSize
            }
        }
    }

}
