package Sensors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SensorTest {
    private Sensor sensor;

    @BeforeEach
    void setUp() {
        sensor = new Sensor("Generic Sensor");
    }

    @Test
    void testGetType() {
        String type = sensor.getType();
        assertEquals("Generic Sensor", type);
    }

    @Test
    void testSetType() {
        sensor.setType("New Sensor");
        String type = sensor.getType();
        assertEquals("New Sensor", type);
    }
}