package dev.guillermosg.hackatonjump.domain.model;


import lombok.*;

import java.util.List;

/**
 * The type User skin response.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSkinResponse {

    List<SkinBuy> skinBuys;
}
