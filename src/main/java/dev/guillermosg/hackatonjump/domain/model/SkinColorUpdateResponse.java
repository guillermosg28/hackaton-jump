package dev.guillermosg.hackatonjump.domain.model;

import lombok.*;

import java.io.Serializable;

/**
 * SkinColorUpdateResponse model
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkinColorUpdateResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String message;
    private String code;
    private Skin skin;
}
