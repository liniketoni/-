package text;

import java.io.IOException;
import java.util.ArrayList;

import dao.User_dao;
import model.User;
import util.UserTools;
//测式类
public class text {

//	public static void main(String[] args) {
//		String a="2875244502";
//		String b="lk200012..";
//		Boolean c=true;
//		User user=new User(a,b,c);
//		System.out.print(user.toString());
//	}
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		//把对象的原始信息和图形保存到文件中 序列化
		String a="admin";
		String b="123456..";
		Boolean c=true;
		User user=new User(a,b,c);
		User user1=new User(a,b,c);
		User user2=new User(a,b,c);
//		System.out.print(user==user2);
		UserTools ut = new UserTools();
		User_dao ud =new User_dao();
		ArrayList<User> Userlist =new ArrayList<User>();
//		ArrayList<User> Userlist =ut.loadBook();
//		System.out.print(ut.loadBook().size());
		Userlist.add(user);
		ud.setUserlist(Userlist);
		ut.storeBook(Userlist);
		 System.out.print(ud.User_Search("0")+" 1 "+"\n");
		 System.out.print(Userlist.toString()+"\n");
		System.out.print(ut.loadBook().size());;
	}

}
