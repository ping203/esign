package com.kyanite.esign.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

import com.kyanite.esign.domain.enumeration.HttpMethod;

/**
 * A ApiInvokeLog.
 */
@Entity
@Table(name = "api_invoke_log")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ApiInvokeLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "api_name")
    private String apiName;

    @Enumerated(EnumType.STRING)
    @Column(name = "method")
    private HttpMethod method;

    @Column(name = "http_status_code")
    private Integer httpStatusCode;

    @Column(name = "time")
    private Instant time;

    @Lob
    @Column(name = "reqeust_data")
    private String reqeustData;

    @Lob
    @Column(name = "response_data")
    private String responseData;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public ApiInvokeLog login(String login) {
        this.login = login;
        return this;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getApiName() {
        return apiName;
    }

    public ApiInvokeLog apiName(String apiName) {
        this.apiName = apiName;
        return this;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public ApiInvokeLog method(HttpMethod method) {
        this.method = method;
        return this;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public ApiInvokeLog httpStatusCode(Integer httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
        return this;
    }

    public void setHttpStatusCode(Integer httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public Instant getTime() {
        return time;
    }

    public ApiInvokeLog time(Instant time) {
        this.time = time;
        return this;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getReqeustData() {
        return reqeustData;
    }

    public ApiInvokeLog reqeustData(String reqeustData) {
        this.reqeustData = reqeustData;
        return this;
    }

    public void setReqeustData(String reqeustData) {
        this.reqeustData = reqeustData;
    }

    public String getResponseData() {
        return responseData;
    }

    public ApiInvokeLog responseData(String responseData) {
        this.responseData = responseData;
        return this;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApiInvokeLog)) {
            return false;
        }
        return id != null && id.equals(((ApiInvokeLog) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ApiInvokeLog{" +
            "id=" + getId() +
            ", login='" + getLogin() + "'" +
            ", apiName='" + getApiName() + "'" +
            ", method='" + getMethod() + "'" +
            ", httpStatusCode=" + getHttpStatusCode() +
            ", time='" + getTime() + "'" +
            ", reqeustData='" + getReqeustData() + "'" +
            ", responseData='" + getResponseData() + "'" +
            "}";
    }
}
