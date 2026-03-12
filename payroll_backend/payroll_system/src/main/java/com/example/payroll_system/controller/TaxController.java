package com.example.payroll_system.controller;


import com.example.payroll_system.dto.TaxDTO;
import com.example.payroll_system.service.TaxService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/taxes")
public class TaxController {

    @Autowired
    private TaxService taxService;

    @GetMapping
    public List<TaxDTO> getAllTaxes() {
        return taxService.getAllTaxes();
    }

    @GetMapping("/{id}")
    public TaxDTO getTaxById(@PathVariable Long id) {
        return taxService.getTaxById(id).orElse(null);
    }

    @PostMapping
    public TaxDTO addTax(@RequestBody TaxDTO taxDTO) {
        return taxService.addTax(taxDTO);  // Pass DTO directly to the service
    }

    @PutMapping("/{id}")
    public TaxDTO updateTax(@PathVariable Long id, @RequestBody TaxDTO taxDTO) {
        return taxService.updateTax(id, taxDTO);  // Pass DTO directly to the service
    }

    @DeleteMapping("/{id}")
    public boolean deleteTax(@PathVariable Long id) {
        return taxService.deleteTax(id);
    }
}
