package com.betomorrow.gradle.sample

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

}
