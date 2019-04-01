package br.com.notficationapp.sonarCloud.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Project {

    @JsonProperty(value = "key")
    private String key;

    @JsonProperty(value = "name")
    private String name;

    public Project(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }
}
