package dev.guillermosg.hackatonjump.domain.service;

import dev.guillermosg.hackatonjump.application.ports.input.SkinBuyUseCase;
import dev.guillermosg.hackatonjump.application.ports.output.SkinBuyOutputPort;
import dev.guillermosg.hackatonjump.application.ports.output.SkinsOutputPort;
import dev.guillermosg.hackatonjump.application.ports.output.UserOutputPort;
import dev.guillermosg.hackatonjump.domain.model.SkinColorUpdateResponse;
import dev.guillermosg.hackatonjump.domain.model.SuccessResponse;
import dev.guillermosg.hackatonjump.domain.model.UserSkinResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Skin buy service.
 */
@Service
@AllArgsConstructor
@Slf4j
public class SkinBuyService implements SkinBuyUseCase {

    private SkinsOutputPort skinsOutputPort;
    private UserOutputPort usersOutputPort;
    private SkinBuyOutputPort skinBuyOutputPort;

    /**
     * @param skinId the skin id
     * @param userId the user id
     * @return SuccessResponse
     */
    @Override
    @Transactional
    public SuccessResponse buySkin(Integer skinId, Integer userId) {
        SuccessResponse successResponse = new SuccessResponse();

        var skin = skinsOutputPort.findById(skinId).orElseThrow(() -> new RuntimeException("Skin no encontrado."));
        var user = usersOutputPort.findById(userId).orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        if(skin.getState()){
            skinBuyOutputPort.save(skin, user);

            skin.setState(false);
            skinsOutputPort.update(skin);

            successResponse.setCode("000");
            successResponse.setMessage("Operación exitosa.");
        }else{
            successResponse.setCode("001");
            successResponse.setMessage("Skin no disponible.");
        }

        return successResponse;
    }

    /**
     * @param userId the user id
     * @return SkinResponse
     */
    @Override
    public UserSkinResponse userSkins(Integer userId) {
        var user = usersOutputPort.findById(userId).orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        var skinBuys = skinBuyOutputPort.findByUser(user.getId());

        return UserSkinResponse.builder()
                .skinBuys(skinBuys)
                .build();
    }

    /**
     * @param skinBuyId
     * @param color
     * @return SkinColorUpdateResponse
     */
    @Override
    public SkinColorUpdateResponse updateColorSkinBuy(Integer skinBuyId, String color) {

        var skinBuy = skinBuyOutputPort
                .findById(skinBuyId).orElseThrow(() -> new RuntimeException("Skin no encontrado."));

        var skin = skinsOutputPort
                .findById(skinBuy.getSkin().getId()).orElseThrow(() -> new RuntimeException("Skin no encontrado."));

        skin.setColor(color);
        skinsOutputPort.update(skin);

        SkinColorUpdateResponse skinColorUpdateResponse = new SkinColorUpdateResponse();
        skinColorUpdateResponse.setCode("000");
        skinColorUpdateResponse.setMessage("Color cambiado exitosamente.");
        skinColorUpdateResponse.setSkin(skin);

        return skinColorUpdateResponse;
    }

    /**
     * @param skinBuyId
     * @return SuccessResponse
     */
    @Override
    public SuccessResponse delete(Integer skinBuyId) {
        SuccessResponse successResponse = new SuccessResponse();

        var skinBuy = skinBuyOutputPort
                .findById(skinBuyId).orElseThrow(() -> new RuntimeException("Skin no encontrado."));

        skinBuyOutputPort.delete(skinBuy);

        successResponse.setCode("000");
        successResponse.setMessage("Operación exitosa.");

        return successResponse;
    }

}
