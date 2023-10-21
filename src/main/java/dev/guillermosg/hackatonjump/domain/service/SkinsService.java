package dev.guillermosg.hackatonjump.domain.service;

import dev.guillermosg.hackatonjump.application.ports.input.SkinsUseCase;
import dev.guillermosg.hackatonjump.application.ports.output.SkinsOutputPort;
import dev.guillermosg.hackatonjump.domain.model.Skin;
import dev.guillermosg.hackatonjump.domain.model.SkinResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Skins service.
 */

@Service
@AllArgsConstructor
@Slf4j
public class SkinsService implements SkinsUseCase {

    private SkinsOutputPort skinsOutputPort;
    /**
     * @return list of skins
     */
    @Override
    public SkinResponse listSkins() {
        List<Skin> skins = skinsOutputPort.findAll();
        return SkinResponse
                .builder()
                .skins(skins)
                .build();
    }

    /**
     * @param skinId the skin id
     * @return the skin
     */
    @Override
    public Skin findById(Integer skinId) {
        return skinsOutputPort
                .findById(skinId).orElseThrow(() -> new RuntimeException("Skin no encontrado."));
    }

}
