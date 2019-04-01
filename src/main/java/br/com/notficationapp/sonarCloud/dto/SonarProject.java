package br.com.notficationapp.sonarCloud.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class SonarProject {

    @JsonProperty(value = "analysedAt")
    private String analysedAt;

    @JsonProperty(value = "project")
    private Project project;

    @JsonProperty(value = "qualityGate")
    private QualityGate qualityGate;

    @JsonProperty(value = "serverUrl")
    private String serverUrl;

    @JsonProperty(value = "status")
    private String status;

    @JsonProperty(value = "taskId")
    private String taskId;

    public SonarProject(String analysedAt, Project project, QualityGate qualityGate, String serverUrl, String status, String taskId) {
        this.analysedAt = analysedAt;
        this.project = project;
        this.qualityGate = qualityGate;
        this.serverUrl = serverUrl;
        this.status = status;
        this.taskId = taskId;
    }

    public String getAnalysedAt() {
        return analysedAt;
    }

    public Project getProject() {
        return project;
    }

    public QualityGate getQualityGate() {
        return qualityGate;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public String getStatus() {
        return status;
    }

    public String getTaskId() {
        return taskId;
    }
}
