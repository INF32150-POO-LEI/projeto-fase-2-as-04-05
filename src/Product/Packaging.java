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

}
