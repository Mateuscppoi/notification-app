package br.com.notficationapp.domain;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.IOException;

@Data
public class OriginJson {

    private String json;

    public JsonNode getAsNode() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(this.json);
    }
}
