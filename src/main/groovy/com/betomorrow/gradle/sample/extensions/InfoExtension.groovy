package com.betomorrow.gradle.sample.extensions

import org.gradle.api.Project
import org.gradle.api.provider.Property

class InfoExtension {

    Property<String> filename
    Property<Integer> logSize

    InfoExtension(Project project) {
        filename = project.objects.property(String)
        logSize = project.objects.property(Integer)

        filename.set("info.json")
        logSize.set(10)
    }

}
