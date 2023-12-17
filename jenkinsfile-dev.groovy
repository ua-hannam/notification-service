def app

node {
        try {
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
        
        slackSend(channel: '#your-channel', message: 'Build successful')
        
        } catch (Exception e) {
            slackSend(channel: '#your-channel', message: 'Build failed: ' + e.getMessage())
        }
}
