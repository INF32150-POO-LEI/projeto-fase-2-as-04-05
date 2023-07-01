package Vehicles;
import General.Position;
import Product.Bag;
import Product.Box;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an Automated Guided Cart (AGC).
 */
public class AGC extends Vehicle{

    private static final int MAX_WEIGHT = 100;
    private int currentWeight = 0;
    private List currentCargo;

    /**
     * Constructs a new AGC
     */
    public AGC() {
        super();
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
     * Returns the quantity of cargo in the vehicle.
     *
     * @return the quantity of cargo in the vehicle
     */
    @Override
    public int getCargoQuantity(){
        if(currentCargo == null){
            return 0;
        }
        else {
            return currentCargo.size();
        }
    }

    /**
     * Returns a string representation of the vehicle.
     *
     * @return a string representation of the vehicle
     */
    @Override
    public String toString() {
        return "AGC";
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

    /**
     * Sets the current weight of the vehicle.
     *
     * @param currentWeight the current weight of the vehicle
     */
    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    /**
     * Sets the current cargo of the vehicle.
     *
     * @param currentCargo the current cargo of the vehicle
     */
    public void setCurrentCargo(List currentCargo) {
        this.currentCargo = currentCargo;
    }
}