package com.kyanite.esign.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.kyanite.esign.domain.enumeration.PdfSignStatus;

import com.kyanite.esign.domain.enumeration.CycleUnit;

/**
 * A PdfSign.
 */
@Entity
@Table(name = "pdf_sign")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PdfSign implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "channel_no")
    private String channelNo;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "pdf_url")
    private String pdfUrl;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "jhi_key")
    private String key;

    @Column(name = "pos_type")
    private String posType;

    @Column(name = "pos_x")
    private Long posX;

    @Column(name = "pos_y")
    private Long posY;

    @Column(name = "width")
    private Long width;

    @Column(name = "sign_type")
    private String signType;

    @Column(name = "request_no")
    private String requestNo;

    @Column(name = "request_time")
    private Instant requestTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PdfSignStatus status;

    @Column(name = "cycle")
    private Long cycle;

    @Enumerated(EnumType.STRING)
    @Column(name = "cycle_unit")
    private CycleUnit cycleUnit;

    @Column(name = "retry")
    private Long retry;

    @Column(name = "retry_switch")
    private Boolean retrySwitch;

    @Column(name = "retry_count")
    private Long retryCount;

    @OneToMany(mappedBy = "pdfSign")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<SealData> sealData = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "pdfSigns", allowSetters = true)
    private DdUser ddUser;

    @ManyToOne
    @JsonIgnoreProperties(value = "pdfSigns", allowSetters = true)
    private PdfFile pdfFile;

    @ManyToOne
    @JsonIgnoreProperties(value = "pdfSigns", allowSetters = true)
    private MsgTask msgTask;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public PdfSign channelNo(String channelNo) {
        this.channelNo = channelNo;
        return this;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getAccountId() {
        return accountId;
    }

    public PdfSign accountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public PdfSign pdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
        return this;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public PdfSign fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getKey() {
        return key;
    }

    public PdfSign key(String key) {
        this.key = key;
        return this;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPosType() {
        return posType;
    }

    public PdfSign posType(String posType) {
        this.posType = posType;
        return this;
    }

    public void setPosType(String posType) {
        this.posType = posType;
    }

    public Long getPosX() {
        return posX;
    }

    public PdfSign posX(Long posX) {
        this.posX = posX;
        return this;
    }

    public void setPosX(Long posX) {
        this.posX = posX;
    }

    public Long getPosY() {
        return posY;
    }

    public PdfSign posY(Long posY) {
        this.posY = posY;
        return this;
    }

    public void setPosY(Long posY) {
        this.posY = posY;
    }

    public Long getWidth() {
        return width;
    }

    public PdfSign width(Long width) {
        this.width = width;
        return this;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public String getSignType() {
        return signType;
    }

    public PdfSign signType(String signType) {
        this.signType = signType;
        return this;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public PdfSign requestNo(String requestNo) {
        this.requestNo = requestNo;
        return this;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public Instant getRequestTime() {
        return requestTime;
    }

    public PdfSign requestTime(Instant requestTime) {
        this.requestTime = requestTime;
        return this;
    }

    public void setRequestTime(Instant requestTime) {
        this.requestTime = requestTime;
    }

    public PdfSignStatus getStatus() {
        return status;
    }

    public PdfSign status(PdfSignStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(PdfSignStatus status) {
        this.status = status;
    }

    public Long getCycle() {
        return cycle;
    }

    public PdfSign cycle(Long cycle) {
        this.cycle = cycle;
        return this;
    }

    public void setCycle(Long cycle) {
        this.cycle = cycle;
    }

    public CycleUnit getCycleUnit() {
        return cycleUnit;
    }

    public PdfSign cycleUnit(CycleUnit cycleUnit) {
        this.cycleUnit = cycleUnit;
        return this;
    }

    public void setCycleUnit(CycleUnit cycleUnit) {
        this.cycleUnit = cycleUnit;
    }

    public Long getRetry() {
        return retry;
    }

    public PdfSign retry(Long retry) {
        this.retry = retry;
        return this;
    }

    public void setRetry(Long retry) {
        this.retry = retry;
    }

    public Boolean isRetrySwitch() {
        return retrySwitch;
    }

    public PdfSign retrySwitch(Boolean retrySwitch) {
        this.retrySwitch = retrySwitch;
        return this;
    }

    public void setRetrySwitch(Boolean retrySwitch) {
        this.retrySwitch = retrySwitch;
    }

    public Long getRetryCount() {
        return retryCount;
    }

    public PdfSign retryCount(Long retryCount) {
        this.retryCount = retryCount;
        return this;
    }

    public void setRetryCount(Long retryCount) {
        this.retryCount = retryCount;
    }

    public Set<SealData> getSealData() {
        return sealData;
    }

    public PdfSign sealData(Set<SealData> sealData) {
        this.sealData = sealData;
        return this;
    }

    public PdfSign addSealData(SealData sealData) {
        this.sealData.add(sealData);
        sealData.setPdfSign(this);
        return this;
    }

    public PdfSign removeSealData(SealData sealData) {
        this.sealData.remove(sealData);
        sealData.setPdfSign(null);
        return this;
    }

    public void setSealData(Set<SealData> sealData) {
        this.sealData = sealData;
    }

    public DdUser getDdUser() {
        return ddUser;
    }

    public PdfSign ddUser(DdUser ddUser) {
        this.ddUser = ddUser;
        return this;
    }

    public void setDdUser(DdUser ddUser) {
        this.ddUser = ddUser;
    }

    public PdfFile getPdfFile() {
        return pdfFile;
    }

    public PdfSign pdfFile(PdfFile pdfFile) {
        this.pdfFile = pdfFile;
        return this;
    }

    public void setPdfFile(PdfFile pdfFile) {
        this.pdfFile = pdfFile;
    }

    public MsgTask getMsgTask() {
        return msgTask;
    }

    public PdfSign msgTask(MsgTask msgTask) {
        this.msgTask = msgTask;
        return this;
    }

    public void setMsgTask(MsgTask msgTask) {
        this.msgTask = msgTask;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PdfSign)) {
            return false;
        }
        return id != null && id.equals(((PdfSign) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PdfSign{" +
            "id=" + getId() +
            ", channelNo='" + getChannelNo() + "'" +
            ", accountId='" + getAccountId() + "'" +
            ", pdfUrl='" + getPdfUrl() + "'" +
            ", fileName='" + getFileName() + "'" +
            ", key='" + getKey() + "'" +
            ", posType='" + getPosType() + "'" +
            ", posX=" + getPosX() +
            ", posY=" + getPosY() +
            ", width=" + getWidth() +
            ", signType='" + getSignType() + "'" +
            ", requestNo='" + getRequestNo() + "'" +
            ", requestTime='" + getRequestTime() + "'" +
            ", status='" + getStatus() + "'" +
            ", cycle=" + getCycle() +
            ", cycleUnit='" + getCycleUnit() + "'" +
            ", retry=" + getRetry() +
            ", retrySwitch='" + isRetrySwitch() + "'" +
            ", retryCount=" + getRetryCount() +
            "}";
    }
}
