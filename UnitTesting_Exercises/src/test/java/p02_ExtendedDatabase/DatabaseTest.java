package p02_ExtendedDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {
    private Database database;
    private final static Person[] PEOPLE = {
            new Person(1, "Pesho"),
            new Person(2, "Gosho"),
            new Person(3, "Tosho")
    };

    @Before
    public void prepareDataBase() throws OperationNotSupportedException {
        database = new Database(PEOPLE);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void createInstanceWithEmptyElementsThrows() throws OperationNotSupportedException {
        Person[] people = new Person[0];
        database = new Database(people);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void createInstanceWithMoreThanSixteenElementsThrows() throws OperationNotSupportedException {
        Person[] people = new Person[17];
        database = new Database(people);
    }

    @Test
    public void createInstanceWithThreeElements() {
        Assert.assertArrayEquals(database.getElements(), PEOPLE);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddPersonWithNullThrows() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void testAddPersonWithExistingId() throws OperationNotSupportedException {
        Person person = new Person(4, "Kiro");
        database.add(person);
        Person foundPerson = database.findById(4);
        Assert.assertEquals(foundPerson.getId(), person.getId());
        Assert.assertEquals(foundPerson.getUsername(), person.getUsername());
        Assert.assertEquals(database.getElements().length, PEOPLE.length + 1);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveFromEmptyDataBaseThrows() throws OperationNotSupportedException {
        for (int i = 0; i < PEOPLE.length; i++) {
            database.remove();
        }
        database.remove();
    }

    @Test
    public void testRemoveFromNotEmptyDataBase() throws OperationNotSupportedException {
        database.remove();
        Person lastPerson = database.getElements()[database.getElements().length - 1];
        Assert.assertEquals(lastPerson.getId(), PEOPLE[PEOPLE.length - 2].getId());
        Assert.assertEquals(lastPerson.getUsername(), PEOPLE[PEOPLE.length - 2].getUsername());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByNullUsernameThrows() throws OperationNotSupportedException {
        database.findByUsername(null);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByNotExistingUsernameThrows() throws OperationNotSupportedException {
        database.findByUsername("pesho");
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByMoreThanOneUsernameThrows() throws OperationNotSupportedException {
        database.add(new Person(4, "Pesho"));
        database.findByUsername("Pesho");
    }

    @Test
    public void testFindByCorrectUsername() throws OperationNotSupportedException {
        Person foundPerson = database.findByUsername("Pesho");
        Assert.assertEquals(foundPerson.getId(), PEOPLE[0].getId());
        Assert.assertEquals(foundPerson.getUsername(), PEOPLE[0].getUsername());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByNotExistingIdThrows() throws OperationNotSupportedException {
        database.findById(4);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByMoreThanOneIdThrows() throws OperationNotSupportedException {
        database.add(new Person(1, "Kiro"));
        database.findById(1);
    }

    @Test
    public void testFindByCorrectId() throws OperationNotSupportedException {
        Person foundPerson = database.findById(1);
        Assert.assertEquals(foundPerson.getId(), PEOPLE[0].getId());
        Assert.assertEquals(foundPerson.getUsername(), PEOPLE[0].getUsername());
    }
}