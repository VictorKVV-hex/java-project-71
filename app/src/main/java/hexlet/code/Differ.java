package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Differ {
    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
//        String str1 = "/mnt/d/VICTOR/HEXLET/java-project-71/file1.json";
//        String filePath1 = "/home/kvv/PROJECTS/java-project-71/file1.json";
        Map<String, Object> map1 = getMap(filePath1);
        Map<String, Object> map2 = getMap(filePath2);
        Set<String> allKeys = new TreeSet<>();
//        Set<String> allKeys = new LinkedHashSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());
        LinkedHashMap<String, String> UnsortedResult =new LinkedHashMap<>();
        Map<String, Object> mapAll = new HashMap<>(map1);
        mapAll.putAll(map2);
        mapAll.forEach((k2, v2) -> UnsortedResult.put(k2, Differ.valueOfMap(map1, map2, k2, v2)));
        return "!";
    }

    public static Map<String, Object> getMap(String FilePath) throws Exception {
        Path testFilePath = Paths.get(FilePath);
        Path fileName = testFilePath.getFileName();
        // Формируем абсолютный путь, если filePath будет содержать относительный путь, то мы всегда будет работать с абсолютным
        Path path = Paths.get(FilePath).toAbsolutePath().normalize();
        // Проверяем существование файла
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        // Читаем файл
        String content = Files.readString(path);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.readValue(content, new TypeReference<Map<String, Object>>() {
        });
        return map;
    }

    public static String valueOfMap(Map<String, Object> data1, Map<String, Object> data2, String key, Object value) {
        String Ret = null;
        boolean isEquals = false;
        boolean isKey1Exists = data1.containsKey(key);
        boolean isKey2Exists = data2.containsKey(key);
        if (isKey1Exists && isKey2Exists) {
            isEquals = data1.get(key).equals(data2.get(key));
        }
        if (!isKey1Exists && isKey2Exists) {
            Ret = "added";
        } else if (isKey1Exists && !isKey2Exists) {
            Ret = "deleted";
        } else if (isKey1Exists && isKey2Exists && !isEquals) {
            Ret = "changed";
        } else if (isKey1Exists && isKey2Exists && isEquals) {
            Ret = "unchanged";
        }
        return Ret;
    }
}
