package com.loanbud.origination.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record LoanApplicationRequest(
    @NotBlank(message = "Business name is required")
    String businessName,

    @NotNull(message = "Requested amount is required")
    @DecimalMin(value = "25000.00", message = "Minimum SBA loan request is $25,000")
    @DecimalMax(value = "5000000.00", message = "Maximum SBA 7(a) loan request is $5,000,000")
    BigDecimal requestedAmount,

    @NotBlank(message = "Loan purpose is required")
    String loanPurpose,

    @NotNull(message = "Annual revenue is required")
    @Positive(message = "Annual revenue must be positive")
    BigDecimal annualRevenue,

    @NotNull(message = "Debt Service Coverage Ratio (DSCR) is required")
    @DecimalMin(value = "1.00", message = "Minimum acceptable DSCR is 1.00")
    BigDecimal dscrRatio
) {}