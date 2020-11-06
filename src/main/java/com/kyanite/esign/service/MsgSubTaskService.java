package com.kyanite.esign.service;

import com.kyanite.esign.domain.MsgSubTask;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link MsgSubTask}.
 */
public interface MsgSubTaskService {

    /**
     * Save a msgSubTask.
     *
     * @param msgSubTask the entity to save.
     * @return the persisted entity.
     */
    MsgSubTask save(MsgSubTask msgSubTask);

    /**
     * Get all the msgSubTasks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MsgSubTask> findAll(Pageable pageable);


    /**
     * Get the "id" msgSubTask.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MsgSubTask> findOne(Long id);

    /**
     * Delete the "id" msgSubTask.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
