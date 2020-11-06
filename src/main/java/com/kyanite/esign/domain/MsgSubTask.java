package com.kyanite.esign.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

import com.kyanite.esign.domain.enumeration.MessageStatus;

/**
 * A MsgSubTask.
 */
@Entity
@Table(name = "msg_sub_task")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MsgSubTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid_list")
    private String useridList;

    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "time")
    private Instant time;

    @Column(name = "rsp_msg")
    private String rspMsg;

    @Column(name = "status")
    private Long status;

    @Column(name = "progress_in_percent")
    private Long progressInPercent;

    @Enumerated(EnumType.STRING)
    @Column(name = "sub_task_status")
    private MessageStatus subTaskStatus;

    @Column(name = "invalid_user_id_list")
    private String invalidUserIdList;

    @Column(name = "forbidden_user_id_list")
    private String forbiddenUserIdList;

    @Column(name = "failed_user_id_list")
    private String failedUserIdList;

    @Column(name = "read_user_id_list")
    private String readUserIdList;

    @Column(name = "unread_user_id_list")
    private String unreadUserIdList;

    @Column(name = "invalid_dept_id_list")
    private String invalidDeptIdList;

    @Column(name = "withdraw")
    private Boolean withdraw;

    @ManyToOne
    @JsonIgnoreProperties(value = "msgSubTasks", allowSetters = true)
    private MsgTask msgTask;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUseridList() {
        return useridList;
    }

    public MsgSubTask useridList(String useridList) {
        this.useridList = useridList;
        return this;
    }

    public void setUseridList(String useridList) {
        this.useridList = useridList;
    }

    public Long getTaskId() {
        return taskId;
    }

    public MsgSubTask taskId(Long taskId) {
        this.taskId = taskId;
        return this;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Instant getTime() {
        return time;
    }

    public MsgSubTask time(Instant time) {
        this.time = time;
        return this;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getRspMsg() {
        return rspMsg;
    }

    public MsgSubTask rspMsg(String rspMsg) {
        this.rspMsg = rspMsg;
        return this;
    }

    public void setRspMsg(String rspMsg) {
        this.rspMsg = rspMsg;
    }

    public Long getStatus() {
        return status;
    }

    public MsgSubTask status(Long status) {
        this.status = status;
        return this;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getProgressInPercent() {
        return progressInPercent;
    }

    public MsgSubTask progressInPercent(Long progressInPercent) {
        this.progressInPercent = progressInPercent;
        return this;
    }

    public void setProgressInPercent(Long progressInPercent) {
        this.progressInPercent = progressInPercent;
    }

    public MessageStatus getSubTaskStatus() {
        return subTaskStatus;
    }

    public MsgSubTask subTaskStatus(MessageStatus subTaskStatus) {
        this.subTaskStatus = subTaskStatus;
        return this;
    }

    public void setSubTaskStatus(MessageStatus subTaskStatus) {
        this.subTaskStatus = subTaskStatus;
    }

    public String getInvalidUserIdList() {
        return invalidUserIdList;
    }

    public MsgSubTask invalidUserIdList(String invalidUserIdList) {
        this.invalidUserIdList = invalidUserIdList;
        return this;
    }

    public void setInvalidUserIdList(String invalidUserIdList) {
        this.invalidUserIdList = invalidUserIdList;
    }

    public String getForbiddenUserIdList() {
        return forbiddenUserIdList;
    }

    public MsgSubTask forbiddenUserIdList(String forbiddenUserIdList) {
        this.forbiddenUserIdList = forbiddenUserIdList;
        return this;
    }

    public void setForbiddenUserIdList(String forbiddenUserIdList) {
        this.forbiddenUserIdList = forbiddenUserIdList;
    }

    public String getFailedUserIdList() {
        return failedUserIdList;
    }

    public MsgSubTask failedUserIdList(String failedUserIdList) {
        this.failedUserIdList = failedUserIdList;
        return this;
    }

    public void setFailedUserIdList(String failedUserIdList) {
        this.failedUserIdList = failedUserIdList;
    }

    public String getReadUserIdList() {
        return readUserIdList;
    }

    public MsgSubTask readUserIdList(String readUserIdList) {
        this.readUserIdList = readUserIdList;
        return this;
    }

    public void setReadUserIdList(String readUserIdList) {
        this.readUserIdList = readUserIdList;
    }

    public String getUnreadUserIdList() {
        return unreadUserIdList;
    }

    public MsgSubTask unreadUserIdList(String unreadUserIdList) {
        this.unreadUserIdList = unreadUserIdList;
        return this;
    }

    public void setUnreadUserIdList(String unreadUserIdList) {
        this.unreadUserIdList = unreadUserIdList;
    }

    public String getInvalidDeptIdList() {
        return invalidDeptIdList;
    }

    public MsgSubTask invalidDeptIdList(String invalidDeptIdList) {
        this.invalidDeptIdList = invalidDeptIdList;
        return this;
    }

    public void setInvalidDeptIdList(String invalidDeptIdList) {
        this.invalidDeptIdList = invalidDeptIdList;
    }

    public Boolean isWithdraw() {
        return withdraw;
    }

    public MsgSubTask withdraw(Boolean withdraw) {
        this.withdraw = withdraw;
        return this;
    }

    public void setWithdraw(Boolean withdraw) {
        this.withdraw = withdraw;
    }

    public MsgTask getMsgTask() {
        return msgTask;
    }

    public MsgSubTask msgTask(MsgTask msgTask) {
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
        if (!(o instanceof MsgSubTask)) {
            return false;
        }
        return id != null && id.equals(((MsgSubTask) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MsgSubTask{" +
            "id=" + getId() +
            ", useridList='" + getUseridList() + "'" +
            ", taskId=" + getTaskId() +
            ", time='" + getTime() + "'" +
            ", rspMsg='" + getRspMsg() + "'" +
            ", status=" + getStatus() +
            ", progressInPercent=" + getProgressInPercent() +
            ", subTaskStatus='" + getSubTaskStatus() + "'" +
            ", invalidUserIdList='" + getInvalidUserIdList() + "'" +
            ", forbiddenUserIdList='" + getForbiddenUserIdList() + "'" +
            ", failedUserIdList='" + getFailedUserIdList() + "'" +
            ", readUserIdList='" + getReadUserIdList() + "'" +
            ", unreadUserIdList='" + getUnreadUserIdList() + "'" +
            ", invalidDeptIdList='" + getInvalidDeptIdList() + "'" +
            ", withdraw='" + isWithdraw() + "'" +
            "}";
    }
}
