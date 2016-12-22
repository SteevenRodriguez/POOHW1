/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colecciones;

/**
 *
 * @author Carolina Burgos, Steven Rodriguez, Emilio Moran
 */
/**
 * @param args the command line arguments
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

/*
 * This is code I wrote with students in class to demonstrate how to read from the movie review file.
 * It searches each review for the queried word and just prints out the score of that review along 
 * with its text to ensure that we can read the two values and use the .contains() method properly.
 */
public class Colecciones {
    
    public static void menuPrincipal(){        
        
        System.out.println("\n");
        System.out.println("~~~~~~~~~~~~~~~~~~~");
        System.out.println("\tMENU");
        System.out.println("~~~~~~~~~~~~~~~~~~~");
        System.out.println("\n1._ Ingresar palabra");
        System.out.println("2._ Salir");
    }

    public static int validar() {
        Scanner scan = new Scanner(System.in);
        int op = 0;

        do {
            try {
                op = Integer.parseInt(scan.nextLine());
            } catch (Exception e) {
                System.out.println("\nIMPORTANTE: Tipo de dato erroneo.");
                System.out.print("\nElija una opcion nuevamente: ");
                continue;
            }
            break;
        } while (true);

        return (op);
    }

    public static void main(String[] args) throws FileNotFoundException {

        int op;
        boolean resultado = true;
        do {
            menuPrincipal();

            do {
                System.out.print("\nEscoger opcion del menu principal: ");
                op = validar();
                System.out.println("");
            } while (op < 1 || op > 2);

            switch (op) {
                case (1):

                    Map<Integer, LinkedList<String>> d = new HashMap<Integer, LinkedList<String>>();

                    File reviewFile = new File("movieReviews.txt");
                    Scanner reviewScanner = new Scanner(reviewFile);
                    Scanner keyboard = new Scanner(System.in);

                    int total = 0;
                    int reviewScore;
                    int cont = 0;
                    String reviewText;
                    String word;

                    System.out.print("Enter a word: ");
                    word = keyboard.nextLine();

                    while (reviewScanner.hasNext()) {

                        LinkedList<String> linea = new LinkedList<String>();

                        reviewScore = reviewScanner.nextInt();
                        reviewText = reviewScanner.nextLine();

                        if (!d.containsKey(reviewScore)) {
                            linea.add(reviewText);
                            d.put(reviewScore, linea);
                        } else {
                            d.get(reviewScore).add(reviewText);
                        }

                                    /*
                        if (reviewText.contains(word)) {
                            System.out.println("Score: " + reviewScore);
                            System.out.println("Text: " + reviewText);
                        }*/
                    }

                    for (HashMap.Entry<Integer, LinkedList<String>> entry : d.entrySet()) {
                        for (String elem : entry.getValue()) {
                            if (elem.contains(word)) {
                                total += entry.getKey();
                                cont++;
                            }
                        }
                    }
                    
                    if (cont > 1) {
                        System.out.println("\n" + word + " appears " + cont + " times");
                        System.out.println("The average score for reviews containing the word " + word + " is " + (float) total / cont);

                    } else {
                        System.out.println("\nPalabra no encontrada en el archivo.");
                    }
                    
                    continue;

                default:
                    System.out.println("Gracias. Siempre seras bienvenido.\n");
            }

            if (op == 2) {
                break;
            }

        } while (resultado);

    }

}

