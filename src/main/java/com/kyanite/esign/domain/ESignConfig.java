package com.kyanite.esign.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A ESignConfig.
 */
@Entity
@Table(name = "e_sign_config")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ESignConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "time")
    private Instant time;

    @Column(name = "channel_no")
    private String channelNo;

    @Column(name = "access_id")
    private String accessId;

    @Column(name = "access_key_secret")
    private String accessKeySecret;

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

    public ESignConfig name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getTime() {
        return time;
    }

    public ESignConfig time(Instant time) {
        this.time = time;
        return this;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public ESignConfig channelNo(String channelNo) {
        this.channelNo = channelNo;
        return this;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getAccessId() {
        return accessId;
    }

    public ESignConfig accessId(String accessId) {
        this.accessId = accessId;
        return this;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public ESignConfig accessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
        return this;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ESignConfig)) {
            return false;
        }
        return id != null && id.equals(((ESignConfig) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ESignConfig{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", time='" + getTime() + "'" +
            ", channelNo='" + getChannelNo() + "'" +
            ", accessId='" + getAccessId() + "'" +
            ", accessKeySecret='" + getAccessKeySecret() + "'" +
            "}";
    }
}
