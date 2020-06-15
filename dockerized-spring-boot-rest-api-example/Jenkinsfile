pipeline {
        agent any
        environment{
           DOCKER_TAG = getDockerTag() 
        }
        stages {
             stage('Sonarqube') {
                 environment {
                     scannerHome = tool 'SonarQubeScanner'
                 }
                 steps {
                     withSonarQubeEnv('SonarQubeServer') {
                        sh "${scannerHome}/bin/sonar-scanner"
                     }
                     timeout(time: 30, unit: 'MINUTES') {
                        script{
                          def qg = waitForQualityGate()
                          if (qg.status != 'OK') {
                            error "Pipeline aborted due to quality gate failure: ${qg.status}"
                          }
                          else{
                            
                               withCredentials([usernamePassword(credentialsId: 'nexus-repo', passwordVariable: 'pass', usernameVariable: 'user')]) {
                                  sh "docker build . -t localhost:8083/repository/sonarqube-repo/spring-rest-api:${DOCKER_TAG}" 
                                  sh "docker login localhost:8083 -u ${user} -p ${pass}"
                                  sh "docker push localhost:8083/repository/sonarqube-repo/spring-rest-api:${DOCKER_TAG}"
                                  sh "docker run -d -p 8060:8080 --name dev-spring localhost:8083/repository/sonarqube-repo/spring-rest-api:${DOCKER_TAG}"
                                  sh "docker run -d -p 8070:8080 --name uat-spring localhost:8083/repository/sonarqube-repo/spring-rest-api:${DOCKER_TAG}"
                               }
        

                          }
                        }
                        
                     }
                 }
             }
        }

}

def getDockerTag(){
  def tag = sh script: "git rev-parse HEAD", returnStdout: true
  return tag
}

