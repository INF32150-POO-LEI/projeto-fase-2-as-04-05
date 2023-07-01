package Product;

/**
 * Represents a packaging interface.
 */
public interface Packaging {

    /**
     * Packs a product into the packaging.
     *
     * @param p the product to pack
     * @return `true` if the product was successfully packed, `false` otherwise
     */
    public boolean pack(Product p);

    /**
     * Sets the loaded status of the object.
     *
     * @param loaded the loaded status to be set
     */
    public void setLoaded(boolean loaded);

    /**
     * Checks if the object is loaded.
     *
     * @return true if the object is loaded, false otherwise
     */
    public boolean isLoaded();

}
