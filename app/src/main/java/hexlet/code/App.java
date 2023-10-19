package hexlet.code;

import picocli.CommandLine;
import java.util.concurrent.Callable;
@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true, version = "auto help demo - picocli 3.0",
        description = "Compares two configuration files and shows a difference.")
public final class App implements Callable<Integer> {
    @CommandLine.Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.") private boolean versionInfoRequested;
    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.") private boolean usageHelpRequested;
    public static void main(String[] args) {
        System.out.println("Hello World111111111111111");
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);

    }

    @Override
    public Integer call() throws Exception {
        return null;
    }
}
