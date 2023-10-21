package dev.guillermosg.hackatonjump.domain.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * SkinResponse model
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkinResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Skin> skins;
}
