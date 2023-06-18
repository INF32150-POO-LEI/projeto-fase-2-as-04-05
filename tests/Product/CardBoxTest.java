package Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CardBoxTest {
    private CardBox cardBox;

    @BeforeEach
    public void setup() {
        double maxWeight = 20.0;
        int maxProducts = 5;
        cardBox = new CardBox(maxWeight, maxProducts);
    }

    @Test
    public void testGetMaxWeight() {
        double maxWeight = cardBox.getMaxWeight();
        assertEquals(20.0, maxWeight);
    }

    @Test
    public void testGetCurrentWeight() {
        double currentWeight = cardBox.getCurrentWeight();
        assertEquals(0.0, currentWeight);
    }

    @Test
    public void testGetProductsInTheBox() {
        List<Product> productsInTheBox = cardBox.getProductsInTheBox();
        assertNotNull(productsInTheBox);
        assertEquals(0, productsInTheBox.size());
    }

    @Test
    public void testGetMaxProducts() {
        int maxProducts = cardBox.getMaxProducts();
        assertEquals(5, maxProducts);
    }

    @Test
    public void testGetCurrentProducts() {
        int currentProducts = cardBox.getCurrentProducts();
        assertEquals(0, currentProducts);
    }

    @Test
    public void testGetCode() {
        int code = cardBox.getCode();
        assertEquals(3003, code);
    }

    @Test
    public void testPack() {
        Product product1 = new Product("Product 1", null);
        Product product2 = new Product("Product 2", null);
        Product oversizedProduct = new Product("Oversized Product", null);

        product1.setWeight(5.0);
        product2.setWeight(7.0);
        oversizedProduct.setWeight(cardBox.getMaxWeight()+1);
        assertTrue(cardBox.pack(product1));
        assertEquals(5.0, cardBox.getCurrentWeight());
        assertEquals(1, cardBox.getCurrentProducts());

        assertTrue(cardBox.pack(product2));
        assertEquals(12.0, cardBox.getCurrentWeight());
        assertEquals(2, cardBox.getCurrentProducts());

        assertFalse(cardBox.pack(oversizedProduct));
        assertEquals(12.0, cardBox.getCurrentWeight());
        assertEquals(2, cardBox.getCurrentProducts());

        // Pack more products until the maximum capacity is reached
        Product product3 = new Product("Product 3", null);
        Product product4 = new Product("Product 4", null);
        Product product5 = new Product("Product 5", null);
        product3.setWeight(3.0);
        product4.setWeight(2.5);
        product5.setWeight(1.0);

        assertTrue(cardBox.pack(product3));
        assertTrue(cardBox.pack(product4));
        assertTrue(cardBox.pack(product5));
        assertEquals(18.5, cardBox.getCurrentWeight());
        assertEquals(5, cardBox.getCurrentProducts());

        // Try to pack one more product, should fail
        Product additionalProduct = new Product("Additional Product", null);
        additionalProduct.setWeight(1.6);
        assertFalse(cardBox.pack(additionalProduct));
        assertEquals(18.5, cardBox.getCurrentWeight());
        assertEquals(5, cardBox.getCurrentProducts());
    }
}


