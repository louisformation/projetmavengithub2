  pipeline {
   agent any
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
     stage("image docker"){
       steps{
         echo "création de l'image docker"
         sh 'docker build -t registry.gretadevops.com:5000/calculator .'
       }
     }
     stage("push registry"){
       steps{
         echo "push de l'image sur le registry"
         sh 'docker push registry.gretadevops.com:5000/calculator'
       }
     }
     stage("deploiement"){
      steps{
         echo "déploiement de l'application"
      }
     }
   }
}
