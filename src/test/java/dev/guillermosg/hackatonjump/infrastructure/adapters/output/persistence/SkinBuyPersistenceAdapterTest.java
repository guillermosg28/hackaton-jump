package dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.guillermosg.hackatonjump.domain.model.Skin;
import dev.guillermosg.hackatonjump.domain.model.SkinBuy;
import dev.guillermosg.hackatonjump.domain.model.User;
import dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.entity.SkinBuyEntity;
import dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.entity.SkinsEntity;
import dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.entity.UserEntity;
import dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.mapper.SkinBuyPersistenceMapper;
import dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.repository.SkinBuyRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SkinBuyPersistenceAdapter.class})
@ExtendWith(SpringExtension.class)
class SkinBuyPersistenceAdapterTest {
    @Autowired
    private SkinBuyPersistenceAdapter skinBuyPersistenceAdapter;

    @MockBean
    private SkinBuyPersistenceMapper skinBuyPersistenceMapper;

    @MockBean
    private SkinBuyRepository skinBuyRepository;

    /**
     * Method under test: {@link SkinBuyPersistenceAdapter#save(Skin, User)}
     */
    @Test
    void testSave() {
        SkinsEntity skin = new SkinsEntity();
        skin.setColor("Color");
        skin.setId(1L);
        skin.setName("Name");
        skin.setPrice(new BigDecimal("2.3"));
        skin.setState(true);
        skin.setTypes("Types");

        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setLastName("Doe");
        user.setName("Name");

        SkinBuyEntity skinBuyEntity = new SkinBuyEntity();
        skinBuyEntity.setId(1L);
        skinBuyEntity.setSkin(skin);
        skinBuyEntity.setUser(user);
        when(skinBuyRepository.save(Mockito.<SkinBuyEntity>any())).thenReturn(skinBuyEntity);

        SkinsEntity skin2 = new SkinsEntity();
        skin2.setColor("Color");
        skin2.setId(1L);
        skin2.setName("Name");
        skin2.setPrice(new BigDecimal("2.3"));
        skin2.setState(true);
        skin2.setTypes("Types");

        UserEntity user2 = new UserEntity();
        user2.setId(1L);
        user2.setLastName("Doe");
        user2.setName("Name");

        SkinBuyEntity skinBuyEntity2 = new SkinBuyEntity();
        skinBuyEntity2.setId(1L);
        skinBuyEntity2.setSkin(skin2);
        skinBuyEntity2.setUser(user2);
        when(skinBuyPersistenceMapper.toSkinBuyEntity(Mockito.<SkinBuy>any())).thenReturn(skinBuyEntity2);
        Skin skin3 = new Skin();
        skinBuyPersistenceAdapter.save(skin3, new User());
        verify(skinBuyRepository).save(Mockito.<SkinBuyEntity>any());
        verify(skinBuyPersistenceMapper).toSkinBuyEntity(Mockito.<SkinBuy>any());
    }

    /**
     * Method under test: {@link SkinBuyPersistenceAdapter#findByUser(Integer)}
     */
    @Test
    void testFindByUser() {
        when(skinBuyRepository.findAllByUserId(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        assertTrue(skinBuyPersistenceAdapter.findByUser(1).isEmpty());
        verify(skinBuyRepository).findAllByUserId(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link SkinBuyPersistenceAdapter#findById(Integer)}
     */
    @Test
    void testFindById() {
        SkinsEntity skin = new SkinsEntity();
        skin.setColor("Color");
        skin.setId(1L);
        skin.setName("Name");
        skin.setPrice(new BigDecimal("2.3"));
        skin.setState(true);
        skin.setTypes("Types");

        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setLastName("Doe");
        user.setName("Name");

        SkinBuyEntity skinBuyEntity = new SkinBuyEntity();
        skinBuyEntity.setId(1L);
        skinBuyEntity.setSkin(skin);
        skinBuyEntity.setUser(user);
        Optional<SkinBuyEntity> ofResult = Optional.of(skinBuyEntity);
        when(skinBuyRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        SkinBuy.SkinBuyBuilder idResult = SkinBuy.builder().id("42");
        Skin.SkinBuilder nameResult = Skin.builder().color("Color").id(1).name("Name");
        SkinBuy.SkinBuyBuilder skinResult = idResult
                .skin(nameResult.price(new BigDecimal("2.3")).state(true).types("Types").build());
        when(skinBuyPersistenceMapper.toUserSkinBuys(Mockito.<SkinBuyEntity>any()))
                .thenReturn(skinResult.user(User.builder().id(1).lastName("Doe").name("Name").build()).build());
        assertTrue(skinBuyPersistenceAdapter.findById(1).isPresent());
        verify(skinBuyRepository).findById(Mockito.<Long>any());
        verify(skinBuyPersistenceMapper).toUserSkinBuys(Mockito.<SkinBuyEntity>any());
    }

    /**
     * Method under test: {@link SkinBuyPersistenceAdapter#delete(SkinBuy)}
     */
    @Test
    void testDelete() {
        doNothing().when(skinBuyRepository).delete(Mockito.<SkinBuyEntity>any());

        SkinsEntity skin = new SkinsEntity();
        skin.setColor("Color");
        skin.setId(1L);
        skin.setName("Name");
        skin.setPrice(new BigDecimal("2.3"));
        skin.setState(true);
        skin.setTypes("Types");

        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setLastName("Doe");
        user.setName("Name");

        SkinBuyEntity skinBuyEntity = new SkinBuyEntity();
        skinBuyEntity.setId(1L);
        skinBuyEntity.setSkin(skin);
        skinBuyEntity.setUser(user);
        when(skinBuyPersistenceMapper.toSkinBuyEntity(Mockito.<SkinBuy>any())).thenReturn(skinBuyEntity);
        skinBuyPersistenceAdapter.delete(new SkinBuy());
        verify(skinBuyRepository).delete(Mockito.<SkinBuyEntity>any());
        verify(skinBuyPersistenceMapper).toSkinBuyEntity(Mockito.<SkinBuy>any());
    }
}

