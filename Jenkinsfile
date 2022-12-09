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

    stage('deploy') {
      steps {
        sshagent() {
          sh 'ssh root@kadiro.sbs'
        }

      }
    }

  }
  environment {
    GITHUB_TOKEN = 'secret'
  }
}