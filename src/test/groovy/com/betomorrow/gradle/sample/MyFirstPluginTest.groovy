package com.betomorrow.gradle.sample

import com.betomorrow.gradle.sample.tasks.InfoTask
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

class MyFirstPluginTest extends Specification {

    def "test apply creates info task"() {
        when:
        def project = ProjectBuilder.builder().build()
        project.apply plugin: 'com.betomorrow.my-first-gradle-plugin'

        then:
        project.tasks.info != null
    }

    def "test info task uses default property values"() {
        when:
        def project = ProjectBuilder.builder().build()
        project.apply plugin: 'com.betomorrow.my-first-gradle-plugin'

        then:
        def infoTask = (InfoTask) project.tasks.info
        infoTask.filePath.get() == "info.json"
        infoTask.logSize.get() == 10
    }

    def "test info task properties can be overriden"() {
        when:
        def project = ProjectBuilder.builder().build()
        project.apply plugin: 'com.betomorrow.my-first-gradle-plugin'

        project.info {
            filename = "package.json"
            logSize = 1
        }

        then:
        def infoTask = (InfoTask) project.tasks.info
        infoTask.filePath.get() == "package.json"
        infoTask.logSize.get() == 1
    }

}
