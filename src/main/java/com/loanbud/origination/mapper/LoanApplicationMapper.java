package com.loanbud.origination.mapper;

import com.loanbud.origination.domain.LoanApplication;
import com.loanbud.origination.dto.LoanApplicationRequest;
import com.loanbud.origination.dto.LoanApplicationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoanApplicationMapper {

    LoanApplication toEntity(LoanApplicationRequest request);

    @Mapping(target = "prequalified", ignore = true)
    @Mapping(target = "eligibilityNotes", ignore = true)
    LoanApplicationResponse toResponse(LoanApplication entity);
}