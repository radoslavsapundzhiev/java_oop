package gifts;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public class GiftFactoryTests {
    private GiftFactory giftFactory;
    private Gift truck;
    private Gift doll;
    private Gift gun;
    @Before
    public void setUp() {
        giftFactory = new GiftFactory();
        truck = new Gift("truck", 25.5);
        doll = new Gift("doll", 15.3);
        gun = new Gift("gun", 12.6);
    }

    @Test
    public void testCreateGiftWithCorrectValues() {
        String returnedMessage = giftFactory.createGift(truck);
        Assert.assertEquals(1, giftFactory.getCount());
        String expectedMessage = String.format("Successfully added gift %s with magic %.2f.", truck.getType(), truck.getMagic());
        Assert.assertEquals(expectedMessage, returnedMessage);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateGiftWithExistingTypeOfGiftShouldThrow() {
        Gift gift = new Gift("truck", 28.6);
        giftFactory.createGift(truck);
        giftFactory.createGift(gift);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveGiftWithNullNameShouldThrow() {
        giftFactory.createGift(truck);
        giftFactory.removeGift(null);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveGiftWithEmptyNameShouldThrow() {
        giftFactory.createGift(truck);
        giftFactory.removeGift("  ");
    }

    @Test
    public void testRemoveGiftSuccessfully() {
        giftFactory.createGift(truck);
        Assert.assertEquals(1, giftFactory.getCount());
        boolean returnedResult = giftFactory.removeGift("truck");
        Assert.assertTrue(returnedResult);
        Assert.assertEquals(0, giftFactory.getCount());
    }

    @Test
    public void testRemoveGiftNotSuccessfully() {
        giftFactory.createGift(truck);
        Assert.assertEquals(1, giftFactory.getCount());
        boolean returnedResult = giftFactory.removeGift("car");
        Assert.assertFalse(returnedResult);
        Assert.assertEquals(1, giftFactory.getCount());
    }

    @Test
    public void testGetPresentWithLeastMagic() {
        giftFactory.createGift(truck);
        giftFactory.createGift(doll);
        giftFactory.createGift(gun);
        Gift gift = giftFactory.getPresentWithLeastMagic();
        Assert.assertEquals(gun.getType(), gift.getType());
    }

    @Test
    public void testGetPresentNotExisting() {
        giftFactory.createGift(truck);
        giftFactory.createGift(doll);
        giftFactory.createGift(gun);
        Gift gift = giftFactory.getPresent("car");
        Assert.assertNull(gift);
    }

    @Test
    public void testGetPresentExisting() {
        giftFactory.createGift(truck);
        giftFactory.createGift(doll);
        giftFactory.createGift(gun);
        Gift gift = giftFactory.getPresent("gun");
        Assert.assertEquals(gun.getType(), gift.getType());
    }

    @Test
    public void testGetPresents() {
        giftFactory.createGift(truck);
        giftFactory.createGift(doll);
        giftFactory.createGift(gun);
        Collection<Gift> gifts = giftFactory.getPresents();
        Assert.assertEquals(3, gifts.size());
    }
}
