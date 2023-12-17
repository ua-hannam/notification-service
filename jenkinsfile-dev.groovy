def app

/* Slack Notification Set */
def notifyProductionDeploy() {
    
    if (currentBuild.currentResult == 'SUCCESS') {
        def message = """
*Build successful*
Job : _${env.JOB_NAME}_[#${env.BUILD_NUMBER}] <${env.BUILD_URL}|OPEN>
"""
        slackSend(message: message, channel: '#backend-build-log', color:  '#00FF00', token: 'token')
    } else {
        def message = """
*Build failed*
- Job : _${env.JOB_NAME}_[#${env.BUILD_NUMBER}] <${env.BUILD_URL}|OPEN>
"""
        slackSend(message: message, channel: '#backend-build-log', color: 'danger', token: 'token')
    }
}

node {
    slackSend(channel: '#backend-bulid-log', message: """
*Build start(_${env.JOB_NAME}_[#${env.BUILD_NUMBER}])*
""")
        
    stage('Checkout') {
        checkout scm 
    }

    stage('Ready') {      
        echo 'Ready to build' 
        gradleHome = tool 'gradle'   
    }

    stage('Build') {
        sh "${gradleHome}/bin/gradle clean build -x test"
    }
}

stage('notifyProductionDeploy') {
  // do stuff
  notifyProductionDeploy()
}
