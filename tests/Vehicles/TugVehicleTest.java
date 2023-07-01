package Vehicles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TugVehicleTest {
    private TugVehicle tugVehicle;

    @BeforeEach
    void setUp() {
        tugVehicle = new TugVehicle();
    }

    @Test
    void putDCIntoTugVehicle() {
        DeliveryCart dc = new DeliveryCart();
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(dc);

        tugVehicle.putDCIntoTugVehicle(vehicles);

        assertEquals(dc, tugVehicle.getTowedAGC());
        assertTrue(dc.isTugged());
    }

    @Test
    void getCargoQuantityTest() {
        int quantity = tugVehicle.getCargoQuantity();

        assertEquals(0, quantity);

        DeliveryCart dc = new DeliveryCart();
        tugVehicle.setTowedAGC(dc);

        quantity = tugVehicle.getCargoQuantity();

        assertEquals(1, quantity);
    }
}
