package org.ums.entity;

/**
 * 封装数据
 *
 * @Date 2022-10-28
 * @Author zqx
 */
public class Stu {
    private String name ;
    private int age ;

    // alt + insert(ins)

    public Stu() {

    }

    public Stu(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
