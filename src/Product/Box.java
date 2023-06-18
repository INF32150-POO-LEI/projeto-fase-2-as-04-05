package Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a box used for packaging products.
 */
public class Box implements Packaging {
    private double maxWeight;
    private double currentWeight;
    public List<Product> productsInTheBox;
    private int code;

    /**
     * Creates a new instance of the `Box` class with the specified maximum weight.
     *
     * @param maxWeight the maximum weight that the box can hold
     */
    public Box(double maxWeight) {
        this.maxWeight = maxWeight;
        this.currentWeight = 0.0;
        this.code = 2002;
        this.productsInTheBox = new ArrayList<>();
    }

    /**
     * Returns the maximum weight that the box can hold.
     *
     * @return the maximum weight of the box
     */
    public double getMaxWeight() {
        return maxWeight;
    }

    /**
     * Returns the current weight of the box.
     *
     * @return the current weight of the box
     */
    public double getCurrentWeight() {
        return currentWeight;
    }

    /**
     * Returns the list of products currently in the box.
     *
     * @return the list of products in the box
     */
    public List<Product> getProductsInTheBox() {
        return productsInTheBox;
    }

    /**
     * Returns the code of the box.
     *
     * @return the code of the box
     */
    public int getCode() {
        return code;
    }

    /**
     * Packs a product into the box.
     *
     * @param p the product to pack
     * @return `true` if the product was successfully packed, `false` otherwise
     */
    public boolean pack(Product p) {
        if ((p.getWeight() + currentWeight) > maxWeight) {
            return false;
        }
        else{
            currentWeight += p.getWeight();
            productsInTheBox.add(p);
            return true;
        }
    }

    /**
     * Checks if the box is full.
     *
     * @return `true` if the box is full, `false` otherwise
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
     * Sets the maximum weight limit for the box.
     *
     * @param maxWeight the maximum weight limit to be set for the box
     */
    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    /**
     * Sets the current weight of the box.
     *
     * @param currentWeight the current weight to be set for the box
     */
    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }

    /**
     * Sets the list of products inside the box.
     *
     * @param productsInTheBox the list of products to be set inside the box
     */
    public void setProductsInTheBox(List<Product> productsInTheBox) {
        this.productsInTheBox = productsInTheBox;
    }

    /**
     * Sets the code of the box.
     *
     * @param code the code to be set for the box
     */
    public void setCode(int code) {
        this.code = code;
    }


}
