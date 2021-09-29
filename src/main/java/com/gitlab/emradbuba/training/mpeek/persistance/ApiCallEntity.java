package com.gitlab.emradbuba.training.mpeek.persistance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ApiCallEntity {
    @Id
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "REQUEST_COUNT")
    private Long requestCount;

    public ApiCallEntity() {
    }

    public ApiCallEntity(String login, Long requestCount) {
        this.login = login;
        this.requestCount = requestCount;
    }

    public String getLogin() {
        return login;
    }

    public Long getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(Long requestCount) {
        this.requestCount = requestCount;
    }

    @Override
    public String toString() {
        return "ApiCallEntity{" +
                "login='" + login + '\'' +
                ", requestCount=" + requestCount +
                '}';
    }

    public ApiCallDTO asDto() {
        return new ApiCallDTO(login, requestCount);
    }
}