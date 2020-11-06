package com.kyanite.esign.service.impl;

import com.kyanite.esign.service.PdfSignService;
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
public class PdfSignServiceImpl implements PdfSignService {

    private final Logger log = LoggerFactory.getLogger(PdfSignServiceImpl.class);

    private final PdfSignRepository pdfSignRepository;

    public PdfSignServiceImpl(PdfSignRepository pdfSignRepository) {
        this.pdfSignRepository = pdfSignRepository;
    }

    @Override
    public PdfSign save(PdfSign pdfSign) {
        log.debug("Request to save PdfSign : {}", pdfSign);
        return pdfSignRepository.save(pdfSign);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PdfSign> findAll(Pageable pageable) {
        log.debug("Request to get all PdfSigns");
        return pdfSignRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PdfSign> findOne(Long id) {
        log.debug("Request to get PdfSign : {}", id);
        return pdfSignRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PdfSign : {}", id);
        pdfSignRepository.deleteById(id);
    }
}
