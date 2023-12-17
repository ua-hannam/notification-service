node {
    try{
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

    slackSend(channel: '#backend-bulid-log', color: '#00FF00', message: """
*Build successful*
Job : _${env.JOB_NAME}_[#${env.BUILD_NUMBER}] <${env.BUILD_URL}|OPEN>
""")

    } catch (Exception e) {
        slackSend(channel: '#backend-bulid-log', color: 'danger', message: """ 
*Build failed*
- Job : _${env.JOB_NAME}_[#${env.BUILD_NUMBER}] <${env.BUILD_URL}|OPEN>
} """)
    }
}
