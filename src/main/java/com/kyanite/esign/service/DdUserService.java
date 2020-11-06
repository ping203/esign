package com.kyanite.esign.service;

import com.kyanite.esign.domain.DdUser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link DdUser}.
 */
public interface DdUserService {

    /**
     * Save a ddUser.
     *
     * @param ddUser the entity to save.
     * @return the persisted entity.
     */
    DdUser save(DdUser ddUser);

    /**
     * Get all the ddUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DdUser> findAll(Pageable pageable);


    /**
     * Get the "id" ddUser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DdUser> findOne(Long id);

    /**
     * Delete the "id" ddUser.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
