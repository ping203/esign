package com.kyanite.esign.web.rest;

import com.kyanite.esign.EsignApp;
import com.kyanite.esign.domain.MsgTask;
import com.kyanite.esign.repository.MsgTaskRepository;
import com.kyanite.esign.service.MsgTaskService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.kyanite.esign.domain.enumeration.DdMessageType;
import com.kyanite.esign.domain.enumeration.MessageStatus;
/**
 * Integration tests for the {@link MsgTaskResource} REST controller.
 */
@SpringBootTest(classes = EsignApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MsgTaskResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DEPT_ID_LIST = "AAAAAAAAAA";
    private static final String UPDATED_DEPT_ID_LIST = "BBBBBBBBBB";

    private static final String DEFAULT_USERID_LIST = "AAAAAAAAAA";
    private static final String UPDATED_USERID_LIST = "BBBBBBBBBB";

    private static final Boolean DEFAULT_TO_ALL_USER = false;
    private static final Boolean UPDATED_TO_ALL_USER = true;

    private static final String DEFAULT_MSG = "AAAAAAAAAA";
    private static final String UPDATED_MSG = "BBBBBBBBBB";

    private static final Instant DEFAULT_EXECUTE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EXECUTE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_AGENT_ID = 1L;
    private static final Long UPDATED_AGENT_ID = 2L;

    private static final DdMessageType DEFAULT_TYPE = DdMessageType.Voice;
    private static final DdMessageType UPDATED_TYPE = DdMessageType.ActionCard;

    private static final MessageStatus DEFAULT_STATUS = MessageStatus.SentSuccessfully;
    private static final MessageStatus UPDATED_STATUS = MessageStatus.Sending;

    @Autowired
    private MsgTaskRepository msgTaskRepository;

    @Autowired
    private MsgTaskService msgTaskService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMsgTaskMockMvc;

    private MsgTask msgTask;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MsgTask createEntity(EntityManager em) {
        MsgTask msgTask = new MsgTask()
            .title(DEFAULT_TITLE)
            .deptIdList(DEFAULT_DEPT_ID_LIST)
            .useridList(DEFAULT_USERID_LIST)
            .toAllUser(DEFAULT_TO_ALL_USER)
            .msg(DEFAULT_MSG)
            .executeTime(DEFAULT_EXECUTE_TIME)
            .agentId(DEFAULT_AGENT_ID)
            .type(DEFAULT_TYPE)
            .status(DEFAULT_STATUS);
        return msgTask;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MsgTask createUpdatedEntity(EntityManager em) {
        MsgTask msgTask = new MsgTask()
            .title(UPDATED_TITLE)
            .deptIdList(UPDATED_DEPT_ID_LIST)
            .useridList(UPDATED_USERID_LIST)
            .toAllUser(UPDATED_TO_ALL_USER)
            .msg(UPDATED_MSG)
            .executeTime(UPDATED_EXECUTE_TIME)
            .agentId(UPDATED_AGENT_ID)
            .type(UPDATED_TYPE)
            .status(UPDATED_STATUS);
        return msgTask;
    }

    @BeforeEach
    public void initTest() {
        msgTask = createEntity(em);
    }

    @Test
    @Transactional
    public void createMsgTask() throws Exception {
        int databaseSizeBeforeCreate = msgTaskRepository.findAll().size();
        // Create the MsgTask
        restMsgTaskMockMvc.perform(post("/api/msg-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(msgTask)))
            .andExpect(status().isCreated());

        // Validate the MsgTask in the database
        List<MsgTask> msgTaskList = msgTaskRepository.findAll();
        assertThat(msgTaskList).hasSize(databaseSizeBeforeCreate + 1);
        MsgTask testMsgTask = msgTaskList.get(msgTaskList.size() - 1);
        assertThat(testMsgTask.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testMsgTask.getDeptIdList()).isEqualTo(DEFAULT_DEPT_ID_LIST);
        assertThat(testMsgTask.getUseridList()).isEqualTo(DEFAULT_USERID_LIST);
        assertThat(testMsgTask.isToAllUser()).isEqualTo(DEFAULT_TO_ALL_USER);
        assertThat(testMsgTask.getMsg()).isEqualTo(DEFAULT_MSG);
        assertThat(testMsgTask.getExecuteTime()).isEqualTo(DEFAULT_EXECUTE_TIME);
        assertThat(testMsgTask.getAgentId()).isEqualTo(DEFAULT_AGENT_ID);
        assertThat(testMsgTask.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testMsgTask.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createMsgTaskWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = msgTaskRepository.findAll().size();

        // Create the MsgTask with an existing ID
        msgTask.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMsgTaskMockMvc.perform(post("/api/msg-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(msgTask)))
            .andExpect(status().isBadRequest());

        // Validate the MsgTask in the database
        List<MsgTask> msgTaskList = msgTaskRepository.findAll();
        assertThat(msgTaskList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMsgTasks() throws Exception {
        // Initialize the database
        msgTaskRepository.saveAndFlush(msgTask);

        // Get all the msgTaskList
        restMsgTaskMockMvc.perform(get("/api/msg-tasks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(msgTask.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].deptIdList").value(hasItem(DEFAULT_DEPT_ID_LIST)))
            .andExpect(jsonPath("$.[*].useridList").value(hasItem(DEFAULT_USERID_LIST)))
            .andExpect(jsonPath("$.[*].toAllUser").value(hasItem(DEFAULT_TO_ALL_USER.booleanValue())))
            .andExpect(jsonPath("$.[*].msg").value(hasItem(DEFAULT_MSG)))
            .andExpect(jsonPath("$.[*].executeTime").value(hasItem(DEFAULT_EXECUTE_TIME.toString())))
            .andExpect(jsonPath("$.[*].agentId").value(hasItem(DEFAULT_AGENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getMsgTask() throws Exception {
        // Initialize the database
        msgTaskRepository.saveAndFlush(msgTask);

        // Get the msgTask
        restMsgTaskMockMvc.perform(get("/api/msg-tasks/{id}", msgTask.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(msgTask.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.deptIdList").value(DEFAULT_DEPT_ID_LIST))
            .andExpect(jsonPath("$.useridList").value(DEFAULT_USERID_LIST))
            .andExpect(jsonPath("$.toAllUser").value(DEFAULT_TO_ALL_USER.booleanValue()))
            .andExpect(jsonPath("$.msg").value(DEFAULT_MSG))
            .andExpect(jsonPath("$.executeTime").value(DEFAULT_EXECUTE_TIME.toString()))
            .andExpect(jsonPath("$.agentId").value(DEFAULT_AGENT_ID.intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingMsgTask() throws Exception {
        // Get the msgTask
        restMsgTaskMockMvc.perform(get("/api/msg-tasks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMsgTask() throws Exception {
        // Initialize the database
        msgTaskService.save(msgTask);

        int databaseSizeBeforeUpdate = msgTaskRepository.findAll().size();

        // Update the msgTask
        MsgTask updatedMsgTask = msgTaskRepository.findById(msgTask.getId()).get();
        // Disconnect from session so that the updates on updatedMsgTask are not directly saved in db
        em.detach(updatedMsgTask);
        updatedMsgTask
            .title(UPDATED_TITLE)
            .deptIdList(UPDATED_DEPT_ID_LIST)
            .useridList(UPDATED_USERID_LIST)
            .toAllUser(UPDATED_TO_ALL_USER)
            .msg(UPDATED_MSG)
            .executeTime(UPDATED_EXECUTE_TIME)
            .agentId(UPDATED_AGENT_ID)
            .type(UPDATED_TYPE)
            .status(UPDATED_STATUS);

        restMsgTaskMockMvc.perform(put("/api/msg-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedMsgTask)))
            .andExpect(status().isOk());

        // Validate the MsgTask in the database
        List<MsgTask> msgTaskList = msgTaskRepository.findAll();
        assertThat(msgTaskList).hasSize(databaseSizeBeforeUpdate);
        MsgTask testMsgTask = msgTaskList.get(msgTaskList.size() - 1);
        assertThat(testMsgTask.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testMsgTask.getDeptIdList()).isEqualTo(UPDATED_DEPT_ID_LIST);
        assertThat(testMsgTask.getUseridList()).isEqualTo(UPDATED_USERID_LIST);
        assertThat(testMsgTask.isToAllUser()).isEqualTo(UPDATED_TO_ALL_USER);
        assertThat(testMsgTask.getMsg()).isEqualTo(UPDATED_MSG);
        assertThat(testMsgTask.getExecuteTime()).isEqualTo(UPDATED_EXECUTE_TIME);
        assertThat(testMsgTask.getAgentId()).isEqualTo(UPDATED_AGENT_ID);
        assertThat(testMsgTask.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testMsgTask.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingMsgTask() throws Exception {
        int databaseSizeBeforeUpdate = msgTaskRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMsgTaskMockMvc.perform(put("/api/msg-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(msgTask)))
            .andExpect(status().isBadRequest());

        // Validate the MsgTask in the database
        List<MsgTask> msgTaskList = msgTaskRepository.findAll();
        assertThat(msgTaskList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMsgTask() throws Exception {
        // Initialize the database
        msgTaskService.save(msgTask);

        int databaseSizeBeforeDelete = msgTaskRepository.findAll().size();

        // Delete the msgTask
        restMsgTaskMockMvc.perform(delete("/api/msg-tasks/{id}", msgTask.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MsgTask> msgTaskList = msgTaskRepository.findAll();
        assertThat(msgTaskList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
