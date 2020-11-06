package com.kyanite.esign.web.rest;

import com.kyanite.esign.domain.SealData;
import com.kyanite.esign.service.SealDataService;
import com.kyanite.esign.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.kyanite.esign.domain.SealData}.
 */
@RestController
@RequestMapping("/api")
public class SealDataResource {

    private final Logger log = LoggerFactory.getLogger(SealDataResource.class);

    private static final String ENTITY_NAME = "sealData";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SealDataService sealDataService;

    public SealDataResource(SealDataService sealDataService) {
        this.sealDataService = sealDataService;
    }

    /**
     * {@code POST  /seal-data} : Create a new sealData.
     *
     * @param sealData the sealData to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sealData, or with status {@code 400 (Bad Request)} if the sealData has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/seal-data")
    public ResponseEntity<SealData> createSealData(@RequestBody SealData sealData) throws URISyntaxException {
        log.debug("REST request to save SealData : {}", sealData);
        if (sealData.getId() != null) {
            throw new BadRequestAlertException("A new sealData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SealData result = sealDataService.save(sealData);
        return ResponseEntity.created(new URI("/api/seal-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /seal-data} : Updates an existing sealData.
     *
     * @param sealData the sealData to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sealData,
     * or with status {@code 400 (Bad Request)} if the sealData is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sealData couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/seal-data")
    public ResponseEntity<SealData> updateSealData(@RequestBody SealData sealData) throws URISyntaxException {
        log.debug("REST request to update SealData : {}", sealData);
        if (sealData.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SealData result = sealDataService.save(sealData);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sealData.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /seal-data} : get all the sealData.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sealData in body.
     */
    @GetMapping("/seal-data")
    public ResponseEntity<List<SealData>> getAllSealData(Pageable pageable) {
        log.debug("REST request to get a page of SealData");
        Page<SealData> page = sealDataService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /seal-data/:id} : get the "id" sealData.
     *
     * @param id the id of the sealData to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sealData, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/seal-data/{id}")
    public ResponseEntity<SealData> getSealData(@PathVariable Long id) {
        log.debug("REST request to get SealData : {}", id);
        Optional<SealData> sealData = sealDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sealData);
    }

    /**
     * {@code DELETE  /seal-data/:id} : delete the "id" sealData.
     *
     * @param id the id of the sealData to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/seal-data/{id}")
    public ResponseEntity<Void> deleteSealData(@PathVariable Long id) {
        log.debug("REST request to delete SealData : {}", id);
        sealDataService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
