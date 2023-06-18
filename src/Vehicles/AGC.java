package Vehicles;
import General.Position;
import Product.Bag;
import Product.Box;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an Automated Guided Cart (AGC).
 */
public class AGC{
    private static final int MAX_WEIGHT = 100;
    private Position currentPosition;
    private Position pickupLocation;
    private Position deliveryLocation;
    private int currentWeight = 0;
    private List currentCargo;

    /**
     * Constructs a new AGC object with the specified positions.
     *
     * @param currentPosition  the current position of the AGC
     * @param pickupLocation   the pickup location of the AGC
     * @param deliveryLocation the delivery location of the AGC
     */
    public AGC(Position currentPosition, Position pickupLocation, Position deliveryLocation) {
        this.pickupLocation = pickupLocation;
        this.currentPosition = currentPosition;
        this.deliveryLocation = deliveryLocation;
        this.currentCargo = new ArrayList<>();
    }

    /**
     * Adds a bag to the AGC's cargo.
     *
     * @param bag the bag to be added
     * @return true if the bag was successfully added, false otherwise
     */
    public boolean addBag(Bag bag) {
        if(bag.getCurrentWeight() + currentWeight < MAX_WEIGHT){
            currentCargo.add(bag);
            currentWeight += bag.getCurrentWeight();
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Adds a box to the AGC's cargo.
     *
     * @param box the box to be added
     * @return true if the box was successfully added, false otherwise
     */
    public boolean addBox(Box box) {
        if(box.getCurrentWeight() + currentWeight < MAX_WEIGHT){
            currentCargo.add(box);
            currentWeight += box.getCurrentWeight();
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Gets the current position of the AGC.
     *
     * @return the current position
     */
    public Position getCurrentPosition() {
        return currentPosition;
    }

    /**
     * Gets the pickup location of the AGC.
     *
     * @return the pickup location
     */
    public Position getPickupLocation() {
        return pickupLocation;
    }

    /**
     * Gets the delivery location of the AGC.
     *
     * @return the delivery location
     */
    public Position getDeliveryLocation() {
        return deliveryLocation;
    }

    /**
     * Gets the current weight of the AGC's cargo.
     *
     * @return the current weight
     */
    public int getCurrentWeight() {
        return currentWeight;
    }

    /**
     * Gets the current cargo of the AGC.
     *
     * @return the current cargo
     */
    public List getCurrentCargo() {
        return currentCargo;
    }

    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void setPickupLocation(Position pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public void setDeliveryLocation(Position deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    public void setCurrentCargo(List currentCargo) {
        this.currentCargo = currentCargo;
    }


}