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
        for(MenuItem m : itemsOrdered){
            tot+=m.getPrice();
        }
        return tot;
    }
}