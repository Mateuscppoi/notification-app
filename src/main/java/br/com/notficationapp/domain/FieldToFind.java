package br.com.notficationapp.domain;

import lombok.Data;

import java.util.Map;

@Data
public class FieldToFind {

    private String attributeName;
    private String messageAttribute;
    private String attributeValue;
    private Boolean isPath;
    private Map<String, String> conditions;

}
