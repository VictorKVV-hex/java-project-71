package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    public static String generate(String str1, String Str2, String formatName) throws Exception {
/*        str1 = "/mnt/d/VICTOR/HEXLET/java-project-71";
        Path testFilePath = Paths.get(str1);
        Path fileName = testFilePath.getFileName();
        System.out.println(fileName);*/
        //        String str1 = "/mnt/d/VICTOR/HEXLET/java-project-71/file1.json";
        String FilePath1 = "/home/kvv/PROJECTS/java-project-71/file1.json";
        // Получаем путь к нужному файлу
        Path testFilePath = Paths.get(FilePath1);
        Path fileName = testFilePath.getFileName();

        // Формируем абсолютный путь, если filePath будет содержать относительный путь, то мы всегда будет работать с абсолютным
        Path path = Paths.get(FilePath1).toAbsolutePath().normalize();
        // Проверяем существование файла
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        // Читаем файл
        String content = Files.readString(path);
        // Выводим содержимое
        System.out.println(content);
/*        List<String> lines = Files.readAllLines(testFilePath, UTF_8);
        System.out.println(lines);*/
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.readValue(content, new TypeReference<Map<String,Object>>(){});
        return "!";
    }
}
