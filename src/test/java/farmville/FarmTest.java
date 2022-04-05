package farmville;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FarmTest {

    private Farm farm;

    @Before
    public void setUp(){
        farm = new Farm("FarmName",3);
    }
    @Test
    public void testGetCount() {
        Assert.assertEquals(0,farm.getCount());
        List<Animal> animalList = createThreeAnimals();
        for (Animal a: animalList){
            farm.add(a);
        }
        Assert.assertEquals(3,farm.getCount());
    }

    private List<Animal> createThreeAnimals() {
        Animal cat = new Animal("Cat",99);
        Animal horse = new Animal("Horse",300);
        Animal cow = new Animal("Cow",33);
        List<Animal> animals = new ArrayList<>();
        animals.add(cat);
        animals.add(horse);
        animals.add(cow);
        return animals;
    }

    @Test
    public void testTestGetName() {
        Assert.assertEquals("FarmName",farm.getName());
    }
    @Test
    public void testGetCapacity() {
        Assert.assertEquals(3,farm.getCapacity());
    }
    @Test
    public void testAddSuccess() {
        Assert.assertEquals(0,farm.getCount());
        List<Animal> animals = createThreeAnimals();
        for (Animal a: animals){
            farm.add(a);
        }
        Assert.assertEquals(3,farm.getCount());
    }
    @Test (expected = IllegalArgumentException.class)
    public void testAddMoreThanCapacity(){
        List<Animal> animals = createThreeAnimals();
        for (Animal a: animals){
            farm.add(a);
        }
        Animal oneMore = new Animal("OneMore",334);
        farm.add(oneMore);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddAnimalIfExistThrowException(){
        Animal oneMore = new Animal("OneMore",334);
        Animal oneMore2 = new Animal("OneMore",334);
        farm.add(oneMore);
        farm.add(oneMore2);
    }

    @Test
    public void testRemove() {
        List<Animal> animals = createThreeAnimals();
        for (Animal a: animals){
            farm.add(a);
        }
        Assert.assertEquals(3,farm.getCount());
        farm.remove("Cow");
        Assert.assertEquals(2,farm.getCount());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetInvalidCapacityLessThanZero(){
        Farm newFarm = new Farm("Invalid",-1);
    }

    @Test (expected = NullPointerException.class)
    public void testSetInvalidNameNull(){
        Farm newFarm = new Farm(null,1);
    }

    @Test (expected = NullPointerException.class)
    public void testSetInvalidNameEmpty(){
        Farm newFarm = new Farm("",1);
    }
}