import java.util.List;

public class ArgumentsData {
    private String sortMode;
    private String dataType;
    private String outputName;
    private List<String> inputNamesArray;

    ArgumentsData() {
        sortMode = "-a";
    }

    public String getSortMode() {
        return sortMode;
    }

    public void setSortMode(String sortMode) {
        this.sortMode = sortMode;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getOutputName() {
        return outputName;
    }

    public void setOutputName(String outputName) {
        this.outputName = outputName;
    }

    public List<String> getInputNamesArray() {
        return inputNamesArray;
    }

    public void setInputNamesArray(List<String> inputNamesArray) {
        this.inputNamesArray = inputNamesArray;
    }
}
