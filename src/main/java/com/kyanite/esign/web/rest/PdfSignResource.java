package com.kyanite.esign.web.rest;

import com.kyanite.esign.domain.PdfSign;
import com.kyanite.esign.service.PdfSignService;
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
 * REST controller for managing {@link com.kyanite.esign.domain.PdfSign}.
 */
@RestController
@RequestMapping("/api")
public class PdfSignResource {

    private final Logger log = LoggerFactory.getLogger(PdfSignResource.class);

    private static final String ENTITY_NAME = "pdfSign";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PdfSignService pdfSignService;

    public PdfSignResource(PdfSignService pdfSignService) {
        this.pdfSignService = pdfSignService;
    }

    /**
     * {@code POST  /pdf-signs} : Create a new pdfSign.
     *
     * @param pdfSign the pdfSign to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pdfSign, or with status {@code 400 (Bad Request)} if the pdfSign has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pdf-signs")
    public ResponseEntity<PdfSign> createPdfSign(@RequestBody PdfSign pdfSign) throws URISyntaxException {
        log.debug("REST request to save PdfSign : {}", pdfSign);
        if (pdfSign.getId() != null) {
            throw new BadRequestAlertException("A new pdfSign cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PdfSign result = pdfSignService.save(pdfSign);
        return ResponseEntity.created(new URI("/api/pdf-signs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pdf-signs} : Updates an existing pdfSign.
     *
     * @param pdfSign the pdfSign to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pdfSign,
     * or with status {@code 400 (Bad Request)} if the pdfSign is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pdfSign couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pdf-signs")
    public ResponseEntity<PdfSign> updatePdfSign(@RequestBody PdfSign pdfSign) throws URISyntaxException {
        log.debug("REST request to update PdfSign : {}", pdfSign);
        if (pdfSign.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PdfSign result = pdfSignService.save(pdfSign);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pdfSign.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pdf-signs} : get all the pdfSigns.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pdfSigns in body.
     */
    @GetMapping("/pdf-signs")
    public ResponseEntity<List<PdfSign>> getAllPdfSigns(Pageable pageable) {
        log.debug("REST request to get a page of PdfSigns");
        Page<PdfSign> page = pdfSignService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pdf-signs/:id} : get the "id" pdfSign.
     *
     * @param id the id of the pdfSign to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pdfSign, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pdf-signs/{id}")
    public ResponseEntity<PdfSign> getPdfSign(@PathVariable Long id) {
        log.debug("REST request to get PdfSign : {}", id);
        Optional<PdfSign> pdfSign = pdfSignService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pdfSign);
    }

    /**
     * {@code DELETE  /pdf-signs/:id} : delete the "id" pdfSign.
     *
     * @param id the id of the pdfSign to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pdf-signs/{id}")
    public ResponseEntity<Void> deletePdfSign(@PathVariable Long id) {
        log.debug("REST request to delete PdfSign : {}", id);
        pdfSignService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
