pipeline {
    agent any
  stages {
    stage('build') {
      steps {
        echo 'build'
      }
    }

    stage('deploy') {
      steps {
        sshagent(credentials : ['remote-server']) {
         sh 'ssh -o StrictHostKeyChecking=no root@161.35.227.21 uptime'
         sh 'ssh -v root@161.35.227.21'
        }

      }
    }

  }
  environment {
    GITHUB_TOKEN = 'secret'
  }
}
