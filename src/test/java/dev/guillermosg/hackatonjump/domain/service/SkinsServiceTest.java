package dev.guillermosg.hackatonjump.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.guillermosg.hackatonjump.application.ports.output.SkinsOutputPort;
import dev.guillermosg.hackatonjump.domain.model.Skin;

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

@ContextConfiguration(classes = {SkinsService.class})
@ExtendWith(SpringExtension.class)
class SkinsServiceTest {
    @MockBean
    private SkinsOutputPort skinsOutputPort;

    @Autowired
    private SkinsService skinsService;

    /**
     * Method under test: {@link SkinsService#listSkins()}
     */
    @Test
    void testListSkins() {
        when(skinsOutputPort.findAll()).thenReturn(new ArrayList<>());
        assertTrue(skinsService.listSkins().getSkins().isEmpty());
        verify(skinsOutputPort).findAll();
    }

    /**
     * Method under test: {@link SkinsService#listSkins()}
     */
    @Test
    void testListSkins2() {
        when(skinsOutputPort.findAll()).thenThrow(new RuntimeException("foo"));
        assertThrows(RuntimeException.class, () -> skinsService.listSkins());
        verify(skinsOutputPort).findAll();
    }

    /**
     * Method under test: {@link SkinsService#findById(Integer)}
     */
    @Test
    void testFindById() {
        Skin.SkinBuilder nameResult = Skin.builder().color("Color").id(1).name("Name");
        Optional<Skin> ofResult = Optional.of(nameResult.price(new BigDecimal("2.3")).state(true).types("Types").build());
        when(skinsOutputPort.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        skinsService.findById(1);
        verify(skinsOutputPort).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link SkinsService#findById(Integer)}
     */
    @Test
    void testFindById2() {
        Optional<Skin> emptyResult = Optional.empty();
        when(skinsOutputPort.findById(Mockito.<Integer>any())).thenReturn(emptyResult);
        assertThrows(RuntimeException.class, () -> skinsService.findById(1));
        verify(skinsOutputPort).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link SkinsService#findById(Integer)}
     */
    @Test
    void testFindById3() {
        when(skinsOutputPort.findById(Mockito.<Integer>any())).thenThrow(new RuntimeException("Skin no encontrado."));
        assertThrows(RuntimeException.class, () -> skinsService.findById(1));
        verify(skinsOutputPort).findById(Mockito.<Integer>any());
    }
}

