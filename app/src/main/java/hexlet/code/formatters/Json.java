package hexlet.code.formatters;

import hexlet.code.Node;

import java.util.*;
import java.util.stream.Collectors;

public class Json {
    static List<String> diffList = new ArrayList<>();

    public static String json(Set<String> allKeys, Map<String, Object> map1, Map<String,
            Object> map2, String extension) {
        diffList.clear();
        if (extension.equals("json")) {
            diffList.add("{");
        }
        for (String key : allKeys) {
            differOfMap(map1, map2, key);
        }
        if (extension.equals("json")) {
            diffList.add("}");
        }
        return String.join("\n", diffList);
    }
    public static void differOfMap(Map<String, Object> data1, Map<String, Object> data2, String key) {
        Object valueMap1 = data1.get(key);
        Object valueMap2 = data2.get(key);
        String valOne = typeValue(data1.get(key));
        String valTwo = typeValue(data2.get(key));
        StringBuilder resultStr = null;
        if (data1.containsKey(key) && data2.containsKey(key)) {
            if (Objects.equals(valueMap1, valueMap2)) {
                diffList.add("{\"type\":\"UNCHANGED\", \"key \"" + key + "\":" + valueMap2 + "}");
            } else {
                diffList.add("{\"type\":\"UPDATED\", \"key \"" + key + "\":" + valueMap1 + ",\"updatedValue\":" + valueMap2 + "}");
//                diffList.add(String.valueOf(new Node("UPDATED", key, valueMap1, valueMap2)));
            }
        } else {
            if (!(data1.containsKey(key))) {
                diffList.add("{\"type\":\"ADDED\", \"key \"" + key + "\":" + valueMap2 + "}");
            } else {
                diffList.add("{\"type\":\"REMOVED\", \"key \"" + key + "\":" + valueMap1 + "}");
            }
        }
    }

    private static String typeValue(Object object) {
        String value = String.valueOf(object);
        if (value.contains("[") || value.contains("{")) {
            ArrayList<String> list = (ArrayList<String>) object;
            return list
                    .stream()
                    .filter(o -> o instanceof String)
                    .map(name -> "\"" + name + "\"")
                    .collect(Collectors.joining(","));
        } else if (object instanceof String) {
            return "'" + value + "'";
        }
        return value;
    }
}
