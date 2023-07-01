package Vehicles;
import Product.Bag;
import Product.Box;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        bag2.setCurrentWeight(250);
        result = deliveryCart.addBag(bag2);
        assertFalse(result);
    }

    @Test
    void addBoxTest() {
        Box box1 = new Box(100);
        boolean result = deliveryCart.addBox(box1);
        assertTrue(result);

        Box box2 = new Box(250);
        box2.setCurrentWeight(250);
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

    @Test
    public void testToString() {
        assertEquals("DC", deliveryCart.toString());
    }

    @Test
    public void testSetTugged() {
        deliveryCart.setTugged(true);
        assertTrue(deliveryCart.isTugged());

        deliveryCart.setTugged(false);
        assertFalse(deliveryCart.isTugged());
    }

    @Test
    public void testIsTugged() {
        assertFalse(deliveryCart.isTugged());

        deliveryCart.setTugged(true);
        assertTrue(deliveryCart.isTugged());
    }

    @Test
    public void testGetCurrentWeight() {
        deliveryCart.setCurrentWeight(150);
        assertEquals(150, deliveryCart.getCurrentWeight());
    }

    @Test
    public void testSetCurrentWeight() {
        deliveryCart.setCurrentWeight(150);
        assertEquals(150, deliveryCart.getCurrentWeight());
    }

    @Test
    public void testSetCurrentCargo() {
        List<Bag> bags = new ArrayList<>();
        Bag bag1 = new Bag(10);
        Bag bag2 = new Bag(5);
        bags.add(bag1);
        bags.add(bag2);

        deliveryCart.setCurrentCargo(bags);

        List<Bag> currentCargo = deliveryCart.getCurrentCargo();

        assertEquals(bags.size(), currentCargo.size());
        assertTrue(currentCargo.contains(bag1));
        assertTrue(currentCargo.contains(bag2));

        List<Bag> newBags = new ArrayList<>();
        Bag bag3 = new Bag(15);
        Bag bag4 = new Bag(8);
        newBags.add(bag3);
        newBags.add(bag4);

        deliveryCart.setCurrentCargo(newBags);

        currentCargo = deliveryCart.getCurrentCargo();

        assertEquals(newBags.size(), currentCargo.size());
        assertTrue(currentCargo.contains(bag3));
        assertTrue(currentCargo.contains(bag4));
    }
}
