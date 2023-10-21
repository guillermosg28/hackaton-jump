package dev.guillermosg.hackatonjump.domain.model;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The model Skin.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Skin implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String types;
    private BigDecimal price;
    private String color;
    private Boolean state;

}
