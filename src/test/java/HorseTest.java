import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HorseTest {

    @Test
    public void testHorseConstructorForNullException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
        assertEquals("Name cannot be null.", e.getMessage());
    }

//    @Test
//    public void testHorseConstructorForNullExceptionMessageName() {
//        try {
//            new Horse(null, 1, 1);
//            fail();
//        } catch (IllegalArgumentException e) {
//            assertEquals("Name cannot be null.", e.getMessage());
//        }
//    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r"})
    public void testHorseConstructorForBlankValue(String name) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));
        assertEquals("Name cannot be blank.", e.getMessage());
    }

    @Test
    public void testHorseConstructorForNegativeSpeed() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("Houdini", -1, 1));
        assertEquals("Speed cannot be negative.", e.getMessage());
    }

    @Test
    public void testHorseConstructorForNegativeDistance() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("Houdini", 1, -1));
        assertEquals("Distance cannot be negative.", e.getMessage());
    }

    @Test
    public void testGetName() {
        Horse horse = new Horse("Houdini", 1, 1);
        assertEquals("Houdini", horse.getName());
    }

    @Test
    public void testGetSpeed() {
        Horse horse = new Horse("Houdini", 1, 1);
        assertEquals(1, horse.getSpeed());
    }

    @Test
    public void testGetDistance() {
        Horse horse = new Horse("Houdini", 1, 1);
        assertEquals(1, horse.getDistance());
    }

    @Test
    public void testGetDistanceZero() {
        Horse horse = new Horse("Houdini", 1);
        assertEquals(0, horse.getDistance());
    }

    @Test
    public void testGetRandomDoubleInMove() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            new Horse("Houdini", 1, 10).move();

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 99.99})
    public void testMove(double value) {
        double speed = 10;
        double distance = 100;
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Houdini", speed, distance);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(value);

            horse.move();

            assertEquals(distance + speed * value, horse.getDistance());
        }
    }
}