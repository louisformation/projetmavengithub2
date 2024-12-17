package com.gretadevops.calculatorD;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// cette annotation va définir les méthodes de cette classe
// comme pouvant analyser l'URL des requêtes afin de dispatcher
// vers le traitement approprié
@RestController
public class CalculatorController {
    // Pour utiliser la classe Calculator qui permet d'additioner
    // deux nombres, il faut que j'instancie cette classe pour
    // obtenir un objet de cette classe.
    // quelque chose du genre: 
    // Calculator calculator = new Calculator();
    // int resultat = calculator.sum(1, 2);
    // ici on utilise plutôt l'injection de dépendances (l'inversion de
    // contrôle): le conteneur spring se charge de l'instanciation
    // de la classe.
    @Autowired
    private Calculator calculator;
    
    // @RequestMapping est une annotation qui va permettre de déterminer
    // la méthode à utiliser en fonction de l'URL.
    @RequestMapping("/sum")
    // ici, on définit la méthode sum qui prend pour paramètres deux
    // entiers a et b, et chaque entier est associé à un paramètre de 
    // l'URL. Cette fonction retourne une valeur de type chaîne.
    String sum(@RequestParam("a") Integer a, @RequestParam("b") Integer b) {
        // on fait appel à la méthode sum du service calculator, et on
        // convertit le résultat en chaîne.
        return String.valueOf(calculator.sum(a, b));
    }
}
