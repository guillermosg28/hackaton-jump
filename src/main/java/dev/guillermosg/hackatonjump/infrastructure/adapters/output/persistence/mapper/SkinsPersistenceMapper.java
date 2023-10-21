package dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.mapper;

import dev.guillermosg.hackatonjump.domain.model.Skin;
import dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.entity.SkinsEntity;
import org.mapstruct.Mapper;

/**
 * The interface Skins persistence mapper.
 */

@Mapper
public interface SkinsPersistenceMapper {

    /**
     * Skin to entity skins entity.
     *
     * @param skin the skin
     * @return the skins entity
     */
    Skin toSkin(SkinsEntity skin);

    /**
     * Entity to skin skins entity.
     *
     * @param skin the skin
     * @return the skins entity
     */
    SkinsEntity toSkinEntity(Skin skin);
}
