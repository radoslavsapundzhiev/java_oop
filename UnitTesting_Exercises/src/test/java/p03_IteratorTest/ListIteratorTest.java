package p03_IteratorTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class ListIteratorTest {
    private ListIterator listIterator;
    private final static String[] ELEMENTS = {
            "Pesho",
            "Gosho",
            "Tosho"
    };

    @Before
    public void setUp() throws OperationNotSupportedException {
        listIterator = new ListIterator(ELEMENTS);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testCreateInstaceWithNullElementsThrows() throws OperationNotSupportedException {
        listIterator = new ListIterator(null);
    }

    @Test
    public void testCreateInstaceWithNotNullElements() {
        Assert.assertTrue(listIterator.hasNext());
    }

    @Test
    public void testMoveWithEmptyElements() throws OperationNotSupportedException {
        String[] elements = new String[0];
        listIterator = new ListIterator(elements);
        Assert.assertFalse(listIterator.move());
    }

    @Test
    public void testMoveWithNotEmptyElements() {
        Assert.assertTrue(listIterator.move());
    }

    @Test
    public void testMoveOnLastElement() {
        for (int i = 0; i < ELEMENTS.length - 1; i++) {
            Assert.assertTrue(listIterator.move());
        }
        Assert.assertFalse(listIterator.move());
    }

    @Test
    public void testHasNextWithEmptyElemets() throws OperationNotSupportedException {
        String[] elements = new String[0];
        listIterator = new ListIterator(elements);
        Assert.assertFalse(listIterator.hasNext());
    }

    @Test
    public void testHasNextWithNotEmptyElements() {
        for (int i = 0; i < ELEMENTS.length - 1; i++) {
            Assert.assertTrue(listIterator.hasNext());
            listIterator.move();
        }
        Assert.assertFalse(listIterator.hasNext());
    }

    @Test(expected = IllegalStateException.class)
    public void testPrintWithEmptyElementsThrows() throws OperationNotSupportedException {
        String[] elements = new String[0];
        listIterator = new ListIterator(elements);
        listIterator.print();
    }

    @Test
    public void testPrintFirstWithNotEmptyElements() {
        String returnedElement = listIterator.print();
        Assert.assertEquals(returnedElement, ELEMENTS[0]);
    }

    @Test
    public void testPrintWithNotEmptyElements() {
        int index = 0;
        while (listIterator.hasNext()) {
            Assert.assertEquals(listIterator.print(), ELEMENTS[index]);
            index++;
            listIterator.move();
        }
    }
}