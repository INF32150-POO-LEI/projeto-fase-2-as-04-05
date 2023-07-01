package General;
import Product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShelfTest {
    private Shelf shelf;
    private Position position;

    @BeforeEach
    public void setup() {
        position = new Position("Test Position", 0, 0);
        shelf = new Shelf(position);
    }

    @Test
    public void testConstructor() {
        assertNotNull(shelf);
        assertEquals(position, shelf.getPosition());
        assertTrue(shelf.isEmpty());
        assertTrue(shelf.getProducts().isEmpty());
    }

    @Test
    public void testAddToShelf() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product("Product 1", null);
        Product product2 = new Product("Product 2", null);
        products.add(product1);
        products.add(product2);

        shelf.addToShelf(products);

        assertFalse(shelf.isEmpty());
        assertEquals(products, shelf.getProducts());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(shelf.isEmpty());

        Product product = new Product("Product", null);
        List<Product> products = new ArrayList<>();
        products.add(product);

        shelf.addToShelf(products);

        assertFalse(shelf.isEmpty());
    }

    @Test
    public void testGetProducts() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product("Product 1", null);
        Product product2 = new Product("Product 2", null);
        products.add(product1);
        products.add(product2);

        shelf.addToShelf(products);

        assertEquals(products, shelf.getProducts());
    }

    @Test
    public void testGetPosition() {
        Position position = new Position("Shelf 1", 0, 0);
        Shelf shelf = new Shelf(position);
        assertEquals(position, shelf.getPosition());
    }

    @Test
    public void testSetPosition() {
        Position position1 = new Position("Shelf 1", 0, 0);
        Position position2 = new Position("Shelf 2", 1, 1);

        Shelf shelf = new Shelf(position1);

        shelf.setPosition(position2);
        assertEquals(position2, shelf.getPosition());
    }
}