package beadandó_1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JátékosTest {
    public JátékosTest(){
        
    }
    
    @Test
    public void testGetKassza() {
        System.out.println("getKassza");
        Mohó moh = new Mohó("László");
        int expResult = 10000;
        int result = moh.getKassza();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testKölt() {
        System.out.println("testKölt");
        int mennyit = 1500;
        Taktikus tak = new Taktikus("Ferenc");
        tak.költ(mennyit);
        int mennyiPénzeVan = tak.getKassza();
        int expResult = 8500;
        assertEquals(mennyiPénzeVan, expResult);
    }
    
    @Test
    public void testNyer() {
        System.out.println("testNyer");
        int mennyit = 2400;
        Óvatos óv = new Óvatos("Zoltán");
        óv.nyer(mennyit);
        int mennyiPénzeVan = óv.getKassza();
        int expResult = 12400;
        assertEquals(mennyiPénzeVan, expResult);
    }

    @Test
    public void testFizet() {
        System.out.println("testFizet");
        Mohó moh = new Mohó("László");
        Taktikus tak = new Taktikus("Ferenc");
        int mennyit = 4000;
        moh.fizet(tak, mennyit);
        assertEquals(moh.getKassza(), 6000);
        assertEquals(tak.getKassza(), 14000);
    }

    @Test
    public void testHázatÉpít() {
        System.out.println("testHázatÉpít");
        Ingatlan ing = new Ingatlan();
        Óvatos óv = new Óvatos("Zoltán");
        ing.megvesz(óv);
        óv.házatÉpít(ing);
        assertEquals(true, ing.getVanHáz());
    }
}