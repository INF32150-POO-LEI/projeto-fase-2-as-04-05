package Sensors;

/**
 * Represents a camera sensor.
 */
public class Camera extends Sensor {
    private int range;
    private int angle;

    /**
     * Constructs a new Camera object.
     */
    public Camera() {
        super("Camera");
        this.range = 30;
        this.angle = 90;
    }

    /**
     * Gets the range of the camera.
     *
     * @return the range of the camera
     */
    public int getRange() {
        return range;
    }

    /**
     * Sets the range of the camera.
     *
     * @param range the range of the camera
     */
    public void setRange(int range) {
        this.range = range;
    }

    /**
     * Gets the angle of the camera.
     *
     * @return the angle of the camera
     */
    public int getAngle() {
        return angle;
    }

    /**
     * Sets the angle of the camera.
     *
     * @param angle the angle of the camera
     */
    public void setAngle(int angle) {
        this.angle = angle;
    }
}
