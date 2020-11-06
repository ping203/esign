package com.kyanite.esign.service.impl;

import com.kyanite.esign.service.PdfFileService;
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
public class PdfFileServiceImpl implements PdfFileService {

    private final Logger log = LoggerFactory.getLogger(PdfFileServiceImpl.class);

    private final PdfFileRepository pdfFileRepository;

    public PdfFileServiceImpl(PdfFileRepository pdfFileRepository) {
        this.pdfFileRepository = pdfFileRepository;
    }

    @Override
    public PdfFile save(PdfFile pdfFile) {
        log.debug("Request to save PdfFile : {}", pdfFile);
        return pdfFileRepository.save(pdfFile);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PdfFile> findAll(Pageable pageable) {
        log.debug("Request to get all PdfFiles");
        return pdfFileRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PdfFile> findOne(Long id) {
        log.debug("Request to get PdfFile : {}", id);
        return pdfFileRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PdfFile : {}", id);
        pdfFileRepository.deleteById(id);
    }
}
