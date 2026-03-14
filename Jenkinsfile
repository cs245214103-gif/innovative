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
     bat 'echo 1 101 Arun 85 2 3 | docker run -i student-manager'
     bat 'docker logs student-container'
   }
  }

 }
}
