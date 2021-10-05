package cf.vbnm;

import java.io.*;

public class Logo {
    final private int x, y;
    private String sense;
    private File logoBMPFilePath;
    private FileInputStream fis;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Logo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public File getLogoPath() {
        return logoBMPFilePath;
    }

    public InputStream getFileStream() {
        if (fis == null)
            try {
                FileInputStream fis = new FileInputStream(logoBMPFilePath);
                return this.fis = fis;
            } catch (IOException e) {
                e.printStackTrace();
            }
        return fis;
    }

    public void setLogoBMPFilePath(File path) throws IOException {
        if (path.canRead()) {
            logoBMPFilePath = path;
        } else throw new IOException("Current file can not be read");
    }

    public void setLogoBMPFilePath(String path) throws IOException {
        File file = new File(path);
        if (file.canRead()) {
            logoBMPFilePath = file;
        } else throw new IOException("Current file can not be read");
    }

    public String getSense() {
        return sense;
    }

    public void setSense(String sense) {
        this.sense = sense;
    }
}
