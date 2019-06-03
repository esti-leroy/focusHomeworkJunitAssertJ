package ru.cft;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Cafe assertJ test")
class CafeAssertjTest {

    private Cafe cafe;

    @BeforeEach
    void setup() {
        cafe = new Cafe();
    }

    @Test
    @DisplayName("Test beans reminder")
    void testBeansReminder() {
        cafe.restockMilk(100);
        cafe.restockBeans(100);
        Coffee coffee = cafe.brew(CoffeeType.Espresso);
        assertThat(coffee.getMilk()).isEqualTo(0);
        assertThat(coffee.getBeans()).isEqualTo(7);
        assertThat(cafe.getBeansInStock()).isEqualTo(93);
        assertThat(cafe.getMilkInStock()).isEqualTo(100);
    }
}
