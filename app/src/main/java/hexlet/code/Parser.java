package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {
    public static Map<String, Object> parser(String content, String extension)
            throws JsonProcessingException, IllegalArgumentException {
        ObjectMapper objectMapper;
        Map<String, Object> map;

        if (extension.toLowerCase().equals("yml")) {
            objectMapper = new YAMLMapper();
            map = objectMapper.readValue(content, new TypeReference<>() {
            });
        } else if (extension.toLowerCase().equals("json")) {
            objectMapper = new ObjectMapper();
            map = objectMapper.readValue(content, new TypeReference<Map<String, Object>>() {
            });
        } else {
            throw new IllegalArgumentException("Wrong extension - " + extension);
        }
        return map;
    }
}
