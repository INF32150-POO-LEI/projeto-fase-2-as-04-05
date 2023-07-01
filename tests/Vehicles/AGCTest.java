package Vehicles;
import Product.Bag;
import Product.Box;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AGCTest {
    private AGC agc;

    @BeforeEach
    void setUp() {
        agc = new AGC();
    }

    @Test
    void addBagTest() {
        Bag bag1 = new Bag(50);
        boolean result = agc.addBag(bag1);
        assertTrue(result);

        Bag bag2 = new Bag(150);
        result = agc.addBag(bag2);
        assertFalse(result);
    }

    @Test
    void addBoxTest() {
        Box box1 = new Box(80);
        boolean result = agc.addBox(box1);
        assertTrue(result);

        Box box2 = new Box(120);
        result = agc.addBox(box2);
        assertFalse(result);
    }

    @Test
    void getCargoQuantityTest() {
        int quantity = agc.getCargoQuantity();
        assertEquals(0, quantity);

        Bag bag1 = new Bag(50);
        Bag bag2 = new Bag(30);
        agc.addBag(bag1);
        agc.addBag(bag2);
        quantity = agc.getCargoQuantity();
        assertEquals(2, quantity);
    }
}
