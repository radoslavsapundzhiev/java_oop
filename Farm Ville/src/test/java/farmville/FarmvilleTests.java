package farmville;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FarmvilleTests {
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS Farm
    private Farm farm;

    @Before
    public void prepareFarm() {
        farm = new Farm("MyFarm", 4);
        Animal cat = new Animal("cat", 12);
        Animal dog = new Animal("dog", 18);
        Animal lion = new Animal("lion", 20);
        farm.add(cat);
        farm.add(dog);
        farm.add(lion);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateFarmWithNameEqualsNullShouldTrows() {
        farm = new Farm(null, 1);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateFarmWithEmptyNameShouldThrows() {
        farm = new Farm("   ", 6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateFarmWithNegativeCapacityShouldThrows() {
        farm = new Farm("MyFarm", -5);
    }

    @Test
    public void testCreateFarmWithCorrectNameAndCapacity() {
        farm = new Farm("MyFarm", 10);
        Assert.assertEquals("MyFarm", farm.getName());
        Assert.assertEquals(10, farm.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAnimalWithFullCapacityShouldThrows() {
        Animal fox = new Animal("fox", 9);
        Animal wolf = new Animal("wolf", 13);
        farm.add(fox);
        farm.add(wolf);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAnimalWithExistingTypeShouldThrows() {
        Animal lion = new Animal("lion", 23);
        farm.add(lion);
    }

    @Test
    public void testAddAnimalWithNotExistingTypeAndEnoughCapacity() {
        Animal fox = new Animal("fox", 9);
        farm.add(fox);
        Assert.assertEquals(4, farm.getCount());
    }

    @Test()
    public void testRemoveNotExistingAnimal() {
        boolean isRemove = farm.remove("fox");
        Assert.assertFalse(isRemove);
        Assert.assertEquals(3, farm.getCount());
    }

    @Test
    public void testRemoveExistingAnimal() {
        boolean isRemove = farm.remove("lion");
        Assert.assertTrue(isRemove);
        Assert.assertEquals(2, farm.getCount());
    }
}
