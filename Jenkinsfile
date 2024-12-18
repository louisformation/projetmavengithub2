  pipeline {
   agent any
    environment {
        DOCKER_IMAGE_NAME = 'kbeber/calculator'
        DOCKER_CREDENTIALS_ID = 'credential_docker_hub_internet' // ID des credentials dans Jenkins
		DOCKER_USER = 'kama.djemel@gmail.com'
		DOCKER_PASS = 'dckr_pat_3taOUuyRCAo_GZvhhAnKm68kAaM'
        }
   stages {
     stage("checkout"){
       steps {
         echo "récupération du projet"
         git branch: 'main',
         credentialsId: 'JenkinsGithubInternet',
         url: 'git@github.com:kadje-be/projetmavengithub.git'
       }
     }
     stage("compile"){
       steps{
         echo "compilation du projet"
         sh 'chmod +x ./mvnw'
         sh './mvnw compile'
       }
     }
     stage("tests"){
        steps{
          echo "test unitaire et test d'integration"
          sh './mvnw test'          
        }
     }
     stage("package"){
       steps{
         echo "création du package de l'application"
                   sh './mvnw package'
       }
     }
        stage('Build Docker Image') {
            steps {
                script {
                    sh "docker build -t ${DOCKER_IMAGE_NAME}:latest ."
                }
            }
        }
        stage('Login to Docker Hub') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: "${DOCKER_CREDENTIALS_ID}", usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        sh "echo ${DOCKER_PASS} | docker login -u ${DOCKER_USER} --password-stdin"
                    }
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                script {
                    sh "docker push ${DOCKER_IMAGE_NAME}:latest"
                }
            }
        }

     stage("deploiement"){
      steps{
         echo "déploiement de l'application"
                echo "déploiement de l'application"
		 sh 'chmod +x TestscriptCalculator.sh'
		 //def result = sh(script: './TestscriptCalculator.sh', returnStatus: true)
		 //echo "Code de retour : ${result}"
                 sh './TestscriptCalculator.sh'
      }
     }
   }
   // l'option post permet de lancer systématiquement certaines commandes apres la fin d'un pipeline
   post {
        // post possede 3 sous-options en fonction de l'etat de la pipeline a la fin:
        // always est lancé quelque soit l'état de réussite de sortie, success seulement si
        // on est arrivé au bout sans erreur, et failure au contraire si on obtient un 
        // code d'erreur lors d'un step.
        always {
            echo "this always happen"
            sh 'docker rm -f calculator 2>/dev/null'
            script {
                sh "docker logout" // Déconnexion de Docker Hub
            }
            
        }
        failure {
            mail to: "kama.djemel@gmail.com",
            subject: "this pipeline failed.",
            body: "you're a failure."
        }
    }

}
