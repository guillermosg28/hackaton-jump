package dev.guillermosg.hackatonjump.infrastructure.adapters.input.rest;

import dev.guillermosg.hackatonjump.application.ports.input.SkinBuyUseCase;
import dev.guillermosg.hackatonjump.application.ports.input.SkinsUseCase;
import dev.guillermosg.hackatonjump.infrastructure.adapters.input.rest.data.*;
import dev.guillermosg.hackatonjump.infrastructure.adapters.input.rest.mapper.SkinsRestMapper;
import dev.guillermosg.hackatonjump.infrastructure.adapters.input.rest.mapper.SuccessResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Skins rest adapter.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/")
public class SkinsRestAdapter implements SkinsApi {

    private final SkinsRestMapper mapper;
    private final SuccessResponseMapper successResponseMapper;
    private final SkinsUseCase skinsUseCase;
    private final SkinBuyUseCase skinBuyUseCase;

    /**
     * @return list of skins
     */
    @Override
    public ResponseEntity<SkinResponseDto> _listSkins() {
        return ResponseEntity.ok().body(mapper.skinResponseToDto(skinsUseCase.listSkins()));
    }

    /**
     * @param skinBuyDto Skin to buy (required)
     * @return SuccessResponseDto
     */
    @Override
    public ResponseEntity<SuccessResponseDto> _buySkin(SkinBuyDto skinBuyDto) {
        return ResponseEntity.ok().body(successResponseMapper
                .successResponseToDto(skinBuyUseCase.buySkin(skinBuyDto.getSkinId(), skinBuyDto.getUserId())));
    }

    /**
     * @param user User id (required)
     * @return UserSkinResponseDto
     */
    @Override
    public ResponseEntity<UserSkinResponseDto> _listMySkins(Integer user) {
        return ResponseEntity.ok().body(mapper.userSkinResponseToDto(skinBuyUseCase.userSkins(user)));
    }

    /**
     * @param skinColorUpdateRequestDto Skin to buy (required)
     * @return SkinColorUpdateResponseDto
     */
    @Override
    public ResponseEntity<SkinColorUpdateResponseDto> _updateColorSkinBuy(SkinColorUpdateRequestDto skinColorUpdateRequestDto) {
        return ResponseEntity.ok()
                .body(mapper
                        .skinColorUpdateResponseToDto(skinBuyUseCase
                                .updateColorSkinBuy(
                                        skinColorUpdateRequestDto.getSkinBuyId(),
                                        skinColorUpdateRequestDto.getColor()
                                )));
    }

    /**
     * @param id Skin id (required)
     * @return SuccessResponseDto
     */
    @Override
    public ResponseEntity<SuccessResponseDto> _deleteSkin(Integer id) {
        return ResponseEntity.ok().body(successResponseMapper
                .successResponseToDto(skinBuyUseCase.delete(id)));
    }

    /**
     * @param id Skin id (required)
     * @return SkinDto
     */
    @Override
    public ResponseEntity<SkinDto> _getSkin(Integer id) {
        return ResponseEntity.ok().body(mapper
                .skinToDto(skinsUseCase.findById(id)));
    }

}
