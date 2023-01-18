import java.io.IOException;
import java.io.RandomAccessFile;

public class MergeSort {
    public static void doIntMergeSort(ArgumentsData data) throws IOException {
        int filesCount = data.getInputNamesArray().size();
        Reader[] readers = new Reader[filesCount];
        RandomAccessFile file = new RandomAccessFile(data.getOutputName(), "rw");
        file.setLength(0);

        for (int i = 0; i < filesCount; ++i) {
            readers[i] = new Reader(data.getInputNamesArray().get(i));
        }

        boolean hasLines = true;
        int prevNumber = 0;
        boolean hasPrevNumber = false;

        while (hasLines) {
            int index = 0;
            int number = 0;
            boolean hasMinOrMax = false;
            int count = 0;

            for (int i = 0; i < filesCount; ++i) {
                if (readers[i].checkNextLine() && readers[i].isReading()) {
                    readers[i].readIntFromFile();

                    if (!hasMinOrMax) {
                        number = readers[i].getNumberValue();
                        hasMinOrMax = true;
                        index = i;
                    }

                    if (data.getSortMode().equals("-a")) {
                        if (number > readers[i].getNumberValue()) {
                            number = readers[i].getNumberValue();
                            index = i;
                        }
                    } else if (data.getSortMode().equals("-d")) {
                        if (number < readers[i].getNumberValue()) {
                            number = readers[i].getNumberValue();
                            index = i;
                        }
                    }

                    ++count;
                }
            }

            if (count > 0) {
                if (!hasPrevNumber) {
                    prevNumber = number;
                }

                readers[index].setSeek(readers[index].getPrevSeek());

                if (data.getSortMode().equals("-a")) {
                    if (prevNumber <= number) {
                        if (!hasPrevNumber) {
                            hasPrevNumber = true;
                        } else {
                            file.writeBytes("\n");
                        }

                        file.writeBytes(Integer.toString(number));
                        prevNumber = number;

                    } else {
                        readers[index].setReading(false);
                    }
                } else if (data.getSortMode().equals("-d")) {
                    if (prevNumber >= number) {
                        if (!hasPrevNumber) {
                            hasPrevNumber = true;
                        } else {
                            file.writeBytes("\n");
                        }

                        file.writeBytes(Integer.toString(number));
                        prevNumber = number;

                    } else {
                        readers[index].setReading(false);
                    }
                }
            } else {
                hasLines = false;
            }
        }

        file.close();
    }

    public static void doStringMergeSort(ArgumentsData data) throws IOException {
        int filesCount = data.getInputNamesArray().size();
        Reader[] readers = new Reader[filesCount];
        RandomAccessFile file = new RandomAccessFile(data.getOutputName(), "rw");
        file.setLength(0);

        for (int i = 0; i < filesCount; ++i) {
            readers[i] = new Reader(data.getInputNamesArray().get(i));
        }

        boolean hasLines = true;
        String prevString = null;
        boolean hasPrevString = false;

        while (hasLines) {
            int index = 0;
            String stringValue = null;
            boolean hasMinOrMax = false;
            int count = 0;

            for (int i = 0; i < filesCount; ++i) {
                if (readers[i].checkNextLine() && readers[i].isReading()) {
                    readers[i].readStringFromFile();

                    if (!hasMinOrMax) {
                        stringValue = readers[i].getStringValue();
                        hasMinOrMax = true;
                        index = i;
                    }

                    if (data.getSortMode().equals("-a")) {
                        if (stringValue.compareTo(readers[i].getStringValue()) > 0) {
                            stringValue = readers[i].getStringValue();
                            index = i;
                        }
                    } else if (data.getSortMode().equals("-d")) {
                        if (stringValue.compareTo(readers[i].getStringValue()) < 0) {
                            stringValue = readers[i].getStringValue();
                            index = i;
                        }
                    }

                    ++count;
                }
            }

            if (count > 0) {
                if (!hasPrevString) {
                    prevString = stringValue;
                }

                readers[index].setSeek(readers[index].getPrevSeek());

                if (data.getSortMode().equals("-a")) {
                    if (prevString.compareTo(stringValue) <= 0) {
                        if (!hasPrevString) {
                            hasPrevString = true;
                        } else {
                            file.writeBytes("\n");
                        }

                        file.writeBytes(stringValue);
                        prevString = stringValue;

                    } else {
                        readers[index].setReading(false);
                    }
                } else if (data.getSortMode().equals("-d")) {
                    if (prevString.compareTo(stringValue) >= 0) {
                        if (!hasPrevString) {
                            hasPrevString = true;
                        } else {
                            file.writeBytes("\n");
                        }

                        file.writeBytes(stringValue);
                        prevString = stringValue;

                    } else {
                        readers[index].setReading(false);
                    }
                }
            } else {
                hasLines = false;
            }
        }

        file.close();
    }
}