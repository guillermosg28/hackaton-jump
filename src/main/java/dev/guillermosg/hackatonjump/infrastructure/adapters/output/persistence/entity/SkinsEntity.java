package dev.guillermosg.hackatonjump.infrastructure.adapters.output.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The type Skins entity.
 */

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "skins")
public class SkinsEntity implements Serializable {

    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "types")
    private String types;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "color")
    private String color;

    @Column(name = "state")
    private Boolean state;



}
