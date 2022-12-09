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
        sshagent(credentials : ['remote-sever']) {
          sh 'ssh -T root@161.35.227.21'
        }

      }
    }

  }
  environment {
    GITHUB_TOKEN = 'secret'
  }
}
