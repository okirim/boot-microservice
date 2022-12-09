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
          sh 'ssh -T root@kadiro.sbs'
        }

      }
    }

  }
  environment {
    GITHUB_TOKEN = 'secret'
  }
}
