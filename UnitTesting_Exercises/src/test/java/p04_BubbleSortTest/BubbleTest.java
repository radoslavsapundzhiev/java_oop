package p04_BubbleSortTest;


import org.junit.Assert;
import org.junit.Test;

public class BubbleTest {
    private final static int[] NUMBERS = { 8, 3, 5, 9, 2};
    private Bubble bubble;

    @Test
    public void setUp() {
        bubble = new Bubble();
    }

    @Test
    public void testSortFiveNumbers() {
        bubble.sort(NUMBERS);
        Assert.assertArrayEquals(NUMBERS, new int[] {2, 3, 5, 8, 9});
    }
}