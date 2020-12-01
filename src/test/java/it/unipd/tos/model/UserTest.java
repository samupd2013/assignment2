////////////////////////////////////////////////////////////////////
// Samuele De Simone 1219399
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;
import it.unipd.tos.model.User;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
public class UserTest{
    @Test
    public void getNome_Test(){
        User u = new User(0,"","",true);
        User u1 = new User(0,"Nome", "Cognome", false);
        User u2 = new User(0,"", "Cognome", true);
        User u3 = new User(0,"Nome", "", false);
        assertEquals("",u.getNome());
        assertEquals("Nome", u1.getNome());
        assertEquals("", u2.getNome());
        assertEquals("Nome", u3.getNome());
    }

    @Test
    public void getCognome_Test(){
        User u = new User(0,"","",true);
        User u1 = new User(0,"Nome", "Cognome", false);
        User u2 = new User(0,"", "Cognome", false);
        User u3 = new User(0,"Nome", "", true);
        assertEquals("",u.getCognome());
        assertEquals("Cognome", u1.getCognome());
        assertEquals("Cognome", u2.getCognome());
        assertEquals("", u3.getCognome());
    }

    @Test
    public void getId_Test(){
        User u = new User(0,"","",true);
        User u1 = new User(1,"Nome", "Cognome", false);
        User u2 = new User(2,"", "Cognome", false);
        User u3 = new User(3,"Nome", "", true);
        assertEquals(0,u.getId());
        assertEquals(1, u1.getId());
        assertEquals(2, u2.getId());
        assertEquals(3, u3.getId());
    }

    @Test
    public void getMinorenne_Test(){
        User u = new User(0,"","",true);
        User u1 = new User(1,"Nome", "Cognome", false);
        assertEquals(true,u.getMinorenne());
        assertEquals(false, u1.getMinorenne());
    }


}