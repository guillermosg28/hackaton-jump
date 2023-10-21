package dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.mapper;


import dev.guillermosg.hackatonjump.domain.model.SkinBuy;
import dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.entity.SkinBuyEntity;
import org.mapstruct.Mapper;

/**
 * The interface Skin buy persistence mapper.
 */

@Mapper
public interface SkinBuyPersistenceMapper {

    /**
     * Skin buy to entity skin buy entity.
     *
     * @param skinBuyEntity the skin buy
     * @return the skin buy entity
     */
    SkinBuy toSkinBuy(SkinBuyEntity skinBuyEntity);

    /**
     * Skin buy to entity skin buy entity.
     *
     * @param skinBuy the skin buy
     * @return the skin buy entity
     */
    SkinBuyEntity toSkinBuyEntity(SkinBuy skinBuy);

    /**
     * @param skinBuyEntity
     * @return SkinBuy
     */
    SkinBuy toUserSkinBuys(SkinBuyEntity skinBuyEntity);
}
