package com.kyanite.esign.web.rest;

import com.kyanite.esign.EsignApp;
import com.kyanite.esign.domain.SealData;
import com.kyanite.esign.repository.SealDataRepository;
import com.kyanite.esign.service.SealDataService;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SealDataResource} REST controller.
 */
@SpringBootTest(classes = EsignApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SealDataResourceIT {

    private static final byte[] DEFAULT_BASE_64_STR = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BASE_64_STR = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_BASE_64_STR_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BASE_64_STR_CONTENT_TYPE = "image/png";

    @Autowired
    private SealDataRepository sealDataRepository;

    @Autowired
    private SealDataService sealDataService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSealDataMockMvc;

    private SealData sealData;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SealData createEntity(EntityManager em) {
        SealData sealData = new SealData()
            .base64Str(DEFAULT_BASE_64_STR)
            .base64StrContentType(DEFAULT_BASE_64_STR_CONTENT_TYPE);
        return sealData;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SealData createUpdatedEntity(EntityManager em) {
        SealData sealData = new SealData()
            .base64Str(UPDATED_BASE_64_STR)
            .base64StrContentType(UPDATED_BASE_64_STR_CONTENT_TYPE);
        return sealData;
    }

    @BeforeEach
    public void initTest() {
        sealData = createEntity(em);
    }

    @Test
    @Transactional
    public void createSealData() throws Exception {
        int databaseSizeBeforeCreate = sealDataRepository.findAll().size();
        // Create the SealData
        restSealDataMockMvc.perform(post("/api/seal-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sealData)))
            .andExpect(status().isCreated());

        // Validate the SealData in the database
        List<SealData> sealDataList = sealDataRepository.findAll();
        assertThat(sealDataList).hasSize(databaseSizeBeforeCreate + 1);
        SealData testSealData = sealDataList.get(sealDataList.size() - 1);
        assertThat(testSealData.getBase64Str()).isEqualTo(DEFAULT_BASE_64_STR);
        assertThat(testSealData.getBase64StrContentType()).isEqualTo(DEFAULT_BASE_64_STR_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createSealDataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sealDataRepository.findAll().size();

        // Create the SealData with an existing ID
        sealData.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSealDataMockMvc.perform(post("/api/seal-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sealData)))
            .andExpect(status().isBadRequest());

        // Validate the SealData in the database
        List<SealData> sealDataList = sealDataRepository.findAll();
        assertThat(sealDataList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSealData() throws Exception {
        // Initialize the database
        sealDataRepository.saveAndFlush(sealData);

        // Get all the sealDataList
        restSealDataMockMvc.perform(get("/api/seal-data?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sealData.getId().intValue())))
            .andExpect(jsonPath("$.[*].base64StrContentType").value(hasItem(DEFAULT_BASE_64_STR_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].base64Str").value(hasItem(Base64Utils.encodeToString(DEFAULT_BASE_64_STR))));
    }
    
    @Test
    @Transactional
    public void getSealData() throws Exception {
        // Initialize the database
        sealDataRepository.saveAndFlush(sealData);

        // Get the sealData
        restSealDataMockMvc.perform(get("/api/seal-data/{id}", sealData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sealData.getId().intValue()))
            .andExpect(jsonPath("$.base64StrContentType").value(DEFAULT_BASE_64_STR_CONTENT_TYPE))
            .andExpect(jsonPath("$.base64Str").value(Base64Utils.encodeToString(DEFAULT_BASE_64_STR)));
    }
    @Test
    @Transactional
    public void getNonExistingSealData() throws Exception {
        // Get the sealData
        restSealDataMockMvc.perform(get("/api/seal-data/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSealData() throws Exception {
        // Initialize the database
        sealDataService.save(sealData);

        int databaseSizeBeforeUpdate = sealDataRepository.findAll().size();

        // Update the sealData
        SealData updatedSealData = sealDataRepository.findById(sealData.getId()).get();
        // Disconnect from session so that the updates on updatedSealData are not directly saved in db
        em.detach(updatedSealData);
        updatedSealData
            .base64Str(UPDATED_BASE_64_STR)
            .base64StrContentType(UPDATED_BASE_64_STR_CONTENT_TYPE);

        restSealDataMockMvc.perform(put("/api/seal-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSealData)))
            .andExpect(status().isOk());

        // Validate the SealData in the database
        List<SealData> sealDataList = sealDataRepository.findAll();
        assertThat(sealDataList).hasSize(databaseSizeBeforeUpdate);
        SealData testSealData = sealDataList.get(sealDataList.size() - 1);
        assertThat(testSealData.getBase64Str()).isEqualTo(UPDATED_BASE_64_STR);
        assertThat(testSealData.getBase64StrContentType()).isEqualTo(UPDATED_BASE_64_STR_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingSealData() throws Exception {
        int databaseSizeBeforeUpdate = sealDataRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSealDataMockMvc.perform(put("/api/seal-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sealData)))
            .andExpect(status().isBadRequest());

        // Validate the SealData in the database
        List<SealData> sealDataList = sealDataRepository.findAll();
        assertThat(sealDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSealData() throws Exception {
        // Initialize the database
        sealDataService.save(sealData);

        int databaseSizeBeforeDelete = sealDataRepository.findAll().size();

        // Delete the sealData
        restSealDataMockMvc.perform(delete("/api/seal-data/{id}", sealData.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SealData> sealDataList = sealDataRepository.findAll();
        assertThat(sealDataList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
