package br.com.notficationapp.sonarCloud.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class QualityGate {

    @JsonProperty(value = "conditions")
    private List<Conditions> conditions;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "status")
    private String status;

    public QualityGate(List<Conditions> conditions, String name, String status) {
        this.conditions = conditions;
        this.name = name;
        this.status = status;
    }

    public List<Conditions> getConditions() {
        return conditions;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }
}
