package com.kyanite.esign.service.impl;

import com.kyanite.esign.service.DdUserService;
import com.kyanite.esign.domain.DdUser;
import com.kyanite.esign.repository.DdUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DdUser}.
 */
@Service
@Transactional
public class DdUserServiceImpl implements DdUserService {

    private final Logger log = LoggerFactory.getLogger(DdUserServiceImpl.class);

    private final DdUserRepository ddUserRepository;

    public DdUserServiceImpl(DdUserRepository ddUserRepository) {
        this.ddUserRepository = ddUserRepository;
    }

    @Override
    public DdUser save(DdUser ddUser) {
        log.debug("Request to save DdUser : {}", ddUser);
        return ddUserRepository.save(ddUser);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DdUser> findAll(Pageable pageable) {
        log.debug("Request to get all DdUsers");
        return ddUserRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<DdUser> findOne(Long id) {
        log.debug("Request to get DdUser : {}", id);
        return ddUserRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DdUser : {}", id);
        ddUserRepository.deleteById(id);
    }
}
