package hexlet.code.formatters;

import hexlet.code.Node;

import java.util.*;
import java.util.stream.Collectors;

public class Json {
    static List<String> diffList = new ArrayList<>();

    public static String json(Set<String> allKeys, Map<String, Object> map1, Map<String,
            Object> map2, String extension) {
        diffList.clear();
        for (String key : allKeys) {
            differOfMap(map1, map2, key);
        }
        return "{\n" + String.join(",\n", diffList) + "\n}";
    }
    public static void differOfMap(Map<String, Object> data1, Map<String, Object> data2, String key) {
        Object valueMap1 = data1.get(key);
        Object valueMap2 = data2.get(key);
        String valOne = typeValue(data1.get(key));
        String valTwo = typeValue(data2.get(key));
//        StringBuilder resultStr = null;
        if (data1.containsKey(key) && data2.containsKey(key)) {
            if (Objects.equals(valueMap1, valueMap2)) {
                diffList.add("{\"type\":\"UNCHANGED\", \"key\":\"" + key + "\", \"value\":" + valTwo + "}");
            } else {
                diffList.add("{\"type\":\"UPDATED\", \"key\":\"" + key + "\", \"value\":" + valOne + ", \"updatedValue\":" + valTwo + "}");
//                diffList.add(String.valueOf(new Node("UPDATED", key, valueMap1, valueMap2)));
            }
        } else {
            if (!(data1.containsKey(key))) {
                diffList.add("{\"type\":\"ADDED\", \"key\":\"" + key + "\", \"value\":" + valTwo + "}");
            } else {
                diffList.add("{\"type\":\"REMOVED\", \"key\":\"" + key + "\", \"value\":" + valOne + "}");
            }
        }
    }

    private static String typeValue(Object object) {
        String value = String.valueOf(object);
        if (value.contains("[")) {
            if (object instanceof Map<?,?>) {
                return  value;
            }
            ArrayList<String> list = (ArrayList<String>) object;
            if (list.get(0) instanceof String) {
                return "[" + list
                        .stream()
                        .map(name -> "\"" + name + "\"")
                        .collect(Collectors.joining(",")) + "]";
            }
        } else if (value.contains("{")) {
            if (object instanceof Map<?, ?>) {
                Map<?, ?> map = (Map<?, ?>) object;
                return "{" + map.entrySet().stream()
//                        .map(entry -> entry.getKey() + ":" + entry.getValue())
                        .map(entry -> {String ret;
                            if (entry.getValue() instanceof String) {
                            ret = "\"" +entry.getKey() + "\":" + "\"" + entry.getValue() + "\"";
                        } else {
                            ret =  "\"" +entry.getKey() + "\":" + entry.getValue();
                        }
                        return ret;
                        })
                        .collect(Collectors.joining(", ")) + "}";
//                return  value;
            }
            ArrayList<String> list = (ArrayList<String>) object;
            if (list.get(0) instanceof String) {
                return "{" + list
                        .stream()
                        .map(name -> "\"" + name + "\"")
                        .collect(Collectors.joining(",")) + "}";
            }
        } else if (object instanceof String) {
            return  "\"" + value + "\"";
        } else {
            return  value;
        }
        return value;
    }
}
