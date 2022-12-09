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
        sshagent(credentials : ['remote-sever']) {
          sh 'ssh root@kadiro.sbs'
        }

      }
    }

  }
  environment {
    GITHUB_TOKEN = 'secret'
  }
}
