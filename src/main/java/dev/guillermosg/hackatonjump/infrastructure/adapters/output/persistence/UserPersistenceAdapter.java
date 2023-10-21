package dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence;

import dev.guillermosg.hackatonjump.application.ports.output.UserOutputPort;
import dev.guillermosg.hackatonjump.domain.model.User;
import dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.mapper.UserPersistenceMapper;
import dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * The type User persistence adapter.
 */

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserOutputPort {

    private final UserRepository userRepository;
    private final UserPersistenceMapper userPersistenceMapper;

    /**
     * @param id the user id
     * @return the user
     */
    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(Long.valueOf(id.longValue())).map(userPersistenceMapper::toUser);
    }
}
