package dev.guillermosg.hackatonjump;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.entity.SkinsEntity;
import dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.repository.SkinsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoadInitialData implements CommandLineRunner {
    @Autowired
    private SkinsRepository repository; // Reemplaza con tu repositorio JPA

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<SkinsEntity> initialData = objectMapper.readValue(
                    getClass().getResourceAsStream("/data.json"),
                    new TypeReference<List<SkinsEntity>>() {}
            );
            repository.saveAll(initialData);
        }
    }
}

