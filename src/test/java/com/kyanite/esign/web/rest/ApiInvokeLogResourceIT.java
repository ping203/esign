package com.kyanite.esign.web.rest;

import com.kyanite.esign.EsignApp;
import com.kyanite.esign.domain.ApiInvokeLog;
import com.kyanite.esign.repository.ApiInvokeLogRepository;
import com.kyanite.esign.service.ApiInvokeLogService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.kyanite.esign.domain.enumeration.HttpMethod;
/**
 * Integration tests for the {@link ApiInvokeLogResource} REST controller.
 */
@SpringBootTest(classes = EsignApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ApiInvokeLogResourceIT {

    private static final String DEFAULT_LOGIN = "AAAAAAAAAA";
    private static final String UPDATED_LOGIN = "BBBBBBBBBB";

    private static final String DEFAULT_API_NAME = "AAAAAAAAAA";
    private static final String UPDATED_API_NAME = "BBBBBBBBBB";

    private static final HttpMethod DEFAULT_METHOD = HttpMethod.GET;
    private static final HttpMethod UPDATED_METHOD = HttpMethod.HEAD;

    private static final Integer DEFAULT_HTTP_STATUS_CODE = 1;
    private static final Integer UPDATED_HTTP_STATUS_CODE = 2;

    private static final Instant DEFAULT_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_REQEUST_DATA = "AAAAAAAAAA";
    private static final String UPDATED_REQEUST_DATA = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_DATA = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_DATA = "BBBBBBBBBB";

    @Autowired
    private ApiInvokeLogRepository apiInvokeLogRepository;

    @Autowired
    private ApiInvokeLogService apiInvokeLogService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restApiInvokeLogMockMvc;

    private ApiInvokeLog apiInvokeLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ApiInvokeLog createEntity(EntityManager em) {
        ApiInvokeLog apiInvokeLog = new ApiInvokeLog()
            .login(DEFAULT_LOGIN)
            .apiName(DEFAULT_API_NAME)
            .method(DEFAULT_METHOD)
            .httpStatusCode(DEFAULT_HTTP_STATUS_CODE)
            .time(DEFAULT_TIME)
            .reqeustData(DEFAULT_REQEUST_DATA)
            .responseData(DEFAULT_RESPONSE_DATA);
        return apiInvokeLog;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ApiInvokeLog createUpdatedEntity(EntityManager em) {
        ApiInvokeLog apiInvokeLog = new ApiInvokeLog()
            .login(UPDATED_LOGIN)
            .apiName(UPDATED_API_NAME)
            .method(UPDATED_METHOD)
            .httpStatusCode(UPDATED_HTTP_STATUS_CODE)
            .time(UPDATED_TIME)
            .reqeustData(UPDATED_REQEUST_DATA)
            .responseData(UPDATED_RESPONSE_DATA);
        return apiInvokeLog;
    }

    @BeforeEach
    public void initTest() {
        apiInvokeLog = createEntity(em);
    }

    @Test
    @Transactional
    public void createApiInvokeLog() throws Exception {
        int databaseSizeBeforeCreate = apiInvokeLogRepository.findAll().size();
        // Create the ApiInvokeLog
        restApiInvokeLogMockMvc.perform(post("/api/api-invoke-logs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(apiInvokeLog)))
            .andExpect(status().isCreated());

        // Validate the ApiInvokeLog in the database
        List<ApiInvokeLog> apiInvokeLogList = apiInvokeLogRepository.findAll();
        assertThat(apiInvokeLogList).hasSize(databaseSizeBeforeCreate + 1);
        ApiInvokeLog testApiInvokeLog = apiInvokeLogList.get(apiInvokeLogList.size() - 1);
        assertThat(testApiInvokeLog.getLogin()).isEqualTo(DEFAULT_LOGIN);
        assertThat(testApiInvokeLog.getApiName()).isEqualTo(DEFAULT_API_NAME);
        assertThat(testApiInvokeLog.getMethod()).isEqualTo(DEFAULT_METHOD);
        assertThat(testApiInvokeLog.getHttpStatusCode()).isEqualTo(DEFAULT_HTTP_STATUS_CODE);
        assertThat(testApiInvokeLog.getTime()).isEqualTo(DEFAULT_TIME);
        assertThat(testApiInvokeLog.getReqeustData()).isEqualTo(DEFAULT_REQEUST_DATA);
        assertThat(testApiInvokeLog.getResponseData()).isEqualTo(DEFAULT_RESPONSE_DATA);
    }

    @Test
    @Transactional
    public void createApiInvokeLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = apiInvokeLogRepository.findAll().size();

        // Create the ApiInvokeLog with an existing ID
        apiInvokeLog.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restApiInvokeLogMockMvc.perform(post("/api/api-invoke-logs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(apiInvokeLog)))
            .andExpect(status().isBadRequest());

        // Validate the ApiInvokeLog in the database
        List<ApiInvokeLog> apiInvokeLogList = apiInvokeLogRepository.findAll();
        assertThat(apiInvokeLogList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllApiInvokeLogs() throws Exception {
        // Initialize the database
        apiInvokeLogRepository.saveAndFlush(apiInvokeLog);

        // Get all the apiInvokeLogList
        restApiInvokeLogMockMvc.perform(get("/api/api-invoke-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(apiInvokeLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].login").value(hasItem(DEFAULT_LOGIN)))
            .andExpect(jsonPath("$.[*].apiName").value(hasItem(DEFAULT_API_NAME)))
            .andExpect(jsonPath("$.[*].method").value(hasItem(DEFAULT_METHOD.toString())))
            .andExpect(jsonPath("$.[*].httpStatusCode").value(hasItem(DEFAULT_HTTP_STATUS_CODE)))
            .andExpect(jsonPath("$.[*].time").value(hasItem(DEFAULT_TIME.toString())))
            .andExpect(jsonPath("$.[*].reqeustData").value(hasItem(DEFAULT_REQEUST_DATA.toString())))
            .andExpect(jsonPath("$.[*].responseData").value(hasItem(DEFAULT_RESPONSE_DATA.toString())));
    }
    
    @Test
    @Transactional
    public void getApiInvokeLog() throws Exception {
        // Initialize the database
        apiInvokeLogRepository.saveAndFlush(apiInvokeLog);

        // Get the apiInvokeLog
        restApiInvokeLogMockMvc.perform(get("/api/api-invoke-logs/{id}", apiInvokeLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(apiInvokeLog.getId().intValue()))
            .andExpect(jsonPath("$.login").value(DEFAULT_LOGIN))
            .andExpect(jsonPath("$.apiName").value(DEFAULT_API_NAME))
            .andExpect(jsonPath("$.method").value(DEFAULT_METHOD.toString()))
            .andExpect(jsonPath("$.httpStatusCode").value(DEFAULT_HTTP_STATUS_CODE))
            .andExpect(jsonPath("$.time").value(DEFAULT_TIME.toString()))
            .andExpect(jsonPath("$.reqeustData").value(DEFAULT_REQEUST_DATA.toString()))
            .andExpect(jsonPath("$.responseData").value(DEFAULT_RESPONSE_DATA.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingApiInvokeLog() throws Exception {
        // Get the apiInvokeLog
        restApiInvokeLogMockMvc.perform(get("/api/api-invoke-logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateApiInvokeLog() throws Exception {
        // Initialize the database
        apiInvokeLogService.save(apiInvokeLog);

        int databaseSizeBeforeUpdate = apiInvokeLogRepository.findAll().size();

        // Update the apiInvokeLog
        ApiInvokeLog updatedApiInvokeLog = apiInvokeLogRepository.findById(apiInvokeLog.getId()).get();
        // Disconnect from session so that the updates on updatedApiInvokeLog are not directly saved in db
        em.detach(updatedApiInvokeLog);
        updatedApiInvokeLog
            .login(UPDATED_LOGIN)
            .apiName(UPDATED_API_NAME)
            .method(UPDATED_METHOD)
            .httpStatusCode(UPDATED_HTTP_STATUS_CODE)
            .time(UPDATED_TIME)
            .reqeustData(UPDATED_REQEUST_DATA)
            .responseData(UPDATED_RESPONSE_DATA);

        restApiInvokeLogMockMvc.perform(put("/api/api-invoke-logs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedApiInvokeLog)))
            .andExpect(status().isOk());

        // Validate the ApiInvokeLog in the database
        List<ApiInvokeLog> apiInvokeLogList = apiInvokeLogRepository.findAll();
        assertThat(apiInvokeLogList).hasSize(databaseSizeBeforeUpdate);
        ApiInvokeLog testApiInvokeLog = apiInvokeLogList.get(apiInvokeLogList.size() - 1);
        assertThat(testApiInvokeLog.getLogin()).isEqualTo(UPDATED_LOGIN);
        assertThat(testApiInvokeLog.getApiName()).isEqualTo(UPDATED_API_NAME);
        assertThat(testApiInvokeLog.getMethod()).isEqualTo(UPDATED_METHOD);
        assertThat(testApiInvokeLog.getHttpStatusCode()).isEqualTo(UPDATED_HTTP_STATUS_CODE);
        assertThat(testApiInvokeLog.getTime()).isEqualTo(UPDATED_TIME);
        assertThat(testApiInvokeLog.getReqeustData()).isEqualTo(UPDATED_REQEUST_DATA);
        assertThat(testApiInvokeLog.getResponseData()).isEqualTo(UPDATED_RESPONSE_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingApiInvokeLog() throws Exception {
        int databaseSizeBeforeUpdate = apiInvokeLogRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restApiInvokeLogMockMvc.perform(put("/api/api-invoke-logs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(apiInvokeLog)))
            .andExpect(status().isBadRequest());

        // Validate the ApiInvokeLog in the database
        List<ApiInvokeLog> apiInvokeLogList = apiInvokeLogRepository.findAll();
        assertThat(apiInvokeLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteApiInvokeLog() throws Exception {
        // Initialize the database
        apiInvokeLogService.save(apiInvokeLog);

        int databaseSizeBeforeDelete = apiInvokeLogRepository.findAll().size();

        // Delete the apiInvokeLog
        restApiInvokeLogMockMvc.perform(delete("/api/api-invoke-logs/{id}", apiInvokeLog.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ApiInvokeLog> apiInvokeLogList = apiInvokeLogRepository.findAll();
        assertThat(apiInvokeLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
