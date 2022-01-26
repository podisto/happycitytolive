package com.simba.happycitytolive.infrastructure.persistence.adapters;

import com.simba.happycitytolive.application.domain.Cadeau;
import com.simba.happycitytolive.application.domain.CadeauRepository;
import com.simba.happycitytolive.application.domain.Habitant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by podisto on 26/01/2022.
 */
@Component
@Slf4j
public class CadeauFileSystemAdapter implements CadeauRepository {

    private final List<Cadeau> listeCadeaux = new ArrayList<>();

    @Value("${application.file-dir:data/cadeaux.txt}")
    private String path;

    @PostConstruct
    public void init() {
        log.info("load cadeau from file system");
        try {
            File resource = new ClassPathResource(path).getFile();
            List<String> lines = Files.readAllLines(Paths.get(resource.getAbsolutePath()));
            lines.remove(0);
            List<Cadeau> cadeaux = lines.stream().map(this::toCadeau).collect(Collectors.toList());
            listeCadeaux.addAll(cadeaux);
        } catch (IOException e) {
            log.error("File not found {}", e.getMessage());
        }
    }

    @Override
    public List<Cadeau> byTrancheAge(Habitant habitant) {
        return listeCadeaux.stream()
                .filter(cadeau -> habitant.ageBetween(cadeau.getTrancheAge()))
                .collect(Collectors.toList());
    }

    @Override
    public void save(Cadeau cadeau) {
        log.info("we don't need to save cadeaux");
    }

    @Override
    public Optional<Cadeau> byReference(String reference) {
        return listeCadeaux.stream().filter(c -> c.getReference().equals(reference)).findAny();
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
