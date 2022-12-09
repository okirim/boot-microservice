pipeline {
  agent {
    node {
      label 'docker'
    }

  }
  stages {
    stage('build') {
      steps {
        echo 'build'
      }
    }

  }
  environment {
    GITHUB_TOKEN = 'secret'
  }
}