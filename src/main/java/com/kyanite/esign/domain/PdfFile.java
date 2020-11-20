package com.kyanite.esign.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A PdfFile.
 */
@Entity
@Table(name = "pdf_file")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PdfFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "media_type")
    private String mediaType;

    @Column(name = "obj_name")
    private String objName;

    @Column(name = "file_url")
    private String fileUrl;

    @OneToMany(mappedBy = "pdfFile")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<PdfSign> pdfSigns = new HashSet<>();

    @OneToMany(mappedBy = "pdfFile")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<MsgTask> msgTasks = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "pdfFiles", allowSetters = true)
    private DdUser creator;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public PdfFile name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMediaType() {
        return mediaType;
    }

    public PdfFile mediaType(String mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getObjName() {
        return objName;
    }

    public PdfFile objName(String objName) {
        this.objName = objName;
        return this;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public PdfFile fileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
        return this;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Set<PdfSign> getPdfSigns() {
        return pdfSigns;
    }

    public PdfFile pdfSigns(Set<PdfSign> pdfSigns) {
        this.pdfSigns = pdfSigns;
        return this;
    }

    public PdfFile addPdfSign(PdfSign pdfSign) {
        this.pdfSigns.add(pdfSign);
        pdfSign.setPdfFile(this);
        return this;
    }

    public PdfFile removePdfSign(PdfSign pdfSign) {
        this.pdfSigns.remove(pdfSign);
        pdfSign.setPdfFile(null);
        return this;
    }

    public void setPdfSigns(Set<PdfSign> pdfSigns) {
        this.pdfSigns = pdfSigns;
    }

    public Set<MsgTask> getMsgTasks() {
        return msgTasks;
    }

    public PdfFile msgTasks(Set<MsgTask> msgTasks) {
        this.msgTasks = msgTasks;
        return this;
    }

    public PdfFile addMsgTask(MsgTask msgTask) {
        this.msgTasks.add(msgTask);
        msgTask.setPdfFile(this);
        return this;
    }

    public PdfFile removeMsgTask(MsgTask msgTask) {
        this.msgTasks.remove(msgTask);
        msgTask.setPdfFile(null);
        return this;
    }

    public void setMsgTasks(Set<MsgTask> msgTasks) {
        this.msgTasks = msgTasks;
    }

    public DdUser getCreator() {
        return creator;
    }

    public PdfFile creator(DdUser ddUser) {
        this.creator = ddUser;
        return this;
    }

    public void setCreator(DdUser ddUser) {
        this.creator = ddUser;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PdfFile)) {
            return false;
        }
        return id != null && id.equals(((PdfFile) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PdfFile{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", mediaType='" + getMediaType() + "'" +
            ", objName='" + getObjName() + "'" +
            ", fileUrl='" + getFileUrl() + "'" +
            "}";
    }
}
