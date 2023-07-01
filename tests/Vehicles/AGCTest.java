package Vehicles;
import Product.Bag;
import Product.Box;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        bag2.setCurrentWeight(150);
        result = agc.addBag(bag2);
        assertFalse(result);
    }

    @Test
    void addBoxTest() {
        Box box1 = new Box(80);
        boolean result = agc.addBox(box1);
        assertTrue(result);

        Box box2 = new Box(120);
        box2.setCurrentWeight(150);
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

    @Test
    public void testGetCurrentWeight() {
        // Set the current weight of the AGC
        agc.setCurrentWeight(50);

        // Retrieve the current weight
        int currentWeight = agc.getCurrentWeight();

        // Assert that the retrieved current weight is correct
        assertEquals(50, currentWeight);
    }

    @Test
    public void testSetCurrentWeight() {
        // Set the current weight of the AGC
        agc.setCurrentWeight(50);

        // Retrieve the current weight
        int currentWeight = agc.getCurrentWeight();

        // Assert that the retrieved current weight is correct
        assertEquals(50, currentWeight);

        // Set a new current weight
        agc.setCurrentWeight(75);

        // Retrieve the updated current weight
        currentWeight = agc.getCurrentWeight();

        // Assert that the updated current weight is correct
        assertEquals(75, currentWeight);
    }

    @Test
    public void testGetCurrentCargo() {
        List<Bag> bags = new ArrayList<>();
        Bag bag1 = new Bag(10);
        Bag bag2 = new Bag(5);
        bags.add(bag1);
        bags.add(bag2);

        agc.setCurrentCargo(bags);

        List<Bag> currentCargo = agc.getCurrentCargo();

        assertEquals(bags.size(), currentCargo.size());
        assertTrue(currentCargo.contains(bag1));
        assertTrue(currentCargo.contains(bag2));
    }

    @Test
    public void testSetCurrentCargo() {
        List<Bag> bags = new ArrayList<>();
        Bag bag1 = new Bag(10);
        Bag bag2 = new Bag(5);
        bags.add(bag1);
        bags.add(bag2);

        agc.setCurrentCargo(bags);

        List<Bag> currentCargo = agc.getCurrentCargo();

        assertEquals(bags.size(), currentCargo.size());
        assertTrue(currentCargo.contains(bag1));
        assertTrue(currentCargo.contains(bag2));

        List<Bag> newBags = new ArrayList<>();
        Bag bag3 = new Bag(8);
        Bag bag4 = new Bag(10);
        newBags.add(bag3);
        newBags.add(bag4);

        agc.setCurrentCargo(newBags);

        currentCargo = agc.getCurrentCargo();

        assertEquals(newBags.size(), currentCargo.size());
        assertTrue(currentCargo.contains(bag3));
        assertTrue(currentCargo.contains(bag4));
    }

    @Test
    public void testToString() {
        String agcString = agc.toString();
        assertEquals("AGC", agcString);
    }
}
