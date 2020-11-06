package com.kyanite.esign.service;

import com.kyanite.esign.domain.MsgSubTask;
import com.kyanite.esign.repository.MsgSubTaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MsgSubTask}.
 */
@Service
@Transactional
public class MsgSubTaskService {

    private final Logger log = LoggerFactory.getLogger(MsgSubTaskService.class);

    private final MsgSubTaskRepository msgSubTaskRepository;

    public MsgSubTaskService(MsgSubTaskRepository msgSubTaskRepository) {
        this.msgSubTaskRepository = msgSubTaskRepository;
    }

    /**
     * Save a msgSubTask.
     *
     * @param msgSubTask the entity to save.
     * @return the persisted entity.
     */
    public MsgSubTask save(MsgSubTask msgSubTask) {
        log.debug("Request to save MsgSubTask : {}", msgSubTask);
        return msgSubTaskRepository.save(msgSubTask);
    }

    /**
     * Get all the msgSubTasks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MsgSubTask> findAll(Pageable pageable) {
        log.debug("Request to get all MsgSubTasks");
        return msgSubTaskRepository.findAll(pageable);
    }


    /**
     * Get one msgSubTask by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MsgSubTask> findOne(Long id) {
        log.debug("Request to get MsgSubTask : {}", id);
        return msgSubTaskRepository.findById(id);
    }

    /**
     * Delete the msgSubTask by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MsgSubTask : {}", id);
        msgSubTaskRepository.deleteById(id);
    }
}
