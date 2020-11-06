package com.kyanite.esign.repository;

import com.kyanite.esign.domain.MsgSubTask;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MsgSubTask entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MsgSubTaskRepository extends JpaRepository<MsgSubTask, Long> {
}
