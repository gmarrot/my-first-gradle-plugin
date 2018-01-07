package com.betomorrow.gradle.sample

import groovy.json.JsonBuilder
import org.ajoberstar.grgit.Grgit
import org.gradle.api.Plugin
import org.gradle.api.Project

import java.time.Instant

class MyFirstPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.with {
            def info = extensions.create("info", InfoExtension)

            task("info", description: "Show project information", group: "other") {
                doLast {
                    def grgit = Grgit.open()

                    def builder = new JsonBuilder()

                    def root = builder.metadata {}
                    root.metadata.name = project.name
                    root.metadata.version = project.version
                    root.metadata.buildDate = Instant.now().toString()
                    root.metadata.revision = grgit.log().first().getAbbreviatedId()
                    root.changelog = grgit.log(maxCommits: info.logSize).collect { it.fullMessage }

                    project.file(info.filename).withWriter {
                        it.write(builder.toPrettyString())
                    }
                }
            }
        }
    }

}
