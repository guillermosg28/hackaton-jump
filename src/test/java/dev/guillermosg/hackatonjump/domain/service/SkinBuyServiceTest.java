package dev.guillermosg.hackatonjump.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.guillermosg.hackatonjump.application.ports.output.SkinBuyOutputPort;
import dev.guillermosg.hackatonjump.application.ports.output.SkinsOutputPort;
import dev.guillermosg.hackatonjump.application.ports.output.UserOutputPort;
import dev.guillermosg.hackatonjump.domain.model.Skin;
import dev.guillermosg.hackatonjump.domain.model.SkinBuy;
import dev.guillermosg.hackatonjump.domain.model.SkinColorUpdateResponse;
import dev.guillermosg.hackatonjump.domain.model.SuccessResponse;
import dev.guillermosg.hackatonjump.domain.model.User;

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

@ContextConfiguration(classes = {SkinBuyService.class})
@ExtendWith(SpringExtension.class)
class SkinBuyServiceTest {
    @MockBean
    private SkinBuyOutputPort skinBuyOutputPort;

    @Autowired
    private SkinBuyService skinBuyService;

    @MockBean
    private SkinsOutputPort skinsOutputPort;

    @MockBean
    private UserOutputPort userOutputPort;

    /**
     * Method under test: {@link SkinBuyService#buySkin(Integer, Integer)}
     */
    @Test
    void testBuySkin() {
        doNothing().when(skinsOutputPort).update(Mockito.<Skin>any());
        Skin.SkinBuilder nameResult = Skin.builder().color("Color").id(1).name("Name");
        Optional<Skin> ofResult = Optional.of(nameResult.price(new BigDecimal("2.3")).state(true).types("Types").build());
        when(skinsOutputPort.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        Optional<User> ofResult2 = Optional.of(User.builder().id(1).lastName("Doe").name("Name").build());
        when(userOutputPort.findById(Mockito.<Integer>any())).thenReturn(ofResult2);
        doNothing().when(skinBuyOutputPort).save(Mockito.<Skin>any(), Mockito.<User>any());
        SuccessResponse actualBuySkinResult = skinBuyService.buySkin(1, 1);
        assertEquals("000", actualBuySkinResult.getCode());
        assertEquals("Operación exitosa.", actualBuySkinResult.getMessage());
        verify(skinsOutputPort).findById(Mockito.<Integer>any());
        verify(skinsOutputPort).update(Mockito.<Skin>any());
        verify(userOutputPort).findById(Mockito.<Integer>any());
        verify(skinBuyOutputPort).save(Mockito.<Skin>any(), Mockito.<User>any());
    }

    /**
     * Method under test: {@link SkinBuyService#buySkin(Integer, Integer)}
     */
    @Test
    void testBuySkin2() {
        Skin.SkinBuilder nameResult = Skin.builder().color("Color").id(1).name("Name");
        Optional<Skin> ofResult = Optional.of(nameResult.price(new BigDecimal("2.3")).state(true).types("Types").build());
        when(skinsOutputPort.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        Optional<User> ofResult2 = Optional.of(User.builder().id(1).lastName("Doe").name("Name").build());
        when(userOutputPort.findById(Mockito.<Integer>any())).thenReturn(ofResult2);
        doThrow(new RuntimeException("000")).when(skinBuyOutputPort).save(Mockito.<Skin>any(), Mockito.<User>any());
        assertThrows(RuntimeException.class, () -> skinBuyService.buySkin(1, 1));
        verify(skinsOutputPort).findById(Mockito.<Integer>any());
        verify(userOutputPort).findById(Mockito.<Integer>any());
        verify(skinBuyOutputPort).save(Mockito.<Skin>any(), Mockito.<User>any());
    }

    /**
     * Method under test: {@link SkinBuyService#buySkin(Integer, Integer)}
     */
    @Test
    void testBuySkin3() {
        Skin.SkinBuilder nameResult = Skin.builder().color("Color").id(1).name("Name");
        Optional<Skin> ofResult = Optional
                .of(nameResult.price(new BigDecimal("2.3")).state(false).types("Types").build());
        when(skinsOutputPort.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        Optional<User> ofResult2 = Optional.of(User.builder().id(1).lastName("Doe").name("Name").build());
        when(userOutputPort.findById(Mockito.<Integer>any())).thenReturn(ofResult2);
        SuccessResponse actualBuySkinResult = skinBuyService.buySkin(1, 1);
        assertEquals("001", actualBuySkinResult.getCode());
        assertEquals("Skin no disponible.", actualBuySkinResult.getMessage());
        verify(skinsOutputPort).findById(Mockito.<Integer>any());
        verify(userOutputPort).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link SkinBuyService#buySkin(Integer, Integer)}
     */
    @Test
    void testBuySkin4() {
        Optional<Skin> emptyResult = Optional.empty();
        when(skinsOutputPort.findById(Mockito.<Integer>any())).thenReturn(emptyResult);
        assertThrows(RuntimeException.class, () -> skinBuyService.buySkin(1, 1));
        verify(skinsOutputPort).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link SkinBuyService#buySkin(Integer, Integer)}
     */
    @Test
    void testBuySkin5() {
        Skin.SkinBuilder nameResult = Skin.builder().color("Color").id(1).name("Name");
        Optional<Skin> ofResult = Optional.of(nameResult.price(new BigDecimal("2.3")).state(true).types("Types").build());
        when(skinsOutputPort.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        Optional<User> emptyResult = Optional.empty();
        when(userOutputPort.findById(Mockito.<Integer>any())).thenReturn(emptyResult);
        assertThrows(RuntimeException.class, () -> skinBuyService.buySkin(1, 1));
        verify(skinsOutputPort).findById(Mockito.<Integer>any());
        verify(userOutputPort).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link SkinBuyService#userSkins(Integer)}
     */
    @Test
    void testUserSkins() {
        Optional<User> ofResult = Optional.of(User.builder().id(1).lastName("Doe").name("Name").build());
        when(userOutputPort.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        when(skinBuyOutputPort.findByUser(Mockito.<Integer>any())).thenReturn(new ArrayList<>());
        assertTrue(skinBuyService.userSkins(1).getSkinBuys().isEmpty());
        verify(userOutputPort).findById(Mockito.<Integer>any());
        verify(skinBuyOutputPort).findByUser(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link SkinBuyService#userSkins(Integer)}
     */
    @Test
    void testUserSkins2() {
        Optional<User> ofResult = Optional.of(User.builder().id(1).lastName("Doe").name("Name").build());
        when(userOutputPort.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        when(skinBuyOutputPort.findByUser(Mockito.<Integer>any())).thenThrow(new RuntimeException("foo"));
        assertThrows(RuntimeException.class, () -> skinBuyService.userSkins(1));
        verify(userOutputPort).findById(Mockito.<Integer>any());
        verify(skinBuyOutputPort).findByUser(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link SkinBuyService#userSkins(Integer)}
     */
    @Test
    void testUserSkins3() {
        Optional<User> emptyResult = Optional.empty();
        when(userOutputPort.findById(Mockito.<Integer>any())).thenReturn(emptyResult);
        assertThrows(RuntimeException.class, () -> skinBuyService.userSkins(1));
        verify(userOutputPort).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link SkinBuyService#updateColorSkinBuy(Integer, String)}
     */
    @Test
    void testUpdateColorSkinBuy() {
        doNothing().when(skinsOutputPort).update(Mockito.<Skin>any());
        Skin.SkinBuilder nameResult = Skin.builder().color("Color").id(1).name("Name");
        Optional<Skin> ofResult = Optional.of(nameResult.price(new BigDecimal("2.3")).state(true).types("Types").build());
        when(skinsOutputPort.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        SkinBuy.SkinBuyBuilder idResult = SkinBuy.builder().id("42");
        Skin.SkinBuilder nameResult2 = Skin.builder().color("Color").id(1).name("Name");
        SkinBuy.SkinBuyBuilder skinResult = idResult
                .skin(nameResult2.price(new BigDecimal("2.3")).state(true).types("Types").build());
        Optional<SkinBuy> ofResult2 = Optional
                .of(skinResult.user(User.builder().id(1).lastName("Doe").name("Name").build()).build());
        when(skinBuyOutputPort.findById(Mockito.<Integer>any())).thenReturn(ofResult2);
        SkinColorUpdateResponse actualUpdateColorSkinBuyResult = skinBuyService.updateColorSkinBuy(1, "Color");
        assertEquals("000", actualUpdateColorSkinBuyResult.getCode());
        assertEquals("Color cambiado exitosamente.", actualUpdateColorSkinBuyResult.getMessage());
        assertEquals("Color", actualUpdateColorSkinBuyResult.getSkin().getColor());
        verify(skinsOutputPort).findById(Mockito.<Integer>any());
        verify(skinsOutputPort).update(Mockito.<Skin>any());
        verify(skinBuyOutputPort).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link SkinBuyService#updateColorSkinBuy(Integer, String)}
     */
    @Test
    void testUpdateColorSkinBuy2() {
        doThrow(new RuntimeException("000")).when(skinsOutputPort).update(Mockito.<Skin>any());
        Skin.SkinBuilder nameResult = Skin.builder().color("Color").id(1).name("Name");
        Optional<Skin> ofResult = Optional.of(nameResult.price(new BigDecimal("2.3")).state(true).types("Types").build());
        when(skinsOutputPort.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        SkinBuy.SkinBuyBuilder idResult = SkinBuy.builder().id("42");
        Skin.SkinBuilder nameResult2 = Skin.builder().color("Color").id(1).name("Name");
        SkinBuy.SkinBuyBuilder skinResult = idResult
                .skin(nameResult2.price(new BigDecimal("2.3")).state(true).types("Types").build());
        Optional<SkinBuy> ofResult2 = Optional
                .of(skinResult.user(User.builder().id(1).lastName("Doe").name("Name").build()).build());
        when(skinBuyOutputPort.findById(Mockito.<Integer>any())).thenReturn(ofResult2);
        assertThrows(RuntimeException.class, () -> skinBuyService.updateColorSkinBuy(1, "Color"));
        verify(skinsOutputPort).findById(Mockito.<Integer>any());
        verify(skinsOutputPort).update(Mockito.<Skin>any());
        verify(skinBuyOutputPort).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link SkinBuyService#updateColorSkinBuy(Integer, String)}
     */
    @Test
    void testUpdateColorSkinBuy3() {
        Optional<Skin> emptyResult = Optional.empty();
        when(skinsOutputPort.findById(Mockito.<Integer>any())).thenReturn(emptyResult);
        SkinBuy.SkinBuyBuilder idResult = SkinBuy.builder().id("42");
        Skin.SkinBuilder nameResult = Skin.builder().color("Color").id(1).name("Name");
        SkinBuy.SkinBuyBuilder skinResult = idResult
                .skin(nameResult.price(new BigDecimal("2.3")).state(true).types("Types").build());
        Optional<SkinBuy> ofResult = Optional
                .of(skinResult.user(User.builder().id(1).lastName("Doe").name("Name").build()).build());
        when(skinBuyOutputPort.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        assertThrows(RuntimeException.class, () -> skinBuyService.updateColorSkinBuy(1, "Color"));
        verify(skinsOutputPort).findById(Mockito.<Integer>any());
        verify(skinBuyOutputPort).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link SkinBuyService#updateColorSkinBuy(Integer, String)}
     */
    @Test
    void testUpdateColorSkinBuy4() {
        Optional<SkinBuy> emptyResult = Optional.empty();
        when(skinBuyOutputPort.findById(Mockito.<Integer>any())).thenReturn(emptyResult);
        assertThrows(RuntimeException.class, () -> skinBuyService.updateColorSkinBuy(1, "Color"));
        verify(skinBuyOutputPort).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link SkinBuyService#delete(Integer)}
     */
    @Test
    void testDelete() {
        doNothing().when(skinBuyOutputPort).delete(Mockito.<SkinBuy>any());
        SkinBuy.SkinBuyBuilder idResult = SkinBuy.builder().id("42");
        Skin.SkinBuilder nameResult = Skin.builder().color("Color").id(1).name("Name");
        SkinBuy.SkinBuyBuilder skinResult = idResult
                .skin(nameResult.price(new BigDecimal("2.3")).state(true).types("Types").build());
        Optional<SkinBuy> ofResult = Optional
                .of(skinResult.user(User.builder().id(1).lastName("Doe").name("Name").build()).build());
        when(skinBuyOutputPort.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        SuccessResponse actualDeleteResult = skinBuyService.delete(1);
        assertEquals("000", actualDeleteResult.getCode());
        assertEquals("Operación exitosa.", actualDeleteResult.getMessage());
        verify(skinBuyOutputPort).findById(Mockito.<Integer>any());
        verify(skinBuyOutputPort).delete(Mockito.<SkinBuy>any());
    }

    /**
     * Method under test: {@link SkinBuyService#delete(Integer)}
     */
    @Test
    void testDelete2() {
        doThrow(new RuntimeException("000")).when(skinBuyOutputPort).delete(Mockito.<SkinBuy>any());
        SkinBuy.SkinBuyBuilder idResult = SkinBuy.builder().id("42");
        Skin.SkinBuilder nameResult = Skin.builder().color("Color").id(1).name("Name");
        SkinBuy.SkinBuyBuilder skinResult = idResult
                .skin(nameResult.price(new BigDecimal("2.3")).state(true).types("Types").build());
        Optional<SkinBuy> ofResult = Optional
                .of(skinResult.user(User.builder().id(1).lastName("Doe").name("Name").build()).build());
        when(skinBuyOutputPort.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        assertThrows(RuntimeException.class, () -> skinBuyService.delete(1));
        verify(skinBuyOutputPort).findById(Mockito.<Integer>any());
        verify(skinBuyOutputPort).delete(Mockito.<SkinBuy>any());
    }

    /**
     * Method under test: {@link SkinBuyService#delete(Integer)}
     */
    @Test
    void testDelete3() {
        Optional<SkinBuy> emptyResult = Optional.empty();
        when(skinBuyOutputPort.findById(Mockito.<Integer>any())).thenReturn(emptyResult);
        assertThrows(RuntimeException.class, () -> skinBuyService.delete(1));
        verify(skinBuyOutputPort).findById(Mockito.<Integer>any());
    }
}

