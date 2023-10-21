package dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.guillermosg.hackatonjump.domain.model.User;
import dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.entity.UserEntity;
import dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.mapper.UserPersistenceMapper;
import dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.repository.UserRepository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserPersistenceAdapter.class})
@ExtendWith(SpringExtension.class)
class UserPersistenceAdapterTest {
    @Autowired
    private UserPersistenceAdapter userPersistenceAdapter;

    @MockBean
    private UserPersistenceMapper userPersistenceMapper;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link UserPersistenceAdapter#findById(Integer)}
     */
    @Test
    void testFindById() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setLastName("Doe");
        userEntity.setName("Name");
        Optional<UserEntity> ofResult = Optional.of(userEntity);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(userPersistenceMapper.toUser(Mockito.<UserEntity>any()))
                .thenReturn(User.builder().id(1).lastName("Doe").name("Name").build());
        assertTrue(userPersistenceAdapter.findById(1).isPresent());
        verify(userRepository).findById(Mockito.<Long>any());
        verify(userPersistenceMapper).toUser(Mockito.<UserEntity>any());
    }
}

