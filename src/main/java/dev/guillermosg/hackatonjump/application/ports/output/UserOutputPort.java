package dev.guillermosg.hackatonjump.application.ports.output;

import dev.guillermosg.hackatonjump.domain.model.User;

import java.util.Optional;

/**
 * The interface User output port.
 */
public interface UserOutputPort {

    /**
     * Find user by id.
     * @param id the user id
     * @return the user
     */
    Optional<User> findById(Integer id);

}
