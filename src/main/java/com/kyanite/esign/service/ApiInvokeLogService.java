package com.kyanite.esign.service;

import com.kyanite.esign.domain.ApiInvokeLog;
import com.kyanite.esign.repository.ApiInvokeLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ApiInvokeLog}.
 */
@Service
@Transactional
public class ApiInvokeLogService {

    private final Logger log = LoggerFactory.getLogger(ApiInvokeLogService.class);

    private final ApiInvokeLogRepository apiInvokeLogRepository;

    public ApiInvokeLogService(ApiInvokeLogRepository apiInvokeLogRepository) {
        this.apiInvokeLogRepository = apiInvokeLogRepository;
    }

    /**
     * Save a apiInvokeLog.
     *
     * @param apiInvokeLog the entity to save.
     * @return the persisted entity.
     */
    public ApiInvokeLog save(ApiInvokeLog apiInvokeLog) {
        log.debug("Request to save ApiInvokeLog : {}", apiInvokeLog);
        return apiInvokeLogRepository.save(apiInvokeLog);
    }

    /**
     * Get all the apiInvokeLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ApiInvokeLog> findAll(Pageable pageable) {
        log.debug("Request to get all ApiInvokeLogs");
        return apiInvokeLogRepository.findAll(pageable);
    }


    /**
     * Get one apiInvokeLog by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ApiInvokeLog> findOne(Long id) {
        log.debug("Request to get ApiInvokeLog : {}", id);
        return apiInvokeLogRepository.findById(id);
    }

    /**
     * Delete the apiInvokeLog by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ApiInvokeLog : {}", id);
        apiInvokeLogRepository.deleteById(id);
    }
}
