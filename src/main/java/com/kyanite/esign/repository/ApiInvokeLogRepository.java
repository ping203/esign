package com.kyanite.esign.repository;

import com.kyanite.esign.domain.ApiInvokeLog;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ApiInvokeLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApiInvokeLogRepository extends JpaRepository<ApiInvokeLog, Long> {
}
