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
        if (this.getCurrentPosition() == null || positions == null) {
            return null;
        }

        int currentX = this.getCurrentPosition().getX();
        int currentY = this.getCurrentPosition().getY();

        for (Position position : positions) {
            if (position.getX() == currentX && position.getY() == (currentY - 1)) {
                switch (position.getName()) {
                    case "Wall":
                    case "Shelf":
                    case "Collect":
                    case "Delivery":
                        return null;
                    case "Floor":
                        this.getCurrentPosition().setVehicleInPosition(null);
                        this.setCurrentPosition(position);
                        position.setVehicleInPosition(this);
                        return this;
                }
            }
        }
        return null;
    }

    public Vehicle moveDownwards(List<Position> positions) {
        if (this.getCurrentPosition() == null || positions == null) {
            return null;
        }

        int currentX = this.getCurrentPosition().getX();
        int currentY = this.getCurrentPosition().getY();

        for (Position position : positions) {
            if (position.getX() == currentX && position.getY() == (currentY + 1)) {
                switch (position.getName()) {
                    case "Wall":
                    case "Shelf":
                    case "Collect":
                    case "Delivery":
                        return null;
                    case "Floor":
                        this.getCurrentPosition().setVehicleInPosition(null);
                        this.setCurrentPosition(position);
                        position.setVehicleInPosition(this);
                        return this;
                }
            }
        }
        return null;
    }


    public Vehicle moveRight(List<Position> positions) {
        if (this.getCurrentPosition() == null || positions == null) {
            return null;
        }

        int currentX = this.getCurrentPosition().getX();
        int currentY = this.getCurrentPosition().getY();

        for (Position position : positions) {
            if ((position.getX() == currentX + 1) && position.getY() == currentY) {
                switch (position.getName()) {
                    case "Wall":
                    case "Shelf":
                    case "Collect":
                    case "Delivery":
                        return null;
                    case "Floor":
                        this.getCurrentPosition().setVehicleInPosition(null);
                        this.setCurrentPosition(position);
                        position.setVehicleInPosition(this);
                        return this;
                }
            }
        }
        return null;
    }

    public Vehicle moveLeft(List<Position> positions) {
        if (this.getCurrentPosition() == null || positions == null) {
            return null;
        }

        int currentX = this.getCurrentPosition().getX();
        int currentY = this.getCurrentPosition().getY();

        for (Position position : positions) {
            if ((position.getX() == currentX - 1) && position.getY() == currentY) {
                switch (position.getName()) {
                    case "Wall":
                    case "Shelf":
                    case "Collect":
                    case "Delivery":
                        return null;
                    case "Floor":
                        this.getCurrentPosition().setVehicleInPosition(null);
                        this.setCurrentPosition(position);
                        position.setVehicleInPosition(this);
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