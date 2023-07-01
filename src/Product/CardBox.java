package Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a card box used for packaging products.
 */
public class CardBox implements Packaging {

    private boolean loaded = false;
    private double maxWeight;
    private double currentWeight;
    public List<Product> productsInTheBox;
    private int maxProducts;
    private int currentProducts;
    private int code;
    private boolean putIntoPallet = false;

    /**
     * Creates a new instance of the `CardBox` class with the specified maximum weight and maximum number of products.
     *
     * @param maxWeight   the maximum weight that the card box can hold
     * @param maxProducts the maximum number of products that the card box can hold
     */
    public CardBox(double maxWeight, int maxProducts) {
        this.maxWeight = maxWeight;
        this.currentWeight = 0.0;
        this.maxProducts = maxProducts;
        this.currentProducts = 0;
        this.code = 3003;
        this.productsInTheBox = new ArrayList<>();
    }

    /**
     * Sets the putIntoPallet status of the object.
     *
     * @param putIntoPallet the putIntoPallet status to be set
     */
    public void putIntoPallet(boolean putIntoPallet) {
        this.putIntoPallet = putIntoPallet;
    }

    /**
     * Sets the loaded status of the object.
     *
     * @param loaded the loaded status to be set
     */
    @Override
    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    /**
     * Checks if the object is loaded.
     *
     * @return true if the object is loaded, false otherwise
     */
    @Override
    public boolean isLoaded() {
        return loaded;
    }

    /**
     * Checks if the object has been put into a pallet.
     *
     * @return true if the object has been put into a pallet, false otherwise
     */
    public boolean getPutIntoPallet() {
        return putIntoPallet;
    }
    /**
     * Returns the current weight of the card box.
     *
     * @return the current weight of the card box
     */
    public double getCurrentWeight() {
        return currentWeight;
    }

    /**
     * Returns the maximum weight that the card box can hold.
     *
     * @return the maximum weight of the card box
     */
    public double getMaxWeight() {
        return maxWeight;
    }

    /**
     * Returns the list of products currently in the card box.
     *
     * @return the list of products in the card box
     */
    public List<Product> getProductsInTheBox() {
        return productsInTheBox;
    }

    /**
     * Returns the maximum number of products that the card box can hold.
     *
     * @return the maximum number of products the card box can hold
     */
    public int getMaxProducts() {
        return maxProducts;
    }

    /**
     * Returns the current number of products in the card box.
     *
     * @return the current number of products in the card box
     */
    public int getCurrentProducts() {
        return currentProducts;
    }

    /**
     * Returns the code of the card box.
     *
     * @return the code of the card box
     */
    public int getCode() {
        return code;
    }

    /**
     * Packs a product into the card box.
     *
     * @param p the product to pack
     * @return `true` if the product was successfully packed, `false` otherwise
     */
    public boolean pack(Product p) {
        if ((getCurrentWeight() + p.getWeight()) <= maxWeight) {
            currentProducts++;
            currentWeight = getCurrentWeight() + p.getWeight();
            productsInTheBox.add(p);
            return true;
        }
        else
            return false;
    }

    /**
     * Sets the maximum weight that the card box can hold.
     *
     * @param maxWeight the maximum weight to set
     */
    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    /**
     * Sets the current weight of the card box.
     *
     * @param currentWeight the current weight to set
     */
    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }

    /**
     * Sets the list of products in the card box.
     *
     * @param productsInTheBox the list of products to set
     */
    public void setProductsInTheBox(List<Product> productsInTheBox) {
        this.productsInTheBox = productsInTheBox;
    }

    /**
     * Sets the maximum number of products that the card box can hold.
     *
     * @param maxProducts the maximum number of products to set
     */
    public void setMaxProducts(int maxProducts) {
        this.maxProducts = maxProducts;
    }

    /**
     * Sets the current number of products in the card box.
     *
     * @param currentProducts the current number of products to set
     */
    public void setCurrentProducts(int currentProducts) {
        this.currentProducts = currentProducts;
    }

    /**
     * Sets the code of the card box.
     *
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }
}
