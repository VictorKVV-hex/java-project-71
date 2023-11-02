package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestDiffer {
    private final String filePath1 = "src/test/resources/file1.json";
    private final String filePath2 = "src/test/resources/file2.json";
    private final String fileEmpty = "src/test/resources/fileEmpty.json";
    private final String filePathYml1 = "src/test/resources/file1.yml";
    private final String filePathYml2 = "src/test/resources/file2.yml";

    @Test
    public void testGenerateJson() throws Exception {
        String actual = Differ.generate(filePath1, filePath2, "json");
        String result = "- follow: false\n"
                + "  host: hexlet.io\n"
                + "- proxy: 123.234.53.22\n"
                + "- timeout: 50\n"
                + "+ timeout: 20\n"
                + "+ verbose: true";
        assertEquals(result, actual, "Files did not match");
    }
    @Test
    public void testGenerateEmptyJson() throws Exception {
        String actual = Differ.generate(filePath1, fileEmpty, "json");
        String result = "- follow: false\n"
                + "- host: hexlet.io\n"
                + "- proxy: 123.234.53.22\n"
                + "- timeout: 50";
        assertEquals(result, actual, "Files did not match");
    }
    @Test
    public void testGenerate2EmptyJson() throws Exception {
        String actual = Differ.generate(fileEmpty, fileEmpty, "json");
        String result = "";
        assertEquals(result, actual, "Files did not match");
    }
    @Test
    public void testGenerateYml() throws Exception {
        String actual = Differ.generate(filePath1, filePath2, "json");
        String result = "- follow: false\n"
                + "  host: hexlet.io\n"
                + "- proxy: 123.234.53.22\n"
                + "- timeout: 50\n"
                + "+ timeout: 20\n"
                + "+ verbose: true";
        assertEquals(result, actual, "Files did not match");
    }
}
