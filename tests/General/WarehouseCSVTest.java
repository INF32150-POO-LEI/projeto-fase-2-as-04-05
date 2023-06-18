package General;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class WarehouseCSVTest {
    private WarehouseCSV warehouseCSV;

    @BeforeEach
    public void setup() {
        warehouseCSV = new WarehouseCSV();
    }

    @Test
    public void testCreateCSV() {
        String filePath = "armazem.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            assertTrue(reader.readLine().startsWith("Dimensions"));
        } catch (IOException e) {
            fail("Failed to read the CSV file: " + e.getMessage());
        }
    }

    @Test
    public void testReadFromCSV() {
        int[] dimensions = warehouseCSV.readFromCSV();
        assertNotNull(dimensions);
        assertEquals(2, dimensions.length);
        System.out.println("Warehouse dimensions: " + Arrays.toString(dimensions));
    }
}
