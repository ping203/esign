package com.kyanite.esign.web.rest;

import com.kyanite.esign.domain.ESignConfig;
import com.kyanite.esign.service.ESignConfigService;
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
 * REST controller for managing {@link com.kyanite.esign.domain.ESignConfig}.
 */
@RestController
@RequestMapping("/api")
public class ESignConfigResource {

    private final Logger log = LoggerFactory.getLogger(ESignConfigResource.class);

    private static final String ENTITY_NAME = "eSignConfig";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ESignConfigService eSignConfigService;

    public ESignConfigResource(ESignConfigService eSignConfigService) {
        this.eSignConfigService = eSignConfigService;
    }

    /**
     * {@code POST  /e-sign-configs} : Create a new eSignConfig.
     *
     * @param eSignConfig the eSignConfig to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new eSignConfig, or with status {@code 400 (Bad Request)} if the eSignConfig has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/e-sign-configs")
    public ResponseEntity<ESignConfig> createESignConfig(@RequestBody ESignConfig eSignConfig) throws URISyntaxException {
        log.debug("REST request to save ESignConfig : {}", eSignConfig);
        if (eSignConfig.getId() != null) {
            throw new BadRequestAlertException("A new eSignConfig cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ESignConfig result = eSignConfigService.save(eSignConfig);
        return ResponseEntity.created(new URI("/api/e-sign-configs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /e-sign-configs} : Updates an existing eSignConfig.
     *
     * @param eSignConfig the eSignConfig to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated eSignConfig,
     * or with status {@code 400 (Bad Request)} if the eSignConfig is not valid,
     * or with status {@code 500 (Internal Server Error)} if the eSignConfig couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/e-sign-configs")
    public ResponseEntity<ESignConfig> updateESignConfig(@RequestBody ESignConfig eSignConfig) throws URISyntaxException {
        log.debug("REST request to update ESignConfig : {}", eSignConfig);
        if (eSignConfig.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ESignConfig result = eSignConfigService.save(eSignConfig);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, eSignConfig.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /e-sign-configs} : get all the eSignConfigs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of eSignConfigs in body.
     */
    @GetMapping("/e-sign-configs")
    public ResponseEntity<List<ESignConfig>> getAllESignConfigs(Pageable pageable) {
        log.debug("REST request to get a page of ESignConfigs");
        Page<ESignConfig> page = eSignConfigService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /e-sign-configs/:id} : get the "id" eSignConfig.
     *
     * @param id the id of the eSignConfig to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the eSignConfig, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/e-sign-configs/{id}")
    public ResponseEntity<ESignConfig> getESignConfig(@PathVariable Long id) {
        log.debug("REST request to get ESignConfig : {}", id);
        Optional<ESignConfig> eSignConfig = eSignConfigService.findOne(id);
        return ResponseUtil.wrapOrNotFound(eSignConfig);
    }

    /**
     * {@code DELETE  /e-sign-configs/:id} : delete the "id" eSignConfig.
     *
     * @param id the id of the eSignConfig to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/e-sign-configs/{id}")
    public ResponseEntity<Void> deleteESignConfig(@PathVariable Long id) {
        log.debug("REST request to delete ESignConfig : {}", id);
        eSignConfigService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
