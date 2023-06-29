package Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a bag used for packaging products.
 */
public class Bag implements Packaging {

    private boolean loaded = false;
    private double maxWeight;
    private List<Product> productsInTheBag;
    private double currentWeight;
    private int code;

    /**
     * Creates a new instance of the `Bag` class with the specified maximum weight.
     *
     * @param maxWeight the maximum weight that the bag can hold
     */
    public Bag(double maxWeight) {
        this.maxWeight = maxWeight;
        this.currentWeight = 0.0;
        this.code = 1001;
        this.productsInTheBag = new ArrayList<>();
    }

    /**
     * Checks if the bag is full.
     *
     * @return `true` if the bag is full, `false` otherwise
     */
    public boolean isFull(){
        if (currentWeight >= maxWeight){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Packs a product into the bag.
     *
     * @param p the product to pack
     * @return `true` if the product was successfully packed, `false` otherwise
     */
    public boolean pack(Product p) {
        if (p.getWeight() > maxWeight) {
            return false;
        }
        else {
            productsInTheBag.add(p);
            currentWeight += p.getWeight();
            return true;
        }
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
     * Returns the maximum weight that the bag can hold.
     *
     * @return the maximum weight of the bag
     */
    public double getMaxWeight() {
        return maxWeight;
    }

    /**
     * Returns the list of products currently in the bag.
     *
     * @return the list of products in the bag
     */
    public List<Product> getProductsInTheBag() {
        return productsInTheBag;
    }

    /**
     * Returns the current weight of the bag.
     *
     * @return the current weight of the bag
     */
    public double getCurrentWeight() {
        return currentWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    /**
     * Retrieves the code of the bag.
     *
     * @return the code of the bag
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets the list of products inside the bag.
     *
     * @param productsInTheBag the list of products to be set inside the bag
     */
    public void setProductsInTheBag(List<Product> productsInTheBag) {
        this.productsInTheBag = productsInTheBag;
    }

    /**
     * Sets the current weight of the bag.
     *
     * @param currentWeight the current weight of the bag
     */
    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }

    /**
     * Sets the code of the bag.
     *
     * @param code the code to be set for the bag
     */
    public void setCode(int code) {
        this.code = code;
    }

}