package dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence;

import dev.guillermosg.hackatonjump.application.ports.output.SkinsOutputPort;
import dev.guillermosg.hackatonjump.domain.model.Skin;
import dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.mapper.SkinsPersistenceMapper;
import dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.repository.SkinsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Skins persistence adapter.
 */
@Component
@RequiredArgsConstructor
public class SkinsPersistenceAdapter implements SkinsOutputPort {

    private final SkinsRepository skinsRepository;
    private final SkinsPersistenceMapper skinsPersistenceMapper;

    /**
     * @return the list
     */
    @Override
    public List<Skin> findAll() {
        return skinsRepository.findAll().stream().map(skinsPersistenceMapper::toSkin).collect(Collectors.toList());
    }

    /**
     * @param id the skin id
     * @return the skin
     */
    @Override
    public Optional<Skin> findById(Integer id) {
        return skinsRepository.findById(Long.valueOf(id.longValue())).map(skinsPersistenceMapper::toSkin);
    }

    /**
     * @param skin the skin
     */
    @Override
    public void update(Skin skin) {
        skinsRepository.save(skinsPersistenceMapper.toSkinEntity(skin));
    }
}
