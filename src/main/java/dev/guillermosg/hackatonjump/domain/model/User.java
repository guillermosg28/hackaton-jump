package dev.guillermosg.hackatonjump.domain.model;

import lombok.*;

import java.io.Serializable;

/**
 * The type User.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String lastName;
}
