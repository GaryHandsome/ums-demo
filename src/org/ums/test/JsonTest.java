package org.ums.test;

import com.google.gson.Gson;
import org.ums.entity.Stu;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2022-10-28
 * @Author zqx
 */
public class JsonTest {
    public static void main(String[] args) {
        // 实例化一个 JAVA 对象
        Stu stu1 = new Stu("zhangsan", 28);
        Stu stu2 = new Stu("wangwu", 38);
        Stu stu3 = new Stu("zhaoliu", 48);
        Stu stu4 = new Stu("tianqi", 58);

        List<Stu> list = new ArrayList<>() ;
        list.add(stu1) ;
        list.add(stu2) ;
        list.add(stu3) ;
        list.add(stu4) ;

        // GSON：把对象转换为 JSON 字符串
        String json1 = new Gson().toJson(stu1);
        System.out.println(json1);

        String json2 = new Gson().toJson(list);
        System.out.println(json2);

    }
}
