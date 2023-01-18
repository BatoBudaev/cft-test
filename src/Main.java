import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ArgumentsData data = new ArgumentsData();
        data.parse(args);
        if (data.getDataType().equals("-i")) {
            MergeSort.doIntMergeSort(data);
        } else if (data.getDataType().equals("-s")) {
            MergeSort.doStringMergeSort(data);
        }

    }
}