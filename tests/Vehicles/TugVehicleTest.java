package Vehicles;
import General.*;
import Product.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TugVehicleTest {

    private TugVehicle tugVehicle;
    private Position currentPosition;

    @BeforeEach
    public void setUp() {
        currentPosition = new Position("Location",0, 0);
        tugVehicle = new TugVehicle(currentPosition);
    }

    @Test
    public void testPutDCIntoTugVehicle() {
        List<Vehicle> vehicles = new ArrayList<>();
        Vehicle deliveryCart = new Vehicle("Veiculo1", new DeliveryCart(new Position("Location",1,1)), "DeliveryCart");
        deliveryCart.setAvailable(true);
        Bag bag = new Bag(2);
        List currentCargo = new ArrayList();
        currentCargo.add(bag);
        deliveryCart.getDc().setCurrentCargo(currentCargo);
        vehicles.add(deliveryCart);

        tugVehicle.putDCIntoTugVehicle(vehicles);

        List<DeliveryCart> towedAGCList = tugVehicle.getTowedAGCList();
        assertEquals(1, towedAGCList.size());
        assertEquals(deliveryCart.getDc(), towedAGCList.get(0));
    }

    @Test
    public void testGetCurrentPosition() {
        Position position = tugVehicle.getCurrentPosition();
        assertEquals(currentPosition, position);
    }

    @Test
    public void testSetTowedAGCList() {
        List<DeliveryCart> towedAGCList = new ArrayList<>();
        DeliveryCart deliveryCart = new DeliveryCart(new Position("Location",2, 2));
        towedAGCList.add(deliveryCart);

        tugVehicle.setTowedAGCList(towedAGCList);

        List<DeliveryCart> updatedTowedAGCList = tugVehicle.getTowedAGCList();
        assertEquals(towedAGCList, updatedTowedAGCList);
    }

    @Test
    public void testSetCurrentPosition() {
        Position newPosition = new Position("Location", 3, 3);

        tugVehicle.setCurrentPosition(newPosition);

        Position updatedPosition = tugVehicle.getCurrentPosition();
        assertEquals(newPosition, updatedPosition);
    }
}

