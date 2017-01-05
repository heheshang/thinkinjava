package practice;

/**
 * Created by Administrator on 2016/11/28 0028.
 */
public class User {
    private String username;
    private String password;
    private String gender;
    private int age;
    public User(String username, String password, String gender, int age) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.age = age;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    //注意这里重写了equals方法
    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }else {
            if(this.getClass() == obj.getClass()){
                User u = (User) obj;
                if(this.getUsername().equals(u.getUsername())){
                    return true;
                }else{
                    return false;
                }

            }else{
                return false;
            }
        }
    }
}
