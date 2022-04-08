package computers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ComputerManagerTests {
    // TODO: Test ComputerManager
    private ComputerManager computerManager;
    private Computer lenovo;
    private Computer asus;
    private Computer acer;

    @Before
    public void setUp() {
        computerManager = new ComputerManager();
        lenovo = new Computer("lenovo", "idea", 2100);
        asus = new Computer("asus", "zen", 1500);
        acer = new Computer("acer", "aspire", 1800);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddComputerWithNullShouldThrow() {
        computerManager.addComputer(null);
    }

    @Test
    public void testAddComputerWithCorrectValue() {
        computerManager.addComputer(lenovo);
        Assert.assertEquals(1, computerManager.getCount());
        Computer computer = computerManager.getComputers().get(0);
        Assert.assertEquals(lenovo.getManufacturer(), computer.getManufacturer());
        Assert.assertEquals(lenovo.getModel(), computer.getModel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddComputerWithExistingComputerShouldThrow() {
        Computer computer = new Computer("lenovo", "idea", 2500);
        computerManager.addComputer(lenovo);
        computerManager.addComputer(computer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetComputerWithNullManufacturerShouldThrow() {
        computerManager.addComputer(lenovo);
        computerManager.addComputer(asus);
        computerManager.getComputer(null, "idea");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetComputerWithNullModelShouldThrow() {
        computerManager.addComputer(lenovo);
        computerManager.addComputer(asus);
        computerManager.getComputer("lenovo", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetComputerWithNotExistingManufacturerShouldThrow() {
        computerManager.addComputer(lenovo);
        computerManager.addComputer(asus);
        computerManager.getComputer("lenovo1", "idea");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetComputerWithNotExistingModelShouldThrow() {
        computerManager.addComputer(lenovo);
        computerManager.addComputer(asus);
        computerManager.getComputer("lenovo", "idea1");
    }

    @Test
    public void testGetComputerWithCorrectValues() {
        computerManager.addComputer(lenovo);
        computerManager.addComputer(asus);
        Computer computer = computerManager.getComputer("lenovo", "idea");
        Assert.assertEquals(lenovo.getModel(), computer.getModel());
        Assert.assertEquals(lenovo.getManufacturer(), computer.getManufacturer());
    }

    @Test
    public void testRemoveComputer() {
        computerManager.addComputer(lenovo);
        computerManager.addComputer(asus);
        Assert.assertEquals(2, computerManager.getCount());
        Computer computer = computerManager.removeComputer("lenovo", "idea");
        Assert.assertEquals(1, computerManager.getCount());
        Assert.assertEquals(lenovo.getModel(), computer.getModel());
        Assert.assertEquals(lenovo.getManufacturer(), computer.getManufacturer());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetComputersByManufacturerWithNullManufacturerShouldThrow() {
        computerManager.addComputer(lenovo);
        computerManager.addComputer(asus);
        computerManager.getComputersByManufacturer(null);
    }

    @Test
    public void testGetComputersByManufacturerWithCorrectValue() {
        Computer thinkpad = new Computer("lenovo", "thinkpad", 2500);
        computerManager.addComputer(lenovo);
        computerManager.addComputer(asus);
        computerManager.addComputer(thinkpad);
        List<Computer> computers = computerManager.getComputersByManufacturer("lenovo");
        Assert.assertEquals(2, computers.size());
    }
}