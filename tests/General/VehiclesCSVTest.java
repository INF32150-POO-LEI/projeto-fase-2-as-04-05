package General;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import Vehicles.*;

import static org.junit.jupiter.api.Assertions.*;

public class VehiclesCSVTest {
    private VehiclesCSV vehiclesCSV;

    @BeforeEach
    public void setup() {
        vehiclesCSV = new VehiclesCSV();
    }

    @Test
    public void testCreateCSV() {
        String filePath = "veiculos.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            assertTrue(reader.readLine().startsWith("ID,Type"));
        } catch (IOException e) {
            fail("Failed to read the CSV file: " + e.getMessage());
        }
    }

    @Test
    public void testReadFromCSV() {

        List<Vehicle> vehicles = vehiclesCSV.readFromCSV();

        assertFalse(vehicles.isEmpty());

        System.out.println("Number of vehicles loaded: " + vehicles.size());
    }
}
