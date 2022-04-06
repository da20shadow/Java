package shopAndGoods;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ShopTest {

    private Shop shop;

    @Before
    public void setUp(){
        this.shop = new Shop();
    }

    @Test
    public void testGetShelves() {

        Map<String,Goods> shelves = new LinkedHashMap<>();
        shelves.put("Shelves1", null);
        shelves.put("Shelves2", null);
        shelves.put("Shelves3", null);
        shelves.put("Shelves4", null);
        shelves.put("Shelves5", null);
        shelves.put("Shelves6", null);
        shelves.put("Shelves7", null);
        shelves.put("Shelves8", null);
        shelves.put("Shelves9", null);
        shelves.put("Shelves10", null);
        shelves.put("Shelves11", null);
        shelves.put("Shelves12", null);

        Assert.assertEquals(shelves,shop.getShelves());
    }

    @Test (expected = UnsupportedOperationException.class)
    public void testGetShelvesReturnExceptionUnmodifiableCollection(){
        shop.getShelves().clear();
    }

    @Test
    public void testAddGoodsSuccessfully() throws OperationNotSupportedException {
        Goods good = new Goods("Breath","99");

        String str = "Goods: 99 is placed successfully!";

        Assert.assertEquals(str,shop.addGoods("Shelves1",good));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddGoodsShelfDoesntExistException() throws OperationNotSupportedException {
        Goods good = new Goods("Breath","99");
        shop.addGoods("InvalidShelve1",good);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddGoodsShelfAlreadyExistException() throws OperationNotSupportedException {
        Goods good = new Goods("Bread","99");

        shop.addGoods("Shelves1",good);
        shop.addGoods("Shelves1",good);
    }

    @Test (expected = OperationNotSupportedException.class)
    public void testAddGoodsShelfAlreadyTakenExistException() throws OperationNotSupportedException {
        Goods good = new Goods("Milk","99");

        shop.addGoods("Shelves1",good);
        shop.addGoods("Shelves2",good);
    }

    @Test
    public void testRemoveGoodsSuccessfully() throws OperationNotSupportedException {

        Goods good = new Goods("Milk","99");
        shop.addGoods("Shelves1",good);
        shop.removeGoods("Shelves1",good);
        Assert.assertNull(shop.getShelves().get("Shelves1"));

    }

    @Test
    public void testRemoveGoodsSuccessfullyMessage() throws OperationNotSupportedException {

        Goods good = new Goods("Milk","99");
        shop.addGoods("Shelves1",good);
        String removed = shop.removeGoods("Shelves1",good);
        String actual = "Goods: 99 is removed successfully!";
        Assert.assertEquals(actual,removed);

    }

    @Test (expected = IllegalArgumentException.class)
    public void testRemoveGoodsShelveDoesntExistException() throws OperationNotSupportedException {

        Goods good = new Goods("Milk","99");
        shop.addGoods("Shelves1",good);
        shop.removeGoods("Shelves13",good);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testRemoveGoodsThatDoesntExistException() throws OperationNotSupportedException {

        Goods good = new Goods("Milk","99");
        Goods good2 = new Goods("Bread","199");
        shop.addGoods("Shelves1",good);
        shop.addGoods("Shelves2",good2);

        shop.removeGoods("Shelves1",good2);
    }
}