package Vehicles;
import General.Position;
import Product.Bag;
import Product.Pallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ULCTest {
    private ULC ulc;

    @BeforeEach
    void setUp() {
        ulc = new ULC();
    }

    @Test
    void testGetCargoQuantity() {
        int quantity = ulc.getCargoQuantity();
        assertEquals(0, quantity);

        Pallet pallet = new Pallet(100, 10);
        ulc.addPallet(pallet);

        quantity = ulc.getCargoQuantity();
        assertEquals(1, quantity);
    }

    @Test
    void testToString() {
        String str = ulc.toString();
        assertEquals("ULC", str);
    }

    @Test
    void testAddPallet() {
        Pallet pallet = new Pallet(50, 5);
        boolean added = ulc.addPallet(pallet);

        assertTrue(added);
        assertEquals(1, ulc.getCurrentCargo().size());
        assertTrue(ulc.getCurrentCargo().contains(pallet));
    }

    @Test
    public void testGetPath() {
        Position position1 = new Position("Position1", 0, 0);
        Position position2 = new Position("Position2", 1, 1);
        List<Position> path = new ArrayList<>();
        path.add(position1);
        path.add(position2);

        ulc.setPath(path);
        assertEquals(path, ulc.getPath());
    }

    @Test
    public void testGetCurrentCargo() {
        List<Pallet> currentCargo = new ArrayList<>();
        currentCargo.add(new Pallet(100, 10));
        ulc.setCurrentCargo(currentCargo);
        assertEquals(currentCargo, ulc.getCurrentCargo());
    }

    @Test
    public void testGetCurrentPosition() {
        Position currentPosition = new Position("currentPosition", 1, 2);
        ulc.setCurrentPosition(currentPosition);
        assertEquals(currentPosition, ulc.getCurrentPosition());
    }

    @Test
    public void testSetPath() {
        List<Position> path = new ArrayList<>();
        path.add(new Position("Position1", 0, 0));
        path.add(new Position("Position2", 1, 1));

        ulc.setPath(path);
        assertEquals(path, ulc.getPath());

        List<Position> newPath = new ArrayList<>();
        newPath.add(new Position("Position3", 2, 2));
        newPath.add(new Position("Position4", 3, 3));

        ulc.setPath(newPath);
        assertEquals(newPath, ulc.getPath());
    }

    @Test
    public void testSetCurrentCargo() {
        List<Pallet> currentCargo = new ArrayList<>();
        ulc.setCurrentCargo(currentCargo);
        assertEquals(currentCargo, ulc.getCurrentCargo());

        currentCargo.add(new Pallet(100, 20));
        ulc.setCurrentCargo(currentCargo);
        assertEquals(currentCargo, ulc.getCurrentCargo());
    }

    @Test
    public void testSetCurrentPosition() {
        Position currentPosition = new Position("currentPosition", 1, 2);
        ulc.setCurrentPosition(currentPosition);
        assertEquals(currentPosition, ulc.getCurrentPosition());

        Position newPosition = new Position("newPosition", 1, 1);
        ulc.setCurrentPosition(newPosition);
        assertEquals(newPosition, ulc.getCurrentPosition());
    }
}