package com.kyanite.esign.domain;

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

    @OneToMany(mappedBy = "msgTask")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<MsgSubTask> msgSubTasks = new HashSet<>();

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
            ", msg='" + getMsg() + "'" +
            ", executeTime='" + getExecuteTime() + "'" +
            ", agentId=" + getAgentId() +
            ", type='" + getType() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
