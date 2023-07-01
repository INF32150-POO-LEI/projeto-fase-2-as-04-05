package Vehicles;
import Product.Bag;
import Product.Box;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeliveryCartTest {
    private DeliveryCart deliveryCart;

    @BeforeEach
    void setUp() {
        deliveryCart = new DeliveryCart();
    }

    @Test
    void addBagTest() {
        Bag bag1 = new Bag(50);
        boolean result = deliveryCart.addBag(bag1);
        assertTrue(result);

        Bag bag2 = new Bag(250);
        result = deliveryCart.addBag(bag2);
        assertFalse(result);
    }

    @Test
    void addBoxTest() {
        Box box1 = new Box(100);
        boolean result = deliveryCart.addBox(box1);
        assertTrue(result);

        Box box2 = new Box(250);
        result = deliveryCart.addBox(box2);
        assertFalse(result);
    }

    @Test
    void getCargoQuantityTest() {
        int quantity = deliveryCart.getCargoQuantity();
        assertEquals(0, quantity);

        Bag bag1 = new Bag(50);
        Bag bag2 = new Bag(30);
        deliveryCart.addBag(bag1);
        deliveryCart.addBag(bag2);
        quantity = deliveryCart.getCargoQuantity();
        assertEquals(2, quantity);
    }

    @Test
    void getMaxWeightTest() {
        int maxWeight = deliveryCart.getMaxWeight();
        assertEquals(200, maxWeight);
    }
}
