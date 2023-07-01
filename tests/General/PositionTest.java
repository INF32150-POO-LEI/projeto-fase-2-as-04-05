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
        Position position = new Position("Position 1", 0, 0);

        AGC agc = new AGC();
        TugVehicle tugVehicle = new TugVehicle();

        boolean addedToPosition = position.addToPosition(agc);
        assertTrue(addedToPosition);
        addedToPosition = position.addToPosition(tugVehicle);
        assertFalse(addedToPosition);
    }

    @Test
    public void testGetName() {
        Position position = new Position("Test Position", 0, 0);
        assertEquals("Test Position", position.getName());
    }

    @Test
    public void testGetMaxVehicles() {
        Position position = new Position("Test Position", 0, 0);
        assertEquals(1, position.getMaxVehicles());
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

    @Test
    public void testGetVehicleInPosition() {
        Position position1 = new Position("Position 1", 0, 0);
        Position position2 = new Position("Position 2", 1, 1);

        AGC agc = new AGC();
        TugVehicle tugVehicle = new TugVehicle();

        position1.addToPosition(agc);
        position2.addToPosition(tugVehicle);

        assertEquals(agc, position1.getVehicleInPosition());
        assertEquals(tugVehicle, position2.getVehicleInPosition());
    }

    @Test
    public void testSetVehicleInPosition() {
        Position position = new Position("Position", 0, 0);
        AGC agc = new AGC();
        position.setVehicleInPosition(agc);
        assertEquals(agc, position.getVehicleInPosition());
    }
}
