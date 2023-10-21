package dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence;

import dev.guillermosg.hackatonjump.application.ports.output.SkinBuyOutputPort;
import dev.guillermosg.hackatonjump.domain.model.Skin;
import dev.guillermosg.hackatonjump.domain.model.SkinBuy;
import dev.guillermosg.hackatonjump.domain.model.User;
import dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.mapper.SkinBuyPersistenceMapper;
import dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.repository.SkinBuyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Skin buy persistence adapter.
 */

@Component
@RequiredArgsConstructor
public class SkinBuyPersistenceAdapter implements SkinBuyOutputPort {

    private final SkinBuyRepository skinBuyRepository;
    private final SkinBuyPersistenceMapper skinBuyPersistenceMapper;

    /**
     * @param skin the skin
     * @param user the user
     */
    @Override
    public void save(Skin skin, User user) {

        SkinBuy skinBuy = new SkinBuy();
        skinBuy.setSkin(skin);
        skinBuy.setUser(user);

        skinBuyRepository.save(skinBuyPersistenceMapper.toSkinBuyEntity(skinBuy));
    }

    /**
     * @param userId the user
     * @return the list
     */
    @Override
    public List<SkinBuy> findByUser(Integer userId) {
        return skinBuyRepository.findAllByUserId(Long.valueOf(userId.longValue())).stream()
                .map(skinBuyPersistenceMapper::toUserSkinBuys)
                .collect(Collectors.toList());
    }

    /**
     * @param skinBuyId the skin buy id
     * @return the skin buy
     */
    @Override
    public Optional<SkinBuy> findById(Integer skinBuyId) {
        return skinBuyRepository.findById(Long.valueOf(skinBuyId.longValue()))
                .map(skinBuyPersistenceMapper::toUserSkinBuys);
    }

    /**
     * @param skinBuy
     */
    @Override
    public void delete(SkinBuy skinBuy) {
        skinBuyRepository.delete(skinBuyPersistenceMapper.toSkinBuyEntity(skinBuy));
    }
}
