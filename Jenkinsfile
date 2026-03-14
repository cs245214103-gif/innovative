pipeline {
 agent any

 stages {

  stage('Clone Code') {
   steps {
    git 'https://github.com/yourrepo/student-manager.git'
   }
  }

  stage('Build') {
   steps {
    sh 'mvn clean package'
   }
  }

  stage('Docker Build') {
   steps {
    sh 'docker build -t student-manager .'
   }
  }

  stage('Run Container') {
   steps {
    sh 'docker run -d student-manager'
   }
  }

 }
}
