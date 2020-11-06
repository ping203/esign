package com.kyanite.esign.repository;

import com.kyanite.esign.domain.PdfFile;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PdfFile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PdfFileRepository extends JpaRepository<PdfFile, Long> {
}
