def app

node {
    try{
    slackSend(channel: '#backend-bulid-log', message: """
Build start- -- ${env.JOB_NAME}
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

    slackSend(channel: '#backend-bulid-log', color: '#00FF00', message: """
Build successful
Job : ${env.JOB_NAME} - [#${env.BUILD_NUMBER}] <${env.BUILD_URL}|OPEN>
""")

    } catch (Exception e) {
        slackSend(channel: '#backend-bulid-log', color: '#FF0000', message: """ 
Build failed
- Job : ${env.JOB_NAME} - [#${env.BUILD_NUMBER}] <${env.BUILD_URL}|OPEN>
} """)
    }
}
