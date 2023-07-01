package Vehicles;
import General.Position;
import Product.Pallet;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Unit Load Carrier (ULC).
 */
public class ULC extends Vehicle {
    private List<Position> path;
    private int currentWeight = 0;
    private List<Pallet> currentCargo;
    private static final int MAX_PALLETS = 1;
    private Position currentPosition;

    /**
     * Constructs a new ULC
     *
     */
    public ULC() {
        super();
        this.path = new ArrayList<>();
        this.currentCargo = new ArrayList<>();
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
    public String toString(){
        return "ULC";
    }

    /**
     * Adds a pallet to the ULC's current cargo.
     *
     * @param p the pallet to be added
     * @return true if the pallet was added successfully, false otherwise
     */
    public boolean addPallet(Pallet p) {
        if(getCurrentCargo().size() < MAX_PALLETS) {
            getCurrentCargo().add(p);
            currentWeight+=p.getCurrentWeight();
            return true;
        }
        else
            return false;
    }

    /**
     * Gets the path of the ULC.
     *
     * @return the path
     */
    public List<Position> getPath() {
        return path;
    }

    /**
     * Gets the current cargo of the ULC.
     *
     * @return the current cargo
     */
    public List<Pallet> getCurrentCargo() {
        return currentCargo;
    }

    /**
     * Gets the current position of the ULC.
     *
     * @return the current position
     */
    public Position getCurrentPosition() {
        return currentPosition;
    }

    /**
     * Sets the path of the ULC.
     *
     * @param path the list of positions to set as the ULC's path
     */
    public void setPath(List<Position> path) {
        this.path = path;
    }

    /**
     * Sets the current cargo of the ULC.
     *
     * @param currentCargo the list of pallets to set as the ULC's current cargo
     */
    public void setCurrentCargo(List<Pallet> currentCargo) {
        this.currentCargo = currentCargo;
    }

    /**
     * Sets the current position of the ULC.
     *
     * @param currentPosition the current position to be set
     */
    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }
}
