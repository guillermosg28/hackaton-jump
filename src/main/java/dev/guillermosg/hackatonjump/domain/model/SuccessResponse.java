package dev.guillermosg.hackatonjump.domain.model;

import lombok.*;

import java.io.Serializable;

/**
 * SuccessResponse model
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String message;
    private String code;
}
