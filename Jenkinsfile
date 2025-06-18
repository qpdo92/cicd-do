pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Deploy') {
            steps {
                sh 'scp target/cicd-do-0.0.1-SNAPSHOT.jar ec2-user@ec2-35-181-114-26.eu-west-3.compute.amazonaws.com:/home/ec2-user/'
            }
        }
    }
}
