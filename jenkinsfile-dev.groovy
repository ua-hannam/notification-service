def app
def notifySlack(STATUS, COLOR) {
    slackSend channel: '#backend-build-log', 
	message: STATUS+" : " + "${env.JOB_NAME}[${env.BUILD_NUMBER}] <${env.BUILD_URL}|OPEN>", 
	color: COLOR, teamDomain: 'uahan'
}


node {
    try {
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
    
    notifySlack("SUCCESS", "#00FF00")

    } catch (Exception e) {
	notifySlack("FAILED", "#FF0000")
    }
}
