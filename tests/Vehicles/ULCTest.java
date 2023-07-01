package Vehicles;
import Product.Pallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ULCTest {
    private ULC ulc;

    @BeforeEach
    void setUp() {
        ulc = new ULC();
    }

    @Test
    void getCargoQuantityTest() {
        int quantity = ulc.getCargoQuantity();
        assertEquals(0, quantity);

        Pallet pallet = new Pallet(100, 10);
        ulc.addPallet(pallet);

        quantity = ulc.getCargoQuantity();
        assertEquals(1, quantity);
    }

    @Test
    void toStringTest() {
        String str = ulc.toString();
        assertEquals("ULC", str);
    }

    @Test
    void addPalletTest() {
        Pallet pallet = new Pallet(50, 5);
        boolean added = ulc.addPallet(pallet);

        assertTrue(added);
        assertEquals(1, ulc.getCurrentCargo().size());
        assertTrue(ulc.getCurrentCargo().contains(pallet));
    }
}
