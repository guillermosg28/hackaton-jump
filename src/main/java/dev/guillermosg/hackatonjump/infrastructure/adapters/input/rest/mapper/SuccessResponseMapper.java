package dev.guillermosg.hackatonjump.infrastructure.adapters.input.rest.mapper;

import dev.guillermosg.hackatonjump.domain.model.SuccessResponse;
import dev.guillermosg.hackatonjump.infrastructure.adapters.input.rest.data.SuccessResponseDto;
import org.mapstruct.Mapper;

/**
 * The interface SuccessResponseMapper
 */
@Mapper
public interface SuccessResponseMapper {

    /**
     * To dto success response dto.
     * @param successResponse
     * @return SuccessResponseDto
     */
    SuccessResponseDto successResponseToDto(SuccessResponse successResponse);
}
