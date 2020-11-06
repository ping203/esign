package com.kyanite.esign.service.impl;

import com.kyanite.esign.service.MsgSubTaskService;
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
public class MsgSubTaskServiceImpl implements MsgSubTaskService {

    private final Logger log = LoggerFactory.getLogger(MsgSubTaskServiceImpl.class);

    private final MsgSubTaskRepository msgSubTaskRepository;

    public MsgSubTaskServiceImpl(MsgSubTaskRepository msgSubTaskRepository) {
        this.msgSubTaskRepository = msgSubTaskRepository;
    }

    @Override
    public MsgSubTask save(MsgSubTask msgSubTask) {
        log.debug("Request to save MsgSubTask : {}", msgSubTask);
        return msgSubTaskRepository.save(msgSubTask);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MsgSubTask> findAll(Pageable pageable) {
        log.debug("Request to get all MsgSubTasks");
        return msgSubTaskRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<MsgSubTask> findOne(Long id) {
        log.debug("Request to get MsgSubTask : {}", id);
        return msgSubTaskRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete MsgSubTask : {}", id);
        msgSubTaskRepository.deleteById(id);
    }
}
