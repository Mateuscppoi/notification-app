package br.com.notficationapp.gateway;

import br.com.notficationapp.domain.FieldToFind;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Component
public class JsonTranslator {

    private static final String BRACKET_REGEX = "([\\[\\]])";

    private final ObjectMapper mapper = new ObjectMapper();

    public JsonNode translateToJsonNode(Object genericJson) throws IOException {
        return mapper.readTree(mapper.writeValueAsString(genericJson));
    }

    public void getDataFromNode(JsonNode jsonNode, List<FieldToFind> fieldToFindList) {
        fieldToFindList.parallelStream().forEach(fieldToFind -> {
            if (fieldToFind.getIsPath()){
                fieldToFind.setAttributeValue(getValueFromPath(fieldToFind, jsonNode));
            } else {
                findFieldWithValue(jsonNode, fieldToFind);
            }
        });
    }

    private void findFieldWithValue(JsonNode jsonNode, FieldToFind fieldToFind) {
        if (Objects.nonNull(fieldToFind.getConditions())) {
            String condition = getConditionName(fieldToFind);
            jsonNode.findParents(fieldToFind.getAttributeName()).stream()
                    .filter(filter -> filter.findValuesAsText(condition).contains(fieldToFind.getConditions().get(condition)))
                    .findFirst().ifPresent(message ->
                    fieldToFind.setAttributeValue(removeQuoteFromValue(fieldToFind, message)));
        } else {
            fieldToFind.setAttributeValue(removeQuoteFromValue(fieldToFind, jsonNode));
        }
    }

    private String removeQuoteFromValue(FieldToFind fieldToFind, JsonNode message) {
        List<JsonNode> values = message.findValues(fieldToFind.getAttributeName());
        if (values.size() > 1) {
            return String.valueOf(message.findPath("/" + fieldToFind.getAttributeName())).replace("\"", "");
        }
        return String.valueOf(values.get(0)).replace("\"", "");
    }

    private String getValueFromPath(FieldToFind fieldToFind, JsonNode message) {
        return String.valueOf(message.at(fieldToFind.getAttributeName())).replace("\"", "");
    }

    private String getConditionName(FieldToFind fieldToFind) {
        return String.valueOf(fieldToFind.getConditions().keySet()).replaceAll(BRACKET_REGEX, "");
    }
}
