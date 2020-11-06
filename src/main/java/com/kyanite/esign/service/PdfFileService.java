package com.kyanite.esign.service;

import com.kyanite.esign.domain.PdfFile;
import com.kyanite.esign.repository.PdfFileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link PdfFile}.
 */
@Service
@Transactional
public class PdfFileService {

    private final Logger log = LoggerFactory.getLogger(PdfFileService.class);

    private final PdfFileRepository pdfFileRepository;

    public PdfFileService(PdfFileRepository pdfFileRepository) {
        this.pdfFileRepository = pdfFileRepository;
    }

    /**
     * Save a pdfFile.
     *
     * @param pdfFile the entity to save.
     * @return the persisted entity.
     */
    public PdfFile save(PdfFile pdfFile) {
        log.debug("Request to save PdfFile : {}", pdfFile);
        return pdfFileRepository.save(pdfFile);
    }

    /**
     * Get all the pdfFiles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PdfFile> findAll(Pageable pageable) {
        log.debug("Request to get all PdfFiles");
        return pdfFileRepository.findAll(pageable);
    }


    /**
     * Get one pdfFile by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PdfFile> findOne(Long id) {
        log.debug("Request to get PdfFile : {}", id);
        return pdfFileRepository.findById(id);
    }

    /**
     * Delete the pdfFile by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PdfFile : {}", id);
        pdfFileRepository.deleteById(id);
    }
}
