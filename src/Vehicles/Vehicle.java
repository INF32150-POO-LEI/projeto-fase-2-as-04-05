package Vehicles;

import General.Position;
import General.Shelf;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
       }
       if (this instanceof TugVehicle){
           shelf.addToShelf(((TugVehicle) this).getTowedAGC().getCurrentCargo());
            ((TugVehicle) this).getTowedAGC().setCurrentCargo(null);
       }
       if(this instanceof ULC){
           shelf.addToShelf(((ULC) this).getCurrentCargo());
              ((ULC) this).setCurrentCargo(null);
       }
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