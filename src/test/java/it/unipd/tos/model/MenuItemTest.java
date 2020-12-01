package it.unipd.tos.model;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.MenuItem.type;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
public class MenuItemTest{
    @Test
    public void getName_Test(){
        MenuItem i1 = new MenuItem("", MenuItem.type.Gelato, 2.50);
        MenuItem i2 = new MenuItem("    ", MenuItem.type.Bevanda, 3.00);
        MenuItem i3 = new MenuItem("Budino pinguino", MenuItem.type.Budino, 5.00);
        assertEquals("",i1.getName());
        assertEquals("    ", i2.getName());
        assertEquals("Budino pinguino", i3.getName());
    }
}