package com.kyanite.esign.service;

import com.kyanite.esign.domain.PdfSign;
import com.kyanite.esign.repository.PdfSignRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link PdfSign}.
 */
@Service
@Transactional
public class PdfSignService {

    private final Logger log = LoggerFactory.getLogger(PdfSignService.class);

    private final PdfSignRepository pdfSignRepository;

    public PdfSignService(PdfSignRepository pdfSignRepository) {
        this.pdfSignRepository = pdfSignRepository;
    }

    /**
     * Save a pdfSign.
     *
     * @param pdfSign the entity to save.
     * @return the persisted entity.
     */
    public PdfSign save(PdfSign pdfSign) {
        log.debug("Request to save PdfSign : {}", pdfSign);
        return pdfSignRepository.save(pdfSign);
    }

    /**
     * Get all the pdfSigns.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PdfSign> findAll(Pageable pageable) {
        log.debug("Request to get all PdfSigns");
        return pdfSignRepository.findAll(pageable);
    }


    /**
     * Get one pdfSign by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PdfSign> findOne(Long id) {
        log.debug("Request to get PdfSign : {}", id);
        return pdfSignRepository.findById(id);
    }

    /**
     * Delete the pdfSign by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PdfSign : {}", id);
        pdfSignRepository.deleteById(id);
    }
}
