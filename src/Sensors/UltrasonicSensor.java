package Sensors;

/**
 * Represents an ultrasonic sensor.
 */
public class UltrasonicSensor extends Sensor {
    private int range;
    private int angle;

    /**
     * Constructs a new UltrasonicSensor object with the specified range and angle.
     *
     * @param range the range of the ultrasonic sensor
     * @param angle the angle of the ultrasonic sensor
     */
    public UltrasonicSensor(int range, int angle) {
        super("Ultrasonic");
        this.range = range;
        this.angle = angle;
    }

    /**
     * Gets the range of the ultrasonic sensor.
     *
     * @return the range of the ultrasonic sensor
     */
    public int getRange() {
        return range;
    }

    /**
     * Sets the range of the ultrasonic sensor.
     *
     * @param range the range of the ultrasonic sensor
     */
    public void setRange(int range) {
        this.range = range;
    }

    /**
     * Gets the angle of the ultrasonic sensor.
     *
     * @return the angle of the ultrasonic sensor
     */
    public int getAngle() {
        return angle;
    }

    /**
     * Sets the angle of the ultrasonic sensor.
     *
     * @param angle the angle of the ultrasonic sensor
     */
    public void setAngle(int angle) {
        this.angle = angle;
    }
}
