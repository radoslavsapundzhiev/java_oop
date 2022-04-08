package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GarageTests {
    //TODO: Test Garage class
    private Garage garage;
    private Car vw;
    private Car hyundai;
    private Car dacia;

    @Before
    public void setUp() {
        garage = new Garage();
        vw = new Car("VW", 120, 25000);
        hyundai = new Car("HYUNDAI", 140, 32000);
        dacia = new Car("DACIA", 100, 19000);
        garage.addCar(vw);
        garage.addCar(hyundai);
        garage.addCar(dacia);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCarWithNullCarShouldThrow() {
        garage.addCar(null);
    }

    @Test
    public void testAddCarWithCorrectValue() {
        Car bmw = new Car("BMW", 160, 50000);
        garage.addCar(bmw);
        Assert.assertEquals(4, garage.getCount());
        List<Car> cars = garage.getCars();
        Assert.assertEquals(4, cars.size());
    }

    @Test
    public void testGetMostExpensiveCarWithEmptyGarage() {
        garage = new Garage();
        Assert.assertNull(garage.getTheMostExpensiveCar());
    }

    @Test
    public void testGetMostExpensiveCarWithNotEmptyGarage() {
        Car mostExpensiveCar = garage.getTheMostExpensiveCar();
        Assert.assertEquals(hyundai.getBrand(), mostExpensiveCar.getBrand());
        Assert.assertEquals(hyundai.getMaxSpeed(), mostExpensiveCar.getMaxSpeed());
    }

    @Test
    public void testFindAllCarsWithMaxSpeedAbove() {
        List<Car> cars = garage.findAllCarsWithMaxSpeedAbove(119);
        Assert.assertEquals(2, cars.size());
    }

    @Test
    public void testFindAllCarsByBrand() {
        Car daciaDuster = new Car("DACIA", 125, 23000);
        garage.addCar(daciaDuster);
        List<Car> cars = garage.findAllCarsByBrand("DACIA");
        Assert.assertEquals(2, cars.size());
    }
}