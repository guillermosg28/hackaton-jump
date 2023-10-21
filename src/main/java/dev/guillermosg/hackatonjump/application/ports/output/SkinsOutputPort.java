package dev.guillermosg.hackatonjump.application.ports.output;

import dev.guillermosg.hackatonjump.domain.model.Skin;

import java.util.List;
import java.util.Optional;

/**
 * The interface Skins output port.
 */
public interface SkinsOutputPort {

    /**
     * List skins.
     * @return the skin response
     */
    List<Skin> findAll();

    /**
     * Find skin by id.
     * @param id the skin id
     * @return the skin
     */
    Optional<Skin> findById(Integer id);

    /**
     * Save skin.
     * @param skin the skin
     */
    void update(Skin skin);
}
