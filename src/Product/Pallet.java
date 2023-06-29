package Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a pallet for packaging cardboxes.
 */
public class Pallet implements Packaging {

    private boolean loaded = false;
    private double maxWeight;
    private double currentWeight;
    private int maxBoxes;
    private List<CardBox> currentBoxes;
    private int code;

    /**
     * Constructs a pallet with the specified maximum weight and maximum number of boxes.
     *
     * @param maxWeight the maximum weight of the pallet
     * @param maxBoxes  the maximum number of boxes the pallet can hold
     */

    public Pallet(double maxWeight, int maxBoxes) {
        this.maxWeight = maxWeight;
        this.currentWeight = 0.0;
        this.maxBoxes = maxBoxes;
        this.currentBoxes = new ArrayList<>();
        this.code = 4004;
    }

    /**
     * Packs a product onto the pallet. This method is not supported for individual products directly onto a pallet.
     *
     * @param p the product to pack
     * @return always returns false as individual products cannot be packed onto a pallet
     */
    public boolean pack(Product p) {
        return false; // can't pack individual products directly onto a pallet
    }

    /**
     * Returns the maximum weight of the pallet.
     *
     * @return the maximum weight of the pallet
     */
    public double getMaxWeight() {
        return maxWeight;
    }

    @Override
    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    @Override
    public boolean isLoaded() {
        return loaded;
    }

    /**
     * Returns the current weight of the pallet.
     *
     * @return the current weight of the pallet
     */

    public double getCurrentWeight() {
        return currentWeight;
    }

    /**
     * Returns the maximum number of boxes the pallet can hold.
     *
     * @return the maximum number of boxes the pallet can hold
     */
    public int getMaxBoxes() {
        return maxBoxes;
    }

    /**
     * Returns the list of card boxes currently on the pallet.
     *
     * @return the list of card boxes currently on the pallet
     */
    public List<CardBox> getCurrentBoxes() {
        return currentBoxes;
    }

    /**
     * Returns the code associated with the pallet.
     *
     * @return the code of the pallet
     */
    public int getCode() {
        return code;
    }

    /**
     * Packs a card box onto the pallet.
     *
     * @param b the card box to pack
     * @return true if the card box was successfully packed, false otherwise
     */
    public boolean pack(CardBox b) {
        if (currentBoxes.size() >= maxBoxes || (currentWeight + b.getCurrentWeight()) > maxWeight) {
            return false;
        }
        else{
            currentWeight = getCurrentWeight() + b.getCurrentWeight();
            currentBoxes.add(b);
            return true;
        }
    }
}
