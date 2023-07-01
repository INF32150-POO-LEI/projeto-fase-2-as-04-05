package Vehicles;

import General.Position;
import java.util.List;
import java.util.Optional;

/**
 * Represents a vehicle with a unique ID, vehicle type, and associated equipment.
 */

public abstract class Vehicle {
    private Position currentPosition, destination;
    private boolean available = false;

    /**
     * Gets the quantity of cargo in the vehicle.
     *
     * @return the quantity of cargo in the vehicle
     */
    public abstract int getCargoQuantity();

    /**
     * Sets the availability status of the vehicle.
     *
     * @param status the availability status to be set
     */
    public void setAvailableStatus(boolean status){
        available = status;
    }

    /**
     * Checks if the vehicle is available.
     *
     * @return true if the vehicle is available, false otherwise
     */
    public boolean isAvailable(){
        if(available == true)
            return true;
        else
            return false;
    }

    /**
     * Gets the current position of the vehicle.
     *
     * @return the current position of the vehicle
     */
    public Position getCurrentPosition() {
        return currentPosition;
    }

    /**
     * Sets the current position of the vehicle.
     *
     * @param currentPosition the current position to be set
     */
    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    /**
     * Gets the destination position of the vehicle.
     *
     * @return the destination position of the vehicle
     */
    public Position getDestination() {
        return destination;
    }

    /**
     * Sets the destination position of the vehicle.
     *
     * @param destination the destination position to be set
     */
    public void setDestination(Position destination) {
        this.destination = destination;
    }
}