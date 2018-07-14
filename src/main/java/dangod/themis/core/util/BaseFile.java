package dangod.themis.core.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

public class BaseFile {
    //指定存储文件夹
    public static final String FOLDER = "file" + File.separator;

    public static int upload(MultipartFile prefile, String path) {
        try {
            String folderPath = path + File.separator;
            File f = new File(folderPath);
            if (!f.exists()) {
                f.mkdirs();
            }
            BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(new File(folderPath + prefile.getOriginalFilename())));
            out.write(prefile.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
        return 0;
    }

    public static int upload(MultipartFile preFile, String path, String afterFileName, boolean isKeepSuffix) {
        try {
            String folderPath = path + File.separator;
            File f = new File(folderPath);
            if (!f.exists()) {
                f.mkdirs();
            }
            if (isKeepSuffix) {
                String[] arr = preFile.getOriginalFilename().split("[.]");
                afterFileName += "." + arr[arr.length - 1];
            }
            BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(new File(folderPath + afterFileName)));
            out.write(preFile.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
        return 0;
    }

    public static int upload(MultipartFile preFile, String path, String afterFileName) {
        return upload(preFile, path, afterFileName, true);
    }

    public static int download(HttpServletResponse response, String folderName, String fileName){
        return download(response, folderName, fileName, fileName);
    }

    public static int download(HttpServletResponse response, String folderName, String fileName, String targetName){
        File file = new File(folderName + File.separator + fileName);
        if (file.exists()) {
            response.setContentType("application/force-download");// 设置下载后不打开
            try {
                response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(targetName, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                bis.close();
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            }
            return 0;
        }
        return -1;
    }

    public static int delete(String folderName, String fileName){
        try {
            File file = new File(folderName + File.separator + fileName);
            file.delete();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return -1;
        }
        return 0;
    }

}