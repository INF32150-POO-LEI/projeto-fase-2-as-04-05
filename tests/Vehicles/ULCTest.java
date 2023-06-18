package Vehicles;
import General.Position;
import Product.Pallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ULCTest {

    private ULC ulc;
    private Position initialPosition;

    @BeforeEach
    public void setUp() {
        initialPosition = new Position("Location",0, 0);
        ulc = new ULC(initialPosition);
    }

    @Test
    public void testAddPallet() {
        Pallet pallet = new Pallet(100,10);
        boolean added = ulc.addPallet(pallet);
        assertTrue(added);
    }

    @Test
    public void testAddPalletExceedMax() {

        Pallet pallet1 = new Pallet(100,10);
        ulc.addPallet(pallet1);

        Pallet pallet2 = new Pallet(100,10);
        boolean added = ulc.addPallet(pallet2);
        assertFalse(added);
    }

    @Test
    public void testGetPath() {
        List<Position> expectedPath = new ArrayList<>();
        ulc.setPath(expectedPath);

        List<Position> actualPath = ulc.getPath();
        assertEquals(expectedPath, actualPath);
    }

    @Test
    public void testGetCurrentCargo() {
        List<Pallet> expectedCargo = new ArrayList<>();
        ulc.setCurrentCargo(expectedCargo);

        List<Pallet> actualCargo = ulc.getCurrentCargo();
        assertEquals(expectedCargo, actualCargo);
    }

    @Test
    public void testGetCurrentPosition() {
        Position expectedPosition = new Position("Location",1, 1);
        ulc.setCurrentPosition(expectedPosition);

        Position actualPosition = ulc.getCurrentPosition();
        assertEquals(expectedPosition, actualPosition);
    }
}


