package com.kyanite.esign.service;

import com.kyanite.esign.domain.MsgTask;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link MsgTask}.
 */
public interface MsgTaskService {

    /**
     * Save a msgTask.
     *
     * @param msgTask the entity to save.
     * @return the persisted entity.
     */
    MsgTask save(MsgTask msgTask);

    /**
     * Get all the msgTasks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MsgTask> findAll(Pageable pageable);


    /**
     * Get the "id" msgTask.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MsgTask> findOne(Long id);

    /**
     * Delete the "id" msgTask.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
