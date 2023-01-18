import java.io.IOException;
import java.io.RandomAccessFile;

public class Reader {
    String fileName;
    private long seek;
    private boolean hasNextLine;
    private int numberValue;
    private String stringValue;

    Reader(String fileName) throws IOException {
        this.fileName = fileName;
        try (RandomAccessFile file = new RandomAccessFile(fileName, "r")) {
            if (file.readLine() != null) {
                setHasNextLine(true);
            }
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getSeek() {
        return seek;
    }

    public void setSeek(long seek) {
        this.seek = seek;
    }

    public boolean isHasNextLine() {
        return hasNextLine;
    }

    public void setHasNextLine(boolean hasNextLine) {
        this.hasNextLine = hasNextLine;
    }

    public int getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(int numberValue) {
        this.numberValue = numberValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public long readIntFromFile() throws IOException {
        long seek;

        try (RandomAccessFile file = new RandomAccessFile(fileName, "r")) {
            file.seek(getSeek());
            String line;
            if ((line = file.readLine()) != null) {
                setNumberValue(Integer.parseInt(line));
            }
            seek = file.getFilePointer();
            setHasNextLine(file.readLine() != null);

        } catch (IOException e) {
            throw new IOException("File not found");
        }

        return seek;
    }

}
