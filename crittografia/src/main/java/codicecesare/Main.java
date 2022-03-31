package codicecesare;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {
    public static void main(String[] args) {
        Scanner tastiera = new Scanner(System.in); // classe per ricevere input da tastiera
        Cifrario cifrario = new Cifrario();// classe per codificare e decodificare i testi
        String decisione; // decisione codifica/decodifica/uscita
        boolean controllo;
        Pattern p;
        Matcher m;
        String input;
        String output;
        String stringchiave;
        int[] chiavi;

        System.out.println('\n' + "Benvenuto nel mio programma!");
        do {
            do {
                controllo = true;
                System.out.println("Inserisci 1 se vuoi codificare;" + '\n' +
                        "Inserisci 2 se vuoi decodificare;" + '\n' +
                        "Premi invio se vuoi uscire;");
                decisione = tastiera.nextLine();

                if (!decisione.equals("1") && !decisione.equals("2") && !decisione.isEmpty()) { // controllo input
                    controllo = false;
                    System.out.println("Errore di inserimento");
                }
            } while (!controllo);
            if(decisione.isEmpty()){
                break;
            }
            do {
                System.out.println('\n'
                        + "Attenzione: essendo solo un prototipo accetta solo 26 lettere a-z minuscole e spazi" + '\n' +
                        "Inserisci il testo ...");
                input = tastiera.nextLine();

                p = Pattern.compile("^[a-z ]+$"); // controllo che il testo sia solo a-z
                m = p.matcher(input);
                controllo = m.find();
                if (!controllo) {
                    System.out.println("Errore di inserimento");
                }
            } while (!controllo);

            do {
                System.out.println('\n' + "Bene! ora inserisci, divisi da spazi, le chiavi con cui codificheranno/decodificheranno ciclicamente il testo ..."
                                + '\n' + "Attenzione: inserire solamente numeri da 1 a 25");
                stringchiave = tastiera.nextLine();
                p = Pattern.compile("[0-9 ]+"); // controllo che il testo sia solo a-z
                m = p.matcher(stringchiave);
                controllo = m.find();
                if (!controllo) {
                    System.out.println("Errore di inserimento");
                }
                String[] stringarrchiave = stringchiave.split(" ");
                chiavi = new int[stringarrchiave.length];
                for (int i = 0; i < stringarrchiave.length; i++) {
                    chiavi[i] = Integer.parseInt(stringarrchiave[i]);
                    if (chiavi[i] < 1 || chiavi[i] > 25) {
                        controllo = false;
                        System.out.println("Attenzione: hai inserito una chiave minore 1 o maggiore di 25");
                    }

                }
            } while (!controllo);
            output = cifrario.sostituzione(input, chiavi, decisione);
            System.out.println('\n' + "il tuo risultato è: " + output + '\n');
        } while (!decisione.isEmpty());
        tastiera.close();
    }
}
