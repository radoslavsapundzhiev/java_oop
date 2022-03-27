package p01_Database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {
    private Database database;
    private final static Integer[] NUMBERS = new Integer[] {1, 6, 4, 3};

    @Before
    public void setUp() throws OperationNotSupportedException {
        database = new Database(NUMBERS);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void createInstanceWithEmptyElementsThrows() throws OperationNotSupportedException {
        Integer[] elements = new Integer[0];
        database = new Database(elements);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void createInstanceWithMoreThenSixteenElementsThrows() throws OperationNotSupportedException {
        Integer[] elements = new Integer[17];
        database = new Database(elements);
    }

    @Test
    public void createInstanceWithElementsBetweenOneAndSixTeenElements() {
        Assert.assertArrayEquals(NUMBERS, database.getElements());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddWithPassedNullElementThrows() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void testAddWithValidInteger() throws OperationNotSupportedException {
        Integer addedElement = 63;
        database.add(addedElement);
        Integer lastElement = database.getElements()[database.getElements().length - 1];
        Assert.assertEquals(database.getElements().length, NUMBERS.length + 1);
        Assert.assertEquals(addedElement, lastElement);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveFromEmptyDataBaseThrows() throws OperationNotSupportedException {
        for (int i = 0; i < NUMBERS.length; i++) {
            database.remove();
        }

        database.remove();
    }

    @Test
    public void testRemoveFromNotEmptyDataBase() throws OperationNotSupportedException {
        database.remove();
        Assert.assertEquals(database.getElements().length, NUMBERS.length - 1);
        Assert.assertEquals(database.getElements()[database.getElements().length - 1], NUMBERS[NUMBERS.length - 2]);
    }

    @Test
    public void testGetElements() {
        Assert.assertArrayEquals(database.getElements(), NUMBERS);
    }

}