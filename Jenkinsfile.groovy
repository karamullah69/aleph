pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build and Push Docker Images') {
            steps {
                 script {
                    docker.build("your-drupal-app1-image:tag", "app1/")
                    docker.build("your-drupal-app2-image:tag", "app2/")
                    docker.withRegistry('https://index.docker.io/v1/', 'docker-registry-credentials') {
                        docker.image("your-drupal-app1-image:tag").push()
                        docker.image("your-drupal-app2-image:tag").push()
                    }
                }
            }
        }
        stage('Deploy to Kubernetes') {
            steps {
                script {
                    // Deploy to Kubernetes using kubectl and Kustomize
                    sh 'kubectl apply -k aleph/'
                }
            }
        }
    }
}
