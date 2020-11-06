package com.kyanite.esign.repository;

import com.kyanite.esign.domain.PdfSign;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PdfSign entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PdfSignRepository extends JpaRepository<PdfSign, Long> {
}
