package com.simba.happycitytolive.infrastructure.persistence.adapters;

import com.simba.happycitytolive.application.domain.Cadeau;
import com.simba.happycitytolive.application.domain.Habitant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by podisto on 26/01/2022.
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class CadeauFileSystemAdapterTest {

    @Autowired
    private CadeauFileSystemAdapter cadeauFileSystemAdapter;

    @Test
    void byTrancheAge() {
        Habitant habitant = new Habitant("Dione", "Omar", "omar@test.com", LocalDate.of(2000, 1, 1), LocalDate.of(2020, 5, 10), "Mostakbal");

        List<Cadeau> cadeaux = cadeauFileSystemAdapter.byTrancheAge(habitant);

        assertThat(cadeaux).contains(new Cadeau("5bd74b84", "Chauffe tasse USB", 15.9, 20, 30));
    }

}