import java.io.*;

public class Main {
    //这里为原logo.img文件
    static private String path = ".\\logo.img";

    public static void main(String[] args) throws Exception {

        File logoFile = new File(path);
        RandomAccessFile accessFile = new RandomAccessFile(logoFile, "rw");
//        accessFile.seek(0x4000 + 8);
        long[] imgStart = {0x0005 * 0x1000, 0x0770 * 0x1000, 0x0edb * 0x1000, 0x1646 * 0x1000};
//        int[] imgLen = {0x076b * 0x1000, 0x076b * 0x1000, 0x076b * 0x1000, 0x076b * 0x1000};
        File[] imgs = new File[]{
                //这里填写要写入的4张图片路径
                new File(".\\1boot.bmp"),
                new File(".\\2fastboot.bmp"),
                new File(".\\3unlock.bmp"),
                new File(".\\4damage.bmp")};
        System.out.println(Arrays.toString(imgStart) + Arrays.toString(imgLen));
        byte[] kb = new byte[1024];
        for (int i = 0; i < imgStart.length; i++) {
            accessFile.seek(imgStart[i]);
            FileInputStream fileInputStream = new FileInputStream(imgs[i]);
            System.out.println(fileInputStream.available());
            int len;
            while ((len = fileInputStream.read(kb)) != -1) {
                accessFile.write(kb, 0, len);
            }
            fileInputStream.close();
        }
        accessFile.close();
    }
}
