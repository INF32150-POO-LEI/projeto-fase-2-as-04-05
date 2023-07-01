package General;

import Vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

/**
 * The Position class represents a position within the distribution center.
 * It contains information about the position's coordinates, name, and the maximum number of vehicles it can accommodate.
 * It also maintains a list of vehicles currently occupying the position.
 */
public class Position {
    private int x;
    private int y;
    private String name;
    private static int MAX_VEHICLES = 1;
    private Vehicle vehicleInPosition; //in current position

    /**
     * Constructs a new Position object with the specified name, x-coordinate, and y-coordinate.
     *
     * @param name The name of the position
     * @param x    The x-coordinate of the position
     * @param y    The y-coordinate of the position
     */
    public Position(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        //this.listOfVehicles = new ArrayList<>();
    }

    /**
     * Returns the name of the position.
     *
     * @return The name of the position
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the maximum number of vehicles allowed in this position.
     *
     * @return The maximum number of vehicles allowed
     */
    public int getMaxVehicles() {
        return MAX_VEHICLES;
    }

    public boolean addToPosition(Vehicle vehicle){
        if(vehicle.toString() == "DC"){
            return true;
        }

        if(getVehicleInPosition() == null) {
            setVehicleInPosition(vehicle);
            vehicle.setCurrentPosition(this);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Sets the name of the position.
     *
     * @param name The name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public Vehicle getVehicleInPosition() {
        return vehicleInPosition;
    }

    public void setVehicleInPosition(Vehicle vehicleInPosition) {
        this.vehicleInPosition = vehicleInPosition;
    }

    /**
     * Returns the x-coordinate of the position.
     *
     * @return The x-coordinate
     */
    public int getX() { return this.x; }

    /**
     * Returns the y-coordinate of the position.
     *
     * @return The y-coordinate
     */
    public int getY() { return this.y; }

    /**
     * Sets the x-coordinate of the position.
     *
     * @param x The x-coordinate to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of the position.
     *
     * @param y The y-coordinate to set
     */
    public void setY(int y) {
        this.y = y;
    }


}