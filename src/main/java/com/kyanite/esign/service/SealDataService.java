package com.kyanite.esign.service;

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
public class SealDataService {

    private final Logger log = LoggerFactory.getLogger(SealDataService.class);

    private final SealDataRepository sealDataRepository;

    public SealDataService(SealDataRepository sealDataRepository) {
        this.sealDataRepository = sealDataRepository;
    }

    /**
     * Save a sealData.
     *
     * @param sealData the entity to save.
     * @return the persisted entity.
     */
    public SealData save(SealData sealData) {
        log.debug("Request to save SealData : {}", sealData);
        return sealDataRepository.save(sealData);
    }

    /**
     * Get all the sealData.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SealData> findAll(Pageable pageable) {
        log.debug("Request to get all SealData");
        return sealDataRepository.findAll(pageable);
    }


    /**
     * Get one sealData by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SealData> findOne(Long id) {
        log.debug("Request to get SealData : {}", id);
        return sealDataRepository.findById(id);
    }

    /**
     * Delete the sealData by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SealData : {}", id);
        sealDataRepository.deleteById(id);
    }
}
