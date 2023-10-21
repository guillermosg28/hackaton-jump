package dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.mapper;


import dev.guillermosg.hackatonjump.domain.model.User;
import dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

/**
 * The interface User persistence mapper.
 */
@Mapper
public interface UserPersistenceMapper {

    /**
     * User to entity user entity.
     *
     * @param user the user
     * @return the user entity
     */
    User toUser(UserEntity user);
}
