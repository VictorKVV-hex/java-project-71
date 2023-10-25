package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import java.util.concurrent.Callable;
@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "auto help demo - picocli 3.0",
        description = "Compares two configuration files and shows a difference.")
public final class App implements Callable<Integer> {
    private static final int SUCCESS_EXIT_CODE = 0;
    private static final int ERROR_EXIT_CODE = 1;
    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    private boolean versionInfoRequested;
    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    private boolean usageHelpRequested;
/*    @CommandLine.Option(names = {"-f", "--format"}, defaultValue = "stylish", paramLabel = "format", description = "output format [default: stylish]")*/
    @Option(names = {"-f", "--format"}, defaultValue = "stylish", paramLabel = "format", description = "output format [default: ${DEFAULT-VALUE}]")
    private String formatName;

    @CommandLine.Parameters(paramLabel = "filepath1", index = "0", description = "path to first file")
    private String filePath1;
    @CommandLine.Parameters(paramLabel = "filepath2", index = "1", description = "path to second file")
    private String filePath2;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);

    }

    @Override
    public Integer call() throws Exception {
        try {
            String formattedDiff = Differ.generate(filePath1, filePath2, formatName);
            System.out.println(formattedDiff);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ERROR_EXIT_CODE;
        }
        return SUCCESS_EXIT_CODE;
    }
}
