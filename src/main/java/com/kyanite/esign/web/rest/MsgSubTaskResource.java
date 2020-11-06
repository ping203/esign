package com.kyanite.esign.web.rest;

import com.kyanite.esign.domain.MsgSubTask;
import com.kyanite.esign.service.MsgSubTaskService;
import com.kyanite.esign.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.kyanite.esign.domain.MsgSubTask}.
 */
@RestController
@RequestMapping("/api")
public class MsgSubTaskResource {

    private final Logger log = LoggerFactory.getLogger(MsgSubTaskResource.class);

    private static final String ENTITY_NAME = "msgSubTask";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MsgSubTaskService msgSubTaskService;

    public MsgSubTaskResource(MsgSubTaskService msgSubTaskService) {
        this.msgSubTaskService = msgSubTaskService;
    }

    /**
     * {@code POST  /msg-sub-tasks} : Create a new msgSubTask.
     *
     * @param msgSubTask the msgSubTask to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new msgSubTask, or with status {@code 400 (Bad Request)} if the msgSubTask has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/msg-sub-tasks")
    public ResponseEntity<MsgSubTask> createMsgSubTask(@RequestBody MsgSubTask msgSubTask) throws URISyntaxException {
        log.debug("REST request to save MsgSubTask : {}", msgSubTask);
        if (msgSubTask.getId() != null) {
            throw new BadRequestAlertException("A new msgSubTask cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MsgSubTask result = msgSubTaskService.save(msgSubTask);
        return ResponseEntity.created(new URI("/api/msg-sub-tasks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /msg-sub-tasks} : Updates an existing msgSubTask.
     *
     * @param msgSubTask the msgSubTask to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated msgSubTask,
     * or with status {@code 400 (Bad Request)} if the msgSubTask is not valid,
     * or with status {@code 500 (Internal Server Error)} if the msgSubTask couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/msg-sub-tasks")
    public ResponseEntity<MsgSubTask> updateMsgSubTask(@RequestBody MsgSubTask msgSubTask) throws URISyntaxException {
        log.debug("REST request to update MsgSubTask : {}", msgSubTask);
        if (msgSubTask.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MsgSubTask result = msgSubTaskService.save(msgSubTask);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, msgSubTask.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /msg-sub-tasks} : get all the msgSubTasks.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of msgSubTasks in body.
     */
    @GetMapping("/msg-sub-tasks")
    public ResponseEntity<List<MsgSubTask>> getAllMsgSubTasks(Pageable pageable) {
        log.debug("REST request to get a page of MsgSubTasks");
        Page<MsgSubTask> page = msgSubTaskService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /msg-sub-tasks/:id} : get the "id" msgSubTask.
     *
     * @param id the id of the msgSubTask to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the msgSubTask, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/msg-sub-tasks/{id}")
    public ResponseEntity<MsgSubTask> getMsgSubTask(@PathVariable Long id) {
        log.debug("REST request to get MsgSubTask : {}", id);
        Optional<MsgSubTask> msgSubTask = msgSubTaskService.findOne(id);
        return ResponseUtil.wrapOrNotFound(msgSubTask);
    }

    /**
     * {@code DELETE  /msg-sub-tasks/:id} : delete the "id" msgSubTask.
     *
     * @param id the id of the msgSubTask to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/msg-sub-tasks/{id}")
    public ResponseEntity<Void> deleteMsgSubTask(@PathVariable Long id) {
        log.debug("REST request to delete MsgSubTask : {}", id);
        msgSubTaskService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
