package com.kyanite.esign.service;

import com.kyanite.esign.domain.MsgTask;
import com.kyanite.esign.repository.MsgTaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MsgTask}.
 */
@Service
@Transactional
public class MsgTaskService {

    private final Logger log = LoggerFactory.getLogger(MsgTaskService.class);

    private final MsgTaskRepository msgTaskRepository;

    public MsgTaskService(MsgTaskRepository msgTaskRepository) {
        this.msgTaskRepository = msgTaskRepository;
    }

    /**
     * Save a msgTask.
     *
     * @param msgTask the entity to save.
     * @return the persisted entity.
     */
    public MsgTask save(MsgTask msgTask) {
        log.debug("Request to save MsgTask : {}", msgTask);
        return msgTaskRepository.save(msgTask);
    }

    /**
     * Get all the msgTasks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MsgTask> findAll(Pageable pageable) {
        log.debug("Request to get all MsgTasks");
        return msgTaskRepository.findAll(pageable);
    }


    /**
     * Get one msgTask by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MsgTask> findOne(Long id) {
        log.debug("Request to get MsgTask : {}", id);
        return msgTaskRepository.findById(id);
    }

    /**
     * Delete the msgTask by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MsgTask : {}", id);
        msgTaskRepository.deleteById(id);
    }
}
