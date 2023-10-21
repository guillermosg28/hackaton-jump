package dev.guillermosg.hackatonjump.infrastructure.adapters.input.rest.mapper;

import dev.guillermosg.hackatonjump.domain.model.*;
import dev.guillermosg.hackatonjump.infrastructure.adapters.input.rest.data.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * The interface SkinsRestMapper
 */
@Mapper
public interface SkinsRestMapper {

    /**
     * To dto skin response dto.
     * @param skinResponse
     * @return SkinResponseDto
     */
    SkinResponseDto skinResponseToDto(SkinResponse skinResponse);

    /**
     * To dto user skin response dto.
     * @param userSkinResponse
     * @return UserSkinResponseDto
     */
    UserSkinResponseDto userSkinResponseToDto(UserSkinResponse userSkinResponse);

    /**
     * To dto user skin buy dto.
     * @param skinBuy
     * @return UserSkinBuyDto
     */
    @Mapping(source = "id", target = "skinBuyId")
    UserSkinBuyDto userSkinBuyToDto(SkinBuy skinBuy);

    /**
     *
     * @param skinColorUpdateResponse
     * @return SkinColorUpdateResponseDto
     */
    SkinColorUpdateResponseDto skinColorUpdateResponseToDto(SkinColorUpdateResponse skinColorUpdateResponse);

    /**
     * To dto skin dto.
     * @param skin
     * @return SkinDto
     */
    SkinDto skinToDto(Skin skin);
}
