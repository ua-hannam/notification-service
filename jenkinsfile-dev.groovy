def app

node {
        try {
        slackSend(channel: '# backend-bulid-log', message: 'Build start', token: 'w8rDsXyLwtcCgxzzFuH3Op0R')
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
        
        slackSend(channel: '# backend-bulid-log', message: 'Build successful', token: 'w8rDsXyLwtcCgxzzFuH3Op0R')
        
        } catch (Exception e) {
            slackSend(channel: '# backend-bulid-log', message: 'Build failed: ' + e.getMessage(), token: 'w8rDsXyLwtcCgxzzFuH3Op0R')
            throw e 
        }
}
