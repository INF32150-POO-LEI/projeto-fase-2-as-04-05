package Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoxTest {
    private Box box;

    @BeforeEach
    public void setup() {
        double maxWeight = 8.0;
        box = new Box(maxWeight);
    }

    @Test
    public void testGetMaxWeight() {
        double maxWeight = box.getMaxWeight();
        assertEquals(8.0, maxWeight);
    }

    @Test
    public void testGetCurrentWeight() {
        double currentWeight = box.getCurrentWeight();
        assertEquals(0.0, currentWeight);
    }

    @Test
    public void testGetProductsInTheBox() {
        List<Product> productsInTheBox = box.getProductsInTheBox();
        assertNotNull(productsInTheBox);
        assertEquals(0, productsInTheBox.size());
    }

    @Test
    public void testGetCode() {
        int code = box.getCode();
        assertEquals(2002, code);
    }

    @Test
    public void testPack() {
        Product product1 = new Product("Product 1", null);
        Product product2 = new Product("Product 2", null);
        Product oversizedProduct = new Product("Oversized Product", null);

        product1.setWeight(2.0);
        assertTrue(box.pack(product1));
        assertEquals(2.0, box.getCurrentWeight());

        product2.setWeight(3.0);
        assertTrue(box.pack(product2));
        assertEquals(5.0, box.getCurrentWeight());

        oversizedProduct.setWeight(8.0);
        assertFalse(box.pack(oversizedProduct));
        assertEquals(5.0, box.getCurrentWeight());
    }

    @Test
    public void testIsFull() {
        assertFalse(box.isFull());

        Product product1 = new Product("Product 1", null);
        Product product2 = new Product("Product 2", null);

        product1.setWeight(6.0);
        assertTrue(box.pack(product1));
        assertFalse(box.isFull());

        product2.setWeight(2.0);
        assertTrue(box.pack(product2));
        assertTrue(box.isFull());
    }
}
