package dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.guillermosg.hackatonjump.domain.model.Skin;
import dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.entity.SkinsEntity;
import dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.mapper.SkinsPersistenceMapper;
import dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.repository.SkinsRepository;

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

@ContextConfiguration(classes = {SkinsPersistenceAdapter.class})
@ExtendWith(SpringExtension.class)
class SkinsPersistenceAdapterTest {
    @Autowired
    private SkinsPersistenceAdapter skinsPersistenceAdapter;

    @MockBean
    private SkinsPersistenceMapper skinsPersistenceMapper;

    @MockBean
    private SkinsRepository skinsRepository;

    /**
     * Method under test: {@link SkinsPersistenceAdapter#findAll()}
     */
    @Test
    void testFindAll() {
        when(skinsRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(skinsPersistenceAdapter.findAll().isEmpty());
        verify(skinsRepository).findAll();
    }

    /**
     * Method under test: {@link SkinsPersistenceAdapter#findById(Integer)}
     */
    @Test
    void testFindById() {
        SkinsEntity skinsEntity = new SkinsEntity();
        skinsEntity.setColor("Color");
        skinsEntity.setId(1L);
        skinsEntity.setName("Name");
        skinsEntity.setPrice(new BigDecimal("2.3"));
        skinsEntity.setState(true);
        skinsEntity.setTypes("Types");
        Optional<SkinsEntity> ofResult = Optional.of(skinsEntity);
        when(skinsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Skin.SkinBuilder nameResult = Skin.builder().color("Color").id(1).name("Name");
        when(skinsPersistenceMapper.toSkin(Mockito.<SkinsEntity>any()))
                .thenReturn(nameResult.price(new BigDecimal("2.3")).state(true).types("Types").build());
        assertTrue(skinsPersistenceAdapter.findById(1).isPresent());
        verify(skinsRepository).findById(Mockito.<Long>any());
        verify(skinsPersistenceMapper).toSkin(Mockito.<SkinsEntity>any());
    }

    /**
     * Method under test: {@link SkinsPersistenceAdapter#update(Skin)}
     */
    @Test
    void testUpdate() {
        SkinsEntity skinsEntity = new SkinsEntity();
        skinsEntity.setColor("Color");
        skinsEntity.setId(1L);
        skinsEntity.setName("Name");
        skinsEntity.setPrice(new BigDecimal("2.3"));
        skinsEntity.setState(true);
        skinsEntity.setTypes("Types");
        when(skinsRepository.save(Mockito.<SkinsEntity>any())).thenReturn(skinsEntity);

        SkinsEntity skinsEntity2 = new SkinsEntity();
        skinsEntity2.setColor("Color");
        skinsEntity2.setId(1L);
        skinsEntity2.setName("Name");
        skinsEntity2.setPrice(new BigDecimal("2.3"));
        skinsEntity2.setState(true);
        skinsEntity2.setTypes("Types");
        when(skinsPersistenceMapper.toSkinEntity(Mockito.<Skin>any())).thenReturn(skinsEntity2);
        skinsPersistenceAdapter.update(new Skin());
        verify(skinsRepository).save(Mockito.<SkinsEntity>any());
        verify(skinsPersistenceMapper).toSkinEntity(Mockito.<Skin>any());
    }
}

