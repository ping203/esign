package com.kyanite.esign.service.impl;

import com.kyanite.esign.service.ESignConfigService;
import com.kyanite.esign.domain.ESignConfig;
import com.kyanite.esign.repository.ESignConfigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ESignConfig}.
 */
@Service
@Transactional
public class ESignConfigServiceImpl implements ESignConfigService {

    private final Logger log = LoggerFactory.getLogger(ESignConfigServiceImpl.class);

    private final ESignConfigRepository eSignConfigRepository;

    public ESignConfigServiceImpl(ESignConfigRepository eSignConfigRepository) {
        this.eSignConfigRepository = eSignConfigRepository;
    }

    @Override
    public ESignConfig save(ESignConfig eSignConfig) {
        log.debug("Request to save ESignConfig : {}", eSignConfig);
        return eSignConfigRepository.save(eSignConfig);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ESignConfig> findAll(Pageable pageable) {
        log.debug("Request to get all ESignConfigs");
        return eSignConfigRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ESignConfig> findOne(Long id) {
        log.debug("Request to get ESignConfig : {}", id);
        return eSignConfigRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ESignConfig : {}", id);
        eSignConfigRepository.deleteById(id);
    }
}
