package com.simba.happycitytolive;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by podisto on 15/01/2022.
 */
class CurrentDateTest {

    @Test
    void testCurrentDate() {
        Clock clock = Clock.systemDefaultZone();
        LocalDate current = LocalDate.now(clock);
        assertThat(LocalDate.now()).isEqualTo(current);
    }
}
