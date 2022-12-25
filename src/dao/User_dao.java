package dao;


import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import model.User;
import util.UserTools;

public class User_dao {
	public User user;
	public UserTools ut;
	public ArrayList<User> Userlist = new ArrayList<User> ();
	
	public void setUserlist( ArrayList<User> Userlist) {
		this.Userlist =Userlist;
	}

	/**
	 * 用户登录
	 * @param u
	 * @return
	 */
	public boolean Login(User u) {
        for (User user :Userlist) {
        	System.out.print(user+"\n");
            if (user.getUser_id().equals(u.getUser_id()) && user.getPass_word().equals(u.getPass_word())){
            	System.out.print("登录成功！！！"+"\n");
            	return true;
            }
        }
        System.out.print("登录失败！！！");
        return false;
	}
	

    /**
     * 通过用户id进行查找
     * @param user_id
     * @return
     */
    public User findByName(String user_id) {
        for (User user :Userlist) {
            String id = user.getUser_id();
            if(id.equals(user_id)){
                return user;
            }
        }
        return null;
    }

    /**
     * 用户模糊查找功能
     * @param user_id
     * @return
     */
    public ArrayList User_Search(String user_id){
    	ArrayList<User> fuzzylist = new ArrayList<User> ();
        for (int i = 0; i< Userlist.size();++i) {
        	String id = Userlist.get(i).getUser_id();
        	System.out.print(id);
            if(id.contains(user_id)){
                fuzzylist.add(Userlist.get(i));
            }
        }
        if(fuzzylist.size()==0){
            return null;
        }
        return fuzzylist;
    }

    /**
     * 无实际作用,但为了体现其持久化操作, 还是给了这么一个方法
     * @param olduser
     * @param newuser
     */
    public void update(User olduser, User newuser) {
        delete(olduser);
        add(newuser);
    }

    /**
     * 删除操作
     * @param olduser
     */
    public boolean delete(User olduser) {
        for (int i = 0; i< Userlist.size();++i) {
            if (Userlist.get(i).getUser_id().equals(olduser.getUser_id()) && Userlist.get(i).getPass_word().equals(olduser.getPass_word())){
            	Userlist.remove(Userlist.get(i));
            	ut = new UserTools();
            	try {
					ut.storeBook(Userlist);
					System.out.print(Userlist);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                return true;
            }
        }
        return false;
    }
    
    /**
     * 添加新用户
     * @param u
     * @return
     */
    public boolean add(User u) {
        for (int i = 0; i< Userlist.size();++i) {
            if (Userlist.get(i).getUser_id().equals(u.getUser_id()) && Userlist.get(i).getPass_word().equals(u.getPass_word())){
            	System.out.print("已有对象，不可重复创建！！！"+"\n");
            	System.out.print(Userlist);
                return false;
            }
        }
        Userlist.add(u);
    	ut = new UserTools();
    	try {
			ut.storeBook(Userlist);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return true;
    }

    public boolean updata(User u, String new_password) {
    	 for (int i = 0; i< Userlist.size();++i) {
             if (Userlist.get(i).getUser_id().equals(u.getUser_id()) && Userlist.get(i).getPass_word().equals(u.getPass_word())){
            	 u.setPass_word(new_password);
            	 Userlist.set(i, u);
            	 ut = new UserTools();
            	 try {
					ut.storeBook(Userlist);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	 return true;
             }    
    	 }
    	return false;
    }
    /**
     * 返回所有用户列表操作
     * @return
     */
    public ArrayList<User> findAll() {
        return Userlist;
    }


}
