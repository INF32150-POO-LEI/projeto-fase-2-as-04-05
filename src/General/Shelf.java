package General;

import General.Position;
import Product.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a shelf that holds products at a specific position.
 */
public class Shelf {
    private Position position;

    private List products;

    /**
     * Constructs a new Shelf object with the given position.
     * Initializes the list of products as an empty ArrayList.
     *
     * @param position the position of the shelf
     */
    public Shelf(Position position) {
        this.position = position;
        this.products = new ArrayList<>();
    }


    /**
     * Checks if the shelf is empty.
     *
     * @return true if the shelf is empty, false otherwise
     */
    public boolean isEmpty(){
        if(products.isEmpty() == true){
            return true;
        }else
            return false;
    }

    /**
     * Adds a list of products to the shelf.
     *
     * @param products the list of products to add
     */
    public void addToShelf(List products){
        this.products.addAll(products);
    }

    /**
     * Returns the list of products on the shelf.
     *
     * @return the list of products on the shelf
     */
    public List getProducts() {
        return products;
    }

    /**
     * Gets the position.
     *
     * @return the position of the shelf
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Sets the position.
     *
     * @param position the position to set
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Sets the list of products.
     *
     * @param products the list of products to set
     */
    public void setProducts(List products) {
        this.products = products;
    }
}