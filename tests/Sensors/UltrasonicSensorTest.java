package Sensors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UltrasonicSensorTest {

    private UltrasonicSensor ultrasonicSensor;

    @BeforeEach
    void setUp() {
        ultrasonicSensor = new UltrasonicSensor(50, 180);
    }

    @Test
    void testGetRange() {
        int range = ultrasonicSensor.getRange();
        assertEquals(50, range);
    }

    @Test
    void testSetRange() {
        ultrasonicSensor.setRange(100);
        int range = ultrasonicSensor.getRange();
        assertEquals(100, range);
    }

    @Test
    void testGetAngle() {
        int angle = ultrasonicSensor.getAngle();
        assertEquals(180, angle);
    }

    @Test
    void testSetAngle() {
        ultrasonicSensor.setAngle(90);
        int angle = ultrasonicSensor.getAngle();
        assertEquals(90, angle);
    }

    @Test
    void testGetType() {
        String type = ultrasonicSensor.getType();
        assertEquals("Ultrasonic", type);
    }

    @Test
    void testSetType() {
        ultrasonicSensor.setType("New Ultrasonic");
        String type = ultrasonicSensor.getType();
        assertEquals("New Ultrasonic", type);
    }
}