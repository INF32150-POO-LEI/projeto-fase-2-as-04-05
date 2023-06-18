package General;
import Product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
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
}
