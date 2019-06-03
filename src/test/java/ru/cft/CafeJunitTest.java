package ru.cft;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Cafe junit test")
class CafeJunitTest {

    private Cafe cafe;

    @BeforeEach
    void setup() {
        cafe = new Cafe();
    }

    @DisplayName("Test valid espresso")
    @Test
    void testValidEspresso() {
        cafe.restockBeans(7);
        Coffee expected = new Coffee(CoffeeType.Espresso, 7, 0);
        Coffee actual = cafe.brew(CoffeeType.Espresso);
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getBeans(), actual.getBeans());
        assertEquals(expected.getMilk(), actual.getMilk());
    }

    @DisplayName("Test valid latte")
    @Test
    void testValidLatte() {
        cafe.restockBeans(7);
        cafe.restockMilk(227);
        Coffee expected = new Coffee(CoffeeType.Latte, 7, 227);
        Coffee actual = cafe.brew(CoffeeType.Latte);
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getBeans(), actual.getBeans());
        assertEquals(expected.getMilk(), actual.getMilk());
    }

    @DisplayName("Test cafe from file")
    @ParameterizedTest
    @CsvFileSource(resources = "/coffee_expected_info.csv")
    void testInvalidLatte(int beans, int milk, CoffeeType type) {
        if (beans > 0) {
            cafe.restockBeans(beans);
        }
        if (milk > 0) {
            cafe.restockMilk(milk);
        }
        Coffee expected = new Coffee(type, beans, milk);
        Coffee actual = cafe.brew(type);
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getBeans(), actual.getBeans());
        assertEquals(expected.getMilk(), actual.getMilk());
    }
}
