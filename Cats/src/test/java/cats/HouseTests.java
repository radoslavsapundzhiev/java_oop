package cats;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HouseTests {
    private House house;
    private Cat pesho;
    private Cat gosho;
    private Cat kiro;

    @Before
    public void setUp() {
        house = new House("Test", 2);
        pesho = new Cat("Pesho");
        gosho = new Cat("Gosho");
        kiro = new Cat("Kiro");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateHouseWithNegativeCapacityShouldThrow() {
        house = new House("Test", -1);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateHouseWithNullNameShouldThrow() {
        house = new House(null, 3);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateHouseWithEmptyNameShouldThrow() {
        house = new House("   ", 3);
    }

    @Test
    public void testCreateHouseWithCorrectValues() {
        Assert.assertEquals(2, house.getCapacity());
        Assert.assertEquals("Test", house.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCatWithNoAvailableCapacityShouldThrow() {
        house.addCat(pesho);
        house.addCat(gosho);
        house.addCat(kiro);
    }

    @Test
    public void testAddCatWithEnoughCapacity() {
        house.addCat(pesho);
        Assert.assertEquals(1, house.getCount());
        house.addCat(gosho);
        Assert.assertEquals(2, house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveCatWithNotExistingCatShouldThrow() {
        house.addCat(pesho);
        house.addCat(gosho);
        house.removeCat("Pesho1");
    }

    @Test
    public void testRemoveCatWithExistingCat() {
        house.addCat(pesho);
        house.addCat(gosho);
        house.removeCat("Pesho");
        Assert.assertEquals(1, house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCatForSaleWithNotExistingCatShouldThrow() {
        house.addCat(pesho);
        house.addCat(gosho);
        house.catForSale("Pesho2");
    }

    @Test
    public void testCatForSaleWithCorrectValues() {
        house.addCat(pesho);
        Assert.assertTrue(pesho.isHungry());
        house.addCat(gosho);
        Assert.assertTrue(gosho.isHungry());
        Cat soldCat = house.catForSale("Pesho");
        Assert.assertFalse(pesho.isHungry());
        Assert.assertEquals(pesho.getName(), soldCat.getName());
        Assert.assertEquals(pesho.isHungry(), soldCat.isHungry());
    }

    @Test
    public void testStatistics() {
        house.addCat(pesho);
        house.addCat(gosho);
        String names = "Pesho, Gosho";
        String expectedResult = String.format("The cat %s is in the house %s!", names, house.getName());
        Assert.assertEquals(expectedResult, house.statistics());
    }
}
