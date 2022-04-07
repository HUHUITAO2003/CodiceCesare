package codicecesare;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {
    public static void main(String[] args) {
        Scanner tastiera = new Scanner(System.in); // classe per ricevere input da tastiera
        Cifrario cifrario = new Cifrario();// classe per codificare e decodificare i testi
        String decisione; // decisione codifica/decodifica/uscita
        boolean controllo; // controllo del contenuto
        String input; // stringa da cifrare/decifrare
        String output; // risultato della cifratura/decifratura
        String stringchiave; // stringa contenente le chiavi
        int[] chiavi; // array derivato da stringchiave

        System.out.println('\n' + "Benvenuto nel mio programma!");
        for(;;){
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

                controllo = contentCheck(input, "^[a-z ]+$");// controllo che il testo sia solo a-z
                if (!controllo) {
                    System.out.println("Errore di inserimento");
                }
            } while (!controllo);

            do {
                System.out.println('\n' + "Bene! ora inserisci, divisi da spazi, le chiavi con cui codificheranno/decodificheranno ciclicamente il testo ..."
                                + '\n' + "Attenzione: inserire solamente numeri da 1 a 25 o lettere dalla b alla z");
                stringchiave = tastiera.nextLine();

                controllo = contentCheck(stringchiave, "[b-z0-9 ]+");// controllo che la chiave sia solo b-z e da 1-9
                if (!controllo) {
                    System.out.println("Errore di inserimento");
                }
                chiavi = toIntArray(stringchiave.split(" "));
            } while (!controllo || chiavi==null);
            output = cifrario.sostituzione(input, chiavi, decisione);
            System.out.println('\n' + "il tuo risultato è: " + output + '\n');
        }

        tastiera.close();
    }

    public static boolean contentCheck(String content, String regex){
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(content);
        return m.find();
    }

    public static int[] toIntArray(String[] array){
        String alfabeto = "abcdefghijklmnopqrstuvwxyz";
        int i = 0;
                int[] chiavi = new int[array.length];
                for (i = 0; i < array.length; i++) {
                    try {
                        chiavi[i] = Integer.parseInt(array[i]);
                    } catch (NumberFormatException e) {
                        String lettera = array[i];
                        chiavi[i] = alfabeto.indexOf(lettera);
                    }
                    if (chiavi[i] < 1 || chiavi[i] > 25) {//controllo chiavi
                        System.out.println("Attenzione: hai inserito una chiave minore 1 o maggiore di 25 o la a");
                        return null;
                    }
                }
        return chiavi;
    }
}