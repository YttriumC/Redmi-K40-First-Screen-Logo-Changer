
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Logo> logos = new ArrayList<>();
        System.out.println("1. normal\n2. fastboot\n3. unlocked\n4. system destroy");

        for (int i = 0; i < 4; i++) {
            final JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setFileFilter(new FileNameExtensionFilter("位图 bitmap/24bit(*.bmp)", "bmp", "BMP"));
            final int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                final Logo logo = new Logo(1080, 2400);
                logo.setLogoBMPFilePath(fileChooser.getSelectedFile());
                logos.add(logo);
            } else {
                System.out.println("Not choose, try again");
                i--;
            }
        }

        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("IMG 镜像文件(*.img)", "img", "IMG"));
        while (fileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            System.out.println("Not choose, try again");
        }
        final File imgFile = fileChooser.getSelectedFile();
        if (!imgFile.exists()) {
            imgFile.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(imgFile);
        int[] imgStart = {0x0005, 0x0800, 0x1000, 0x1700};
        byte[] bytes = new byte[0x1000];
        for (int i = 0; i < 4; i++) {
            fos.write(bytes);
        }
        final byte[] logoStr = "LOGO!!!!".getBytes(StandardCharsets.US_ASCII);
        System.arraycopy(logoStr, 0, bytes, 0, logoStr.length);
        byte[] logoInfo = {
                0x05, 0x00, 0x00, 0x00, 0x6B, 0x07, 0x00, 0x00, 0x00, 0x08, 0x00, 0x00, 0x6B, 0x07, 0x00, 0x00,
                0x00, 0x10, 0x00, 0x00, 0x6B, 0x07, 0x00, 0x00, 0x00, 0x17, 0x00, 0x00, 0x6B, 0x07, 0x00, 0x00
        };
        System.arraycopy(logoInfo, 0, bytes, 8, logoInfo.length);
        fos.write(bytes);
        int currentPos = 5;
        for (int i = 0; i < imgStart.length; i++) {
            Arrays.fill(bytes, (byte) 0);
            for (; currentPos < imgStart[i]; currentPos++) {
                fos.write(bytes);
            }
            Arrays.fill(bytes, (byte) 0);
            while ((logos.get(i).getFileStream().read(bytes)) != -1) {
                fos.write(bytes);
                currentPos++;
            }
            logos.get(i).getFileStream().close();
            Arrays.fill(bytes, (byte) 0);
            for (; currentPos < imgStart[i]; currentPos++) {
                fos.write(bytes);
            }
        }
        Arrays.fill(bytes, (byte) 0);
        for (; currentPos < 0x4000; currentPos++) {
            fos.write(bytes);
        }
    }
}
