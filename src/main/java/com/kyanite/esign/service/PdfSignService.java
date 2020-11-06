package com.kyanite.esign.service;

import com.kyanite.esign.domain.PdfSign;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link PdfSign}.
 */
public interface PdfSignService {

    /**
     * Save a pdfSign.
     *
     * @param pdfSign the entity to save.
     * @return the persisted entity.
     */
    PdfSign save(PdfSign pdfSign);

    /**
     * Get all the pdfSigns.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PdfSign> findAll(Pageable pageable);


    /**
     * Get the "id" pdfSign.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PdfSign> findOne(Long id);

    /**
     * Delete the "id" pdfSign.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
