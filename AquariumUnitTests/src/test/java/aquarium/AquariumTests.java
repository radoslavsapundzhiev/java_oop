package aquarium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AquariumTests {
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS Aquarium
    private Aquarium aquarium;
    private Fish pesho;
    private Fish gosho;
    private Fish kiro;

    @Before
    public void setUp() {
        aquarium = new Aquarium("test", 2);
        pesho = new Fish("Pesho");
        gosho = new Fish("Gosho");
        kiro = new Fish("Kiro");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAquariumWithNegativeCapacityShouldThrow() {
        aquarium = new Aquarium("test", -1);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateAquariumWithNullNameShouldThrow() {
        aquarium = new Aquarium(null, 2);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateAquariumWithEmptyNameShouldThrow() {
        aquarium = new Aquarium("    ", 2);
    }

    @Test
    public void testCreateAquariumWithCorrectParams() {
        Assert.assertEquals(2, aquarium.getCapacity());
        Assert.assertEquals("test", aquarium.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddWithNoCapacityShouldThrow() {
        aquarium.add(pesho);
        aquarium.add(gosho);
        aquarium.add(kiro);
    }

    @Test
    public void testAddWithCorrectValues() {
        aquarium.add(pesho);
        Assert.assertEquals(1, aquarium.getCount());
        aquarium.add(gosho);
        Assert.assertEquals(2, aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveWithNullFishShouldThrow() {
        aquarium.add(pesho);
        aquarium.add(gosho);
        aquarium.remove("Pesho1");
    }

    @Test
    public void testRemoveWithCorrectValue() {
        aquarium.add(pesho);
        Assert.assertEquals(1, aquarium.getCount());
        aquarium.add(gosho);
        Assert.assertEquals(2, aquarium.getCount());
        aquarium.remove("Pesho");
        Assert.assertEquals(1, aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSellFishWithNotExistingFishShouldThrow() {
        aquarium.add(pesho);
        aquarium.add(gosho);
        aquarium.sellFish("Kiro");
    }

    @Test
    public void testSellFishWithCorrectValue() {
        aquarium.add(pesho);
        Assert.assertTrue(pesho.isAvailable());
        aquarium.add(gosho);
        Assert.assertTrue(gosho.isAvailable());
        Fish soldFish = aquarium.sellFish("Pesho");
        Assert.assertFalse(soldFish.isAvailable());
        Assert.assertFalse(pesho.isAvailable());
        Assert.assertEquals(pesho.getName(), soldFish.getName());
    }

    @Test
    public void testReport() {
        aquarium.add(pesho);
        aquarium.add(gosho);
        String names = "Pesho, Gosho";
        String expectedResult = String.format("Fish available at %s: %s", aquarium.getName(), names);
        Assert.assertEquals(expectedResult, aquarium.report());
    }
}

