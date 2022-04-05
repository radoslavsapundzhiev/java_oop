package shopAndGoods;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class ShopTest {
    private Shop shop;
    private Goods apple;
    private Goods cherry;
    private Goods lemon;

    @Before
    public void setUp() {
        apple = new Goods("apple", "10");
        cherry = new Goods("cherry", "15");
        lemon = new Goods("lemon", "11");
    }

    @Test
    public void testCreateEmptyShopShouldHaveTwelveShelves() {
        shop = new Shop();
        Assert.assertEquals(12, shop.getShelves().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddGoodsWithWrongShelfShouldTrows() throws OperationNotSupportedException {
        shop = new Shop();
        shop.addGoods("Shelves13", apple);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddGoodsWithAlreadyTakenShelfShouldTrows() throws OperationNotSupportedException {
        shop = new Shop();
        shop.addGoods("Shelves1", apple);
        shop.addGoods("Shelves1", cherry);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddWithExistingGoodsShouldThrows() throws OperationNotSupportedException {
        shop = new Shop();
        shop.addGoods("Shelves1", apple);
        shop.addGoods("Shelves2", cherry);
        shop.addGoods("Shelves3", cherry);
    }

    @Test
    public void testAddWithCorrectGoods() throws OperationNotSupportedException {
        shop = new Shop();
        String appleResult = shop.addGoods("Shelves1", apple);
        String cherryResult = shop.addGoods("Shelves2", cherry);
        Assert.assertEquals(cherry.getName(), shop.getShelves().get("Shelves2").getName());
        Assert.assertEquals(cherry.getGoodsCode(), shop.getShelves().get("Shelves2").getGoodsCode());
        Assert.assertEquals(apple.getName(), shop.getShelves().get("Shelves1").getName());
        Assert.assertEquals(apple.getGoodsCode(), shop.getShelves().get("Shelves1").getGoodsCode());
        String expectedCherryResult = String.format("Goods: %s is placed successfully!", cherry.getGoodsCode());
        String expectedAppleResult = String.format("Goods: %s is placed successfully!", apple.getGoodsCode());
        Assert.assertEquals(expectedAppleResult, appleResult);
        Assert.assertEquals(expectedCherryResult, cherryResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveWithNotExistingShelfThrows() throws OperationNotSupportedException {
        shop = new Shop();
        shop.addGoods("Shelves1", apple);
        shop.addGoods("Shelves2", cherry);
        shop.removeGoods("Shelves14", cherry);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveWithNotExistingGoodsThrows() throws OperationNotSupportedException {
        shop = new Shop();
        shop.addGoods("Shelves1", apple);
        shop.addGoods("Shelves2", cherry);
        shop.removeGoods("Shelves1", cherry);
    }

    @Test
    public void testRemoveCorrectly() throws OperationNotSupportedException {
        shop = new Shop();
        shop.addGoods("Shelves1", apple);
        shop.addGoods("Shelves2", cherry);
        String appleResult =  shop.removeGoods("Shelves1", apple);
        Assert.assertNull(shop.getShelves().get("Shelves1"));
        String expectedAppleResult = String.format("Goods: %s is removed successfully!", apple.getGoodsCode());
        Assert.assertEquals(expectedAppleResult, appleResult);
    }
}