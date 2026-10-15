package com.loanbud.origination.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record LoanApplicationResponse(
    UUID id,
    String businessName,
    BigDecimal requestedAmount,
    String loanPurpose,
    BigDecimal annualRevenue,
    BigDecimal dscrRatio,
    String status,
    boolean prequalified,
    String eligibilityNotes,
    LocalDateTime createdAt
) {}