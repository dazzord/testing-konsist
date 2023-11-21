import java.io.*

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("de.mannodermaus.android-junit5") version "1.9.3.0"
}

android {
    namespace = "com.sample"

    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
        isCoreLibraryDesugaringEnabled = true
    }


}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")

    testImplementation("com.lemonappdev:konsist:0.13.0")
}



open class CreateSummaryXml : DefaultTask() {
    @TaskAction
    fun createSummaryXml() {
        val projectDir = project.projectDir.absolutePath
        val inputDir = "$projectDir/build/test-results/testDebugUnitTest"
        val outputDir = "build/summary.xml"

        val dir = File(inputDir)
        val outputFile = project.file(outputDir)

        if (!dir.exists() || !dir.isDirectory) {
            println("Directory of konsist test not found in this path: $inputDir")
            return
        }

        var totalTests = 0
        var totalTestsPassed = 0

        val docBuilder = javax.xml.parsers.DocumentBuilderFactory.newInstance().newDocumentBuilder()
        val doc: org.w3c.dom.Document = docBuilder.newDocument()
        val root: org.w3c.dom.Element = doc.createElement("architectureAdherence")
        doc.appendChild(root)

        dir.listFiles { file -> file.name.endsWith(".xml") }?.forEach { file ->
            println("Found XML file: ${file.name}")
            val parsedXml = javax.xml.parsers.DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file)

            val tests = parsedXml.documentElement.getAttribute("tests").toInt()
            val failures = parsedXml.documentElement.getAttribute("failures").toInt()

            totalTests += tests
            totalTestsPassed += (tests - failures)

            val rootModule = doc.createElement("${file.name}")

            val adherenceRatio = if ((tests - failures) == 0) "0" else (tests.toDouble() / (tests - failures)).toString()

            rootModule.appendChild(doc.createElement("totalTests").apply { textContent = tests.toString() })
            rootModule.appendChild(doc.createElement("totalTestsPassed").apply { textContent = (tests - failures).toString() })
            rootModule.appendChild(doc.createElement("ratioAdherence").apply { textContent = adherenceRatio })

            root.appendChild(rootModule)
        }


        root.appendChild(doc.createElement("ProjectTotalTests").apply { textContent = totalTests.toString() })
        root.appendChild(doc.createElement("ProjectTotalTestPassed").apply { textContent = totalTestsPassed.toString() })
        root.appendChild(doc.createElement("ProjectRatioAdherence").apply { textContent = (totalTestsPassed.toDouble() / totalTests).toString() })

        val transformer = javax.xml.transform.TransformerFactory.newInstance().newTransformer()
        val source = javax.xml.transform.dom.DOMSource(doc)
        val writer = FileWriter(outputFile)
        val result = javax.xml.transform.stream.StreamResult(writer)

        transformer.transform(source, result)
        writer.close()
    }
}

tasks.register<CreateSummaryXml>("createSummaryXml")