package com.kyanite.esign.service;

import com.kyanite.esign.domain.ESignConfig;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ESignConfig}.
 */
public interface ESignConfigService {

    /**
     * Save a eSignConfig.
     *
     * @param eSignConfig the entity to save.
     * @return the persisted entity.
     */
    ESignConfig save(ESignConfig eSignConfig);

    /**
     * Get all the eSignConfigs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ESignConfig> findAll(Pageable pageable);


    /**
     * Get the "id" eSignConfig.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ESignConfig> findOne(Long id);

    /**
     * Delete the "id" eSignConfig.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
