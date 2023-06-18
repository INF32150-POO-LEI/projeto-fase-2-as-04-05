package Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a product.
 */
public class Product {
    private static int ID = 0;
    public String name;
    public double weight; // em kg
    public ProductType productType;
    public boolean isPackaged;

    public PackagingType packagingType;

    /**
     * Constructs a product with the specified name and product type.
     *
     * @param name        the name of the product
     * @param productType the product type
     */
    public Product(String name, ProductType productType) {
        this.ID = ++ID;
        this.name = name;
        this.productType = productType;
        isPackaged = false;
    }

    /**
     * Returns the ID of the product.
     *
     * @return the ID of the product
     */
    public int getId() { return ID; }

    /**
     * Sets the ID of the product.
     *
     * @param id the ID of the product
     */
    public void setId(int id) { this.ID = id; }

    /**
     * Returns the name of the product.
     *
     * @return the name of the product
     */
    public String getName() { return name; }

    /**
     * Sets the name of the product.
     *
     * @param name the name of the product
     */
    public void setName(String name) { this.name = name; }

    /**
     * Returns the weight of the product in kilograms.
     *
     * @return the weight of the product
     */
    public double getWeight() { return weight; }

    /**
     * Sets the weight of the product based on its product type.
     */
    public void setWeights(){
        if (getProductType() == ProductType.CLOTHING){
            weight = generateRandomNumber(0.085, 1.0);
        }
        else if(getProductType() == ProductType.ACCESSORY){
            weight = generateRandomNumber(0.01, 0.5);
        }
        else if(getProductType() == ProductType.SMALL_TOY){
            weight = generateRandomNumber(0.05, 0.5);
        }
        else if(getProductType() == ProductType.LARGE_TOY){
            weight = generateRandomNumber(1.0, 5.0);
        }
        else if(getProductType() == ProductType.SMALL_ELECTRONIC_EQUIPMENT){
            weight = generateRandomNumber(0.1, 1.0);
        }
        else if(getProductType() == ProductType.LARGE_ELECTRONIC_EQUIPMENT){
            weight = generateRandomNumber(1.0, 10.0);
        }
        else if(getProductType() == ProductType.BOOK){
            weight = generateRandomNumber(0.2, 2.0);
        }
    }

    /**
     * Generates a random number between the specified minimum and maximum values.
     *
     * @param min the minimum value
     * @param max the maximum value
     * @return the generated random number
     */
    public static double generateRandomNumber(double min, double max) {
        Random random = new Random();
        return min + (max - min) * random.nextDouble();
    }

    /**
     * Returns the product type.
     *
     * @return the product type
     */
    public ProductType getProductType() { return productType; }

    /**
     * Sets the product type.
     *
     * @param productType the product type
     */
    public void setProductType(ProductType productType) { this.productType = productType; }

    /**
     * Checks if the product is packaged.
     *
     * @return true if the product is packaged, false otherwise
     */
    public boolean isPackaged() { return isPackaged; }

    /**
     * Sets the packaged status of the product.
     *
     * @param packaged true if the product is packaged, false otherwise
     */

    public void setPackaged(boolean packaged) { isPackaged = packaged; }

    /**
     * Returns the packaging type of the product.
     *
     * @return the packaging type of the product
     */
    public PackagingType getPackagingType() { return packagingType; }

    /**
     * Sets the packaging type of the product.
     *
     * @param packagingType the packaging type of the product
     */
    public void setPackagingType(PackagingType packagingType) { this.packagingType = packagingType; }

    /**
     * Sets the weight of the product.
     *
     * @param weight the weight of the product
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

}
