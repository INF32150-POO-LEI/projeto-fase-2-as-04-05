package Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class BagTest {
    private Bag bag;
    @BeforeEach
    public void setup() {
        double maxWeight = 2.0;
        bag = new Bag(maxWeight);
    }

    @Test
    public void testIsFull() {
        assertFalse(bag.isFull());

        List<Product> products = createProducts();
        for (Product product : products) {
            product.setWeight(0.5);
            bag.pack(product);
        }

        assertTrue(bag.isFull());
    }

    @Test
    public void testPack() {
        List<Product> products = createProducts();

        for (Product product : products) {
            product.setWeight(0.5);
            assertTrue(bag.pack(product));
        }

        assertTrue(bag.isFull());

        Product oversizedProduct = new Product("Oversized", null);
        oversizedProduct.setWeight(20);
        assertFalse(bag.pack(oversizedProduct));
    }

    @Test
    public void testGetMaxWeight() {
        double maxWeight = bag.getMaxWeight();
        assertEquals(2.0, maxWeight);
    }

    @Test
    public void testGetProductsInTheBag() {
        List<Product> products = createProducts();

        for (Product product : products) {
            bag.pack(product);
        }

        List<Product> productsInTheBag = bag.getProductsInTheBag();

        assertEquals(products.size(), productsInTheBag.size());
        for (int i = 0; i < products.size(); i++) {
            assertEquals(products.get(i), productsInTheBag.get(i));
        }
    }

    @Test
    public void testGetCurrentWeight() {
        List<Product> products = createProducts();

        for (Product product : products) {
            bag.pack(product);
        }

        double currentWeight = bag.getCurrentWeight();
        double expectedWeight = 0.0;
        for (Product product : products) {
            expectedWeight += product.getWeight();
        }

        assertEquals(expectedWeight, currentWeight);
    }

    private List<Product> createProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Product 1", null));
        products.add(new Product("Product 2", null));
        products.add(new Product("Product 3", null));
        products.add(new Product("Product 4", null));
        return products;
    }
}
