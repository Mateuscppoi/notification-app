package br.com.notficationapp.sonarCloud.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Conditions {

    @JsonProperty(value = "errorThreshold")
    private String errorThreshold;

    @JsonProperty(value = "metric")
    private String metric;

    @JsonProperty(value = "onLeakPeriod")
    private Boolean onLeakPeriod;

    @JsonProperty(value = "operator")
    private String operator;

    @JsonProperty(value = "status")
    private String status;

    @JsonProperty(value = "value")
    private String value;

    public Conditions(String errorThreshold, String metric, Boolean onLeakPeriod, String operator, String status, String value) {
        this.errorThreshold = errorThreshold;
        this.metric = metric;
        this.onLeakPeriod = onLeakPeriod;
        this.operator = operator;
        this.status = status;
        this.value = value;
    }

    public String getErrorThreshold() {
        return errorThreshold;
    }

    public String getMetric() {
        return metric;
    }

    public Boolean getOnLeakPeriod() {
        return onLeakPeriod;
    }

    public String getOperator() {
        return operator;
    }

    public String getStatus() {
        return status;
    }

    public String getValue() {
        return value;
    }
}
