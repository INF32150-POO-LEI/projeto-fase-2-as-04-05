package Vehicles;

import General.Position;
import General.Shelf;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

    public Position moveUpwards(List<Position> positions) {
        if (this.getCurrentPosition() == null || positions == null) {
            return null;
        }

        int currentX = this.getCurrentPosition().getX();
        int currentY = this.getCurrentPosition().getY();

        for (Position position : positions) {
            if (position.getX() == currentX && position.getY() == (currentY - 1)) {
                this.getCurrentPosition().setVehicleInPosition(null);
                this.setCurrentPosition(position);
                position.setVehicleInPosition(this);
                return position;
            }
        }
        return null;
    }

    public Position moveDownwards(List<Position> positions) {
        if (this.getCurrentPosition() == null || positions == null) {
            return null;
        }

        int currentX = this.getCurrentPosition().getX();
        int currentY = this.getCurrentPosition().getY();

        for (Position position : positions) {
            if (position.getX() == currentX && position.getY() == (currentY + 1)) {
                this.getCurrentPosition().setVehicleInPosition(null);
                this.setCurrentPosition(position);
                position.setVehicleInPosition(this);
                return position;
            }
        }
        return null;
    }

    public void loadShelf(Shelf shelf){
       if(this instanceof AGC){
           shelf.addToShelf(((AGC) this).getCurrentCargo());
       }
       if (this instanceof TugVehicle){
           shelf.addToShelf(((TugVehicle) this).getTowedAGC().getCurrentCargo());
       }
       if(this instanceof ULC){
           shelf.addToShelf(((ULC) this).getCurrentCargo());
       }
    }

    public Position moveRight(List<Position> positions) {
        if (this.getCurrentPosition() == null || positions == null) {
            return null;
        }

        int currentX = this.getCurrentPosition().getX();
        int currentY = this.getCurrentPosition().getY();

        for (Position position : positions) {
            if ((position.getX() == currentX + 1) && position.getY() == currentY) {
                        this.getCurrentPosition().setVehicleInPosition(null);
                        this.setCurrentPosition(position);
                        position.setVehicleInPosition(this);
                        return position;
                }
        }
        return null;
    }

    public Position moveLeft(List<Position> positions) {
        if (this.getCurrentPosition() == null || positions == null) {
            return null;
        }

        int currentX = this.getCurrentPosition().getX();
        int currentY = this.getCurrentPosition().getY();

        for (Position position : positions) {
            if ((position.getX() == currentX - 1) && position.getY() == currentY) {
                this.getCurrentPosition().setVehicleInPosition(null);
                this.setCurrentPosition(position);
                position.setVehicleInPosition(this);
                return position;
            }
        }
        return null;
    }


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