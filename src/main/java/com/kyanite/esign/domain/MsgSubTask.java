package com.kyanite.esign.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

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

    @Column(name = "rsp")
    private String rsp;

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

    public String getRsp() {
        return rsp;
    }

    public MsgSubTask rsp(String rsp) {
        this.rsp = rsp;
        return this;
    }

    public void setRsp(String rsp) {
        this.rsp = rsp;
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
            ", rsp='" + getRsp() + "'" +
            "}";
    }
}
