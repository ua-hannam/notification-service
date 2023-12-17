pipeline {
    agent any
    
    post {
        success {
            slackSend (
                channel: '#backend-build-log', 
                color: '#00FF00', 
                message: """
SUCCESS 
Job : ${env.JOB_NAME} - [#${env.BUILD_NUMBER}]
<${env.BUILD_URL}|OPEN>
"""
            )
        }
        failure {
            slackSend (
                channel: '#backend-build-log', 
                color: '#FF0000', 
                message: """
FAIL 
- Job : ${env.JOB_NAME} - [#${env.BUILD_NUMBER}]
<${env.BUILD_URL}|OPEN>
} 
                """
            )
        }
    }
}
