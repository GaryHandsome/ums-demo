package org.ums.entity;

/**
 * 实体对象 - 用户对象
 *
 * @Date 2022-10-26
 * @Author zqx
 */
public class UserInfo {
    private int id ;
    private String username ;
    private String password ;
    private int userAge ;
    private String userSex ;
    private double weight ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userAge=" + userAge +
                ", userSex='" + userSex + '\'' +
                ", weight=" + weight +
                '}';
    }
}
