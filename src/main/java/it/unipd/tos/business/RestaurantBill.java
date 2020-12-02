////////////////////////////////////////////////////////////////////
// Samuele De Simone 1219399
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;
import java.util.List;
import java.util.Random;

import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;
import it.unipd.tos.model.MenuItem.type;
import it.unipd.tos.business.exceptions.TakeAwayBillException;
import java.time.LocalTime;
public class RestaurantBill implements TakeAwayBill{
    public static int counterRegalo = 0;
    public double getOrderPrice(List<MenuItem> itemsOrdered, User user, LocalTime t) throws TakeAwayBillException{
        double tot = 0;
        int numGelati = 0;
        double minPrezzoGelato = Double.MAX_VALUE; 
        double totBevande = 0;
        for(MenuItem m : itemsOrdered){
            tot+=m.getPrice();
            if(m.getType() == type.Gelato){
                numGelati += 1;
                if(m.getPrice() < minPrezzoGelato){
                    minPrezzoGelato = m.getPrice();
                }
            }
            if(m.getType()==type.Bevanda){
                totBevande += m.getPrice();
            }
        }
        
        if(numGelati > 5){
            tot -= minPrezzoGelato * 0.5;
        }
        if(tot-totBevande > 50){
            tot *= 0.9;
        }
        
        if(itemsOrdered.size() > 30){
            throw new TakeAwayBillException("Non si possono ordinare più di 30 elementi");
        }
        
        if(tot < 10){
            tot += 0.5;
        }
        
        if(checkRegalo(user,t)){
            tot = 0;
        }

        return tot;
    }
    private boolean checkRegalo(User u, LocalTime t){
        Random r = new Random();
        if(counterRegalo < 10 && u.isMinorenne() && t.getHour() == 18 && r.nextInt(2) == counterRegalo){
            counterRegalo++;
            return true;
        }else{
            return false;
        }
    }
}