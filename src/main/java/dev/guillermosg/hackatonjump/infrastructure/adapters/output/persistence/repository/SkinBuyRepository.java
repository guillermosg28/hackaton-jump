package dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.repository;

import dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.entity.SkinBuyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Skin buy repository.
 */

@Repository
public interface SkinBuyRepository extends JpaRepository<SkinBuyEntity, Long> {

    /**
     * Find all by user id list.
     * @param userId the user id
     * @return the list
     */
    List<SkinBuyEntity> findAllByUserId(Long userId);
}
