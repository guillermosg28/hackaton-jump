package dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.repository;

import dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.entity.SkinsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Skins repository.
 */

@Repository
public interface SkinsRepository extends JpaRepository<SkinsEntity, Long> {
}
