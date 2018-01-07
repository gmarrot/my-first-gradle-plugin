package com.betomorrow.gradle.sample.tasks

import groovy.json.JsonBuilder
import org.ajoberstar.grgit.Grgit
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.TaskAction

import java.time.Instant

class InfoTask extends DefaultTask {

    Property<String> filePath = project.objects.property(String)
    Property<Integer> logSize = project.objects.property(Integer)

    void setFilePath(String value) {
        filePath.set(value)
    }

    void setLogSize(int value) {
        logSize.set(value)
    }

    @TaskAction
    void generateFile() {
        def grgit = Grgit.open()

        def builder = new JsonBuilder()

        def root = builder.metadata {}
        root.metadata.name = project.name
        root.metadata.version = project.version
        root.metadata.buildDate = Instant.now().toString()
        root.metadata.revision = grgit.log().first().getAbbreviatedId()
        root.changelog = grgit.log(maxCommits: logSize.get()).collect { it.fullMessage }

        project.file(filePath.get()).withWriter {
            it.write(builder.toPrettyString())
        }
    }

}
