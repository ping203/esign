package com.kyanite.esign.web.rest;

import com.kyanite.esign.EsignApp;
import com.kyanite.esign.domain.DdUser;
import com.kyanite.esign.repository.DdUserRepository;
import com.kyanite.esign.service.DdUserService;

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
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DdUserResource} REST controller.
 */
@SpringBootTest(classes = EsignApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DdUserResourceIT {

    private static final String DEFAULT_UNIONID = "AAAAAAAAAA";
    private static final String UPDATED_UNIONID = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final String DEFAULT_USERID = "AAAAAAAAAA";
    private static final String UPDATED_USERID = "BBBBBBBBBB";

    private static final String DEFAULT_IS_LEADER_IN_DEPTS = "AAAAAAAAAA";
    private static final String UPDATED_IS_LEADER_IN_DEPTS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_BOSS = false;
    private static final Boolean UPDATED_IS_BOSS = true;

    private static final BigDecimal DEFAULT_HIRED_DATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_HIRED_DATE = new BigDecimal(2);

    private static final Boolean DEFAULT_IS_SENIOR = false;
    private static final Boolean UPDATED_IS_SENIOR = true;

    private static final String DEFAULT_TEL = "AAAAAAAAAA";
    private static final String UPDATED_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_DEPARTMENT = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENT = "BBBBBBBBBB";

    private static final String DEFAULT_WORK_PLACE = "AAAAAAAAAA";
    private static final String UPDATED_WORK_PLACE = "BBBBBBBBBB";

    private static final String DEFAULT_ORDER_IN_DEPTS = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_IN_DEPTS = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE = "BBBBBBBBBB";

    private static final String DEFAULT_ERRMSG = "AAAAAAAAAA";
    private static final String UPDATED_ERRMSG = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ACTIVE = false;
    private static final Boolean UPDATED_ACTIVE = true;

    private static final String DEFAULT_AVATAR = "AAAAAAAAAA";
    private static final String UPDATED_AVATAR = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ADMIN = false;
    private static final Boolean UPDATED_IS_ADMIN = true;

    private static final Boolean DEFAULT_IS_HIDE = false;
    private static final Boolean UPDATED_IS_HIDE = true;

    private static final String DEFAULT_JOBNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_JOBNUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EXTATTR = "AAAAAAAAAA";
    private static final String UPDATED_EXTATTR = "BBBBBBBBBB";

    private static final String DEFAULT_STATE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_STATE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_POSITION = "AAAAAAAAAA";
    private static final String UPDATED_POSITION = "BBBBBBBBBB";

    private static final String DEFAULT_ROLES = "AAAAAAAAAA";
    private static final String UPDATED_ROLES = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_ID = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ID_NUMBER = "BBBBBBBBBB";

    @Autowired
    private DdUserRepository ddUserRepository;

    @Autowired
    private DdUserService ddUserService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDdUserMockMvc;

    private DdUser ddUser;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DdUser createEntity(EntityManager em) {
        DdUser ddUser = new DdUser()
            .unionid(DEFAULT_UNIONID)
            .remark(DEFAULT_REMARK)
            .userid(DEFAULT_USERID)
            .isLeaderInDepts(DEFAULT_IS_LEADER_IN_DEPTS)
            .isBoss(DEFAULT_IS_BOSS)
            .hiredDate(DEFAULT_HIRED_DATE)
            .isSenior(DEFAULT_IS_SENIOR)
            .tel(DEFAULT_TEL)
            .department(DEFAULT_DEPARTMENT)
            .workPlace(DEFAULT_WORK_PLACE)
            .orderInDepts(DEFAULT_ORDER_IN_DEPTS)
            .mobile(DEFAULT_MOBILE)
            .errmsg(DEFAULT_ERRMSG)
            .active(DEFAULT_ACTIVE)
            .avatar(DEFAULT_AVATAR)
            .isAdmin(DEFAULT_IS_ADMIN)
            .isHide(DEFAULT_IS_HIDE)
            .jobnumber(DEFAULT_JOBNUMBER)
            .name(DEFAULT_NAME)
            .extattr(DEFAULT_EXTATTR)
            .stateCode(DEFAULT_STATE_CODE)
            .position(DEFAULT_POSITION)
            .roles(DEFAULT_ROLES)
            .accountId(DEFAULT_ACCOUNT_ID)
            .idNumber(DEFAULT_ID_NUMBER);
        return ddUser;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DdUser createUpdatedEntity(EntityManager em) {
        DdUser ddUser = new DdUser()
            .unionid(UPDATED_UNIONID)
            .remark(UPDATED_REMARK)
            .userid(UPDATED_USERID)
            .isLeaderInDepts(UPDATED_IS_LEADER_IN_DEPTS)
            .isBoss(UPDATED_IS_BOSS)
            .hiredDate(UPDATED_HIRED_DATE)
            .isSenior(UPDATED_IS_SENIOR)
            .tel(UPDATED_TEL)
            .department(UPDATED_DEPARTMENT)
            .workPlace(UPDATED_WORK_PLACE)
            .orderInDepts(UPDATED_ORDER_IN_DEPTS)
            .mobile(UPDATED_MOBILE)
            .errmsg(UPDATED_ERRMSG)
            .active(UPDATED_ACTIVE)
            .avatar(UPDATED_AVATAR)
            .isAdmin(UPDATED_IS_ADMIN)
            .isHide(UPDATED_IS_HIDE)
            .jobnumber(UPDATED_JOBNUMBER)
            .name(UPDATED_NAME)
            .extattr(UPDATED_EXTATTR)
            .stateCode(UPDATED_STATE_CODE)
            .position(UPDATED_POSITION)
            .roles(UPDATED_ROLES)
            .accountId(UPDATED_ACCOUNT_ID)
            .idNumber(UPDATED_ID_NUMBER);
        return ddUser;
    }

    @BeforeEach
    public void initTest() {
        ddUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createDdUser() throws Exception {
        int databaseSizeBeforeCreate = ddUserRepository.findAll().size();
        // Create the DdUser
        restDdUserMockMvc.perform(post("/api/dd-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ddUser)))
            .andExpect(status().isCreated());

        // Validate the DdUser in the database
        List<DdUser> ddUserList = ddUserRepository.findAll();
        assertThat(ddUserList).hasSize(databaseSizeBeforeCreate + 1);
        DdUser testDdUser = ddUserList.get(ddUserList.size() - 1);
        assertThat(testDdUser.getUnionid()).isEqualTo(DEFAULT_UNIONID);
        assertThat(testDdUser.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testDdUser.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testDdUser.getIsLeaderInDepts()).isEqualTo(DEFAULT_IS_LEADER_IN_DEPTS);
        assertThat(testDdUser.isIsBoss()).isEqualTo(DEFAULT_IS_BOSS);
        assertThat(testDdUser.getHiredDate()).isEqualTo(DEFAULT_HIRED_DATE);
        assertThat(testDdUser.isIsSenior()).isEqualTo(DEFAULT_IS_SENIOR);
        assertThat(testDdUser.getTel()).isEqualTo(DEFAULT_TEL);
        assertThat(testDdUser.getDepartment()).isEqualTo(DEFAULT_DEPARTMENT);
        assertThat(testDdUser.getWorkPlace()).isEqualTo(DEFAULT_WORK_PLACE);
        assertThat(testDdUser.getOrderInDepts()).isEqualTo(DEFAULT_ORDER_IN_DEPTS);
        assertThat(testDdUser.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testDdUser.getErrmsg()).isEqualTo(DEFAULT_ERRMSG);
        assertThat(testDdUser.isActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testDdUser.getAvatar()).isEqualTo(DEFAULT_AVATAR);
        assertThat(testDdUser.isIsAdmin()).isEqualTo(DEFAULT_IS_ADMIN);
        assertThat(testDdUser.isIsHide()).isEqualTo(DEFAULT_IS_HIDE);
        assertThat(testDdUser.getJobnumber()).isEqualTo(DEFAULT_JOBNUMBER);
        assertThat(testDdUser.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDdUser.getExtattr()).isEqualTo(DEFAULT_EXTATTR);
        assertThat(testDdUser.getStateCode()).isEqualTo(DEFAULT_STATE_CODE);
        assertThat(testDdUser.getPosition()).isEqualTo(DEFAULT_POSITION);
        assertThat(testDdUser.getRoles()).isEqualTo(DEFAULT_ROLES);
        assertThat(testDdUser.getAccountId()).isEqualTo(DEFAULT_ACCOUNT_ID);
        assertThat(testDdUser.getIdNumber()).isEqualTo(DEFAULT_ID_NUMBER);
    }

    @Test
    @Transactional
    public void createDdUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ddUserRepository.findAll().size();

        // Create the DdUser with an existing ID
        ddUser.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDdUserMockMvc.perform(post("/api/dd-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ddUser)))
            .andExpect(status().isBadRequest());

        // Validate the DdUser in the database
        List<DdUser> ddUserList = ddUserRepository.findAll();
        assertThat(ddUserList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDdUsers() throws Exception {
        // Initialize the database
        ddUserRepository.saveAndFlush(ddUser);

        // Get all the ddUserList
        restDdUserMockMvc.perform(get("/api/dd-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ddUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].unionid").value(hasItem(DEFAULT_UNIONID)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID)))
            .andExpect(jsonPath("$.[*].isLeaderInDepts").value(hasItem(DEFAULT_IS_LEADER_IN_DEPTS)))
            .andExpect(jsonPath("$.[*].isBoss").value(hasItem(DEFAULT_IS_BOSS.booleanValue())))
            .andExpect(jsonPath("$.[*].hiredDate").value(hasItem(DEFAULT_HIRED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].isSenior").value(hasItem(DEFAULT_IS_SENIOR.booleanValue())))
            .andExpect(jsonPath("$.[*].tel").value(hasItem(DEFAULT_TEL)))
            .andExpect(jsonPath("$.[*].department").value(hasItem(DEFAULT_DEPARTMENT)))
            .andExpect(jsonPath("$.[*].workPlace").value(hasItem(DEFAULT_WORK_PLACE)))
            .andExpect(jsonPath("$.[*].orderInDepts").value(hasItem(DEFAULT_ORDER_IN_DEPTS)))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE)))
            .andExpect(jsonPath("$.[*].errmsg").value(hasItem(DEFAULT_ERRMSG)))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].avatar").value(hasItem(DEFAULT_AVATAR)))
            .andExpect(jsonPath("$.[*].isAdmin").value(hasItem(DEFAULT_IS_ADMIN.booleanValue())))
            .andExpect(jsonPath("$.[*].isHide").value(hasItem(DEFAULT_IS_HIDE.booleanValue())))
            .andExpect(jsonPath("$.[*].jobnumber").value(hasItem(DEFAULT_JOBNUMBER)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].extattr").value(hasItem(DEFAULT_EXTATTR)))
            .andExpect(jsonPath("$.[*].stateCode").value(hasItem(DEFAULT_STATE_CODE)))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION)))
            .andExpect(jsonPath("$.[*].roles").value(hasItem(DEFAULT_ROLES)))
            .andExpect(jsonPath("$.[*].accountId").value(hasItem(DEFAULT_ACCOUNT_ID)))
            .andExpect(jsonPath("$.[*].idNumber").value(hasItem(DEFAULT_ID_NUMBER)));
    }
    
    @Test
    @Transactional
    public void getDdUser() throws Exception {
        // Initialize the database
        ddUserRepository.saveAndFlush(ddUser);

        // Get the ddUser
        restDdUserMockMvc.perform(get("/api/dd-users/{id}", ddUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ddUser.getId().intValue()))
            .andExpect(jsonPath("$.unionid").value(DEFAULT_UNIONID))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID))
            .andExpect(jsonPath("$.isLeaderInDepts").value(DEFAULT_IS_LEADER_IN_DEPTS))
            .andExpect(jsonPath("$.isBoss").value(DEFAULT_IS_BOSS.booleanValue()))
            .andExpect(jsonPath("$.hiredDate").value(DEFAULT_HIRED_DATE.intValue()))
            .andExpect(jsonPath("$.isSenior").value(DEFAULT_IS_SENIOR.booleanValue()))
            .andExpect(jsonPath("$.tel").value(DEFAULT_TEL))
            .andExpect(jsonPath("$.department").value(DEFAULT_DEPARTMENT))
            .andExpect(jsonPath("$.workPlace").value(DEFAULT_WORK_PLACE))
            .andExpect(jsonPath("$.orderInDepts").value(DEFAULT_ORDER_IN_DEPTS))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE))
            .andExpect(jsonPath("$.errmsg").value(DEFAULT_ERRMSG))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.avatar").value(DEFAULT_AVATAR))
            .andExpect(jsonPath("$.isAdmin").value(DEFAULT_IS_ADMIN.booleanValue()))
            .andExpect(jsonPath("$.isHide").value(DEFAULT_IS_HIDE.booleanValue()))
            .andExpect(jsonPath("$.jobnumber").value(DEFAULT_JOBNUMBER))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.extattr").value(DEFAULT_EXTATTR))
            .andExpect(jsonPath("$.stateCode").value(DEFAULT_STATE_CODE))
            .andExpect(jsonPath("$.position").value(DEFAULT_POSITION))
            .andExpect(jsonPath("$.roles").value(DEFAULT_ROLES))
            .andExpect(jsonPath("$.accountId").value(DEFAULT_ACCOUNT_ID))
            .andExpect(jsonPath("$.idNumber").value(DEFAULT_ID_NUMBER));
    }
    @Test
    @Transactional
    public void getNonExistingDdUser() throws Exception {
        // Get the ddUser
        restDdUserMockMvc.perform(get("/api/dd-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDdUser() throws Exception {
        // Initialize the database
        ddUserService.save(ddUser);

        int databaseSizeBeforeUpdate = ddUserRepository.findAll().size();

        // Update the ddUser
        DdUser updatedDdUser = ddUserRepository.findById(ddUser.getId()).get();
        // Disconnect from session so that the updates on updatedDdUser are not directly saved in db
        em.detach(updatedDdUser);
        updatedDdUser
            .unionid(UPDATED_UNIONID)
            .remark(UPDATED_REMARK)
            .userid(UPDATED_USERID)
            .isLeaderInDepts(UPDATED_IS_LEADER_IN_DEPTS)
            .isBoss(UPDATED_IS_BOSS)
            .hiredDate(UPDATED_HIRED_DATE)
            .isSenior(UPDATED_IS_SENIOR)
            .tel(UPDATED_TEL)
            .department(UPDATED_DEPARTMENT)
            .workPlace(UPDATED_WORK_PLACE)
            .orderInDepts(UPDATED_ORDER_IN_DEPTS)
            .mobile(UPDATED_MOBILE)
            .errmsg(UPDATED_ERRMSG)
            .active(UPDATED_ACTIVE)
            .avatar(UPDATED_AVATAR)
            .isAdmin(UPDATED_IS_ADMIN)
            .isHide(UPDATED_IS_HIDE)
            .jobnumber(UPDATED_JOBNUMBER)
            .name(UPDATED_NAME)
            .extattr(UPDATED_EXTATTR)
            .stateCode(UPDATED_STATE_CODE)
            .position(UPDATED_POSITION)
            .roles(UPDATED_ROLES)
            .accountId(UPDATED_ACCOUNT_ID)
            .idNumber(UPDATED_ID_NUMBER);

        restDdUserMockMvc.perform(put("/api/dd-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedDdUser)))
            .andExpect(status().isOk());

        // Validate the DdUser in the database
        List<DdUser> ddUserList = ddUserRepository.findAll();
        assertThat(ddUserList).hasSize(databaseSizeBeforeUpdate);
        DdUser testDdUser = ddUserList.get(ddUserList.size() - 1);
        assertThat(testDdUser.getUnionid()).isEqualTo(UPDATED_UNIONID);
        assertThat(testDdUser.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testDdUser.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testDdUser.getIsLeaderInDepts()).isEqualTo(UPDATED_IS_LEADER_IN_DEPTS);
        assertThat(testDdUser.isIsBoss()).isEqualTo(UPDATED_IS_BOSS);
        assertThat(testDdUser.getHiredDate()).isEqualTo(UPDATED_HIRED_DATE);
        assertThat(testDdUser.isIsSenior()).isEqualTo(UPDATED_IS_SENIOR);
        assertThat(testDdUser.getTel()).isEqualTo(UPDATED_TEL);
        assertThat(testDdUser.getDepartment()).isEqualTo(UPDATED_DEPARTMENT);
        assertThat(testDdUser.getWorkPlace()).isEqualTo(UPDATED_WORK_PLACE);
        assertThat(testDdUser.getOrderInDepts()).isEqualTo(UPDATED_ORDER_IN_DEPTS);
        assertThat(testDdUser.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testDdUser.getErrmsg()).isEqualTo(UPDATED_ERRMSG);
        assertThat(testDdUser.isActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testDdUser.getAvatar()).isEqualTo(UPDATED_AVATAR);
        assertThat(testDdUser.isIsAdmin()).isEqualTo(UPDATED_IS_ADMIN);
        assertThat(testDdUser.isIsHide()).isEqualTo(UPDATED_IS_HIDE);
        assertThat(testDdUser.getJobnumber()).isEqualTo(UPDATED_JOBNUMBER);
        assertThat(testDdUser.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDdUser.getExtattr()).isEqualTo(UPDATED_EXTATTR);
        assertThat(testDdUser.getStateCode()).isEqualTo(UPDATED_STATE_CODE);
        assertThat(testDdUser.getPosition()).isEqualTo(UPDATED_POSITION);
        assertThat(testDdUser.getRoles()).isEqualTo(UPDATED_ROLES);
        assertThat(testDdUser.getAccountId()).isEqualTo(UPDATED_ACCOUNT_ID);
        assertThat(testDdUser.getIdNumber()).isEqualTo(UPDATED_ID_NUMBER);
    }

    @Test
    @Transactional
    public void updateNonExistingDdUser() throws Exception {
        int databaseSizeBeforeUpdate = ddUserRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDdUserMockMvc.perform(put("/api/dd-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ddUser)))
            .andExpect(status().isBadRequest());

        // Validate the DdUser in the database
        List<DdUser> ddUserList = ddUserRepository.findAll();
        assertThat(ddUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDdUser() throws Exception {
        // Initialize the database
        ddUserService.save(ddUser);

        int databaseSizeBeforeDelete = ddUserRepository.findAll().size();

        // Delete the ddUser
        restDdUserMockMvc.perform(delete("/api/dd-users/{id}", ddUser.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DdUser> ddUserList = ddUserRepository.findAll();
        assertThat(ddUserList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
