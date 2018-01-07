import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

class MyFirstFunctionalTest extends Specification {

    @Rule
    final TemporaryFolder testProjectDir = new TemporaryFolder()
    File buildFile

    def setup() {
        buildFile = testProjectDir.newFile('build.gradle')
    }

    def "hello world task prints Hello World!"() {
        given:
        buildFile << """
            task helloWorld {
                doLast {
                    println 'Hello World!'
                }                
            }
        """

        when:
        def result = GradleRunner.create()
                .withProjectDir(testProjectDir.root)
                .withArguments("helloWorld")
                .build()

        then:
        result.output.contains("Hello World!")
        result.task(":helloWorld").outcome == TaskOutcome.SUCCESS
    }

    def "info task generates metadata file"() {
        given:
        buildFile << """
            plugins {
                id 'com.betomorrow.my-first-gradle-plugin'
            }

            version = "1.0-SNAPSHOT"

            info {
                filename = "package.json"
                logSize = 1
            }
        """

        when:
        def result = GradleRunner.create()
                .withProjectDir(testProjectDir.root)
                .withDebug(true)
                .withArguments("info")
                .withPluginClasspath()
                .build()

        then:
        result.task(":info").outcome == TaskOutcome.SUCCESS
    }

}
