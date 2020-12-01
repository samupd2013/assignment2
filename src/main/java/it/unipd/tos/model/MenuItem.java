////////////////////////////////////////////////////////////////////
// Samuele De Simone 1219399
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;


public class MenuItem {
    public enum type { Gelato, Budino, Bevanda }
    private type itemType;
    private String name;
    private double price;

    public MenuItem(String n, type t, double p){
        itemType = t;
        name = n;
        price = p;
    }

    public String getName(){
        return name;
    }
    
    public type getType(){
        return itemType;
    }

    public double getPrice(){
        return price;
    }
}