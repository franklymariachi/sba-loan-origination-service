package com.loanbud.origination.service;

import com.loanbud.origination.domain.LoanApplication;
import com.loanbud.origination.dto.LoanApplicationRequest;
import com.loanbud.origination.dto.LoanApplicationResponse;
import com.loanbud.origination.mapper.LoanApplicationMapper;
import com.loanbud.origination.repository.LoanApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoanOriginationService {

    private static final BigDecimal MIN_DSCR = new BigDecimal("1.25");
    private final LoanApplicationRepository repository;
    private final LoanApplicationMapper mapper;

    @Transactional
    public LoanApplicationResponse submitApplication(LoanApplicationRequest request) {
        LoanApplication entity = mapper.toEntity(request);
        
        // Evaluate initial SBA eligibility business rule
        boolean isEligible = entity.getDscrRatio().compareTo(MIN_DSCR) >= 0;
        entity.setStatus(isEligible ? "PREQUALIFIED" : "UNDER_REVIEW");

        LoanApplication saved = repository.save(entity);

        String notes = isEligible 
            ? "Meets SBA 7(a) minimum Debt Service Coverage Ratio threshold (>= 1.25x)."
            : "DSCR below standard threshold (1.25x). Routed to underwriting manual review.";

        return new LoanApplicationResponse(
            saved.getId(),
            saved.getBusinessName(),
            saved.getRequestedAmount(),
            saved.getLoanPurpose(),
            saved.getAnnualRevenue(),
            saved.getDscrRatio(),
            saved.getStatus(),
            isEligible,
            notes,
            saved.getCreatedAt()
        );
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "loanApplications", key = "#id")
    public LoanApplicationResponse getApplicationById(UUID id) {
        LoanApplication entity = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Loan application not found: " + id));
        return mapper.toResponse(entity);
    }

    @Transactional(readOnly = true)
    public List<LoanApplicationResponse> getAllApplications() {
        return repository.findAll().stream()
            .map(mapper::toResponse)
            .toList();
    }
}