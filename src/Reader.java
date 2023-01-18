import java.io.IOException;
import java.io.RandomAccessFile;

public class Reader {
    private String fileName;
    private long seek;
    private long prevSeek;
    private boolean isReading;
    private int numberValue;
    private String stringValue;

    Reader(String fileName) {
        this.fileName = fileName;
        isReading = true;
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

    public long getPrevSeek() {
        return prevSeek;
    }

    public void setPrevSeek(long prevSeek) {
        this.prevSeek = prevSeek;
    }

    public int getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(int numberValue) {
        this.numberValue = numberValue;
    }

    public boolean isReading() {
        return isReading;
    }

    public void setReading(boolean reading) {
        isReading = reading;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public void readIntFromFile() throws IOException, NumberFormatException {
        try (RandomAccessFile file = new RandomAccessFile(fileName, "r")) {
            file.seek(getSeek());
            String line;

            if ((line = file.readLine()) != null) {
                setNumberValue(Integer.parseInt(line));
            }

            setPrevSeek(file.getFilePointer());
        } catch (IOException e) {
            throw new IOException("File not found");
        } catch (NumberFormatException e) {
            throw new NumberFormatException("The file must contain only integers");
        }
    }

    public void readStringFromFile() throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(fileName, "r")) {
            file.seek(getSeek());
            String line;

            if ((line = file.readLine()) != null) {
                setStringValue(line);
            }

            setPrevSeek(file.getFilePointer());
        } catch (IOException e) {
            throw new IOException("File not found");
        }
    }

    public boolean checkNextLine() throws IOException {
        boolean hasNextLine = false;

        try (RandomAccessFile file = new RandomAccessFile(fileName, "r")) {
            file.seek(getSeek());

            if (file.readLine() != null) {
                hasNextLine = true;
            }

            return hasNextLine;
        } catch (IOException e) {
            throw new IOException("File not found");
        }
    }
}
