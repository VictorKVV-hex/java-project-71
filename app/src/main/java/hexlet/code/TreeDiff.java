package hexlet.code;

import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.TreeSet;
import java.util.Objects;

public class TreeDiff {
    private static final String UNCHANGED = "UNCHANGED";
    private static final String UPDATED = "UPDATED";
    private static final String ADDED = "ADDED";
    private static final String REMOVED = "REMOVED";
    public static List<Map<String, Object>>  getDifferList(Map<String, Object> map1, Map<String, Object> map2) {
        List<Map<String, Object>> diffList = new ArrayList<>();
        diffList.clear();
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());
        for (String key : allKeys) {
            Object valueMap1 = map1.get(key);
            Object valueMap2 = map2.get(key);

            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (Objects.equals(valueMap1, valueMap2)) {
                    diffList.add(putHm(UNCHANGED, key, valueMap1, valueMap2));
                } else {
                    diffList.add(putHm(UPDATED, key, valueMap1, valueMap2));
                }
            } else {
                if (!(map1.containsKey(key))) {
                    diffList.add(putHmAdd(ADDED, key, valueMap2));
                } else {
                    diffList.add(putHmREMOVED(REMOVED, key, valueMap1));
                }
            }
        }
        return diffList;
    }
    public static Map<String, Object> putHm(String type, String key, Object valueMap1, Object valueMap2) {
        var hM = new LinkedHashMap<String, Object>();
        hM.put("type", type);
        hM.put("key", key);
        hM.put("value", valueMap1);
        hM.put("updatedValue", valueMap2);
        return hM;
    }
    public static Map<String, Object> putHmAdd(String type, String key, Object valueMap2) {
        var hM = new LinkedHashMap<String, Object>();
        hM.put("type", type);
        hM.put("key", key);
        hM.put("updatedValue", valueMap2);
        return hM;
    }
    public static Map<String, Object> putHmREMOVED(String type, String key, Object valueMap1) {
        var hM = new LinkedHashMap<String, Object>();
        hM.put("type", type);
        hM.put("key", key);
        hM.put("value", valueMap1);
        return hM;
    }

}
