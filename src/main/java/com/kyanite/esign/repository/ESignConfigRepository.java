package com.kyanite.esign.repository;

import com.kyanite.esign.domain.ESignConfig;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ESignConfig entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ESignConfigRepository extends JpaRepository<ESignConfig, Long> {
}
