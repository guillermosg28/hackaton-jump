package dev.guillermosg.hackatonjump;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.entity.SkinsEntity;
import dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.entity.UserEntity;
import dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.repository.SkinsRepository;
import dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoadInitialData implements CommandLineRunner {
    @Autowired
    private SkinsRepository skinsrepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if (skinsrepository.count() == 0) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<SkinsEntity> skinsData = objectMapper.readValue(
                    getClass().getResourceAsStream("/data/skins.json"),
                    new TypeReference<List<SkinsEntity>>() {}
            );
            skinsrepository.saveAll(skinsData);
        }

        if(userRepository.count() == 0){
            ObjectMapper objectMapper = new ObjectMapper();
            List<UserEntity> usersData = objectMapper.readValue(
                    getClass().getResourceAsStream("/data/users.json"),
                    new TypeReference<List<UserEntity>>() {}
            );
            userRepository.saveAll(usersData);
        }
    }
}

