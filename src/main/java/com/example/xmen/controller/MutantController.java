package com.example.xmen.controller;

import com.example.xmen.dao.Stat;
import com.example.xmen.dto.DnaRequestBody;
import com.example.xmen.service.MutantService;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MutantController {
    private final MutantService mutantService;
    private static final Logger logger = LogManager.getLogger(MutantController.class);

    public MutantController(MutantService mutantService) {
        this.mutantService = mutantService;
    }

    @PostMapping("/mutant")
    @ApiOperation(value = "Creation of a meeting")
    public ResponseEntity<Void> isMutant(@RequestBody DnaRequestBody dnaRequestBody) {
        logger.info(String.format("Analyzing dna= %s", dnaRequestBody.toString()));
        boolean result = mutantService.isMutant(dnaRequestBody);
        logger.info(String.format("The result was %s", result));
        return result ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/stats")
    @ResponseBody
    @ApiOperation(value = "Creation of a meeting")
    public ResponseEntity<Stat> stats() {
        logger.info("Getting statistics");
        Stat stat = mutantService.getStats();
        logger.info("Statistics returned");
        return ResponseEntity.ok(stat);
    }
}
