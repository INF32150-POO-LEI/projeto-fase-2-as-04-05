package Vehicles;
import General.Position;
import Product.Product;
import Product.Box;
import Product.Bag;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeliveryCartTest {
    private DeliveryCart deliveryCart;
    private Position currentPosition;

    @BeforeEach
    public void setUp() {
        currentPosition = new Position("Location1", 0,0); // Example position
        deliveryCart = new DeliveryCart(currentPosition);
    }

    @Test
    public void testGetCurrentPosition() {
        assertEquals(currentPosition, deliveryCart.getCurrentPosition());
    }

    @Test
    public void testGetCurrentCargo() {
        List<Product> currentCargo = deliveryCart.getCurrentCargo();
        assertNotNull(currentCargo);
        assertTrue(currentCargo.isEmpty());
    }

    @Test
    public void testGetMaxWeight() {
        assertEquals(200, deliveryCart.getMaxWeight());
    }

    @Test
    public void testAddBag() {
        Bag bag = new Bag(10);
        bag.setCurrentWeight(10);
        assertTrue(deliveryCart.addBag(bag));
        assertEquals(10, deliveryCart.getCurrentWeight());
        assertEquals(List.of(bag), deliveryCart.getCurrentCargo());
    }

    @Test
    public void testAddBox() {
        Box box = new Box(20);
        box.setCurrentWeight(20);
        assertTrue(deliveryCart.addBox(box));
        assertEquals(20, deliveryCart.getCurrentWeight());
        assertEquals(List.of(box), deliveryCart.getCurrentCargo());
    }

    @Test
    public void testGetCurrentWeight() {
        assertEquals(0, deliveryCart.getCurrentWeight());
    }

    @Test
    public void testSetCurrentPosition() {
        Position newPosition = new Position("Location1", 1,1); // Example new position
        deliveryCart.setCurrentPosition(newPosition);
        assertEquals(newPosition, deliveryCart.getCurrentPosition());
    }

    @Test
    public void testSetCurrentWeight() {
        deliveryCart.setCurrentWeight(50); // Example new weight
        assertEquals(50, deliveryCart.getCurrentWeight());
    }

    @Test
    public void testSetCurrentCargo() {
        List newCargo = new ArrayList<>(); // Example new cargo
        Box box = new Box(30);
        newCargo.add(box);
        deliveryCart.setCurrentCargo(newCargo);
        assertEquals(newCargo, deliveryCart.getCurrentCargo());
    }
}

