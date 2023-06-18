package General;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import Product.*;

import static org.junit.jupiter.api.Assertions.*;

public class ProductsCSVTest {
    private ProductsCSV productsCSV;

    @BeforeEach
    public void setup() {
        productsCSV = new ProductsCSV();
    }

    @Test
    public void testCreateCSV() {
        // Ensure that the CSV file is created successfully
        String filePath = "produtos.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            assertTrue(reader.readLine().startsWith("Product,Type"));
        } catch (IOException e) {
            fail("Failed to read the CSV file: " + e.getMessage());
        }
    }

    @Test
    public void testReadFromCSV() {
        List<Product> products = productsCSV.readFromCSV();

        System.out.println("Number of products loaded: " + products.size());
    }
}
