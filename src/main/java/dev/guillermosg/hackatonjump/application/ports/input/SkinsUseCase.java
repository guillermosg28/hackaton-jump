package dev.guillermosg.hackatonjump.application.ports.input;

import dev.guillermosg.hackatonjump.domain.model.Skin;
import dev.guillermosg.hackatonjump.domain.model.SkinResponse;

/**
 * The interface Skins use case.
 */
public interface SkinsUseCase {

    /**
     * List skins.
     * @return the skin response
     */
    SkinResponse listSkins();

    /**
     * Find by id.
     * @param skinId the skin id
     * @return the skin
     */
    Skin findById(Integer skinId);

}
