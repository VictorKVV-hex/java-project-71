package hexlet.code;

import java.util.*;

public class TreeDiff {
    private static final String UNCHANGED = "UNCHANGED";
    private static final String UPDATED = "UPDATED";
    private static final String ADDED = "ADDED";
    private static final String REMOVED = "REMOVED";
    public static List<Map<String,Object>>  getDifferList(Map<String, Object> map1, Map<String, Object> map2) {
        List<Map<String,Object>> diffList = new ArrayList<>();
        diffList.clear();
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());
        for (String key : allKeys) {
            Object valueMap1 = map1.get(key);
            Object valueMap2 = map2.get(key);

            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (Objects.equals(valueMap1, valueMap2)) {
//                    diffList.add(new Node(UNCHANGED, key, valueMap1, valueMap2));
//                    diffList.add(Map.of("status", UNCHANGED, "key", key,"Value", valueMap1, "updatedValue", valueMap2));
                    diffList.add(putHm(UNCHANGED, key, valueMap1, valueMap2));
                } else {
//                    diffList.add(new Node(UPDATED, key, valueMap1, valueMap2));
//                    diffList.add(Map.of("status", UPDATED, "key", key,"Value", valueMap1, "updatedValue", valueMap2));
                    diffList.add(putHm(UPDATED, key, valueMap1, valueMap2));
                }
            } else {
                if (!(map1.containsKey(key))) {
//                    diffList.add(new Node(ADDED, key, valueMap2, null));
//                    diffList.add(Map.of("status", ADDED, "key", key,"Value", valueMap1));
                    diffList.add(putHm(ADDED, key, valueMap1, valueMap2));
                } else {
//                    diffList.add(new Node(REMOVED, key, valueMap1, null));
//                    diffList.add(Map.of("status", REMOVED, "key", key,"Value", valueMap1));
                    diffList.add(putHm(REMOVED, key, valueMap1, valueMap2));
                }
            }
        }
        return diffList;
    }
    public static Map<String,Object> putHm(String Status, String key, Object valueMap1, Object valueMap2) {
        var hM = new HashMap<String,Object>();
        hM.put("status", Status);
        hM.put("key", key);
        hM.put("value", valueMap1);
        hM.put("updatedValue", valueMap2);
        return hM;
    }

}
