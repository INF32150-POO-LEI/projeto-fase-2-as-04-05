package Vehicles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleTest {

    private Vehicle vehicle;

    @BeforeEach
    void setUp() {
        AGC agc = new AGC(null, null,null);
        ULC ulc = new ULC(null);
        TugVehicle tugVehicle = new TugVehicle(null);
        DeliveryCart dc = new DeliveryCart(null);

        vehicle = new Vehicle("V001", agc, "Car");
        vehicle.setUlc(ulc);
        vehicle.setTugVehicle(tugVehicle);
        vehicle.setDc(dc);
    }

    @Test
    void testGetAgc() {
        AGC agc = vehicle.getAgc();
        assertNotNull(agc);
    }

    @Test
    void testGetUlc() {
        ULC ulc = vehicle.getUlc();
        assertNotNull(ulc);
    }

    @Test
    void testGetTugVehicle() {
        TugVehicle tugVehicle = vehicle.getTugVehicle();
        assertNotNull(tugVehicle);
    }

    @Test
    void testGetDc() {
        DeliveryCart dc = vehicle.getDc();
        assertNotNull(dc);
    }

    @Test
    void testGetVehicleType() {
        String vehicleType = vehicle.getVehicleType();
        assertEquals("Car", vehicleType);
    }

    @Test
    void testGetId() {
        String id = vehicle.getId();
        assertEquals("V001", id);
    }

    @Test
    void testIsAvailable() {
        boolean available = vehicle.isAvailable();
        assertFalse(available);
    }

    @Test
    void testSetId() {
        vehicle.setId("V002");
        String id = vehicle.getId();
        assertEquals("V002", id);
    }

    @Test
    void testSetVehicleType() {
        vehicle.setVehicleType("ULC");
        String vehicleType = vehicle.getVehicleType();
        assertEquals("ULC", vehicleType);
    }

    @Test
    void testSetAgc() {
        AGC newAgc = new AGC(null,null,null);
        vehicle.setAgc(newAgc);
        AGC agc = vehicle.getAgc();
        assertEquals(newAgc, agc);
    }

    @Test
    void testSetUlc() {
        ULC newUlc = new ULC(null);
        vehicle.setUlc(newUlc);
        ULC ulc = vehicle.getUlc();
        assertEquals(newUlc, ulc);
    }

    @Test
    void testSetTugVehicle() {
        TugVehicle newTugVehicle = new TugVehicle(null);
        vehicle.setTugVehicle(newTugVehicle);
        TugVehicle tugVehicle = vehicle.getTugVehicle();
        assertEquals(newTugVehicle, tugVehicle);
    }

    @Test
    void testSetDc() {
        DeliveryCart newDc = new DeliveryCart(null);
        vehicle.setDc(newDc);
        DeliveryCart dc = vehicle.getDc();
        assertEquals(newDc, dc);
    }
}

