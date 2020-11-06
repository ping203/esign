package com.kyanite.esign.service;

import com.kyanite.esign.domain.ESignConfig;
import com.kyanite.esign.repository.ESignConfigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ESignConfig}.
 */
@Service
@Transactional
public class ESignConfigService {

    private final Logger log = LoggerFactory.getLogger(ESignConfigService.class);

    private final ESignConfigRepository eSignConfigRepository;

    public ESignConfigService(ESignConfigRepository eSignConfigRepository) {
        this.eSignConfigRepository = eSignConfigRepository;
    }

    /**
     * Save a eSignConfig.
     *
     * @param eSignConfig the entity to save.
     * @return the persisted entity.
     */
    public ESignConfig save(ESignConfig eSignConfig) {
        log.debug("Request to save ESignConfig : {}", eSignConfig);
        return eSignConfigRepository.save(eSignConfig);
    }

    /**
     * Get all the eSignConfigs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ESignConfig> findAll(Pageable pageable) {
        log.debug("Request to get all ESignConfigs");
        return eSignConfigRepository.findAll(pageable);
    }


    /**
     * Get one eSignConfig by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ESignConfig> findOne(Long id) {
        log.debug("Request to get ESignConfig : {}", id);
        return eSignConfigRepository.findById(id);
    }

    /**
     * Delete the eSignConfig by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ESignConfig : {}", id);
        eSignConfigRepository.deleteById(id);
    }
}
