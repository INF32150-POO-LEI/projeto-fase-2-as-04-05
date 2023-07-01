package Sensors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LidarTest {
    private Lidar lidar;

    @BeforeEach
    void setUp() {
        lidar = new Lidar(50, 120);
    }

    @Test
    void testGetRange() {
        int range = lidar.getRange();
        assertEquals(50, range);
    }

    @Test
    void testSetRange() {
        lidar.setRange(70);
        int range = lidar.getRange();
        assertEquals(70, range);
    }

    @Test
    void testGetAngle() {
        int angle = lidar.getAngle();
        assertEquals(120, angle);
    }

    @Test
    void testSetAngle() {
        lidar.setAngle(90);
        int angle = lidar.getAngle();
        assertEquals(90, angle);
    }
}