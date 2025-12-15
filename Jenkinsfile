pipeline {
    agent any

    tools {
        jdk 'jdk21'
        maven 'maven'
    }

    environment {
        SONAR_TOKEN = credentials('sonar-token')
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Asmae7779/refactored-finance-app.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQubeServer') {
                    bat 'mvn sonar:sonar'
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 30, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }

   post {
       always {
           emailext(
               subject: "📌 Jenkins - ${env.JOB_NAME} #${env.BUILD_NUMBER} : ${currentBuild.currentResult}",
               body: """
   Bonjour,

   Le pipeline Jenkins est terminé.

   Projet : ${env.JOB_NAME}
   Build  : #${env.BUILD_NUMBER}
   Statut : ${currentBuild.currentResult}

   Détails :
   ${env.BUILD_URL}

   Cordialement,
   Jenkins
   """,
               to: "asmaeelyakoubi73@gmail.com"
           )
       }

       success {
           echo "SUCCESS"
       }

       failure {
           echo "FAILURE"
       }

       aborted {
           echo "ABORTED"
       }
   }
}