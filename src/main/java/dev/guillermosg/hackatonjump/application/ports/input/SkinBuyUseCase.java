package dev.guillermosg.hackatonjump.application.ports.input;

import dev.guillermosg.hackatonjump.domain.model.SkinColorUpdateResponse;
import dev.guillermosg.hackatonjump.domain.model.SuccessResponse;
import dev.guillermosg.hackatonjump.domain.model.UserSkinResponse;

/**
 * The interface Skin buy use case.
 */
public interface SkinBuyUseCase {

    /**
     * Buy skin success response.
     * @param skinId the skin id
     * @param userId the user id
     * @return the success response
     */
    SuccessResponse buySkin(Integer skinId, Integer userId);

    /**
     * User skins.
     * @param userId the user id
     * @return the skin response
     */
    UserSkinResponse userSkins(Integer userId);

    /**
     *
     * @param skinBuyId
     * @param color
     * @return SkinColorUpdateResponse
     */
    SkinColorUpdateResponse updateColorSkinBuy(Integer skinBuyId, String color);

    /**
     *
     * @param skinBuyId
     * @return SuccessResponse
     */
    SuccessResponse delete(Integer skinBuyId);

}
