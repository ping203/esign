package com.kyanite.esign.service.impl;

import com.kyanite.esign.service.MsgTaskService;
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
public class MsgTaskServiceImpl implements MsgTaskService {

    private final Logger log = LoggerFactory.getLogger(MsgTaskServiceImpl.class);

    private final MsgTaskRepository msgTaskRepository;

    public MsgTaskServiceImpl(MsgTaskRepository msgTaskRepository) {
        this.msgTaskRepository = msgTaskRepository;
    }

    @Override
    public MsgTask save(MsgTask msgTask) {
        log.debug("Request to save MsgTask : {}", msgTask);
        return msgTaskRepository.save(msgTask);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MsgTask> findAll(Pageable pageable) {
        log.debug("Request to get all MsgTasks");
        return msgTaskRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<MsgTask> findOne(Long id) {
        log.debug("Request to get MsgTask : {}", id);
        return msgTaskRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete MsgTask : {}", id);
        msgTaskRepository.deleteById(id);
    }
}
