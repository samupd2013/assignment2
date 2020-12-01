////////////////////////////////////////////////////////////////////
// Samuele De Simone 1219399
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

public class User {
    private int id;
    private String nome;
    private String cognome;
    private boolean minorenne;

    public User(int i, String n, String c, boolean min){
        this.id = i;
        this.nome = n;
        this.cognome = c;
        this.minorenne = min;
    }
    
    public int getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }
    
    public String getCognome(){
        return cognome;
    }

    public boolean getMinorenne(){
        return minorenne;
    }

}