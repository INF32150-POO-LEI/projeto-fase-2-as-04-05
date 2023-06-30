package Vehicles;

import General.Position;
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
    public void setAvailableStatus(boolean status){
        available = status;
    }

    public Vehicle moveUpwards(List<Position> positions) {
        for (Position position : positions) {
            if (position.getX() == this.getCurrentPosition().getX() && position.getY() == (this.getCurrentPosition().getY() - 1)) {
                switch (position.getName()) {
                    case "Wall":
                    case "Shelf":
                    case "Collect":
                    case "Delivery":
                        return null;
                    case "Floor":
                    default:
                        this.setCurrentPosition(position);
                        return this;
                }
            }
        }
        return null;
    }

    public Vehicle moveDownwards(List<Position> positions) {
        for (Position position : positions) {
            if (position.getX() == this.getCurrentPosition().getX() && position.getY() == (this.getCurrentPosition().getY() + 1)) {
                switch (position.getName()) {
                    case "Wall":
                    case "Shelf":
                    case "Collect":
                    case "Delivery":
                        return null;
                    case "Floor":
                    default:
                        this.setCurrentPosition(position);
                        return this;
                }
            }
        }
        return null;
    }

    public Vehicle moveRight(List<Position> positions) {
        for (Position position : positions) {
            if ((position.getX() == this.getCurrentPosition().getX() + 1) && position.getY() == this.getCurrentPosition().getY()) {
                switch (position.getName()) {
                    case "Wall":
                    case "Shelf":
                    case "Collect":
                    case "Delivery":
                        return null;
                    case "Floor":
                    default:
                        this.setCurrentPosition(position);
                        return this;
                }
            }
        }
        return null;
    }

    public Vehicle moveLeft(List<Position> positions) {
        for (Position position : positions) {
            if ((position.getX() == this.getCurrentPosition().getX() - 1) && position.getY() == this.getCurrentPosition().getY()) {
                switch (position.getName()) {
                    case "Wall":
                    case "Shelf":
                    case "Collect":
                    case "Delivery":
                        return null;
                    case "Floor":
                    default:
                        this.setCurrentPosition(position);
                        return this;
                }
            }
        }
        return null;
    }



    public abstract int getCargoQuantity();

    public boolean isAvailable(){
        if(available == true){
            return true;
        }
        else{
            return false;
        }
    }

    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void setDestination(Position destination) {
        this.destination = destination;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public Position getDestination() {
        return destination;
    }
}