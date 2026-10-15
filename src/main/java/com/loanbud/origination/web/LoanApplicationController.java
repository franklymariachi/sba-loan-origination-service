package com.loanbud.origination.web;

import com.loanbud.origination.dto.LoanApplicationRequest;
import com.loanbud.origination.dto.LoanApplicationResponse;
import com.loanbud.origination.service.LoanOriginationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/loan-applications")
@RequiredArgsConstructor
@Tag(name = "Loan Origination Engine", description = "SBA 7(a) borrower intake & eligibility workflow endpoints")
public class LoanApplicationController {

    private final LoanOriginationService service;

    @PostMapping
    @Operation(summary = "Submit a new small business loan application", description = "Evaluates borrower intake metrics and determines SBA prequalification status.")
    public ResponseEntity<LoanApplicationResponse> submitApplication(@Valid @RequestBody LoanApplicationRequest request) {
        LoanApplicationResponse response = service.submitApplication(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get loan application status by ID")
    public ResponseEntity<LoanApplicationResponse> getApplicationById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getApplicationById(id));
    }

    @GetMapping
    @Operation(summary = "List all loan applications")
    public ResponseEntity<List<LoanApplicationResponse>> getAllApplications() {
        return ResponseEntity.ok(service.getAllApplications());
    }
}