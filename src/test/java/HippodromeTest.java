import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HippodromeTest {
    @Test
    public void testHippodromeConstructorForNullException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", e.getMessage());
    }

    @Test
    public void testHippodromeConstructorForEmptyList() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", e.getMessage());
    }

    @Test
    public void testGetHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i=1; i<=30; i++) {
            horses.add(new Horse("Horse" + i, 1, 1));
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void testMove() {
        List<Horse> horses = new ArrayList<>();
        for (int i=1; i<=50; i++) {
            Horse mockHorse = mock(Horse.class);
            horses.add(mockHorse);
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    @Test
    public void testGetWinner() {
        List<Horse> horses = new ArrayList<>();
        Horse horse1 = new Horse("Houdini", 2.4, 20);
        Horse horse2 = new Horse("Comanche", 2.5, 19);
        Horse horse3 = new Horse("Ranger", 2.6, 22);
        Horse horse4 = new Horse("Elvis", 2.7, 24);
        Horse horse5 = new Horse("Yoda", 2.8, 28);
        Horse horse6 = new Horse("Pegasus", 2.9, 27);
        Horse horse7 = new Horse("Shelby", 3, 30);

        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);
        horses.add(horse4);
        horses.add(horse5);
        horses.add(horse6);
        horses.add(horse7);

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        assertSame(horse7, hippodrome.getWinner());
    }
}