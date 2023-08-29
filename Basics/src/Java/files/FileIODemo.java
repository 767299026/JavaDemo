package Java.files;

import java.io.*;

//进行文件读写和IO流操作时，需要注意流的关闭和异常处理
public class FileIODemo {
    public static void main(String[] args) throws IOException {
        // 创建文件
        File file = new File("data.txt");//在工作路径下新建文件

        // 写入数据(文件读写方式)
        FileWriter writer = new FileWriter(file);
        writer.write("Hello, world!");
        writer.close();

        // 读取数据(文件读写方式)
        FileReader reader = new FileReader(file);
        char[] buffer = new char[1024];
        int length = reader.read(buffer);
        String data = new String(buffer, 0, length);
        reader.close();
        System.out.println(data);

        // IO流
        //InputStream和OutputStream是用于读取和写入字节流的基本抽象类,可以用于读写文件、网络数据等字节流

        // 输入流读取data.txt的内容
        InputStream inputStream = new FileInputStream("data.txt");
        byte[] inputBuffer = new byte[1024];
        int inputLength = inputStream.read(inputBuffer);
        String inputData = new String(inputBuffer, 0, inputLength);
        inputStream.close();
        System.out.println(inputData);

        // 输出流将给定的字符串进行输出
        OutputStream outputStream = new FileOutputStream("output.txt");//在工作路径下输出文件
        outputStream.write("Hello, world!".getBytes());
        outputStream.close();

        // 字节数组流将从文件读到的数据转换成字节数组并输出
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(inputData.getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer2 = new byte[1024];
        int len;
        while ((len = byteArrayInputStream.read(buffer2)) > -1) {
            byteArrayOutputStream.write(buffer2, 0, len);
        }
        byteArrayOutputStream.flush();
        System.out.println(byteArrayOutputStream.toString());
    }
}
