package dev.guillermosg.hackatonjump.application.ports.output;

import dev.guillermosg.hackatonjump.domain.model.Skin;
import dev.guillermosg.hackatonjump.domain.model.SkinBuy;
import dev.guillermosg.hackatonjump.domain.model.User;

import java.util.List;
import java.util.Optional;

/**
 * The interface Skin buy output port.
 */

public interface SkinBuyOutputPort {

    /**
     * Save.
     * @param skin the skin
     * @param user the user
     */
    void save(Skin skin, User user);

    /**
     * Find by user.
     * @param userId the user
     * @return the list
     */
    List<SkinBuy> findByUser(Integer userId);

    /**
     * Find by id.
     * @param skinBuyId the skin buy id
     * @return the skin buy
     */
    Optional<SkinBuy> findById(Integer skinBuyId);

    /**
     * Delete.
     * @param skinBuy the skin buy
     */
    void delete(SkinBuy skinBuy);

}
