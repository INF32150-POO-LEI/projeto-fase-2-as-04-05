package Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PalletTest {
    private Pallet pallet;

    @BeforeEach
    public void setup() {
        double maxWeight = 100.0;
        int maxBoxes = 5;
        pallet = new Pallet(maxWeight, maxBoxes);
    }

    @Test
    public void testGetMaxWeight() {
        double maxWeight = pallet.getMaxWeight();
        assertEquals(100.0, maxWeight);
    }

    @Test
    public void testGetCurrentWeight() {
        double currentWeight = pallet.getCurrentWeight();
        assertEquals(0.0, currentWeight);
    }

    @Test
    public void testGetMaxBoxes() {
        int maxBoxes = pallet.getMaxBoxes();
        assertEquals(5, maxBoxes);
    }

    @Test
    public void testGetCurrentBoxes() {
        List<CardBox> currentBoxes = pallet.getCurrentBoxes();
        assertNotNull(currentBoxes);
        assertEquals(0, currentBoxes.size());
    }

    @Test
    public void testGetCode() {
        int code = pallet.getCode();
        assertEquals(4004, code);
    }

    @Test
    public void testPack() {
        CardBox box1 = new CardBox(20.0, 2);
        CardBox box2 = new CardBox(15.0, 3);
        CardBox oversizedBox = new CardBox(pallet.getMaxWeight() + 1, 1);
        box1.setCurrentWeight(20.0);
        box2.setCurrentWeight(15.0);
        oversizedBox.setCurrentWeight(pallet.getMaxWeight()+1);

        assertTrue(pallet.pack(box1));
        assertEquals(20.0, pallet.getCurrentWeight());
        assertEquals(1, pallet.getCurrentBoxes().size());

        assertTrue(pallet.pack(box2));
        assertEquals(35.0, pallet.getCurrentWeight());
        assertEquals(2, pallet.getCurrentBoxes().size());

        assertFalse(pallet.pack(oversizedBox));
        assertEquals(35.0, pallet.getCurrentWeight());
        assertEquals(2, pallet.getCurrentBoxes().size());

        // Pack more boxes until the maximum capacity is reached
        CardBox box3 = new CardBox(25.0, 1);
        CardBox box4 = new CardBox(10.0, 1);
        CardBox box5 = new CardBox(5.0, 1);
        box3.setCurrentWeight(25.0);
        box4.setCurrentWeight(10.0);
        box5.setCurrentWeight(5.0);

        assertTrue(pallet.pack(box3));
        assertTrue(pallet.pack(box4));
        assertTrue(pallet.pack(box5));
        assertEquals(75.0, pallet.getCurrentWeight());
        assertEquals(5, pallet.getCurrentBoxes().size());

        // Try to pack one more box, should fail
        CardBox additionalBox = new CardBox(8.0, 1);
        assertFalse(pallet.pack(additionalBox));
        assertEquals(75.0, pallet.getCurrentWeight());
        assertEquals(5, pallet.getCurrentBoxes().size());
    }

    @Test
    public void testPackIndividualProduct() {
        Product product = new Product("Product", null);
        product.setWeight(10.0);
        assertFalse(pallet.pack(product));
        assertEquals(0.0, pallet.getCurrentWeight());
        assertEquals(0, pallet.getCurrentBoxes().size());
    }
}
