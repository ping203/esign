package com.kyanite.esign.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.kyanite.esign.domain.enumeration.DdMessageType;

import com.kyanite.esign.domain.enumeration.MessageStatus;

/**
 * A MsgTask.
 */
@Entity
@Table(name = "msg_task")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MsgTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "dept_id_list")
    private String deptIdList;

    @Column(name = "userid_list")
    private String useridList;

    @Column(name = "to_all_user")
    private Boolean toAllUser;

    @Column(name = "markdown")
    private String markdown;

    @Column(name = "single_title")
    private String singleTitle;

    @Column(name = "single_url")
    private String singleUrl;

    @Column(name = "pdf_url")
    private String pdfUrl;

    @Column(name = "cover_url")
    private String coverUrl;

    @Column(name = "number_of_shards")
    private Long numberOfShards;

    @Column(name = "complete_sharding")
    private Boolean completeSharding;

    @Column(name = "msg")
    private String msg;

    @Column(name = "execute_time")
    private Instant executeTime;

    @Column(name = "agent_id")
    private Long agentId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private DdMessageType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private MessageStatus status;

    @Column(name = "progress_in_percent")
    private Long progressInPercent;

    @Column(name = "jhi_key")
    private String key;

    @OneToMany(mappedBy = "msgTask")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<MsgSubTask> msgSubTasks = new HashSet<>();

    @OneToMany(mappedBy = "msgTask")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<PdfSign> pdfSigns = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "msgTasks", allowSetters = true)
    private DdUser sender;

    @ManyToOne
    @JsonIgnoreProperties(value = "msgTasks", allowSetters = true)
    private PdfFile pdfFile;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public MsgTask title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeptIdList() {
        return deptIdList;
    }

    public MsgTask deptIdList(String deptIdList) {
        this.deptIdList = deptIdList;
        return this;
    }

    public void setDeptIdList(String deptIdList) {
        this.deptIdList = deptIdList;
    }

    public String getUseridList() {
        return useridList;
    }

    public MsgTask useridList(String useridList) {
        this.useridList = useridList;
        return this;
    }

    public void setUseridList(String useridList) {
        this.useridList = useridList;
    }

    public Boolean isToAllUser() {
        return toAllUser;
    }

    public MsgTask toAllUser(Boolean toAllUser) {
        this.toAllUser = toAllUser;
        return this;
    }

    public void setToAllUser(Boolean toAllUser) {
        this.toAllUser = toAllUser;
    }

    public String getMarkdown() {
        return markdown;
    }

    public MsgTask markdown(String markdown) {
        this.markdown = markdown;
        return this;
    }

    public void setMarkdown(String markdown) {
        this.markdown = markdown;
    }

    public String getSingleTitle() {
        return singleTitle;
    }

    public MsgTask singleTitle(String singleTitle) {
        this.singleTitle = singleTitle;
        return this;
    }

    public void setSingleTitle(String singleTitle) {
        this.singleTitle = singleTitle;
    }

    public String getSingleUrl() {
        return singleUrl;
    }

    public MsgTask singleUrl(String singleUrl) {
        this.singleUrl = singleUrl;
        return this;
    }

    public void setSingleUrl(String singleUrl) {
        this.singleUrl = singleUrl;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public MsgTask pdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
        return this;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public MsgTask coverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
        return this;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public Long getNumberOfShards() {
        return numberOfShards;
    }

    public MsgTask numberOfShards(Long numberOfShards) {
        this.numberOfShards = numberOfShards;
        return this;
    }

    public void setNumberOfShards(Long numberOfShards) {
        this.numberOfShards = numberOfShards;
    }

    public Boolean isCompleteSharding() {
        return completeSharding;
    }

    public MsgTask completeSharding(Boolean completeSharding) {
        this.completeSharding = completeSharding;
        return this;
    }

    public void setCompleteSharding(Boolean completeSharding) {
        this.completeSharding = completeSharding;
    }

    public String getMsg() {
        return msg;
    }

    public MsgTask msg(String msg) {
        this.msg = msg;
        return this;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Instant getExecuteTime() {
        return executeTime;
    }

    public MsgTask executeTime(Instant executeTime) {
        this.executeTime = executeTime;
        return this;
    }

    public void setExecuteTime(Instant executeTime) {
        this.executeTime = executeTime;
    }

    public Long getAgentId() {
        return agentId;
    }

    public MsgTask agentId(Long agentId) {
        this.agentId = agentId;
        return this;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public DdMessageType getType() {
        return type;
    }

    public MsgTask type(DdMessageType type) {
        this.type = type;
        return this;
    }

    public void setType(DdMessageType type) {
        this.type = type;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public MsgTask status(MessageStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    public Long getProgressInPercent() {
        return progressInPercent;
    }

    public MsgTask progressInPercent(Long progressInPercent) {
        this.progressInPercent = progressInPercent;
        return this;
    }

    public void setProgressInPercent(Long progressInPercent) {
        this.progressInPercent = progressInPercent;
    }

    public String getKey() {
        return key;
    }

    public MsgTask key(String key) {
        this.key = key;
        return this;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Set<MsgSubTask> getMsgSubTasks() {
        return msgSubTasks;
    }

    public MsgTask msgSubTasks(Set<MsgSubTask> msgSubTasks) {
        this.msgSubTasks = msgSubTasks;
        return this;
    }

    public MsgTask addMsgSubTask(MsgSubTask msgSubTask) {
        this.msgSubTasks.add(msgSubTask);
        msgSubTask.setMsgTask(this);
        return this;
    }

    public MsgTask removeMsgSubTask(MsgSubTask msgSubTask) {
        this.msgSubTasks.remove(msgSubTask);
        msgSubTask.setMsgTask(null);
        return this;
    }

    public void setMsgSubTasks(Set<MsgSubTask> msgSubTasks) {
        this.msgSubTasks = msgSubTasks;
    }

    public Set<PdfSign> getPdfSigns() {
        return pdfSigns;
    }

    public MsgTask pdfSigns(Set<PdfSign> pdfSigns) {
        this.pdfSigns = pdfSigns;
        return this;
    }

    public MsgTask addPdfSign(PdfSign pdfSign) {
        this.pdfSigns.add(pdfSign);
        pdfSign.setMsgTask(this);
        return this;
    }

    public MsgTask removePdfSign(PdfSign pdfSign) {
        this.pdfSigns.remove(pdfSign);
        pdfSign.setMsgTask(null);
        return this;
    }

    public void setPdfSigns(Set<PdfSign> pdfSigns) {
        this.pdfSigns = pdfSigns;
    }

    public DdUser getSender() {
        return sender;
    }

    public MsgTask sender(DdUser ddUser) {
        this.sender = ddUser;
        return this;
    }

    public void setSender(DdUser ddUser) {
        this.sender = ddUser;
    }

    public PdfFile getPdfFile() {
        return pdfFile;
    }

    public MsgTask pdfFile(PdfFile pdfFile) {
        this.pdfFile = pdfFile;
        return this;
    }

    public void setPdfFile(PdfFile pdfFile) {
        this.pdfFile = pdfFile;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MsgTask)) {
            return false;
        }
        return id != null && id.equals(((MsgTask) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MsgTask{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", deptIdList='" + getDeptIdList() + "'" +
            ", useridList='" + getUseridList() + "'" +
            ", toAllUser='" + isToAllUser() + "'" +
            ", markdown='" + getMarkdown() + "'" +
            ", singleTitle='" + getSingleTitle() + "'" +
            ", singleUrl='" + getSingleUrl() + "'" +
            ", pdfUrl='" + getPdfUrl() + "'" +
            ", coverUrl='" + getCoverUrl() + "'" +
            ", numberOfShards=" + getNumberOfShards() +
            ", completeSharding='" + isCompleteSharding() + "'" +
            ", msg='" + getMsg() + "'" +
            ", executeTime='" + getExecuteTime() + "'" +
            ", agentId=" + getAgentId() +
            ", type='" + getType() + "'" +
            ", status='" + getStatus() + "'" +
            ", progressInPercent=" + getProgressInPercent() +
            ", key='" + getKey() + "'" +
            "}";
    }
}
