package com.kyanite.esign.repository;

import com.kyanite.esign.domain.SealData;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SealData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SealDataRepository extends JpaRepository<SealData, Long> {
}
