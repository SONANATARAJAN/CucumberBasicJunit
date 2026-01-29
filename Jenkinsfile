pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Run Maven Tests') {
            steps {
                sh 'mvn clean test'
            }
        }
    }

  
}
