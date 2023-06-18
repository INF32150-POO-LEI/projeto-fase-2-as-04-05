package Vehicles;
import General.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Product.*;
import java.util.ArrayList;
import java.util.List;

public class AGCTest {

    private AGC agc;

    @BeforeEach
    void setUp() {
        Position currentPosition = new Position("Location3", 0, 0);
        Position pickupLocation = new Position("Location2", 1, 1);
        Position deliveryLocation = new Position("Location1", 2, 2);
        agc = new AGC(currentPosition, pickupLocation, deliveryLocation);
    }

    @Test
    void testAddBag() {
        Bag bag = new Bag(2);
        bag.setCurrentWeight(1);

        assertTrue(agc.addBag(bag));
        assertEquals(1, agc.getCurrentCargo().size());
        assertEquals(1, agc.getCurrentWeight());
    }

    @Test
    void testAddBox() {
        Box box = new Box(5);
        box.setCurrentWeight(2);
        assertTrue(agc.addBox(box));
        assertEquals(1, agc.getCurrentCargo().size());
        assertEquals(2, agc.getCurrentWeight());
    }

    @Test
    void testGetCurrentPosition() {
        Position currentPosition = agc.getCurrentPosition();
        assertNotNull(currentPosition);
        assertEquals(0, currentPosition.getX());
        assertEquals(0, currentPosition.getY());
    }

    @Test
    void testGetPickupLocation() {
        Position pickupLocation = agc.getPickupLocation();
        assertNotNull(pickupLocation);
        assertEquals(1, pickupLocation.getX());
        assertEquals(1, pickupLocation.getY());
    }

    @Test
    void testGetDeliveryLocation() {
        Position deliveryLocation = agc.getDeliveryLocation();
        assertNotNull(deliveryLocation);
        assertEquals(2, deliveryLocation.getX());
        assertEquals(2, deliveryLocation.getY());
    }

    @Test
    void testGetCurrentWeight() {
        int currentWeight = agc.getCurrentWeight();
        assertEquals(0, currentWeight);
    }

    @Test
    void testGetCurrentCargo() {
        List currentCargo = agc.getCurrentCargo();
        assertNotNull(currentCargo);
        assertTrue(currentCargo.isEmpty());
    }

    @Test
    void testSetCurrentPosition() {
        Position newPosition = new Position("NewLocation", 3,3);
        agc.setCurrentPosition(newPosition);
        assertEquals(newPosition, agc.getCurrentPosition());
    }

    @Test
    void testSetPickupLocation() {
        Position newPickupLocation = new Position("Collect", 4,4);
        agc.setPickupLocation(newPickupLocation);
        assertEquals(newPickupLocation, agc.getPickupLocation());
    }

    @Test
    void testSetDeliveryLocation() {
        Position newDeliveryLocation = new Position("Delivery", 5,5);
        agc.setDeliveryLocation(newDeliveryLocation);
        assertEquals(newDeliveryLocation, agc.getDeliveryLocation());
    }

    @Test
    void testSetCurrentWeight() {
        agc.setCurrentWeight(10);
        assertEquals(10, agc.getCurrentWeight());
    }

    @Test
    void testSetCurrentCargo() {
        List<Box> newCurrentCargo = new ArrayList<>();
        newCurrentCargo.add(new Box(5));
        newCurrentCargo.add(new Box(5));
        agc.setCurrentCargo(newCurrentCargo);
        assertEquals(newCurrentCargo, agc.getCurrentCargo());
    }
}


