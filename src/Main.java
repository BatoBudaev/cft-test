import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.RandomAccessFile;

public class Main {
    public static void main(String[] args) throws IOException {
        ArgumentsData data = new ArgumentsData();
        parse(args, data);
        System.out.println(data.getSortMode());
        System.out.println(data.getDataType());
        System.out.println(data.getOutputName());


        int filesCount = data.getInputNamesArray().size();
        Reader[] readers = new Reader[filesCount];

        for (int i = 0; i < filesCount; i++) {
            readers[i] = new Reader(data.getInputNamesArray().get(i));
        }
        boolean hasLines = true;

        while (hasLines) {
            int index = 0;
            long seek = 0;
            int minNumber = 0;
            boolean hasMin = false;
            int hasLinesCount = 0;
            int count = 0;


            for (int i = 0; i < filesCount; i++) {
                if (readers[i].isHasNextLine()) {
                    seek = readers[i].readIntFromFile();

                    if (!hasMin) {
                        minNumber = readers[i].getNumberValue();
                        hasMin = true;
                    }

                    if (readers[i].isHasNextLine()) {
                        hasLinesCount++;
                    }

                    if (minNumber > readers[i].getNumberValue()) {
                        minNumber = readers[i].getNumberValue();
                        index = i;
                    }

                    count++;
                } else {
                    System.out.println("is empty " + readers[i].getFileName());
                }
            }

            if (hasLinesCount == 0) {
                hasLines = false;
            }

            if (count > 0) {
                readers[index].setSeek(seek);
                System.out.println(minNumber);
            }
        }
    }


    public static void parse(String[] args, ArgumentsData data) {
        int argIndex = 0;

        try {
            if (args[argIndex].equals("-d") || (args[argIndex].equals("-a"))) {
                data.setSortMode(args[argIndex++]);
            }

            if (args[argIndex].equals("-s") || (args[argIndex].equals("-i"))) {
                data.setDataType(args[argIndex++]);
            } else {
                throw new IllegalArgumentException("Argument must be s or i");
            }

            data.setOutputName(args[argIndex++]);

            List<String> inputNamesArray = new ArrayList<>();

            for (; argIndex < args.length; argIndex++) {
                inputNamesArray.add(args[argIndex]);
            }

            if (inputNamesArray.isEmpty()) {
                throw new IllegalArgumentException("There must be at least one input file");
            } else {
                data.setInputNamesArray(inputNamesArray);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Not enough arguments");
        }
    }
}
