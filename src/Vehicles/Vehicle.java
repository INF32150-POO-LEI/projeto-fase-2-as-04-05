package Vehicles;

import General.Position;
import Product.Bag;
import Product.Box;
import Product.Pallet;
import Vehicles.AGC;
import Vehicles.DeliveryCart;
import Vehicles.TugVehicle;
import Vehicles.ULC;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Represents a vehicle with a unique ID, vehicle type, and associated equipment.
 */

public abstract class Vehicle {
    private Position currentPosition, destination;
    private boolean available = false;

    public void setAvailableStatus(boolean status){
        available = status;
    }

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