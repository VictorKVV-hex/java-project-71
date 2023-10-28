package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestDiffer {
    private final String filePath1 = "src/test/resources/file1.json";
    private final String filePath2 = "src/test/resources/file2.json";

    @Test
    public void testGenerate () throws Exception {
        String actual = Differ.generate(filePath1, filePath2, null);
        String Result = "- follow: false\n" +
                "  host: hexlet.io\n" +
                "- proxy: 123.234.53.22\n" +
                "- timeout: 50\n" +
                "+ timeout: 20\n" +
                "+ verbose: true";
        assertEquals(Result, actual, "Files did not match");
    }
}
