package com.kyanite.esign.web.rest;

import com.kyanite.esign.domain.MsgTask;
import com.kyanite.esign.service.MsgTaskService;
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
 * REST controller for managing {@link com.kyanite.esign.domain.MsgTask}.
 */
@RestController
@RequestMapping("/api")
public class MsgTaskResource {

    private final Logger log = LoggerFactory.getLogger(MsgTaskResource.class);

    private static final String ENTITY_NAME = "msgTask";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MsgTaskService msgTaskService;

    public MsgTaskResource(MsgTaskService msgTaskService) {
        this.msgTaskService = msgTaskService;
    }

    /**
     * {@code POST  /msg-tasks} : Create a new msgTask.
     *
     * @param msgTask the msgTask to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new msgTask, or with status {@code 400 (Bad Request)} if the msgTask has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/msg-tasks")
    public ResponseEntity<MsgTask> createMsgTask(@RequestBody MsgTask msgTask) throws URISyntaxException {
        log.debug("REST request to save MsgTask : {}", msgTask);
        if (msgTask.getId() != null) {
            throw new BadRequestAlertException("A new msgTask cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MsgTask result = msgTaskService.save(msgTask);
        return ResponseEntity.created(new URI("/api/msg-tasks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /msg-tasks} : Updates an existing msgTask.
     *
     * @param msgTask the msgTask to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated msgTask,
     * or with status {@code 400 (Bad Request)} if the msgTask is not valid,
     * or with status {@code 500 (Internal Server Error)} if the msgTask couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/msg-tasks")
    public ResponseEntity<MsgTask> updateMsgTask(@RequestBody MsgTask msgTask) throws URISyntaxException {
        log.debug("REST request to update MsgTask : {}", msgTask);
        if (msgTask.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MsgTask result = msgTaskService.save(msgTask);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, msgTask.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /msg-tasks} : get all the msgTasks.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of msgTasks in body.
     */
    @GetMapping("/msg-tasks")
    public ResponseEntity<List<MsgTask>> getAllMsgTasks(Pageable pageable) {
        log.debug("REST request to get a page of MsgTasks");
        Page<MsgTask> page = msgTaskService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /msg-tasks/:id} : get the "id" msgTask.
     *
     * @param id the id of the msgTask to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the msgTask, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/msg-tasks/{id}")
    public ResponseEntity<MsgTask> getMsgTask(@PathVariable Long id) {
        log.debug("REST request to get MsgTask : {}", id);
        Optional<MsgTask> msgTask = msgTaskService.findOne(id);
        return ResponseUtil.wrapOrNotFound(msgTask);
    }

    /**
     * {@code DELETE  /msg-tasks/:id} : delete the "id" msgTask.
     *
     * @param id the id of the msgTask to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/msg-tasks/{id}")
    public ResponseEntity<Void> deleteMsgTask(@PathVariable Long id) {
        log.debug("REST request to delete MsgTask : {}", id);
        msgTaskService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
