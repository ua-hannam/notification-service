def app

node {
    try {
        notifySlack("STARTED", "#0000FF")
    
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
 	} catch(e) {
        notifySlack("FAILED", "#FF0000")
	}
}
