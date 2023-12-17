def app

node {
        try {
        slackSend(channel: '# backend-bulid-log', message: 'Build start')
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
        
        slackSend(channel: '# backend-bulid-log', message: 'Build successful')
        
        } catch (Exception e) {
            slackSend(channel: '# backend-bulid-log', message: 'Build failed: ' + e.getMessage())
            throw e 
        }
}
