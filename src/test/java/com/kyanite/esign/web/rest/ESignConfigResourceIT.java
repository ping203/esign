package com.kyanite.esign.web.rest;

import com.kyanite.esign.EsignApp;
import com.kyanite.esign.domain.ESignConfig;
import com.kyanite.esign.repository.ESignConfigRepository;
import com.kyanite.esign.service.ESignConfigService;

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

/**
 * Integration tests for the {@link ESignConfigResource} REST controller.
 */
@SpringBootTest(classes = EsignApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ESignConfigResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CHANNEL_NO = "AAAAAAAAAA";
    private static final String UPDATED_CHANNEL_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ACCESS_ID = "AAAAAAAAAA";
    private static final String UPDATED_ACCESS_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ACCESS_KEY_SECRET = "AAAAAAAAAA";
    private static final String UPDATED_ACCESS_KEY_SECRET = "BBBBBBBBBB";

    @Autowired
    private ESignConfigRepository eSignConfigRepository;

    @Autowired
    private ESignConfigService eSignConfigService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restESignConfigMockMvc;

    private ESignConfig eSignConfig;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ESignConfig createEntity(EntityManager em) {
        ESignConfig eSignConfig = new ESignConfig()
            .name(DEFAULT_NAME)
            .time(DEFAULT_TIME)
            .channelNo(DEFAULT_CHANNEL_NO)
            .accessId(DEFAULT_ACCESS_ID)
            .accessKeySecret(DEFAULT_ACCESS_KEY_SECRET);
        return eSignConfig;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ESignConfig createUpdatedEntity(EntityManager em) {
        ESignConfig eSignConfig = new ESignConfig()
            .name(UPDATED_NAME)
            .time(UPDATED_TIME)
            .channelNo(UPDATED_CHANNEL_NO)
            .accessId(UPDATED_ACCESS_ID)
            .accessKeySecret(UPDATED_ACCESS_KEY_SECRET);
        return eSignConfig;
    }

    @BeforeEach
    public void initTest() {
        eSignConfig = createEntity(em);
    }

    @Test
    @Transactional
    public void createESignConfig() throws Exception {
        int databaseSizeBeforeCreate = eSignConfigRepository.findAll().size();
        // Create the ESignConfig
        restESignConfigMockMvc.perform(post("/api/e-sign-configs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eSignConfig)))
            .andExpect(status().isCreated());

        // Validate the ESignConfig in the database
        List<ESignConfig> eSignConfigList = eSignConfigRepository.findAll();
        assertThat(eSignConfigList).hasSize(databaseSizeBeforeCreate + 1);
        ESignConfig testESignConfig = eSignConfigList.get(eSignConfigList.size() - 1);
        assertThat(testESignConfig.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testESignConfig.getTime()).isEqualTo(DEFAULT_TIME);
        assertThat(testESignConfig.getChannelNo()).isEqualTo(DEFAULT_CHANNEL_NO);
        assertThat(testESignConfig.getAccessId()).isEqualTo(DEFAULT_ACCESS_ID);
        assertThat(testESignConfig.getAccessKeySecret()).isEqualTo(DEFAULT_ACCESS_KEY_SECRET);
    }

    @Test
    @Transactional
    public void createESignConfigWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = eSignConfigRepository.findAll().size();

        // Create the ESignConfig with an existing ID
        eSignConfig.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restESignConfigMockMvc.perform(post("/api/e-sign-configs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eSignConfig)))
            .andExpect(status().isBadRequest());

        // Validate the ESignConfig in the database
        List<ESignConfig> eSignConfigList = eSignConfigRepository.findAll();
        assertThat(eSignConfigList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllESignConfigs() throws Exception {
        // Initialize the database
        eSignConfigRepository.saveAndFlush(eSignConfig);

        // Get all the eSignConfigList
        restESignConfigMockMvc.perform(get("/api/e-sign-configs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eSignConfig.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].time").value(hasItem(DEFAULT_TIME.toString())))
            .andExpect(jsonPath("$.[*].channelNo").value(hasItem(DEFAULT_CHANNEL_NO)))
            .andExpect(jsonPath("$.[*].accessId").value(hasItem(DEFAULT_ACCESS_ID)))
            .andExpect(jsonPath("$.[*].accessKeySecret").value(hasItem(DEFAULT_ACCESS_KEY_SECRET)));
    }
    
    @Test
    @Transactional
    public void getESignConfig() throws Exception {
        // Initialize the database
        eSignConfigRepository.saveAndFlush(eSignConfig);

        // Get the eSignConfig
        restESignConfigMockMvc.perform(get("/api/e-sign-configs/{id}", eSignConfig.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(eSignConfig.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.time").value(DEFAULT_TIME.toString()))
            .andExpect(jsonPath("$.channelNo").value(DEFAULT_CHANNEL_NO))
            .andExpect(jsonPath("$.accessId").value(DEFAULT_ACCESS_ID))
            .andExpect(jsonPath("$.accessKeySecret").value(DEFAULT_ACCESS_KEY_SECRET));
    }
    @Test
    @Transactional
    public void getNonExistingESignConfig() throws Exception {
        // Get the eSignConfig
        restESignConfigMockMvc.perform(get("/api/e-sign-configs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateESignConfig() throws Exception {
        // Initialize the database
        eSignConfigService.save(eSignConfig);

        int databaseSizeBeforeUpdate = eSignConfigRepository.findAll().size();

        // Update the eSignConfig
        ESignConfig updatedESignConfig = eSignConfigRepository.findById(eSignConfig.getId()).get();
        // Disconnect from session so that the updates on updatedESignConfig are not directly saved in db
        em.detach(updatedESignConfig);
        updatedESignConfig
            .name(UPDATED_NAME)
            .time(UPDATED_TIME)
            .channelNo(UPDATED_CHANNEL_NO)
            .accessId(UPDATED_ACCESS_ID)
            .accessKeySecret(UPDATED_ACCESS_KEY_SECRET);

        restESignConfigMockMvc.perform(put("/api/e-sign-configs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedESignConfig)))
            .andExpect(status().isOk());

        // Validate the ESignConfig in the database
        List<ESignConfig> eSignConfigList = eSignConfigRepository.findAll();
        assertThat(eSignConfigList).hasSize(databaseSizeBeforeUpdate);
        ESignConfig testESignConfig = eSignConfigList.get(eSignConfigList.size() - 1);
        assertThat(testESignConfig.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testESignConfig.getTime()).isEqualTo(UPDATED_TIME);
        assertThat(testESignConfig.getChannelNo()).isEqualTo(UPDATED_CHANNEL_NO);
        assertThat(testESignConfig.getAccessId()).isEqualTo(UPDATED_ACCESS_ID);
        assertThat(testESignConfig.getAccessKeySecret()).isEqualTo(UPDATED_ACCESS_KEY_SECRET);
    }

    @Test
    @Transactional
    public void updateNonExistingESignConfig() throws Exception {
        int databaseSizeBeforeUpdate = eSignConfigRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restESignConfigMockMvc.perform(put("/api/e-sign-configs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eSignConfig)))
            .andExpect(status().isBadRequest());

        // Validate the ESignConfig in the database
        List<ESignConfig> eSignConfigList = eSignConfigRepository.findAll();
        assertThat(eSignConfigList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteESignConfig() throws Exception {
        // Initialize the database
        eSignConfigService.save(eSignConfig);

        int databaseSizeBeforeDelete = eSignConfigRepository.findAll().size();

        // Delete the eSignConfig
        restESignConfigMockMvc.perform(delete("/api/e-sign-configs/{id}", eSignConfig.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ESignConfig> eSignConfigList = eSignConfigRepository.findAll();
        assertThat(eSignConfigList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
