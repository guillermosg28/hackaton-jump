package dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The type Skin buy entity.
 */

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "skin_buys")
public class SkinBuyEntity implements Serializable {

    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="skin_id", nullable=false)
    private SkinsEntity skin;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity user;
}
