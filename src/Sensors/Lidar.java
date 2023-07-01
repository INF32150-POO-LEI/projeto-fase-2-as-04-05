package Sensors;

/**
 * Represents a Lidar sensor.
 */
public class Lidar extends Sensor {
    private int range;
    private int angle;

    /**
     * Constructs a new Lidar object with the specified range and angle.
     *
     * @param range the range of the Lidar sensor
     * @param angle the angle of the Lidar sensor
     */
    public Lidar(int range, int angle) {
        super("Lidar");
        this.range = range;
        this.angle = angle;
    }

    /**
     * Gets the range of the Lidar sensor.
     *
     * @return the range of the Lidar sensor
     */
    public int getRange() {
        return range;
    }

    /**
     * Sets the range of the Lidar sensor.
     *
     * @param range the range of the Lidar sensor
     */
    public void setRange(int range) {
        this.range = range;
    }

    /**
     * Gets the angle of the Lidar sensor.
     *
     * @return the angle of the Lidar sensor
     */
    public int getAngle() {
        return angle;
    }

    /**
     * Sets the angle of the Lidar sensor.
     *
     * @param angle the angle of the Lidar sensor
     */
    public void setAngle(int angle) {
        this.angle = angle;
    }
}