////////////////////////////////////////////////////////////////////
// Samuele De Simone 1219399
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import it.unipd.tos.business.RestaurantBill;
import it.unipd.tos.business.exceptions.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;
import it.unipd.tos.model.MenuItem.type;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import org.junit.rules.ExpectedException;
import org.junit.Rule;

public class RestaurantBillTest{
    @Test
    public void TotalPrice_Test(){
        List<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        User u01 = new User(01,"Luca", "Rossi", true);
        LocalTime t = LocalTime.parse("12:00");
        RestaurantBill testBill = new RestaurantBill();

        itemsOrdered.add(new MenuItem("Gelato cioccolato e crema", MenuItem.type.Gelato, 2.50));
        itemsOrdered.add(new MenuItem("Coca cola", MenuItem.type.Bevanda, 3.00));
        itemsOrdered.add(new MenuItem("Budino pinguino", MenuItem.type.Budino, 5.00));
        itemsOrdered.add(new MenuItem("Gelato nocciola stracciatella", MenuItem.type.Gelato, 2.50));

        try{
            assertEquals(13.00, testBill.getOrderPrice(itemsOrdered, u01, t),0.0);
        } 
        catch (TakeAwayBillException exc){
            exc.getMessage();
        }
    } 

    @Test
    public void TotaleConPiu5Gelati_Test(){
        List<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        User u01 = new User(01,"Luca", "Rossi", true);
        LocalTime t = LocalTime.parse("15:00");
        RestaurantBill testBill = new RestaurantBill();

        itemsOrdered.add(new MenuItem("Gelato cioccolato e crema", MenuItem.type.Gelato, 3.50));  
        itemsOrdered.add(new MenuItem("Gelato", MenuItem.type.Gelato, 1.00));
        itemsOrdered.add(new MenuItem("Gelato crema", MenuItem.type.Gelato, 3.50));
        itemsOrdered.add(new MenuItem("Gelato cioccolato", MenuItem.type.Gelato, 4.00));
        itemsOrdered.add(new MenuItem("Coca cola", MenuItem.type.Bevanda, 3.00));
        itemsOrdered.add(new MenuItem("Budino pinguino", MenuItem.type.Budino, 5.00));
        itemsOrdered.add(new MenuItem("Gelato nocciola stracciatella", MenuItem.type.Gelato, 2.50));
        itemsOrdered.add(new MenuItem("Gelato nocciola", MenuItem.type.Gelato, 2.50));

        try{
            assertEquals(24.5, testBill.getOrderPrice(itemsOrdered, u01, t),0.0);
        } 
        catch (TakeAwayBillException exc){
            exc.getMessage();
        }
    }
    
    @Test
    public void TotMaggioreDi50_Test(){
        List<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        User u01 = new User(01,"Luca", "Rossi", true);
        LocalTime t = LocalTime.parse("15:00");
        RestaurantBill testBill = new RestaurantBill();

        itemsOrdered.add(new MenuItem("Gelato cioccolato e crema", MenuItem.type.Gelato, 60.00));

        try{
            assertEquals(54, testBill.getOrderPrice(itemsOrdered, u01, t),0.0);
        } 
        catch (TakeAwayBillException exc){
            exc.getMessage();
        }
    }

    @Test
    public void maggiore50_Piu5Gelati_Test(){
        List<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        User u01 = new User(01,"Luca", "Rossi", true);
        LocalTime t = LocalTime.parse("15:00");
        RestaurantBill testBill = new RestaurantBill();

        itemsOrdered.add(new MenuItem("Gelato cioccolato e crema", MenuItem.type.Gelato, 10.00));  
        itemsOrdered.add(new MenuItem("Gelato", MenuItem.type.Gelato, 2.00));
        itemsOrdered.add(new MenuItem("Gelato crema", MenuItem.type.Gelato, 10.00));
        itemsOrdered.add(new MenuItem("Gelato cioccolato", MenuItem.type.Gelato, 10.00));
        itemsOrdered.add(new MenuItem("Coca cola", MenuItem.type.Bevanda, 15.00));
        itemsOrdered.add(new MenuItem("Budino pinguino", MenuItem.type.Budino, 10.00));
        itemsOrdered.add(new MenuItem("Gelato nocciola stracciatella", MenuItem.type.Gelato, 10.00));
        itemsOrdered.add(new MenuItem("Gelato nocciola", MenuItem.type.Gelato, 20.00)); 

        try{
            assertEquals(77.40, testBill.getOrderPrice(itemsOrdered, u01, t),0.0);
        } 
        catch (TakeAwayBillException exc){
            exc.getMessage();
        }
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void NumberElementsExceed30_Test() throws TakeAwayBillException {
        List<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        User u01 = new User(01,"Luca", "Rossi", true);
        LocalTime t = LocalTime.parse("15:00");
        RestaurantBill testBill = new RestaurantBill();

        thrown.expect(TakeAwayBillException.class);
        thrown.expectMessage("Non si possono ordinare più di 30 elementi");

        for(int i = 1; i <= 35; i++){
            itemsOrdered.add(new MenuItem("Gelato", type.Gelato, 2.00));
        }

        testBill.getOrderPrice(itemsOrdered, u01, t);
    } 

    @Test 
    public void menoDi10Euro_Test(){
        List<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        User u01 = new User(01,"Luca", "Rossi", true);
        LocalTime t = LocalTime.parse("15:00");
        RestaurantBill testBill = new RestaurantBill();

        itemsOrdered.add(new MenuItem("Gelato cioccolato e crema", MenuItem.type.Gelato, 5.00));  

        try{
            assertEquals(5.50, testBill.getOrderPrice(itemsOrdered, u01, t),0.0);
        } 
        catch (TakeAwayBillException exc){
            exc.getMessage();
        }
    }

    @Test
    public void meno10Euro_Piu5Gelati_Test(){
        List<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        User u01 = new User(01,"Luca", "Rossi", true);
        LocalTime t = LocalTime.parse("15:00");
        RestaurantBill testBill = new RestaurantBill();

        itemsOrdered.add(new MenuItem("Gelato cioccolato e crema", MenuItem.type.Gelato, 1.00));  
        itemsOrdered.add(new MenuItem("Gelato", MenuItem.type.Gelato, 2.00));
        itemsOrdered.add(new MenuItem("Gelato crema", MenuItem.type.Gelato, 1.00));
        itemsOrdered.add(new MenuItem("Gelato cioccolato", MenuItem.type.Gelato, 1.00));
        itemsOrdered.add(new MenuItem("Coca cola", MenuItem.type.Bevanda, 1.00));
        itemsOrdered.add(new MenuItem("Budino pinguino", MenuItem.type.Budino, 1.00));
        itemsOrdered.add(new MenuItem("Gelato nocciola stracciatella", MenuItem.type.Gelato, 1.00));
        itemsOrdered.add(new MenuItem("Gelato nocciola", MenuItem.type.Gelato, 1.00)); 

        try{
            assertEquals(9.00, testBill.getOrderPrice(itemsOrdered, u01, t),0.0);
        } 
        catch (TakeAwayBillException exc){
            exc.getMessage();
        }
    }

    @Test 
    public void regalo_Test(){
        List<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        User u01 = new User(01,"Luca", "Rossi", true);
        LocalTime t = LocalTime.parse("18:30");
        RestaurantBill testBill = new RestaurantBill();
        
        itemsOrdered.add(new MenuItem("Gelato cioccolato e crema", MenuItem.type.Gelato, 1.00));

        try{
            double ris = testBill.getOrderPrice(itemsOrdered, u01, t);
            assertTrue(ris == 0.0 || ris == 1.50);
        } 
        catch (TakeAwayBillException exc){
            exc.getMessage();
        }
    }

    @Test 
    public void singoloRegaloPiuOrdiniStessoUSer_Test(){
        List<MenuItem> itemsOrdered1 = new ArrayList<MenuItem>();
        List<MenuItem> itemsOrdered2 = new ArrayList<MenuItem>();
        List<MenuItem> itemsOrdered3 = new ArrayList<MenuItem>();
        User u01 = new User(01,"Luca", "Rossi", true);
        LocalTime t = LocalTime.parse("18:30");
        RestaurantBill testBill = new RestaurantBill();
        
        itemsOrdered1.add(new MenuItem("Gelato cioccolato e crema", MenuItem.type.Gelato, 1.00));
        itemsOrdered2.add(new MenuItem("Gelato cioccolato e crema", MenuItem.type.Gelato, 1.00));
        itemsOrdered3.add(new MenuItem("Gelato cioccolato e crema", MenuItem.type.Gelato, 1.00));

        try{
            double ris = testBill.getOrderPrice(itemsOrdered1, u01, t) + testBill.getOrderPrice(itemsOrdered2, u01, t) + testBill.getOrderPrice(itemsOrdered3, u01, t);
            assertTrue(ris == 3.00 /*2+1 di commissioni*/ || ris == 4.50/*3+1.50 di commissioni*/);
        } 
        catch (TakeAwayBillException exc){
            exc.getMessage();
        }
    }

    @Test 
    public void massimo10Regali_Test(){
        List<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        LocalTime t = LocalTime.parse("18:30");
        RestaurantBill testBill = new RestaurantBill();
        
        itemsOrdered.add(new MenuItem("Gelato cioccolato e crema", MenuItem.type.Gelato, 11.00));

        try{
            int ris = 0;
            for(int i=0;i<2000;i++){
                ris+=testBill.getOrderPrice(itemsOrdered, new User(i,"Luca","Rossi", true), t);
            }
            assertTrue(ris >= 22000-110 && ris <= 22000);
        } 
        catch (TakeAwayBillException exc){
            exc.getMessage();
        }
    }
}