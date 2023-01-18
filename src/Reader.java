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
        this.isReading = true;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getSeek() {
        return this.seek;
    }

    public void setSeek(long seek) {
        this.seek = seek;
    }

    public long getPrevSeek() {
        return this.prevSeek;
    }

    public void setPrevSeek(long prevSeek) {
        this.prevSeek = prevSeek;
    }

    public int getNumberValue() {
        return this.numberValue;
    }

    public void setNumberValue(int numberValue) {
        this.numberValue = numberValue;
    }

    public boolean isReading() {
        return this.isReading;
    }

    public void setReading(boolean reading) {
        this.isReading = reading;
    }

    public String getStringValue() {
        return this.stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public void readIntFromFile() throws IOException, NumberFormatException {
        try {
            RandomAccessFile file = new RandomAccessFile(fileName, "r");

            try {
                file.seek(getSeek());
                String line;
                if ((line = file.readLine()) != null) {
                    setNumberValue(Integer.parseInt(line));
                }

                setPrevSeek(file.getFilePointer());
            } catch (Throwable var5) {
                try {
                    file.close();
                } catch (Throwable var4) {
                    var5.addSuppressed(var4);
                }

                throw var5;
            }

            file.close();
        } catch (IOException var6) {
            throw new IOException("File not found");
        } catch (NumberFormatException var7) {
            throw new NumberFormatException("The file must contain only integers");
        }
    }

    public void readStringFromFile() throws IOException {
        try {
            RandomAccessFile file = new RandomAccessFile(fileName, "r");

            try {
                file.seek(getSeek());
                String line;
                if ((line = file.readLine()) != null) {
                    setStringValue(line);
                }

                setPrevSeek(file.getFilePointer());
            } catch (Throwable var5) {
                try {
                    file.close();
                } catch (Throwable var4) {
                    var5.addSuppressed(var4);
                }

                throw var5;
            }

            file.close();
        } catch (IOException var6) {
            throw new IOException("File not found");
        }
    }

    public boolean checkNextLine() throws IOException {
        boolean hasNextLine = false;

        try {
            RandomAccessFile file = new RandomAccessFile(fileName, "r");

            try {
                file.seek(getSeek());
                if (file.readLine() != null) {
                    hasNextLine = true;
                }
            } catch (Throwable var6) {
                try {
                    file.close();
                } catch (Throwable var5) {
                    var6.addSuppressed(var5);
                }

                throw var6;
            }

            file.close();
            return hasNextLine;
        } catch (IOException var7) {
            throw new IOException("File not found");
        }
    }
}
