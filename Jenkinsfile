pipeline {
    agent any

    environment {
        IMAGE_NAME = "sonajerry/java-maven-test"
        CONTAINER_NAME = "java-maven-test-container"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'docker',   // Jenkins credential ID
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh '''
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                    '''
                }
            }
        }

        stage('Docker Build') {
            steps {
                // Build Docker image that contains your Maven project
                sh '''
                    docker build -t $IMAGE_NAME .
                '''
            }
        }

        stage('Run Maven Tests') {
            steps {
                // Run Maven tests inside Docker container
                sh '''
                    docker run --rm -v $PWD:/app -w /app $IMAGE_NAME mvn test
                '''
            }
        }

        stage('Docker Push') {
            steps {
                sh 'docker push $IMAGE_NAME'
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                    docker rm -f $CONTAINER_NAME || true
                    docker run -d --name $CONTAINER_NAME $IMAGE_NAME
                '''
            }
        }
        stage('Run Maven Tests') {
    steps {
        sh '''
            docker run --rm -v $PWD:/app -w /app $IMAGE_NAME mvn clean test
        '''
    }
}

    }

    post {
        success {
            echo "✅ Maven tests ran, Docker image built & deployed successfully"
        }
        failure {
            echo "❌ Pipeline failed"
        }
    }
}
