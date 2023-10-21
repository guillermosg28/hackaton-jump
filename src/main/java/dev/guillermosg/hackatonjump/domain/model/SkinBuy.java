package dev.guillermosg.hackatonjump.domain.model;


import lombok.*;

import java.io.Serializable;

/**
 * The model Skin.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkinBuy implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private Skin skin;
    private User user;
}
