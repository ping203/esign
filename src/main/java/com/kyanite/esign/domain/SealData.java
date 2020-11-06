package com.kyanite.esign.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A SealData.
 */
@Entity
@Table(name = "seal_data")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SealData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "base_64_str")
    private byte[] base64Str;

    @Column(name = "base_64_str_content_type")
    private String base64StrContentType;

    @ManyToOne
    @JsonIgnoreProperties(value = "sealData", allowSetters = true)
    private PdfSign pdfSign;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getBase64Str() {
        return base64Str;
    }

    public SealData base64Str(byte[] base64Str) {
        this.base64Str = base64Str;
        return this;
    }

    public void setBase64Str(byte[] base64Str) {
        this.base64Str = base64Str;
    }

    public String getBase64StrContentType() {
        return base64StrContentType;
    }

    public SealData base64StrContentType(String base64StrContentType) {
        this.base64StrContentType = base64StrContentType;
        return this;
    }

    public void setBase64StrContentType(String base64StrContentType) {
        this.base64StrContentType = base64StrContentType;
    }

    public PdfSign getPdfSign() {
        return pdfSign;
    }

    public SealData pdfSign(PdfSign pdfSign) {
        this.pdfSign = pdfSign;
        return this;
    }

    public void setPdfSign(PdfSign pdfSign) {
        this.pdfSign = pdfSign;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SealData)) {
            return false;
        }
        return id != null && id.equals(((SealData) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SealData{" +
            "id=" + getId() +
            ", base64Str='" + getBase64Str() + "'" +
            ", base64StrContentType='" + getBase64StrContentType() + "'" +
            "}";
    }
}
