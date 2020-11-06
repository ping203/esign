package com.kyanite.esign.service;

import com.kyanite.esign.domain.PdfFile;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link PdfFile}.
 */
public interface PdfFileService {

    /**
     * Save a pdfFile.
     *
     * @param pdfFile the entity to save.
     * @return the persisted entity.
     */
    PdfFile save(PdfFile pdfFile);

    /**
     * Get all the pdfFiles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PdfFile> findAll(Pageable pageable);


    /**
     * Get the "id" pdfFile.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PdfFile> findOne(Long id);

    /**
     * Delete the "id" pdfFile.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
