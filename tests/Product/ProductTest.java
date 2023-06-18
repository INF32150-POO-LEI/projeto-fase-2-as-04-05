package Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product("Example Product", ProductType.CLOTHING);
    }

    @Test
    void testSetWeights() {
        product.setWeights();
        double weight = product.getWeight();
        assertTrue(weight >= 0.085 && weight <= 1.0);
    }

    @Test
    void testGenerateRandomNumber() {
        double randomNumber = Product.generateRandomNumber(0.5, 1.0);
        assertTrue(randomNumber >= 0.5 && randomNumber <= 1.0);
    }

    @Test
    void testGettersAndSetters() {
        product.setId(1);
        product.setName("New Product");
        product.setWeight(0.5);
        product.setProductType(ProductType.ACCESSORY);
        product.setPackaged(true);
        product.setPackagingType(PackagingType.BOX);

        assertEquals(1, product.getId());
        assertEquals("New Product", product.getName());
        assertEquals(0.5, product.getWeight());
        assertEquals(ProductType.ACCESSORY, product.getProductType());
        assertTrue(product.isPackaged());
        assertEquals(PackagingType.BOX, product.getPackagingType());
    }

}

