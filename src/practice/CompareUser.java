package practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28 0028.
 */
public class CompareUser {
    public static void main(String[] args){
        User u1 = new User("zhangsan","222","男",12);
        User u2 = new User("lisi","333","男",15);
        User u3 = new User("wangwu","555","女",13);
        User u4 = new User("zhangsan","444","女",14);

        List<User> users = new ArrayList();
        users.add(u1);
        users.add(u2);
        users.add(u3);
        List<User> users1 = new ArrayList<>();
        users1.add(u4);
//        users1.add(u4);
//        users1.add(u4);
//        users1.add(u4);
//        users1.add(u4);
//        users1.add(u4);
        users1.add(u4);
        boolean isEqual = false;
//        System.out.println(users.retainAll(users1));
//        System.out.println(users1.retainAll(users));
//        System.out.println(users1);
        for(User a : users){
            if(users1.contains(a)){
                System.out.println("ok!!!"+a.getUsername()+"sss"+a.getPassword()+a.getGender());
            }
        }


//        System.out.println("u1与u4的比较结果是："+isEqual);
    }
}
