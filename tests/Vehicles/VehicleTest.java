package Vehicles;
import General.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VehicleTest {
    private Vehicle vehicle;

    @BeforeEach
    void setUp() {
        vehicle = new AGC();
    }

    @Test
    void setAvailableStatusTest() {
        vehicle.setAvailableStatus(true);
        assertTrue(vehicle.isAvailable());
    }

    @Test
    void isAvailableTest() {
        vehicle.setAvailableStatus(true);
        boolean available = vehicle.isAvailable();
        assertTrue(available);

        vehicle.setAvailableStatus(false);
        available = vehicle.isAvailable();
        assertFalse(available);
    }

    @Test
    void setCurrentPositionTest() {
        Position position = new Position("newPosition", 0, 0);
        vehicle.setCurrentPosition(position);
        assertEquals(position, vehicle.getCurrentPosition());
    }

    @Test
    void setDestinationTest() {
        Position destination = new Position("newDestination", 10, 10);
        vehicle.setDestination(destination);
        assertEquals(destination, vehicle.getDestination());
    }
}
