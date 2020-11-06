package com.kyanite.esign.service.impl;

import com.kyanite.esign.service.SealDataService;
import com.kyanite.esign.domain.SealData;
import com.kyanite.esign.repository.SealDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SealData}.
 */
@Service
@Transactional
public class SealDataServiceImpl implements SealDataService {

    private final Logger log = LoggerFactory.getLogger(SealDataServiceImpl.class);

    private final SealDataRepository sealDataRepository;

    public SealDataServiceImpl(SealDataRepository sealDataRepository) {
        this.sealDataRepository = sealDataRepository;
    }

    @Override
    public SealData save(SealData sealData) {
        log.debug("Request to save SealData : {}", sealData);
        return sealDataRepository.save(sealData);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SealData> findAll(Pageable pageable) {
        log.debug("Request to get all SealData");
        return sealDataRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<SealData> findOne(Long id) {
        log.debug("Request to get SealData : {}", id);
        return sealDataRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SealData : {}", id);
        sealDataRepository.deleteById(id);
    }
}
