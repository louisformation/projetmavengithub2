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
   // l'option post permet de lancer systématiquement certaines commandes apres la fin d'un pipeline
   post {
        // post possede 3 sous-options en fonction de l'etat de la pipeline a la fin:
        // always est lancé quelque soit l'état de réussite de sortie, success seulement si
        // on est arrivé au bout sans erreur, et failure au contraire si on obtient un 
        // code d'erreur lors d'un step.
        always {
            echo "this always happen"
            //sh 'docker rm -f calculatortest 2>/dev/null'
        }
        failure {
            mail to: "kama.djemel@gmail.com",
            subject: "this pipeline failed.",
            body: "you're a failure."
        }
    }

}
