package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
//        String str1 = "/mnt/d/VICTOR/HEXLET/java-project-71/file1.json";
//        String filePath1 = "/home/kvv/PROJECTS/java-project-71/file1.json";
        // Получаем путь к нужному файлу
        Map<String, Object> map1 = getMap(filePath1);
        Map<String, Object> map2 = getMap(filePath2);

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
//        System.out.println(content);
/*        List<String> lines = Files.readAllLines(testFilePath, UTF_8);
        System.out.println(lines);*/
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.readValue(content, new TypeReference<Map<String, Object>>() {
        });
        return map;
    }
}
