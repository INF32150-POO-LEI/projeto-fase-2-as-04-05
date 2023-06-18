package Vehicles;
import General.Position;
import Product.Product;
import Product.Box;
import Product.Bag;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a delivery cart.
 */
public class DeliveryCart {
    private static final int MAX_WEIGHT = 200;
    private Position currentPosition;
    private int currentWeight;
    private List currentCargo;

    /**
     * Constructs a new DeliveryCart object with the specified current position.
     *
     * @param currentPosition the current position of the delivery cart
     */
    public DeliveryCart(Position currentPosition) {
        this.currentPosition = currentPosition;
        this.currentCargo = new ArrayList<>();
        this.currentWeight = 0;
    }

    /**
     * Gets the current position of the delivery cart.
     *
     * @return the current position
     */
    public Position getCurrentPosition() {
        return currentPosition;
    }

    /**
     * Gets the current cargo of the delivery cart.
     *
     * @return the current cargo
     */
    public List getCurrentCargo() {
        return currentCargo;
    }

    /**
     * Gets the maximum weight that the delivery cart can carry.
     *
     * @return the maximum weight
     */
    public int getMaxWeight() {
        return MAX_WEIGHT;
    }

    /**
     * Adds a bag to the delivery cart's cargo.
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
     * Adds a box to the delivery cart's cargo.
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
     * Gets the current weight of the delivery cart's cargo.
     *
     * @return the current weight
     */
    public int getCurrentWeight() {
        return currentWeight;
    }

    /**
     * Sets the current position of the delivery cart.
     *
     * @param currentPosition the current position to be set
     */
    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    /**
     * Sets the current weight of the delivery cart's cargo.
     *
     * @param currentWeight the current weight to be set
     */
    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    /**
     * Sets the current cargo of the delivery cart.
     *
     * @param currentCargo the current cargo to be set
     */
    public void setCurrentCargo(List currentCargo) {
        this.currentCargo = currentCargo;
    }
}