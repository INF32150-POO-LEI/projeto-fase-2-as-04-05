package General;
import Vehicles.AGC;
import Vehicles.DeliveryCart;
import Vehicles.TugVehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Vehicles.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {
    private Position position;

    @BeforeEach
    public void setup() {
        position = new Position("Wall", 0, 0);
    }

    @Test
    public void testConstructor() {
        assertNotNull(position);
    }

    @Test
    public void testAddToPosition() {
        Position position = new Position("Test Position", 0, 0);

        Vehicle vehicle1 = new Vehicle("Vehicle 1", (AGC) null, "AGC");
        Vehicle vehicle2 = new Vehicle("Vehicle 2", (TugVehicle) null, "TugVehicle");
        Vehicle vehicle3 = new Vehicle("Vehicle 3", (DeliveryCart) null, "DeliveryCart");

        // Add the first vehicle to the position
        assertTrue(position.addToPosition(vehicle1));
        assertEquals(1, position.getListOfVehicles().size());
        assertTrue(position.getListOfVehicles().contains(vehicle1));

        // Add the second vehicle to the position
        assertTrue(position.addToPosition(vehicle2));
        assertEquals(2, position.getListOfVehicles().size());
        assertTrue(position.getListOfVehicles().contains(vehicle2));

        // Try to add a third vehicle to the position (should fail)
        assertFalse(position.addToPosition(vehicle3));
        assertEquals(2, position.getListOfVehicles().size());
        assertFalse(position.getListOfVehicles().contains(vehicle3));
    }

    @Test
    public void testGetName() {
        Position position = new Position("Test Position", 0, 0);

        // Verify the name of the position
        assertEquals("Test Position", position.getName());
    }

    @Test
    public void testGetListOfVehicles() {
        Position position = new Position("Test Position", 0, 0);
        List<Vehicle> vehicles = new ArrayList<>();
        Vehicle vehicle1 = new Vehicle("Vehicle 1", (AGC) null, "AGC");
        Vehicle vehicle2 = new Vehicle("Vehicle 2", (TugVehicle) null, "TugVehicle");
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        position.setListOfVehicles(vehicles);

        // Verify the list of vehicles in the position
        assertEquals(vehicles, position.getListOfVehicles());
    }

    @Test
    public void testGetMaxVehicles() {
        int expectedMaxVehicles = 2;
        Position position = new Position("Test Position", 0, 0);

        // Verify the maximum number of vehicles in the position
        assertEquals(expectedMaxVehicles, position.getMaxVehicles());
    }

    @Test
    public void testSetMaxVehicles() {
        int expectedMaxVehicles = 5;
        Position position = new Position("Test Position", 0, 0);

        // Set the maximum number of vehicles in the position
        position.setMaxVehicles(expectedMaxVehicles);

        // Verify that the maximum number of vehicles is set correctly
        assertEquals(expectedMaxVehicles, position.getMaxVehicles());
    }

    @Test
    public void testSetName() {
        String expectedName = "New Name";
        Position position = new Position("Test Position", 0, 0);

        // Set the new name for the position
        position.setName(expectedName);

        // Verify that the name is set correctly
        assertEquals(expectedName, position.getName());
    }

    @Test
    public void testGetX() {
        int expectedX = 10; // Provide the expected value for X
        Position position = new Position("Test Position", expectedX, 0);

        // Verify that the X coordinate is retrieved correctly
        assertEquals(expectedX, position.getX());
    }

    @Test
    public void testGetY() {
        int expectedY = 5; // Provide the expected value for Y
        Position position = new Position("Test Position", 0, expectedY);

        // Verify that the Y coordinate is retrieved correctly
        assertEquals(expectedY, position.getY());
    }

    @Test
    public void testSetX() {
        int newX = 10; // Provide the new value for X
        Position position = new Position("Test Position", 0, 0);

        // Set the X coordinate using the setX() method
        position.setX(newX);

        // Verify that the X coordinate is updated correctly
        assertEquals(newX, position.getX());
    }

    @Test
    public void testSetY() {
        int newY = 5; // Provide the new value for Y
        Position position = new Position("Test Position", 0, 0);

        position.setY(newY);

        assertEquals(newY, position.getY());
    }
}
