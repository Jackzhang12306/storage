package cn.com.ecict.util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件操作工具类
 *
 * @author cyq
 *
 */
public class FileUtil {

    public static boolean isExistFile(String filepath) {
        File f = new File(filepath);
        return f.exists() && f.isFile();
    }

    /**
     * 删除单个文件
     * @param fileName
     * @return
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    /**
     * 将1行数据写入空文件
     * @param fileName
     * @param lines
     * @return
     */
    public static boolean writeNewFile(String fileName, String line) {
        File file = new File(fileName);
        try {
            if (file.isFile()) {
                file.delete();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(line);
            bw.flush();
            bw.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将多行数据写入空文件
     *
     * @param fileName
     * @param lines
     * @return
     */
    public static boolean writeFile(String fileName, List<String> lines) {
        File file = new File(fileName);
        try {
            if (file.isFile()) {
                file.delete();
            }
            // 如果文件不存在，则创建新的文件
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            for (String line : lines) {
                bw.write(line);
            }
            bw.flush();
            bw.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static List<String> readFile(String filePath) {
        List<String> data = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = "";
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
            reader.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String readfile(String filePath) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean deleteLine(String filePath, String key) {
        StringBuilder sb = new StringBuilder(4096);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = "";
            while ((line = reader.readLine()) != null) {
                if (line.contains(key))
                    continue;
                sb.append(line + "\n");
            }
            reader.close();
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(sb.toString());
            writer.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断文件file是否包含 key
     */
    public static boolean isContain(String file, String key) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = reader.readLine()) != null) {
                if (line.contains(key)) {
                    reader.close();
                    return true;
                }
            }
            reader.close();
            return false;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 追加1行
     *
     * @param file
     * @param line
     */
    public static void appand(String file, String line) {
        // 打开一个随机访问文件流，按读写方式
        RandomAccessFile randomFile = null;
        try {
            randomFile = new RandomAccessFile(file, "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.writeBytes(line + "\n");
            randomFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 追加多行
     *
     * @param file
     * @param lines
     */
    public static void appand(String file, List<String> lines) {
        // 打开一个随机访问文件流，按读写方式
        RandomAccessFile randomFile = null;
        try {
            randomFile = new RandomAccessFile(file, "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            for (String line : lines) {
                randomFile.writeBytes(line);
            }
            randomFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        boolean flag = isContain("/tmp/hosts", "192.168.1.81");
        System.out.println(flag);
        ;
    }
}

