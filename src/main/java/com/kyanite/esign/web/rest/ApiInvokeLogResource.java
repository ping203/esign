package com.kyanite.esign.web.rest;

import com.kyanite.esign.domain.ApiInvokeLog;
import com.kyanite.esign.service.ApiInvokeLogService;
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
 * REST controller for managing {@link com.kyanite.esign.domain.ApiInvokeLog}.
 */
@RestController
@RequestMapping("/api")
public class ApiInvokeLogResource {

    private final Logger log = LoggerFactory.getLogger(ApiInvokeLogResource.class);

    private static final String ENTITY_NAME = "apiInvokeLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ApiInvokeLogService apiInvokeLogService;

    public ApiInvokeLogResource(ApiInvokeLogService apiInvokeLogService) {
        this.apiInvokeLogService = apiInvokeLogService;
    }

    /**
     * {@code POST  /api-invoke-logs} : Create a new apiInvokeLog.
     *
     * @param apiInvokeLog the apiInvokeLog to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new apiInvokeLog, or with status {@code 400 (Bad Request)} if the apiInvokeLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/api-invoke-logs")
    public ResponseEntity<ApiInvokeLog> createApiInvokeLog(@RequestBody ApiInvokeLog apiInvokeLog) throws URISyntaxException {
        log.debug("REST request to save ApiInvokeLog : {}", apiInvokeLog);
        if (apiInvokeLog.getId() != null) {
            throw new BadRequestAlertException("A new apiInvokeLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ApiInvokeLog result = apiInvokeLogService.save(apiInvokeLog);
        return ResponseEntity.created(new URI("/api/api-invoke-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /api-invoke-logs} : Updates an existing apiInvokeLog.
     *
     * @param apiInvokeLog the apiInvokeLog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated apiInvokeLog,
     * or with status {@code 400 (Bad Request)} if the apiInvokeLog is not valid,
     * or with status {@code 500 (Internal Server Error)} if the apiInvokeLog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/api-invoke-logs")
    public ResponseEntity<ApiInvokeLog> updateApiInvokeLog(@RequestBody ApiInvokeLog apiInvokeLog) throws URISyntaxException {
        log.debug("REST request to update ApiInvokeLog : {}", apiInvokeLog);
        if (apiInvokeLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ApiInvokeLog result = apiInvokeLogService.save(apiInvokeLog);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, apiInvokeLog.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /api-invoke-logs} : get all the apiInvokeLogs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of apiInvokeLogs in body.
     */
    @GetMapping("/api-invoke-logs")
    public ResponseEntity<List<ApiInvokeLog>> getAllApiInvokeLogs(Pageable pageable) {
        log.debug("REST request to get a page of ApiInvokeLogs");
        Page<ApiInvokeLog> page = apiInvokeLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /api-invoke-logs/:id} : get the "id" apiInvokeLog.
     *
     * @param id the id of the apiInvokeLog to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the apiInvokeLog, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/api-invoke-logs/{id}")
    public ResponseEntity<ApiInvokeLog> getApiInvokeLog(@PathVariable Long id) {
        log.debug("REST request to get ApiInvokeLog : {}", id);
        Optional<ApiInvokeLog> apiInvokeLog = apiInvokeLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(apiInvokeLog);
    }

    /**
     * {@code DELETE  /api-invoke-logs/:id} : delete the "id" apiInvokeLog.
     *
     * @param id the id of the apiInvokeLog to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/api-invoke-logs/{id}")
    public ResponseEntity<Void> deleteApiInvokeLog(@PathVariable Long id) {
        log.debug("REST request to delete ApiInvokeLog : {}", id);
        apiInvokeLogService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
