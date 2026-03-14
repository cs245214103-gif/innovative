pipeline {
 agent any

 
 tools {
  jdk 'JDK'
  maven 'MAVEN'
 }

 stages {

  stage('Clone Code') {
   steps {
    git branch: 'main', url: 'https://github.com/cs245214103-gif/innovative.git'
   }
  }

  stage('Build') {
   steps {
    bat 'mvn clean package'
   }
  }

  stage('Docker Build') {
   steps {
    bat 'docker build -t student-manager .'
   }
  }

  stage('Run Container') {
   steps {
    bat 'docker run student-manager'
   }
  }

 }
}
