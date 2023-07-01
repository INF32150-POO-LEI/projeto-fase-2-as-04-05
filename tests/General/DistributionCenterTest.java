package General;
import static org.junit.jupiter.api.Assertions.*;
import Product.*;
import Vehicles.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;


public class DistributionCenterTest {

    private DistributionCenter distributionCenter;
    private List<Vehicle> vehicles;
    private List<Position> positionsList;

    @BeforeEach
    public void setup() {
        distributionCenter = new DistributionCenter(10, 10);
        vehicles = new ArrayList<>();
        positionsList = distributionCenter.getPositionsList();
    }
    @Test
    public void testPackProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Product 1", ProductType.CLOTHING));
        products.add(new Product("Product 2", ProductType.ACCESSORY));

        List packedItems = distributionCenter.packProducts(products);

        assertNotNull(packedItems);
    }

    @Test
    public void testGetX() {
        distributionCenter.setX(5);

        int xCoordinate = distributionCenter.getX();
        assertEquals(5, xCoordinate);
    }

    @Test
    public void testSetX() {
        distributionCenter.setX(10);

        int xCoordinate = distributionCenter.getX();
        assertEquals(10, xCoordinate);
    }

    @Test
    public void testSetY() {
        distributionCenter.setY(10);

        int yCoordinate = distributionCenter.getY();
        assertEquals(10, yCoordinate);
    }

    @Test
    public void testGetShelves() {
        List<Shelf> shelves = distributionCenter.getShelves();
        assertNotNull(shelves);
    }

    @Test
    public void testGetPositionsList() {
        List<Position> positionsList = distributionCenter.getPositionsList();
        assertNotNull(positionsList);
    }

    @Test
    public void testGetVehicles() {
        List<Vehicle> vehicles = distributionCenter.getVehicles();
        assertNotNull(vehicles);
    }
    @Test
    public void testGetY() {
        distributionCenter.setY(5);

        int yCoordinate = distributionCenter.getY();
        assertEquals(5, yCoordinate);
    }

    @Test
    public void testCreateDistributionCenter() {
        distributionCenter.createDistributionCenter();

        List<Position> positionsList = distributionCenter.getPositionsList();
        assertNotNull(positionsList);
    }

    @Test
    public void testPrintStorageMatrix() {
        Position[][] storageMatrix = {
                { new Position("Floor", 0, 0), new Position("Shelf", 1, 0), new Position("Floor", 2, 0) },
                { new Position("Wall", 0, 1), new Position("Floor", 1, 1), new Position("Wall", 2, 1) },
                { new Position("Shelf", 0, 2), new Position("Floor", 1, 2), new Position("Shelf", 2, 2) }
        };

        DistributionCenter.printStorageMatrix(storageMatrix);
    }

    @Test
    public void testConstructor() {
        assertNotNull(distributionCenter);
    }

    @Test
    public void testPositionMatrixToList() {
        // Create a sample position matrix
        Position[][] positionMatrix = new Position[2][3];
        positionMatrix[0][0] = new Position("Floor", 0, 0);
        positionMatrix[0][1] = new Position("Wall", 0, 1);
        positionMatrix[0][2] = new Position("Shelf", 0, 2);
        positionMatrix[1][0] = new Position("Floor", 1, 0);
        positionMatrix[1][1] = new Position("Wall", 1, 1);
        positionMatrix[1][2] = new Position("Floor", 1, 2);

        // Call the method to convert position matrix to list
        List<Position> positionsList = distributionCenter.positionMatrixToList(positionMatrix);

        // Verify the size and elements of the positions list
        assertEquals(6, positionsList.size(), "The size of the positions list should be 6");
        assertEquals(positionMatrix[0][0], positionsList.get(0), "The first element in the positions list should match the corresponding position in the matrix");
        assertEquals(positionMatrix[0][1], positionsList.get(1), "The second element in the positions list should match the corresponding position in the matrix");
        assertEquals(positionMatrix[0][2], positionsList.get(2), "The third element in the positions list should match the corresponding position in the matrix");
        assertEquals(positionMatrix[1][0], positionsList.get(3), "The fourth element in the positions list should match the corresponding position in the matrix");
        assertEquals(positionMatrix[1][1], positionsList.get(4), "The fifth element in the positions list should match the corresponding position in the matrix");
        assertEquals(positionMatrix[1][2], positionsList.get(5), "The sixth element in the positions list should match the corresponding position in the matrix");
    }

    @Test
    public void countPackedItemsResults() {
        List<Object> packedItems = new ArrayList<>();
        packedItems.add(new Pallet(10,10));
        packedItems.add(new Box(5));
        packedItems.add(new Bag(2));
        packedItems.add(new Box(5));
        packedItems.add(new Bag(2));
        packedItems.add(new Pallet(10,10));
        packedItems.add(new Box(2));
        packedItems.add(new Box(2));

        String expectedResults = "\nTotal de embalagens: 8\n" +
                "2 pallets (Cada pallet tem 100kg de peso máximo e um máximo de 10 caixas de madeira tendo cada caixa de madeira presente um peso máximo de 10kg)\n" +
                "2 bags (Cada bag tem um peso máximo de 2kg)\n" +
                "4 boxes (Cada caixa tem um máximo de 5kg)\n";

        String actualResults = distributionCenter.countPackedItemsResults(packedItems);

        assertEquals(expectedResults, actualResults, "The countPackedItemsResults output should match the expected results");
    }

    @Test
    public void testSetVehiclesStartingPositions() {
        // Create a list of vehicles
        List<Vehicle> vehicles = new ArrayList<>();
        Vehicle vehicle1 = new ULC();
        Vehicle vehicle2 = new DeliveryCart();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);

        // Set the starting positions for the vehicles
        distributionCenter.setVehiclesStartingPositions(vehicles, distributionCenter.getPositionsList());

        // Assert that the vehicles have been assigned starting positions
        assertNotNull(vehicle1.getCurrentPosition());
        assertNotNull(vehicle2.getCurrentPosition());
    }

    @Test
    public void testPrintHowManyAvailableVehicles() {
        // Create a list of vehicles
        List<Vehicle> vehicles = new ArrayList<>();
        Vehicle vehicle1 = new ULC();
        Vehicle vehicle2 = new DeliveryCart();
        Vehicle vehicle3 = new TugVehicle();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        vehicles.add(vehicle3);

        // Set the availability of vehicles
        vehicle1.setAvailableStatus(true);
        vehicle2.setAvailableStatus(false);
        vehicle3.setAvailableStatus(true);

        // Call the method to get the available vehicles information
        String result = distributionCenter.printHowManyAvailableVehicles(vehicles);

        // Assert that the result string contains the correct information
        assertTrue(result.contains("2 veiculos"));
        assertTrue(result.contains("1 do tipo ULC"));
        assertTrue(result.contains("0 do tipo delivery cart"));
        assertTrue(result.contains("1 do tipo tug vehicle"));
    }

    @Test
    public void testLoadVehicles() {
        // Create a list of packed items
        List<Object> packedItems = new ArrayList<>();
        Pallet pallet = new Pallet(100, 10);
        Bag bag = new Bag(2);
        Box box = new Box(5);
        packedItems.add(pallet);
        packedItems.add(bag);
        packedItems.add(box);

        // Create a list of vehicles
        List<Vehicle> vehicles = new ArrayList<>();
        Vehicle vehicle1 = new ULC();
        Vehicle vehicle2 = new DeliveryCart();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);

        // Load the packed items onto the vehicles
        distributionCenter.loadVehicles(packedItems, vehicles);

        // Assert that the packed items have been loaded onto the vehicles
        assertFalse(vehicle1.isAvailable());
        assertFalse(vehicle2.isAvailable());
    }
}