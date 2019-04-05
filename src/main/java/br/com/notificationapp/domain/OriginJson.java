package br.com.notificationapp.domain;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.IOException;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class OriginJson {

    @Id
    private Long _id;
    private String origin;
    private String json;
    private LocalDate createdDate;

    public JsonNode getAsNode() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(this.json);
    }
}
