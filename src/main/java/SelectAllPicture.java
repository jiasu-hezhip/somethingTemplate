
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Encoder;

import java.io.*;

public class SelectAllPicture {

    public static void main(String[] args) {
        String path = "/Users/js/pic"; //这里输入指定文件夹路径
        getAllPictures(path);
    }


    public static void getAllPictures(String path) {
        File dir = new File(path);
        String[] fileList = dir.list();    //获取指定文件夹中所有文件的文件名
        for (int i = 0; i < fileList.length; i++) {
            try {
                String picPath = path + "/" + fileList[i];
                File file = new File(picPath);
                String imageStr = "data:image/jpeg;base64,"+getImageStr(file);
                String txtPath = picPath.substring(0, picPath.lastIndexOf(".")) + ".txt";
                writeTxt(txtPath, imageStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeTxt(String txtPath, String content) {
        try {
            File file = new File(txtPath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
            fw.close();
        } catch (Exception e) {
        }
    }


    public static String getImageStr(File imageFile) {
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(imageFile));
            byte[] data = new byte[in.available()];
            in.read(data);
            in.close();
            String imageStr = Base64.encodeBase64String(data);;
            return imageStr;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
