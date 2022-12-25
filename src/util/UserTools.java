package util;



import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.User;

public class UserTools {
    /**
     * 将存储用户信息的集合存储至本地User.txt文件中
     * @param bookList
     * @throws IOException
     */
    public void storeBook(ArrayList<User> UserList) throws IOException {
        FileOutputStream fos = new FileOutputStream("D://User.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(UserList);
        oos.close();
        fos.close();
        System.out.println("保存用户数据到本地成功");
    }

    /**
     * 将序列化后的文件反序列化后使用对象
     * @return
     * @throws IOException
     */
    public ArrayList<User> loadBook() throws IOException {
        ArrayList<User> UserList = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("D://User.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            try (fis; ois) {
            	UserList = (ArrayList<User>) ois.readObject();
                System.out.println("成功获取本地数据");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("获取本地数据失败");
            }
            return UserList;
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("未找到文件");
            return UserList;
        } catch (EOFException eofException) {
            System.out.println("数据为空");
            return UserList;
        }

    }
}
