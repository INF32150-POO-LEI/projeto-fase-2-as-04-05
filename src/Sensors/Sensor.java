package Sensors;

/**
 * Represents a generic sensor.
 */
public class Sensor {
    private String type;

    /**
     * Constructs a new Sensor object with the specified type.
     *
     * @param type the type of the sensor
     */
    public Sensor(String type) {
        this.type = type;
    }

    /**
     * Gets the type of the sensor.
     *
     * @return the type of the sensor
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the sensor.
     *
     * @param type the type of the sensor
     */
    public void setType(String type) {
        this.type = type;
    }
}
