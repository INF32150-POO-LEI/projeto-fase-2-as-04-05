package Vehicles;

import General.Position;
import General.Shelf;
import java.util.List;
/**
 * Represents the superclass for all the other vehicles
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
     * Moves the vehicle upwards to the adjacent position in the given list of positions.
     *
     * @param positions the list of positions to check for an upward movement
     * @return the new position if a valid upward movement is possible, otherwise null
     */
    public Position moveUpwards(List<Position> positions) {
        if (this.getCurrentPosition() == null || positions == null) {
            return null;
        }

        int currentX = this.getCurrentPosition().getX();
        int currentY = this.getCurrentPosition().getY();

        for (Position position : positions) {
            if (position.getX() == currentX && position.getY() == (currentY - 1)) {
                if(position.getName().equals("Shelf")){
                    return position;
                }
                else if(position.getName().equals("Entry")){
                    return position;
                }
                else if(position.getName().equals("Exit")){
                    return position;
                }
                else if(position.getVehicleInPosition() != null){
                    return null;
                }
                else{
                    this.getCurrentPosition().setVehicleInPosition(null);
                    this.setCurrentPosition(position);
                    position.setVehicleInPosition(this);
                    return position;
                }
            }
        }
        return null;
    }

    /**
     * Moves the vehicle downwards to the adjacent position in the given list of positions.
     *
     * @param positions the list of positions to check for a downward movement
     * @return the new position if a valid downward movement is possible, otherwise null
     */
    public Position moveDownwards(List<Position> positions) {
        if (this.getCurrentPosition() == null || positions == null) {
            return null;
        }

        int currentX = this.getCurrentPosition().getX();
        int currentY = this.getCurrentPosition().getY();

        for (Position position : positions) {
            if (position.getX() == currentX && position.getY() == (currentY + 1)) {
                if(position.getName().equals("Shelf")){
                    return position;
                }
                else if(position.getName().equals("Entry")){
                    return position;
                }
                else if(position.getName().equals("Exit")){
                    return position;
                }
                else if(position.getVehicleInPosition() != null){
                    return null;
                }
                else{
                    this.getCurrentPosition().setVehicleInPosition(null);
                    this.setCurrentPosition(position);
                    position.setVehicleInPosition(this);
                    return position;
                }
            }
        }
        return null;
    }

    /**
     * Loads the given shelf by transferring the current cargo of the vehicle to the shelf.
     *
     * @param shelf the shelf to be loaded
     */
    public void loadShelf(Shelf shelf){
       if(this instanceof AGC){
           shelf.addToShelf(((AGC) this).getCurrentCargo());
           ((AGC) this).setCurrentCargo(null);
           ((AGC) this).setCurrentWeight(0);
       }
       if (this instanceof TugVehicle){
           shelf.addToShelf(((TugVehicle) this).getTowedAGC().getCurrentCargo());
            ((TugVehicle) this).getTowedAGC().setCurrentCargo(null);
           ((TugVehicle) this).getTowedAGC().setCurrentWeight(0);
       }
       if(this instanceof ULC){
           shelf.addToShelf(((ULC) this).getCurrentCargo());
              ((ULC) this).setCurrentCargo(null);
              ((ULC) this).setCurrentWeight(0);
       }
    }

    /**
     * Returns the weight of the vehicle.
     * If the vehicle is an AGC, the current weight of the AGC is returned.
     * If the vehicle is a TugVehicle, the weight of the towed AGC is returned if available, otherwise 0 is returned.
     * If the vehicle is a ULC, the current weight of the ULC is returned.
     *
     * @return the weight of the vehicle
     */

    public int getWeight(){
        if(this instanceof AGC){
            return ((AGC) this).getCurrentWeight();
        }
        if(this instanceof TugVehicle){
            if(((TugVehicle) this).getTowedAGC() == null){
                return 0;
            }
            else{
                return ((TugVehicle) this).getTowedAGC().getCurrentWeight();
            }
        }
        if(this instanceof ULC){
            return ((ULC) this).getCurrentWeight();
        }
        return 0;
    }
    /**
     * Moves the vehicle to the right to the adjacent position in the given list of positions.
     *
     * @param positions the list of positions to check for a right movement
     * @return the new position if a valid right movement is possible, otherwise null
     */
    public Position moveRight(List<Position> positions) {
        if (this.getCurrentPosition() == null || positions == null) {
            return null;
        }

        int currentX = this.getCurrentPosition().getX();
        int currentY = this.getCurrentPosition().getY();

        for (Position position : positions) {
            if ((position.getX() == currentX + 1) && position.getY() == currentY) {
                if(position.getName().equals("Shelf")){
                    return position;
                }
                else if(position.getName().equals("Entry")){
                    return position;
                }
                else if(position.getName().equals("Exit")){
                    return position;
                }
                else if(position.getVehicleInPosition() != null){
                    return null;
                }
                else{
                    this.getCurrentPosition().setVehicleInPosition(null);
                    this.setCurrentPosition(position);
                    position.setVehicleInPosition(this);
                    return position;
                }
                }
        }
        return null;
    }

    /**
     * Moves the vehicle to the left to the adjacent position in the given list of positions.
     *
     * @param positions the list of positions to check for a left movement
     * @return the new position if a valid left movement is possible, otherwise null
     */
    public Position moveLeft(List<Position> positions) {
        if (this.getCurrentPosition() == null || positions == null) {
            return null;
        }

        int currentX = this.getCurrentPosition().getX();
        int currentY = this.getCurrentPosition().getY();

        for (Position position : positions) {
            if ((position.getX() == currentX - 1) && position.getY() == currentY) {
                if(position.getName().equals("Shelf")){
                    return position;
                }
                else if(position.getName().equals("Entry")){
                    return position;
                }
                else if(position.getName().equals("Exit")){
                    return position;
                }
                else if(position.getVehicleInPosition() != null){
                    return null;
                }
                else{
                    this.getCurrentPosition().setVehicleInPosition(null);
                    this.setCurrentPosition(position);
                    position.setVehicleInPosition(this);
                    return position;
                }
            }
        }
        return null;
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