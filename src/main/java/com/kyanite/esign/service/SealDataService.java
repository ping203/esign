package com.kyanite.esign.service;

import com.kyanite.esign.domain.SealData;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link SealData}.
 */
public interface SealDataService {

    /**
     * Save a sealData.
     *
     * @param sealData the entity to save.
     * @return the persisted entity.
     */
    SealData save(SealData sealData);

    /**
     * Get all the sealData.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SealData> findAll(Pageable pageable);


    /**
     * Get the "id" sealData.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SealData> findOne(Long id);

    /**
     * Delete the "id" sealData.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
