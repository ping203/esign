package com.kyanite.esign.web.rest;

import com.kyanite.esign.EsignApp;
import com.kyanite.esign.domain.PdfFile;
import com.kyanite.esign.repository.PdfFileRepository;
import com.kyanite.esign.service.PdfFileService;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PdfFileResource} REST controller.
 */
@SpringBootTest(classes = EsignApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PdfFileResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MEDIA_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_MEDIA_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_OBJ_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OBJ_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_URL = "AAAAAAAAAA";
    private static final String UPDATED_FILE_URL = "BBBBBBBBBB";

    private static final String DEFAULT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_KEY = "BBBBBBBBBB";

    @Autowired
    private PdfFileRepository pdfFileRepository;

    @Autowired
    private PdfFileService pdfFileService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPdfFileMockMvc;

    private PdfFile pdfFile;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PdfFile createEntity(EntityManager em) {
        PdfFile pdfFile = new PdfFile()
            .name(DEFAULT_NAME)
            .mediaType(DEFAULT_MEDIA_TYPE)
            .objName(DEFAULT_OBJ_NAME)
            .fileUrl(DEFAULT_FILE_URL)
            .key(DEFAULT_KEY);
        return pdfFile;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PdfFile createUpdatedEntity(EntityManager em) {
        PdfFile pdfFile = new PdfFile()
            .name(UPDATED_NAME)
            .mediaType(UPDATED_MEDIA_TYPE)
            .objName(UPDATED_OBJ_NAME)
            .fileUrl(UPDATED_FILE_URL)
            .key(UPDATED_KEY);
        return pdfFile;
    }

    @BeforeEach
    public void initTest() {
        pdfFile = createEntity(em);
    }

    @Test
    @Transactional
    public void createPdfFile() throws Exception {
        int databaseSizeBeforeCreate = pdfFileRepository.findAll().size();
        // Create the PdfFile
        restPdfFileMockMvc.perform(post("/api/pdf-files")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pdfFile)))
            .andExpect(status().isCreated());

        // Validate the PdfFile in the database
        List<PdfFile> pdfFileList = pdfFileRepository.findAll();
        assertThat(pdfFileList).hasSize(databaseSizeBeforeCreate + 1);
        PdfFile testPdfFile = pdfFileList.get(pdfFileList.size() - 1);
        assertThat(testPdfFile.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPdfFile.getMediaType()).isEqualTo(DEFAULT_MEDIA_TYPE);
        assertThat(testPdfFile.getObjName()).isEqualTo(DEFAULT_OBJ_NAME);
        assertThat(testPdfFile.getFileUrl()).isEqualTo(DEFAULT_FILE_URL);
        assertThat(testPdfFile.getKey()).isEqualTo(DEFAULT_KEY);
    }

    @Test
    @Transactional
    public void createPdfFileWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pdfFileRepository.findAll().size();

        // Create the PdfFile with an existing ID
        pdfFile.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPdfFileMockMvc.perform(post("/api/pdf-files")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pdfFile)))
            .andExpect(status().isBadRequest());

        // Validate the PdfFile in the database
        List<PdfFile> pdfFileList = pdfFileRepository.findAll();
        assertThat(pdfFileList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPdfFiles() throws Exception {
        // Initialize the database
        pdfFileRepository.saveAndFlush(pdfFile);

        // Get all the pdfFileList
        restPdfFileMockMvc.perform(get("/api/pdf-files?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pdfFile.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].mediaType").value(hasItem(DEFAULT_MEDIA_TYPE)))
            .andExpect(jsonPath("$.[*].objName").value(hasItem(DEFAULT_OBJ_NAME)))
            .andExpect(jsonPath("$.[*].fileUrl").value(hasItem(DEFAULT_FILE_URL)))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY)));
    }
    
    @Test
    @Transactional
    public void getPdfFile() throws Exception {
        // Initialize the database
        pdfFileRepository.saveAndFlush(pdfFile);

        // Get the pdfFile
        restPdfFileMockMvc.perform(get("/api/pdf-files/{id}", pdfFile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pdfFile.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.mediaType").value(DEFAULT_MEDIA_TYPE))
            .andExpect(jsonPath("$.objName").value(DEFAULT_OBJ_NAME))
            .andExpect(jsonPath("$.fileUrl").value(DEFAULT_FILE_URL))
            .andExpect(jsonPath("$.key").value(DEFAULT_KEY));
    }
    @Test
    @Transactional
    public void getNonExistingPdfFile() throws Exception {
        // Get the pdfFile
        restPdfFileMockMvc.perform(get("/api/pdf-files/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePdfFile() throws Exception {
        // Initialize the database
        pdfFileService.save(pdfFile);

        int databaseSizeBeforeUpdate = pdfFileRepository.findAll().size();

        // Update the pdfFile
        PdfFile updatedPdfFile = pdfFileRepository.findById(pdfFile.getId()).get();
        // Disconnect from session so that the updates on updatedPdfFile are not directly saved in db
        em.detach(updatedPdfFile);
        updatedPdfFile
            .name(UPDATED_NAME)
            .mediaType(UPDATED_MEDIA_TYPE)
            .objName(UPDATED_OBJ_NAME)
            .fileUrl(UPDATED_FILE_URL)
            .key(UPDATED_KEY);

        restPdfFileMockMvc.perform(put("/api/pdf-files")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPdfFile)))
            .andExpect(status().isOk());

        // Validate the PdfFile in the database
        List<PdfFile> pdfFileList = pdfFileRepository.findAll();
        assertThat(pdfFileList).hasSize(databaseSizeBeforeUpdate);
        PdfFile testPdfFile = pdfFileList.get(pdfFileList.size() - 1);
        assertThat(testPdfFile.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPdfFile.getMediaType()).isEqualTo(UPDATED_MEDIA_TYPE);
        assertThat(testPdfFile.getObjName()).isEqualTo(UPDATED_OBJ_NAME);
        assertThat(testPdfFile.getFileUrl()).isEqualTo(UPDATED_FILE_URL);
        assertThat(testPdfFile.getKey()).isEqualTo(UPDATED_KEY);
    }

    @Test
    @Transactional
    public void updateNonExistingPdfFile() throws Exception {
        int databaseSizeBeforeUpdate = pdfFileRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPdfFileMockMvc.perform(put("/api/pdf-files")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pdfFile)))
            .andExpect(status().isBadRequest());

        // Validate the PdfFile in the database
        List<PdfFile> pdfFileList = pdfFileRepository.findAll();
        assertThat(pdfFileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePdfFile() throws Exception {
        // Initialize the database
        pdfFileService.save(pdfFile);

        int databaseSizeBeforeDelete = pdfFileRepository.findAll().size();

        // Delete the pdfFile
        restPdfFileMockMvc.perform(delete("/api/pdf-files/{id}", pdfFile.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PdfFile> pdfFileList = pdfFileRepository.findAll();
        assertThat(pdfFileList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
