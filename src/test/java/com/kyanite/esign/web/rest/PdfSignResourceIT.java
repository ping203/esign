package com.kyanite.esign.web.rest;

import com.kyanite.esign.EsignApp;
import com.kyanite.esign.domain.PdfSign;
import com.kyanite.esign.repository.PdfSignRepository;
import com.kyanite.esign.service.PdfSignService;

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

import com.kyanite.esign.domain.enumeration.PdfSignStatus;
import com.kyanite.esign.domain.enumeration.CycleUnit;
/**
 * Integration tests for the {@link PdfSignResource} REST controller.
 */
@SpringBootTest(classes = EsignApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PdfSignResourceIT {

    private static final String DEFAULT_CHANNEL_NO = "AAAAAAAAAA";
    private static final String UPDATED_CHANNEL_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_ID = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PDF_URL = "AAAAAAAAAA";
    private static final String UPDATED_PDF_URL = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_POS_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_POS_TYPE = "BBBBBBBBBB";

    private static final Long DEFAULT_POS_X = 1L;
    private static final Long UPDATED_POS_X = 2L;

    private static final Long DEFAULT_POS_Y = 1L;
    private static final Long UPDATED_POS_Y = 2L;

    private static final Long DEFAULT_WIDTH = 1L;
    private static final Long UPDATED_WIDTH = 2L;

    private static final String DEFAULT_SIGN_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SIGN_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_NO = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_NO = "BBBBBBBBBB";

    private static final Instant DEFAULT_REQUEST_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_REQUEST_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final PdfSignStatus DEFAULT_STATUS = PdfSignStatus.NotActive;
    private static final PdfSignStatus UPDATED_STATUS = PdfSignStatus.Effective;

    private static final Long DEFAULT_CYCLE = 1L;
    private static final Long UPDATED_CYCLE = 2L;

    private static final CycleUnit DEFAULT_CYCLE_UNIT = CycleUnit.Hour;
    private static final CycleUnit UPDATED_CYCLE_UNIT = CycleUnit.Day;

    private static final Long DEFAULT_RETRY = 1L;
    private static final Long UPDATED_RETRY = 2L;

    private static final Boolean DEFAULT_RETRY_SWITCH = false;
    private static final Boolean UPDATED_RETRY_SWITCH = true;

    private static final Long DEFAULT_RETRY_COUNT = 1L;
    private static final Long UPDATED_RETRY_COUNT = 2L;

    @Autowired
    private PdfSignRepository pdfSignRepository;

    @Autowired
    private PdfSignService pdfSignService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPdfSignMockMvc;

    private PdfSign pdfSign;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PdfSign createEntity(EntityManager em) {
        PdfSign pdfSign = new PdfSign()
            .channelNo(DEFAULT_CHANNEL_NO)
            .accountId(DEFAULT_ACCOUNT_ID)
            .pdfUrl(DEFAULT_PDF_URL)
            .fileName(DEFAULT_FILE_NAME)
            .key(DEFAULT_KEY)
            .posType(DEFAULT_POS_TYPE)
            .posX(DEFAULT_POS_X)
            .posY(DEFAULT_POS_Y)
            .width(DEFAULT_WIDTH)
            .signType(DEFAULT_SIGN_TYPE)
            .requestNo(DEFAULT_REQUEST_NO)
            .requestTime(DEFAULT_REQUEST_TIME)
            .status(DEFAULT_STATUS)
            .cycle(DEFAULT_CYCLE)
            .cycleUnit(DEFAULT_CYCLE_UNIT)
            .retry(DEFAULT_RETRY)
            .retrySwitch(DEFAULT_RETRY_SWITCH)
            .retryCount(DEFAULT_RETRY_COUNT);
        return pdfSign;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PdfSign createUpdatedEntity(EntityManager em) {
        PdfSign pdfSign = new PdfSign()
            .channelNo(UPDATED_CHANNEL_NO)
            .accountId(UPDATED_ACCOUNT_ID)
            .pdfUrl(UPDATED_PDF_URL)
            .fileName(UPDATED_FILE_NAME)
            .key(UPDATED_KEY)
            .posType(UPDATED_POS_TYPE)
            .posX(UPDATED_POS_X)
            .posY(UPDATED_POS_Y)
            .width(UPDATED_WIDTH)
            .signType(UPDATED_SIGN_TYPE)
            .requestNo(UPDATED_REQUEST_NO)
            .requestTime(UPDATED_REQUEST_TIME)
            .status(UPDATED_STATUS)
            .cycle(UPDATED_CYCLE)
            .cycleUnit(UPDATED_CYCLE_UNIT)
            .retry(UPDATED_RETRY)
            .retrySwitch(UPDATED_RETRY_SWITCH)
            .retryCount(UPDATED_RETRY_COUNT);
        return pdfSign;
    }

    @BeforeEach
    public void initTest() {
        pdfSign = createEntity(em);
    }

    @Test
    @Transactional
    public void createPdfSign() throws Exception {
        int databaseSizeBeforeCreate = pdfSignRepository.findAll().size();
        // Create the PdfSign
        restPdfSignMockMvc.perform(post("/api/pdf-signs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pdfSign)))
            .andExpect(status().isCreated());

        // Validate the PdfSign in the database
        List<PdfSign> pdfSignList = pdfSignRepository.findAll();
        assertThat(pdfSignList).hasSize(databaseSizeBeforeCreate + 1);
        PdfSign testPdfSign = pdfSignList.get(pdfSignList.size() - 1);
        assertThat(testPdfSign.getChannelNo()).isEqualTo(DEFAULT_CHANNEL_NO);
        assertThat(testPdfSign.getAccountId()).isEqualTo(DEFAULT_ACCOUNT_ID);
        assertThat(testPdfSign.getPdfUrl()).isEqualTo(DEFAULT_PDF_URL);
        assertThat(testPdfSign.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
        assertThat(testPdfSign.getKey()).isEqualTo(DEFAULT_KEY);
        assertThat(testPdfSign.getPosType()).isEqualTo(DEFAULT_POS_TYPE);
        assertThat(testPdfSign.getPosX()).isEqualTo(DEFAULT_POS_X);
        assertThat(testPdfSign.getPosY()).isEqualTo(DEFAULT_POS_Y);
        assertThat(testPdfSign.getWidth()).isEqualTo(DEFAULT_WIDTH);
        assertThat(testPdfSign.getSignType()).isEqualTo(DEFAULT_SIGN_TYPE);
        assertThat(testPdfSign.getRequestNo()).isEqualTo(DEFAULT_REQUEST_NO);
        assertThat(testPdfSign.getRequestTime()).isEqualTo(DEFAULT_REQUEST_TIME);
        assertThat(testPdfSign.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPdfSign.getCycle()).isEqualTo(DEFAULT_CYCLE);
        assertThat(testPdfSign.getCycleUnit()).isEqualTo(DEFAULT_CYCLE_UNIT);
        assertThat(testPdfSign.getRetry()).isEqualTo(DEFAULT_RETRY);
        assertThat(testPdfSign.isRetrySwitch()).isEqualTo(DEFAULT_RETRY_SWITCH);
        assertThat(testPdfSign.getRetryCount()).isEqualTo(DEFAULT_RETRY_COUNT);
    }

    @Test
    @Transactional
    public void createPdfSignWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pdfSignRepository.findAll().size();

        // Create the PdfSign with an existing ID
        pdfSign.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPdfSignMockMvc.perform(post("/api/pdf-signs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pdfSign)))
            .andExpect(status().isBadRequest());

        // Validate the PdfSign in the database
        List<PdfSign> pdfSignList = pdfSignRepository.findAll();
        assertThat(pdfSignList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPdfSigns() throws Exception {
        // Initialize the database
        pdfSignRepository.saveAndFlush(pdfSign);

        // Get all the pdfSignList
        restPdfSignMockMvc.perform(get("/api/pdf-signs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pdfSign.getId().intValue())))
            .andExpect(jsonPath("$.[*].channelNo").value(hasItem(DEFAULT_CHANNEL_NO)))
            .andExpect(jsonPath("$.[*].accountId").value(hasItem(DEFAULT_ACCOUNT_ID)))
            .andExpect(jsonPath("$.[*].pdfUrl").value(hasItem(DEFAULT_PDF_URL)))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME)))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY)))
            .andExpect(jsonPath("$.[*].posType").value(hasItem(DEFAULT_POS_TYPE)))
            .andExpect(jsonPath("$.[*].posX").value(hasItem(DEFAULT_POS_X.intValue())))
            .andExpect(jsonPath("$.[*].posY").value(hasItem(DEFAULT_POS_Y.intValue())))
            .andExpect(jsonPath("$.[*].width").value(hasItem(DEFAULT_WIDTH.intValue())))
            .andExpect(jsonPath("$.[*].signType").value(hasItem(DEFAULT_SIGN_TYPE)))
            .andExpect(jsonPath("$.[*].requestNo").value(hasItem(DEFAULT_REQUEST_NO)))
            .andExpect(jsonPath("$.[*].requestTime").value(hasItem(DEFAULT_REQUEST_TIME.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].cycle").value(hasItem(DEFAULT_CYCLE.intValue())))
            .andExpect(jsonPath("$.[*].cycleUnit").value(hasItem(DEFAULT_CYCLE_UNIT.toString())))
            .andExpect(jsonPath("$.[*].retry").value(hasItem(DEFAULT_RETRY.intValue())))
            .andExpect(jsonPath("$.[*].retrySwitch").value(hasItem(DEFAULT_RETRY_SWITCH.booleanValue())))
            .andExpect(jsonPath("$.[*].retryCount").value(hasItem(DEFAULT_RETRY_COUNT.intValue())));
    }
    
    @Test
    @Transactional
    public void getPdfSign() throws Exception {
        // Initialize the database
        pdfSignRepository.saveAndFlush(pdfSign);

        // Get the pdfSign
        restPdfSignMockMvc.perform(get("/api/pdf-signs/{id}", pdfSign.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pdfSign.getId().intValue()))
            .andExpect(jsonPath("$.channelNo").value(DEFAULT_CHANNEL_NO))
            .andExpect(jsonPath("$.accountId").value(DEFAULT_ACCOUNT_ID))
            .andExpect(jsonPath("$.pdfUrl").value(DEFAULT_PDF_URL))
            .andExpect(jsonPath("$.fileName").value(DEFAULT_FILE_NAME))
            .andExpect(jsonPath("$.key").value(DEFAULT_KEY))
            .andExpect(jsonPath("$.posType").value(DEFAULT_POS_TYPE))
            .andExpect(jsonPath("$.posX").value(DEFAULT_POS_X.intValue()))
            .andExpect(jsonPath("$.posY").value(DEFAULT_POS_Y.intValue()))
            .andExpect(jsonPath("$.width").value(DEFAULT_WIDTH.intValue()))
            .andExpect(jsonPath("$.signType").value(DEFAULT_SIGN_TYPE))
            .andExpect(jsonPath("$.requestNo").value(DEFAULT_REQUEST_NO))
            .andExpect(jsonPath("$.requestTime").value(DEFAULT_REQUEST_TIME.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.cycle").value(DEFAULT_CYCLE.intValue()))
            .andExpect(jsonPath("$.cycleUnit").value(DEFAULT_CYCLE_UNIT.toString()))
            .andExpect(jsonPath("$.retry").value(DEFAULT_RETRY.intValue()))
            .andExpect(jsonPath("$.retrySwitch").value(DEFAULT_RETRY_SWITCH.booleanValue()))
            .andExpect(jsonPath("$.retryCount").value(DEFAULT_RETRY_COUNT.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPdfSign() throws Exception {
        // Get the pdfSign
        restPdfSignMockMvc.perform(get("/api/pdf-signs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePdfSign() throws Exception {
        // Initialize the database
        pdfSignService.save(pdfSign);

        int databaseSizeBeforeUpdate = pdfSignRepository.findAll().size();

        // Update the pdfSign
        PdfSign updatedPdfSign = pdfSignRepository.findById(pdfSign.getId()).get();
        // Disconnect from session so that the updates on updatedPdfSign are not directly saved in db
        em.detach(updatedPdfSign);
        updatedPdfSign
            .channelNo(UPDATED_CHANNEL_NO)
            .accountId(UPDATED_ACCOUNT_ID)
            .pdfUrl(UPDATED_PDF_URL)
            .fileName(UPDATED_FILE_NAME)
            .key(UPDATED_KEY)
            .posType(UPDATED_POS_TYPE)
            .posX(UPDATED_POS_X)
            .posY(UPDATED_POS_Y)
            .width(UPDATED_WIDTH)
            .signType(UPDATED_SIGN_TYPE)
            .requestNo(UPDATED_REQUEST_NO)
            .requestTime(UPDATED_REQUEST_TIME)
            .status(UPDATED_STATUS)
            .cycle(UPDATED_CYCLE)
            .cycleUnit(UPDATED_CYCLE_UNIT)
            .retry(UPDATED_RETRY)
            .retrySwitch(UPDATED_RETRY_SWITCH)
            .retryCount(UPDATED_RETRY_COUNT);

        restPdfSignMockMvc.perform(put("/api/pdf-signs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPdfSign)))
            .andExpect(status().isOk());

        // Validate the PdfSign in the database
        List<PdfSign> pdfSignList = pdfSignRepository.findAll();
        assertThat(pdfSignList).hasSize(databaseSizeBeforeUpdate);
        PdfSign testPdfSign = pdfSignList.get(pdfSignList.size() - 1);
        assertThat(testPdfSign.getChannelNo()).isEqualTo(UPDATED_CHANNEL_NO);
        assertThat(testPdfSign.getAccountId()).isEqualTo(UPDATED_ACCOUNT_ID);
        assertThat(testPdfSign.getPdfUrl()).isEqualTo(UPDATED_PDF_URL);
        assertThat(testPdfSign.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testPdfSign.getKey()).isEqualTo(UPDATED_KEY);
        assertThat(testPdfSign.getPosType()).isEqualTo(UPDATED_POS_TYPE);
        assertThat(testPdfSign.getPosX()).isEqualTo(UPDATED_POS_X);
        assertThat(testPdfSign.getPosY()).isEqualTo(UPDATED_POS_Y);
        assertThat(testPdfSign.getWidth()).isEqualTo(UPDATED_WIDTH);
        assertThat(testPdfSign.getSignType()).isEqualTo(UPDATED_SIGN_TYPE);
        assertThat(testPdfSign.getRequestNo()).isEqualTo(UPDATED_REQUEST_NO);
        assertThat(testPdfSign.getRequestTime()).isEqualTo(UPDATED_REQUEST_TIME);
        assertThat(testPdfSign.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPdfSign.getCycle()).isEqualTo(UPDATED_CYCLE);
        assertThat(testPdfSign.getCycleUnit()).isEqualTo(UPDATED_CYCLE_UNIT);
        assertThat(testPdfSign.getRetry()).isEqualTo(UPDATED_RETRY);
        assertThat(testPdfSign.isRetrySwitch()).isEqualTo(UPDATED_RETRY_SWITCH);
        assertThat(testPdfSign.getRetryCount()).isEqualTo(UPDATED_RETRY_COUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingPdfSign() throws Exception {
        int databaseSizeBeforeUpdate = pdfSignRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPdfSignMockMvc.perform(put("/api/pdf-signs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pdfSign)))
            .andExpect(status().isBadRequest());

        // Validate the PdfSign in the database
        List<PdfSign> pdfSignList = pdfSignRepository.findAll();
        assertThat(pdfSignList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePdfSign() throws Exception {
        // Initialize the database
        pdfSignService.save(pdfSign);

        int databaseSizeBeforeDelete = pdfSignRepository.findAll().size();

        // Delete the pdfSign
        restPdfSignMockMvc.perform(delete("/api/pdf-signs/{id}", pdfSign.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PdfSign> pdfSignList = pdfSignRepository.findAll();
        assertThat(pdfSignList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
