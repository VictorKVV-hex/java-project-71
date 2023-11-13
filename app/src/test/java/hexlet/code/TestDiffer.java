package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static hexlet.code.Differ.getPath;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class TestDiffer {
    private final String filePath1 = "src/test/resources/file1.json";
    private final String filePath2 = "src/test/resources/file2.json";
    private final String fileEmpty = "src/test/resources/fileEmpty.json";
    private final String filePathYml1 = "src/test/resources/file1.yml";
    private final String filePathYml2 = "src/test/resources/file2.yml";
    private final String filePathNestedJson1 = "src/test/resources/fileNested1.json";
    private final String filePathNestedJson2 = "src/test/resources/fileNested2.json";
    private final String filePathNestedYml1 = "src/test/resources/fileNested1.yml";
    private final String filePathNestedYml2 = "src/test/resources/fileNested2.yml";

    @Test
    public void testGenerateJson() throws Exception {
        String actual = Differ.generate(filePath1, filePath2, "stylish");
        String result = "{\n"
                + " - follow: false\n"
                + "   host: hexlet.io\n"
                + " - proxy: 123.234.53.22\n"
                + " - timeout: 50\n"
                + " + timeout: 20\n"
                + " + verbose: true\n"
                + "}";
        assertEquals(result, actual, "Files did not match");
    }
    @Test
    public void testGenerateEmptyJson() throws Exception {
        String actual = Differ.generate(filePath1, fileEmpty, "stylish");
        String result = "{\n"
                + " - follow: false\n"
                + " - host: hexlet.io\n"
                + " - proxy: 123.234.53.22\n"
                + " - timeout: 50\n"
                + "}";
        assertEquals(result, actual, "Files did not match");
    }
    @Test
    public void testGenerate2EmptyJson() throws Exception {
        String actual = Differ.generate(fileEmpty, fileEmpty, "stylish");
        String result = "{\n}";
        assertEquals(result, actual, "Files did not match");
    }
    @Test
    public void testGenerateYml() throws Exception {
        String actual = Differ.generate(filePathYml1, filePathYml2, "stylish");
        String result = " - follow: false\n"
                + "   host: hexlet.io\n"
                + " - proxy: 123.234.53.22\n"
                + " - timeout: 50\n"
                + " + timeout: 20\n"
                + " + verbose: true";
        assertEquals(result, actual, "Files did not match");
    }
    @Test
    public  void testGetPath() {
        String path = "src/test/resources/file1.json";
        String pathNotExist = "src/test/resources/fileNotExist.json";
        boolean isFile1 = Files.exists(Path.of(path));
        boolean isFileNotExist = Files.exists(Path.of(pathNotExist));
        assertTrue(isFile1);
        assertFalse(isFileNotExist);
    }
    @Test
    public void testGenerateNestedJson() throws Exception {
        String actual = Differ.generate(filePathNestedJson1, filePathNestedJson2, "stylish");
        String filePath = "src/test/resources/fileNestedResult.json";
        String result = Files.readString(getPath(filePath)).replace("\r", ""); // Читаем файл
//        String result = Files.lines(getPath(filePath)).reduce("", (a, b) -> a.trim() + b + "\n").trim();
        assertEquals(result, actual, "Files did not match");
    }
    @Test
    public void testGenerateNestedYml() throws Exception {
        String actual = Differ.generate(filePathNestedYml1, filePathNestedYml2, "stylish");
        String filePath = "src/test/resources/fileNestedResult.yml";
//        String result = Files.readString(getPath(filePath)).replace("\r", ""); // Читаем файл
        String result = "   " + Files.lines(getPath(filePath)).reduce("", (a, b) -> a + b + "\n").trim();
        assertEquals(result, actual, "Files did not match");
    }
    @Test
    public void testGeneratePlainJson() throws Exception {
        String actual = Differ.generate(filePathNestedJson1, filePathNestedJson2, "plain");
        String filePath = "src/test/resources/fileResultPlain.json";
        String result = Files.lines(getPath(filePath)).reduce("", (a, b) -> a + b + "\n").trim();
        assertEquals(result, actual, "Files did not match");
    }
    @Test
    public void testGenerateJsonJson() throws Exception {
        String actual = Differ.generate(filePathNestedJson1, filePathNestedJson2, "json");
        String filePath = "src/test/resources/fileNestedResultJson.json";
        String result = Files.lines(getPath(filePath)).reduce("", (a, b) -> a + b + "\n").trim();
        assertEquals(result, actual, "Files did not match");
    }
}
