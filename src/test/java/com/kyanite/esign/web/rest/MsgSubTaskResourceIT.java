package com.kyanite.esign.web.rest;

import com.kyanite.esign.EsignApp;
import com.kyanite.esign.domain.MsgSubTask;
import com.kyanite.esign.repository.MsgSubTaskRepository;
import com.kyanite.esign.service.MsgSubTaskService;

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

import com.kyanite.esign.domain.enumeration.MessageStatus;
/**
 * Integration tests for the {@link MsgSubTaskResource} REST controller.
 */
@SpringBootTest(classes = EsignApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MsgSubTaskResourceIT {

    private static final String DEFAULT_USERID_LIST = "AAAAAAAAAA";
    private static final String UPDATED_USERID_LIST = "BBBBBBBBBB";

    private static final Long DEFAULT_TASK_ID = 1L;
    private static final Long UPDATED_TASK_ID = 2L;

    private static final Instant DEFAULT_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_RSP_MSG = "AAAAAAAAAA";
    private static final String UPDATED_RSP_MSG = "BBBBBBBBBB";

    private static final Long DEFAULT_STATUS = 1L;
    private static final Long UPDATED_STATUS = 2L;

    private static final Long DEFAULT_PROGRESS_IN_PERCENT = 1L;
    private static final Long UPDATED_PROGRESS_IN_PERCENT = 2L;

    private static final MessageStatus DEFAULT_SUB_TASK_STATUS = MessageStatus.SentSuccessfully;
    private static final MessageStatus UPDATED_SUB_TASK_STATUS = MessageStatus.Sending;

    private static final String DEFAULT_INVALID_USER_ID_LIST = "AAAAAAAAAA";
    private static final String UPDATED_INVALID_USER_ID_LIST = "BBBBBBBBBB";

    private static final String DEFAULT_FORBIDDEN_USER_ID_LIST = "AAAAAAAAAA";
    private static final String UPDATED_FORBIDDEN_USER_ID_LIST = "BBBBBBBBBB";

    private static final String DEFAULT_FAILED_USER_ID_LIST = "AAAAAAAAAA";
    private static final String UPDATED_FAILED_USER_ID_LIST = "BBBBBBBBBB";

    private static final String DEFAULT_READ_USER_ID_LIST = "AAAAAAAAAA";
    private static final String UPDATED_READ_USER_ID_LIST = "BBBBBBBBBB";

    private static final String DEFAULT_UNREAD_USER_ID_LIST = "AAAAAAAAAA";
    private static final String UPDATED_UNREAD_USER_ID_LIST = "BBBBBBBBBB";

    private static final String DEFAULT_INVALID_DEPT_ID_LIST = "AAAAAAAAAA";
    private static final String UPDATED_INVALID_DEPT_ID_LIST = "BBBBBBBBBB";

    private static final Boolean DEFAULT_WITHDRAW = false;
    private static final Boolean UPDATED_WITHDRAW = true;

    @Autowired
    private MsgSubTaskRepository msgSubTaskRepository;

    @Autowired
    private MsgSubTaskService msgSubTaskService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMsgSubTaskMockMvc;

    private MsgSubTask msgSubTask;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MsgSubTask createEntity(EntityManager em) {
        MsgSubTask msgSubTask = new MsgSubTask()
            .useridList(DEFAULT_USERID_LIST)
            .taskId(DEFAULT_TASK_ID)
            .time(DEFAULT_TIME)
            .rspMsg(DEFAULT_RSP_MSG)
            .status(DEFAULT_STATUS)
            .progressInPercent(DEFAULT_PROGRESS_IN_PERCENT)
            .subTaskStatus(DEFAULT_SUB_TASK_STATUS)
            .invalidUserIdList(DEFAULT_INVALID_USER_ID_LIST)
            .forbiddenUserIdList(DEFAULT_FORBIDDEN_USER_ID_LIST)
            .failedUserIdList(DEFAULT_FAILED_USER_ID_LIST)
            .readUserIdList(DEFAULT_READ_USER_ID_LIST)
            .unreadUserIdList(DEFAULT_UNREAD_USER_ID_LIST)
            .invalidDeptIdList(DEFAULT_INVALID_DEPT_ID_LIST)
            .withdraw(DEFAULT_WITHDRAW);
        return msgSubTask;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MsgSubTask createUpdatedEntity(EntityManager em) {
        MsgSubTask msgSubTask = new MsgSubTask()
            .useridList(UPDATED_USERID_LIST)
            .taskId(UPDATED_TASK_ID)
            .time(UPDATED_TIME)
            .rspMsg(UPDATED_RSP_MSG)
            .status(UPDATED_STATUS)
            .progressInPercent(UPDATED_PROGRESS_IN_PERCENT)
            .subTaskStatus(UPDATED_SUB_TASK_STATUS)
            .invalidUserIdList(UPDATED_INVALID_USER_ID_LIST)
            .forbiddenUserIdList(UPDATED_FORBIDDEN_USER_ID_LIST)
            .failedUserIdList(UPDATED_FAILED_USER_ID_LIST)
            .readUserIdList(UPDATED_READ_USER_ID_LIST)
            .unreadUserIdList(UPDATED_UNREAD_USER_ID_LIST)
            .invalidDeptIdList(UPDATED_INVALID_DEPT_ID_LIST)
            .withdraw(UPDATED_WITHDRAW);
        return msgSubTask;
    }

    @BeforeEach
    public void initTest() {
        msgSubTask = createEntity(em);
    }

    @Test
    @Transactional
    public void createMsgSubTask() throws Exception {
        int databaseSizeBeforeCreate = msgSubTaskRepository.findAll().size();
        // Create the MsgSubTask
        restMsgSubTaskMockMvc.perform(post("/api/msg-sub-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(msgSubTask)))
            .andExpect(status().isCreated());

        // Validate the MsgSubTask in the database
        List<MsgSubTask> msgSubTaskList = msgSubTaskRepository.findAll();
        assertThat(msgSubTaskList).hasSize(databaseSizeBeforeCreate + 1);
        MsgSubTask testMsgSubTask = msgSubTaskList.get(msgSubTaskList.size() - 1);
        assertThat(testMsgSubTask.getUseridList()).isEqualTo(DEFAULT_USERID_LIST);
        assertThat(testMsgSubTask.getTaskId()).isEqualTo(DEFAULT_TASK_ID);
        assertThat(testMsgSubTask.getTime()).isEqualTo(DEFAULT_TIME);
        assertThat(testMsgSubTask.getRspMsg()).isEqualTo(DEFAULT_RSP_MSG);
        assertThat(testMsgSubTask.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testMsgSubTask.getProgressInPercent()).isEqualTo(DEFAULT_PROGRESS_IN_PERCENT);
        assertThat(testMsgSubTask.getSubTaskStatus()).isEqualTo(DEFAULT_SUB_TASK_STATUS);
        assertThat(testMsgSubTask.getInvalidUserIdList()).isEqualTo(DEFAULT_INVALID_USER_ID_LIST);
        assertThat(testMsgSubTask.getForbiddenUserIdList()).isEqualTo(DEFAULT_FORBIDDEN_USER_ID_LIST);
        assertThat(testMsgSubTask.getFailedUserIdList()).isEqualTo(DEFAULT_FAILED_USER_ID_LIST);
        assertThat(testMsgSubTask.getReadUserIdList()).isEqualTo(DEFAULT_READ_USER_ID_LIST);
        assertThat(testMsgSubTask.getUnreadUserIdList()).isEqualTo(DEFAULT_UNREAD_USER_ID_LIST);
        assertThat(testMsgSubTask.getInvalidDeptIdList()).isEqualTo(DEFAULT_INVALID_DEPT_ID_LIST);
        assertThat(testMsgSubTask.isWithdraw()).isEqualTo(DEFAULT_WITHDRAW);
    }

    @Test
    @Transactional
    public void createMsgSubTaskWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = msgSubTaskRepository.findAll().size();

        // Create the MsgSubTask with an existing ID
        msgSubTask.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMsgSubTaskMockMvc.perform(post("/api/msg-sub-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(msgSubTask)))
            .andExpect(status().isBadRequest());

        // Validate the MsgSubTask in the database
        List<MsgSubTask> msgSubTaskList = msgSubTaskRepository.findAll();
        assertThat(msgSubTaskList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMsgSubTasks() throws Exception {
        // Initialize the database
        msgSubTaskRepository.saveAndFlush(msgSubTask);

        // Get all the msgSubTaskList
        restMsgSubTaskMockMvc.perform(get("/api/msg-sub-tasks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(msgSubTask.getId().intValue())))
            .andExpect(jsonPath("$.[*].useridList").value(hasItem(DEFAULT_USERID_LIST)))
            .andExpect(jsonPath("$.[*].taskId").value(hasItem(DEFAULT_TASK_ID.intValue())))
            .andExpect(jsonPath("$.[*].time").value(hasItem(DEFAULT_TIME.toString())))
            .andExpect(jsonPath("$.[*].rspMsg").value(hasItem(DEFAULT_RSP_MSG)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.intValue())))
            .andExpect(jsonPath("$.[*].progressInPercent").value(hasItem(DEFAULT_PROGRESS_IN_PERCENT.intValue())))
            .andExpect(jsonPath("$.[*].subTaskStatus").value(hasItem(DEFAULT_SUB_TASK_STATUS.toString())))
            .andExpect(jsonPath("$.[*].invalidUserIdList").value(hasItem(DEFAULT_INVALID_USER_ID_LIST)))
            .andExpect(jsonPath("$.[*].forbiddenUserIdList").value(hasItem(DEFAULT_FORBIDDEN_USER_ID_LIST)))
            .andExpect(jsonPath("$.[*].failedUserIdList").value(hasItem(DEFAULT_FAILED_USER_ID_LIST)))
            .andExpect(jsonPath("$.[*].readUserIdList").value(hasItem(DEFAULT_READ_USER_ID_LIST)))
            .andExpect(jsonPath("$.[*].unreadUserIdList").value(hasItem(DEFAULT_UNREAD_USER_ID_LIST)))
            .andExpect(jsonPath("$.[*].invalidDeptIdList").value(hasItem(DEFAULT_INVALID_DEPT_ID_LIST)))
            .andExpect(jsonPath("$.[*].withdraw").value(hasItem(DEFAULT_WITHDRAW.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getMsgSubTask() throws Exception {
        // Initialize the database
        msgSubTaskRepository.saveAndFlush(msgSubTask);

        // Get the msgSubTask
        restMsgSubTaskMockMvc.perform(get("/api/msg-sub-tasks/{id}", msgSubTask.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(msgSubTask.getId().intValue()))
            .andExpect(jsonPath("$.useridList").value(DEFAULT_USERID_LIST))
            .andExpect(jsonPath("$.taskId").value(DEFAULT_TASK_ID.intValue()))
            .andExpect(jsonPath("$.time").value(DEFAULT_TIME.toString()))
            .andExpect(jsonPath("$.rspMsg").value(DEFAULT_RSP_MSG))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.intValue()))
            .andExpect(jsonPath("$.progressInPercent").value(DEFAULT_PROGRESS_IN_PERCENT.intValue()))
            .andExpect(jsonPath("$.subTaskStatus").value(DEFAULT_SUB_TASK_STATUS.toString()))
            .andExpect(jsonPath("$.invalidUserIdList").value(DEFAULT_INVALID_USER_ID_LIST))
            .andExpect(jsonPath("$.forbiddenUserIdList").value(DEFAULT_FORBIDDEN_USER_ID_LIST))
            .andExpect(jsonPath("$.failedUserIdList").value(DEFAULT_FAILED_USER_ID_LIST))
            .andExpect(jsonPath("$.readUserIdList").value(DEFAULT_READ_USER_ID_LIST))
            .andExpect(jsonPath("$.unreadUserIdList").value(DEFAULT_UNREAD_USER_ID_LIST))
            .andExpect(jsonPath("$.invalidDeptIdList").value(DEFAULT_INVALID_DEPT_ID_LIST))
            .andExpect(jsonPath("$.withdraw").value(DEFAULT_WITHDRAW.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingMsgSubTask() throws Exception {
        // Get the msgSubTask
        restMsgSubTaskMockMvc.perform(get("/api/msg-sub-tasks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMsgSubTask() throws Exception {
        // Initialize the database
        msgSubTaskService.save(msgSubTask);

        int databaseSizeBeforeUpdate = msgSubTaskRepository.findAll().size();

        // Update the msgSubTask
        MsgSubTask updatedMsgSubTask = msgSubTaskRepository.findById(msgSubTask.getId()).get();
        // Disconnect from session so that the updates on updatedMsgSubTask are not directly saved in db
        em.detach(updatedMsgSubTask);
        updatedMsgSubTask
            .useridList(UPDATED_USERID_LIST)
            .taskId(UPDATED_TASK_ID)
            .time(UPDATED_TIME)
            .rspMsg(UPDATED_RSP_MSG)
            .status(UPDATED_STATUS)
            .progressInPercent(UPDATED_PROGRESS_IN_PERCENT)
            .subTaskStatus(UPDATED_SUB_TASK_STATUS)
            .invalidUserIdList(UPDATED_INVALID_USER_ID_LIST)
            .forbiddenUserIdList(UPDATED_FORBIDDEN_USER_ID_LIST)
            .failedUserIdList(UPDATED_FAILED_USER_ID_LIST)
            .readUserIdList(UPDATED_READ_USER_ID_LIST)
            .unreadUserIdList(UPDATED_UNREAD_USER_ID_LIST)
            .invalidDeptIdList(UPDATED_INVALID_DEPT_ID_LIST)
            .withdraw(UPDATED_WITHDRAW);

        restMsgSubTaskMockMvc.perform(put("/api/msg-sub-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedMsgSubTask)))
            .andExpect(status().isOk());

        // Validate the MsgSubTask in the database
        List<MsgSubTask> msgSubTaskList = msgSubTaskRepository.findAll();
        assertThat(msgSubTaskList).hasSize(databaseSizeBeforeUpdate);
        MsgSubTask testMsgSubTask = msgSubTaskList.get(msgSubTaskList.size() - 1);
        assertThat(testMsgSubTask.getUseridList()).isEqualTo(UPDATED_USERID_LIST);
        assertThat(testMsgSubTask.getTaskId()).isEqualTo(UPDATED_TASK_ID);
        assertThat(testMsgSubTask.getTime()).isEqualTo(UPDATED_TIME);
        assertThat(testMsgSubTask.getRspMsg()).isEqualTo(UPDATED_RSP_MSG);
        assertThat(testMsgSubTask.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testMsgSubTask.getProgressInPercent()).isEqualTo(UPDATED_PROGRESS_IN_PERCENT);
        assertThat(testMsgSubTask.getSubTaskStatus()).isEqualTo(UPDATED_SUB_TASK_STATUS);
        assertThat(testMsgSubTask.getInvalidUserIdList()).isEqualTo(UPDATED_INVALID_USER_ID_LIST);
        assertThat(testMsgSubTask.getForbiddenUserIdList()).isEqualTo(UPDATED_FORBIDDEN_USER_ID_LIST);
        assertThat(testMsgSubTask.getFailedUserIdList()).isEqualTo(UPDATED_FAILED_USER_ID_LIST);
        assertThat(testMsgSubTask.getReadUserIdList()).isEqualTo(UPDATED_READ_USER_ID_LIST);
        assertThat(testMsgSubTask.getUnreadUserIdList()).isEqualTo(UPDATED_UNREAD_USER_ID_LIST);
        assertThat(testMsgSubTask.getInvalidDeptIdList()).isEqualTo(UPDATED_INVALID_DEPT_ID_LIST);
        assertThat(testMsgSubTask.isWithdraw()).isEqualTo(UPDATED_WITHDRAW);
    }

    @Test
    @Transactional
    public void updateNonExistingMsgSubTask() throws Exception {
        int databaseSizeBeforeUpdate = msgSubTaskRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMsgSubTaskMockMvc.perform(put("/api/msg-sub-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(msgSubTask)))
            .andExpect(status().isBadRequest());

        // Validate the MsgSubTask in the database
        List<MsgSubTask> msgSubTaskList = msgSubTaskRepository.findAll();
        assertThat(msgSubTaskList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMsgSubTask() throws Exception {
        // Initialize the database
        msgSubTaskService.save(msgSubTask);

        int databaseSizeBeforeDelete = msgSubTaskRepository.findAll().size();

        // Delete the msgSubTask
        restMsgSubTaskMockMvc.perform(delete("/api/msg-sub-tasks/{id}", msgSubTask.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MsgSubTask> msgSubTaskList = msgSubTaskRepository.findAll();
        assertThat(msgSubTaskList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
