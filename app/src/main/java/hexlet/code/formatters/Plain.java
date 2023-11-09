package hexlet.code.formatters;

import java.util.*;

public class Plain {
    static List<String> diffList = new ArrayList<>();
    public static List<String> Plain(Set<String> allKeys, Map<String, Object> map1, Map<String,
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
        return diffList;
    }
    public static void differOfMap(Map<String, Object> data1, Map<String, Object> data2, String key) {
        Object valueMap1 = data1.get(key);
        Object valueMap2 = data2.get(key);
        StringBuilder resultStr = null;
        if (data1.containsKey(key) && data2.containsKey(key)) {
            if (Objects.equals(valueMap1, valueMap2)) {
//                diffList.add("  " + key + ": " + (String)valueMap2);
//                diffList.add(String.format("   %s: %s", key, valueMap2));
            } else {
                diffList.add(String.valueOf(resultStr.append("Property ").append("'").append(key).append("' ").
                        append("was updated. ").append("From ").append(valueMap1).
                        append(" to ").append(valueMap2).append('\n')));
            }
        } else {
            if (!(data1.containsKey(key))) {
//                diffList.add(String.format(" + %s: %s", key, valueMap2));
/*                diffList.add(String.valueOf(resultStr.append("Property ").append("'").append(key).
                        append("'").append(" was added with value: ").append(valueMap1).append('\n')));*/
                diffList.add("Property '" + key + "' was added with value: '" + valueMap1 +"'\n");
            } else {
//                diffList.add(String.format(" - %s: %s", key, valueMap1));
/*                diffList.add(String.valueOf(resultStr.append("Property ").append("'").
                        append(key).append("'").append(" was removed").append('\n')));*/
                diffList.add("Property '" + key + "' was removed\n");
            }
        }
    }
}
