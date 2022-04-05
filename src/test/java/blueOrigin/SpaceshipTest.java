package blueOrigin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SpaceshipTest {

    private Spaceship spaceship;

    @Before
    public void setUp(){
        this.spaceship = new Spaceship("Raketa", 3);
    }
    @Test
    public void testGetCount() {
        Assert.assertEquals(0,spaceship.getCount());
        Astronaut astronaut = new Astronaut("Riki",90);
        spaceship.add(astronaut);
        Assert.assertEquals(1,spaceship.getCount());
    }

    @Test
    public void testTestGetName() {
        Assert.assertEquals("Raketa",spaceship.getName());
    }

    private List<Astronaut> createAstronautsList() {
        Astronaut paulo = new Astronaut("Paulo",99);
        Astronaut alex = new Astronaut("Alex",59);
        Astronaut riki = new Astronaut("Rikardo",33);
        List<Astronaut> astronauts = new ArrayList<>();
        astronauts.add(paulo);
        astronauts.add(alex);
        astronauts.add(riki);
        return astronauts;
    }

    @Test
    public void testGetCapacity() {
        Assert.assertEquals(3,spaceship.getCapacity());
    }

    @Test
    public void testAddSuccessfully() {
        Assert.assertEquals(0,spaceship.getCount());

        List<Astronaut> astronauts = createAstronautsList();

        for (Astronaut astronaut: astronauts){
            spaceship.add(astronaut);
        }
        Assert.assertEquals(3,spaceship.getCount());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddThrowException(){
        List<Astronaut> astronauts = createAstronautsList();

        for (Astronaut astronaut: astronauts){
            spaceship.add(astronaut);
        }
        Astronaut oneMore = new Astronaut("Sharo",100);
        spaceship.add(oneMore);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddThrowExceptionIfExist(){

        Astronaut riki = new Astronaut("Rikardo",33);
        Astronaut riki2 = new Astronaut("Rikardo",33);
        spaceship.add(riki);
        spaceship.add(riki2);
    }

    @Test
    public void testRemove() {
        Assert.assertEquals(0,spaceship.getCount());
        List<Astronaut> astronauts = createAstronautsList();

        for (Astronaut astronaut: astronauts){
            spaceship.add(astronaut);
        }
        Assert.assertEquals(3,spaceship.getCount());

        spaceship.remove("Rikardo");
        Assert.assertEquals(2,spaceship.getCount());
    }
    @Test (expected = IllegalArgumentException.class)
    public void testSetCapacityInvalidZeroCapacity(){
        Spaceship sp = new Spaceship("Someone",-1);
    }

    @Test (expected = NullPointerException.class)
    public void testSetInvalidName(){
        Spaceship sp = new Spaceship(null,1);
    }
    @Test (expected = NullPointerException.class)
    public void testSetEmptyName(){
        Spaceship sp = new Spaceship("",1);
    }
}