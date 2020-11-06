package com.kyanite.esign.repository;

import com.kyanite.esign.domain.MsgTask;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MsgTask entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MsgTaskRepository extends JpaRepository<MsgTask, Long> {
}
