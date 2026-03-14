pipeline {
agent any


tools {
    maven 'MAVEN'
    jdk 'JDK'
}

stages {

    stage('Clone Code') {
        steps {
            git branch: 'main', url: 'https://github.com/cs245214103-gif/innovative.git'
        }
    }

    stage('Build Project') {
        steps {
            bat 'mvn clean package'
        }
    }

    stage('Build Docker Image') {
        steps {
            bat 'docker build -t student-manager .'
        }
    }

    stage('Run Container') {
        steps {
            bat 'docker rm -f student-container || exit 0'
            bat 'docker run -d -p 9090:9090 --name student-container student-manager'
        }
    }

}

}
