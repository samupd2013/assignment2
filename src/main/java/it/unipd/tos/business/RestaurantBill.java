////////////////////////////////////////////////////////////////////
// Samuele De Simone 1219399
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;
import java.util.List;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;
import it.unipd.tos.model.MenuItem.type;
import it.unipd.tos.business.exceptions.TakeAwayBillException;
public class RestaurantBill implements TakeAwayBill{
    public double getOrderPrice(List<MenuItem> itemsOrdered, User user) throws TakeAwayBillException{
        double tot = 0;
        int numGelati = 0;
        double minPrezzoGelato = Double.MAX_VALUE; 
        double totBevande = 0;
        for(MenuItem m : itemsOrdered){
            tot+=m.getPrice();
            if(m.getType() == type.Gelato){
                numGelati += 1;
            }
            if(m.getType() == type.Gelato && m.getPrice() < minPrezzoGelato){
                minPrezzoGelato = m.getPrice();
            }
            if(m.getType()==type.Bevanda){
                totBevande += m.getPrice();
            }
        }
        
        if(numGelati > 5){
            tot -= minPrezzoGelato * 0.5;
        }
        if(tot-totBevande > 50){
            tot = tot*0.9;
        }
        
        return tot;
    }
}