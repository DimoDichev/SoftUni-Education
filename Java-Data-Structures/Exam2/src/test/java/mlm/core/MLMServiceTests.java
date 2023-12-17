package mlm.core;

import mlm.models.Seller;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class MLMServiceTests {
    private MLMService service;

    private Seller seller = new Seller("first");

    @Before
    public void setup() {
        service = new MLMServiceImpl();
    }

    @Test
    public void addSeller() {
        service.addSeller(seller);

        assertTrue(service.exists(seller));
    }

    @Test
    public void hireSeller() {
        service.addSeller(seller);

        Seller second = new Seller("second");
        service.hireSeller(seller, second);

        assertTrue(service.exists(seller));
        assertTrue(service.exists(second));
    }

    @Test
    public void fire() {
        service.addSeller(seller);
        Seller hired = new Seller("hired");

        service.hireSeller(seller, hired);

        assertTrue(service.exists(seller));
        assertTrue(service.exists(hired));

        service.fire(hired);

        assertTrue(service.exists(seller));
        assertFalse(service.exists(hired));
    }

    @Test
    public void makeSaleWithoutParents() {
        service.addSeller(seller);

        service.makeSale(seller, 100);

        assertEquals(100, seller.earnings);
    }

    @Test
    public void makeSaleWithParents() {
        service.addSeller(seller);
        Seller second = new Seller("second");
        Seller third = new Seller("third");

        service.hireSeller(seller, second);
        service.hireSeller(second, third);

        service.makeSale(third, 100);

        assertEquals(90, third.earnings);
        assertEquals(5, seller.earnings);
        assertEquals(5, second.earnings);
    }

    @Test
    public void getByTotalSalesOnEmpty() {
        Collection<Seller> byTotalSalesMade = service.getByTotalSalesMade();

        assertTrue(byTotalSalesMade.isEmpty());
    }
}
