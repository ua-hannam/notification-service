def app

/* Slack Notification Set */
def notifyProductionDeploy() {
    if (currentBuild.currentResult == 'SUCCESS') {
        def message = "@here Build <${env.BUILD_URL}|${currentBuild.displayName}> " +
            "${currentBuild.currentResult} deployed to the production"
        slackSend(message: message, channel: '#micro', color:  '#00FF00', token: 'token')
    } else {
        def message = "@here Build <${env.BUILD_URL}|${currentBuild.displayName}> " +
            "${currentBuild.currentResult} deployed to the production"
        slackSend(message: message, channel: '#micro', color: 'danger', token: 'token')
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
