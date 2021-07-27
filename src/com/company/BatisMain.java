package com.company;

import com.company.model.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class BatisMain {
    private static SqlSessionFactory factory=null;
    public static void main(String[] args) throws IOException {
        String resource = "com/company/config/SqlMapConfig.xml";
        Reader reader = null;
        SqlSession session = null;
        reader = Resources.getResourceAsReader(resource);
        factory = new SqlSessionFactoryBuilder().build(reader);
        factory.getConfiguration().addMapper(Student.class);
        reader.close();
        try {
            session =factory.openSession();
            List<Student> students = session.selectList("getAll");

            for (Student st : students){
                System.out.print(st.getId()+ " ");
                System.out.print(st.getName()+ " ");
                System.out.print(st.getBranch()+ " ");
                System.out.print(st.getPercentage()+ " ");
                System.out.print(st.getEmail()+ " ");
                System.out.print(st.getPhone()+ " ");
                System.out.println();
            }
        }finally {
            if (session!=null){
                session.close();
            }
        }
        session.close();

        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

        try {
            session =factory.openSession();
            Student st = session.selectOne("getNB",2);

                System.out.print(st.getId()+ " ");
                System.out.print(st.getName()+ " ");
                System.out.print(st.getBranch()+ " ");
                System.out.print(st.getPercentage()+ " ");
                System.out.print(st.getEmail()+ " ");
                System.out.print(st.getPhone()+ " ");
                System.out.println();
        }finally {
            if (session!=null){
                session.close();
            }
        }
        session.close();

        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

        try {
            session =factory.openSession();
            Student stud = session.selectOne("getNB",2);
            stud.setEmail("mukamanakenny@gmail.com");
            stud.setPhone(783765567);
            session.update("update",stud);

            Student std = session.selectOne("getNB",2);

            System.out.print(std.getId()+ " ");
            System.out.print(std.getName()+ " ");
            System.out.print(std.getBranch()+ " ");
            System.out.print(std.getPercentage()+ " ");
            System.out.print(std.getEmail()+ " ");
            System.out.print(std.getPhone()+ " ");
            System.out.println();
        }finally {
            if (session!=null){
                session.commit();
                session.close();
            }
        }
        session.close();

        System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");

        try {
            session =factory.openSession();
            Student stude = new Student("name","KGLC",80,897655987,"igih@gmail.com");
            session.insert("insert",stude);
            Student std = session.selectOne("getNB",2);
        }finally {
            if (session!=null){
                session.commit();
                session.close();
            }
        }
        session.close();

        System.out.println("ddddddddddddddddddddddddddddddddddddddddddddddd");

        try {
            session =factory.openSession();;
            session.delete("delete",2);
            System.out.println("Deleted!!!!!!!!!!!!!!!");

        }finally {
            if (session!=null){
                session.commit();
                session.close();
            }
        }
        session.close();
    }
    }
