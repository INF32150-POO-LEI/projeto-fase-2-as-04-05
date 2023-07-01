package Sensors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CameraTest {
    private Camera camera;

    @BeforeEach
    void setUp() {
        camera = new Camera();
    }

    @Test
    void testGetRange() {
        int range = camera.getRange();
        assertEquals(30, range);
    }

    @Test
    void testSetRange() {
        camera.setRange(50);
        int range = camera.getRange();
        assertEquals(50, range);
    }

    @Test
    void testGetAngle() {
        int angle = camera.getAngle();
        assertEquals(90, angle);
    }

    @Test
    void testSetAngle() {
        camera.setAngle(120);
        int angle = camera.getAngle();
        assertEquals(120, angle);
    }
}