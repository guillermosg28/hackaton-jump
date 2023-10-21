package dev.guillermosg.hackatonjump.infrastructure.adapters.input.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.guillermosg.hackatonjump.application.ports.input.SkinBuyUseCase;
import dev.guillermosg.hackatonjump.application.ports.input.SkinsUseCase;
import dev.guillermosg.hackatonjump.domain.model.Skin;
import dev.guillermosg.hackatonjump.domain.model.SkinColorUpdateResponse;
import dev.guillermosg.hackatonjump.domain.model.SkinResponse;
import dev.guillermosg.hackatonjump.domain.model.SuccessResponse;
import dev.guillermosg.hackatonjump.domain.model.UserSkinResponse;
import dev.guillermosg.hackatonjump.infrastructure.adapters.input.rest.data.SkinBuyDto;
import dev.guillermosg.hackatonjump.infrastructure.adapters.input.rest.data.SkinColorUpdateRequestDto;
import dev.guillermosg.hackatonjump.infrastructure.adapters.input.rest.data.SkinColorUpdateResponseDto;
import dev.guillermosg.hackatonjump.infrastructure.adapters.input.rest.data.SkinDto;
import dev.guillermosg.hackatonjump.infrastructure.adapters.input.rest.data.SkinResponseDto;
import dev.guillermosg.hackatonjump.infrastructure.adapters.input.rest.data.SuccessResponseDto;
import dev.guillermosg.hackatonjump.infrastructure.adapters.input.rest.data.UserSkinResponseDto;
import dev.guillermosg.hackatonjump.infrastructure.adapters.input.rest.mapper.SkinsRestMapper;
import dev.guillermosg.hackatonjump.infrastructure.adapters.input.rest.mapper.SuccessResponseMapper;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SkinsRestAdapter.class})
@ExtendWith(SpringExtension.class)
class SkinsRestAdapterTest {
    @MockBean
    private SkinBuyUseCase skinBuyUseCase;

    @Autowired
    private SkinsRestAdapter skinsRestAdapter;

    @MockBean
    private SkinsRestMapper skinsRestMapper;

    @MockBean
    private SkinsUseCase skinsUseCase;

    @MockBean
    private SuccessResponseMapper successResponseMapper;

    /**
     * Method under test: {@link SkinsRestAdapter#_listSkins()}
     */
    @Test
    void test_listSkins() {
        when(skinsRestMapper.skinResponseToDto(Mockito.<SkinResponse>any())).thenReturn(new SkinResponseDto());
        SkinResponse.SkinResponseBuilder builderResult = SkinResponse.builder();
        when(skinsUseCase.listSkins()).thenReturn(builderResult.skins(new ArrayList<>()).build());
        ResponseEntity<SkinResponseDto> actual_listSkinsResult = skinsRestAdapter._listSkins();
        assertTrue(actual_listSkinsResult.hasBody());
        assertTrue(actual_listSkinsResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actual_listSkinsResult.getStatusCode());
        verify(skinsRestMapper).skinResponseToDto(Mockito.<SkinResponse>any());
        verify(skinsUseCase).listSkins();
    }

    /**
     * Method under test: {@link SkinsRestAdapter#_buySkin(SkinBuyDto)}
     */
    @Test
    void test_buySkin() {
        when(successResponseMapper.successResponseToDto(Mockito.<SuccessResponse>any()))
                .thenReturn(new SuccessResponseDto());
        when(skinBuyUseCase.buySkin(Mockito.<Integer>any(), Mockito.<Integer>any()))
                .thenReturn(SuccessResponse.builder().code("Code").message("Not all who wander are lost").build());
        ResponseEntity<SuccessResponseDto> actual_buySkinResult = skinsRestAdapter._buySkin(new SkinBuyDto());
        assertTrue(actual_buySkinResult.hasBody());
        assertTrue(actual_buySkinResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actual_buySkinResult.getStatusCode());
        verify(successResponseMapper).successResponseToDto(Mockito.<SuccessResponse>any());
        verify(skinBuyUseCase).buySkin(Mockito.<Integer>any(), Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link SkinsRestAdapter#_listMySkins(Integer)}
     */
    @Test
    void test_listMySkins() {
        when(skinsRestMapper.userSkinResponseToDto(Mockito.<UserSkinResponse>any()))
                .thenReturn(new UserSkinResponseDto());
        UserSkinResponse.UserSkinResponseBuilder builderResult = UserSkinResponse.builder();
        when(skinBuyUseCase.userSkins(Mockito.<Integer>any()))
                .thenReturn(builderResult.skinBuys(new ArrayList<>()).build());
        ResponseEntity<UserSkinResponseDto> actual_listMySkinsResult = skinsRestAdapter._listMySkins(1);
        assertTrue(actual_listMySkinsResult.hasBody());
        assertTrue(actual_listMySkinsResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actual_listMySkinsResult.getStatusCode());
        verify(skinsRestMapper).userSkinResponseToDto(Mockito.<UserSkinResponse>any());
        verify(skinBuyUseCase).userSkins(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link SkinsRestAdapter#_updateColorSkinBuy(SkinColorUpdateRequestDto)}
     */
    @Test
    void test_updateColorSkinBuy() {
        when(skinsRestMapper.skinColorUpdateResponseToDto(Mockito.<SkinColorUpdateResponse>any()))
                .thenReturn(new SkinColorUpdateResponseDto());
        SkinColorUpdateResponse.SkinColorUpdateResponseBuilder messageResult = SkinColorUpdateResponse.builder()
                .code("Code")
                .message("Not all who wander are lost");
        Skin.SkinBuilder nameResult = Skin.builder().color("Color").id(1).name("Name");
        when(skinBuyUseCase.updateColorSkinBuy(Mockito.<Integer>any(), Mockito.<String>any())).thenReturn(
                messageResult.skin(nameResult.price(new BigDecimal("2.3")).state(true).types("Types").build()).build());
        ResponseEntity<SkinColorUpdateResponseDto> actual_updateColorSkinBuyResult = skinsRestAdapter
                ._updateColorSkinBuy(new SkinColorUpdateRequestDto());
        assertTrue(actual_updateColorSkinBuyResult.hasBody());
        assertTrue(actual_updateColorSkinBuyResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actual_updateColorSkinBuyResult.getStatusCode());
        verify(skinsRestMapper).skinColorUpdateResponseToDto(Mockito.<SkinColorUpdateResponse>any());
        verify(skinBuyUseCase).updateColorSkinBuy(Mockito.<Integer>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link SkinsRestAdapter#_deleteSkin(Integer)}
     */
    @Test
    void test_deleteSkin() {
        when(successResponseMapper.successResponseToDto(Mockito.<SuccessResponse>any()))
                .thenReturn(new SuccessResponseDto());
        when(skinBuyUseCase.delete(Mockito.<Integer>any()))
                .thenReturn(SuccessResponse.builder().code("Code").message("Not all who wander are lost").build());
        ResponseEntity<SuccessResponseDto> actual_deleteSkinResult = skinsRestAdapter._deleteSkin(1);
        assertTrue(actual_deleteSkinResult.hasBody());
        assertTrue(actual_deleteSkinResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actual_deleteSkinResult.getStatusCode());
        verify(successResponseMapper).successResponseToDto(Mockito.<SuccessResponse>any());
        verify(skinBuyUseCase).delete(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link SkinsRestAdapter#_getSkin(Integer)}
     */
    @Test
    void test_getSkin() {
        when(skinsRestMapper.skinToDto(Mockito.<Skin>any())).thenReturn(new SkinDto());
        Skin.SkinBuilder nameResult = Skin.builder().color("Color").id(1).name("Name");
        when(skinsUseCase.findById(Mockito.<Integer>any()))
                .thenReturn(nameResult.price(new BigDecimal("2.3")).state(true).types("Types").build());
        ResponseEntity<SkinDto> actual_getSkinResult = skinsRestAdapter._getSkin(1);
        assertTrue(actual_getSkinResult.hasBody());
        assertTrue(actual_getSkinResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actual_getSkinResult.getStatusCode());
        verify(skinsRestMapper).skinToDto(Mockito.<Skin>any());
        verify(skinsUseCase).findById(Mockito.<Integer>any());
    }
}

