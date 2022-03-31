package codicecesare;

public class Cifrario {
    String alfabeto = "abcdefghijklmnopqrstuvwxyz";
    //String[] lettere = alfabeto.split("(?!^)");
/*
    public Cifrario(String[] testochiario, String[] chiavi){
        this.testochiario = testochiario;
        this.chiavi = chiavi;
    }*/
    public String sostituzione(String input, int[] chiavi, String tipo){
        String output = "";
        int ichiave = 0;
        
        for(int i = 0; i < input.length() ; i++){
            String lettera = Character.toString(input.charAt(i));
            if(lettera.equals(" ")){
                output += " ";
            }else{
                if(tipo.equals("1"))
                    output += Character.toString(alfabeto.charAt((alfabeto.indexOf(lettera)+chiavi[ichiave])%26));
                else
                    output += Character.toString(alfabeto.charAt((alfabeto.indexOf(lettera)-chiavi[ichiave]+26)%26));
                    
                ichiave =(ichiave+1)%chiavi.length;
            }
        }
        return output;
    }
}
