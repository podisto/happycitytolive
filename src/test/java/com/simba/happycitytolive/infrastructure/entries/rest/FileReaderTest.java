package com.simba.happycitytolive.infrastructure.entries.rest;

import com.simba.happycitytolive.application.domain.Cadeau;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by podisto on 26/01/2022.
 */
@Slf4j
class FileReaderTest {

    @Test
    void loadFileFromClassPath() throws IOException {
        File resource = new ClassPathResource("data/cadeaux.txt").getFile();
        List<String> lines = Files.readAllLines(Paths.get(resource.getAbsolutePath()));
        lines.remove(0);
        List<Cadeau> cadeaux = lines.stream()
                .map(this::toCadeau)
                .collect(Collectors.toList());

        assertThat(cadeaux).contains(new Cadeau("b9dcca0d", "Chocolats Toblerone", 12.9, 60, 150));
    }

    private Cadeau toCadeau(String line) {
        String[] elements = line.split(",");
        double amount = Double.parseDouble(elements[2]);
        String[] trancheAge = elements[3].split("-");
        int min = Integer.parseInt(trancheAge[0]);
        int max = Integer.parseInt(trancheAge[1]);
        return new Cadeau(elements[0], elements[1], amount, min, max);
    }
}
