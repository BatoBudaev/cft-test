import java.util.ArrayList;
import java.util.List;

public class ArgumentsData {
    private String sortMode = "-a";
    private String dataType;
    private String outputName;
    private List<String> inputNamesArray;

    public String getSortMode() {
        return this.sortMode;
    }

    public void setSortMode(String sortMode) {
        this.sortMode = sortMode;
    }

    public String getDataType() {
        return this.dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getOutputName() {
        return this.outputName;
    }

    public void setOutputName(String outputName) {
        this.outputName = outputName;
    }

    public List<String> getInputNamesArray() {
        return this.inputNamesArray;
    }

    public void setInputNamesArray(List<String> inputNamesArray) {
        this.inputNamesArray = inputNamesArray;
    }

    public void parse(String[] args) {
        int argIndex = 0;

        try {
            if (args[argIndex].equals("-d") || args[argIndex].equals("-a")) {
                setSortMode(args[argIndex++]);
            }

            if (!args[argIndex].equals("-s") && !args[argIndex].equals("-i")) {
                throw new IllegalArgumentException("Argument must be s or i");
            } else {
                setDataType(args[argIndex++]);
                setOutputName(args[argIndex++]);

                List<String> inputNamesArray = new ArrayList<>();

                for (; argIndex < args.length; argIndex++) {
                    inputNamesArray.add(args[argIndex]);
                }

                if (inputNamesArray.isEmpty()) {
                    throw new IllegalArgumentException("There must be at least one input file");
                } else {
                    setInputNamesArray(inputNamesArray);
                }
            }
        } catch (ArrayIndexOutOfBoundsException var4) {
            throw new ArrayIndexOutOfBoundsException("Not enough arguments");
        }
    }
}
