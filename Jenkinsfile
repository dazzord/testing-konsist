pipeline {
    agent any

    environment {
        DIR = 'testing-konsist'
        ANDROID_HOME = '/var/jenkins_home/sdk'
    }
    options{
        disableConcurrentBuilds()
    }
    stages {
        stage('Check Github') {
            steps {
                script {
      if (fileExists('testing-konsist')) {
        echo 'The directory exists!'
        sh(""" rm -rf testing-konsist """)
      }
      sh 'git clone https://github.com/dazzord/testing-konsist.git'
    }

      }
        }
        stage('Konsist') {
            steps {

                script {
  try {
        dir(DIR) {
                    sh './gradlew konsist_test:test'
                }
  } catch (Exception e) {
     sh "exit 0"
  }
}


            }
        }
        stage('Konsist Xml Output') {
            steps {
                dir(DIR) {
                    sh './gradlew createSummaryXml'
                }
            }
        }
    }
}
