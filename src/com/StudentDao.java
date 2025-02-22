package com;

import com.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// 数据访问层：原子性的增删改查
public class StudentDao {
    private final String URL = "jdbc:mysql://localhost:3306/student";
    private final String USERNAME = "root";
    private final String PASSWORD = "12241122---qrx";

    public boolean isExist(int sno) { // true：此人存在 false：不存在
        return queryStudentBySno(sno) != null;
    }

    public boolean addStudent(Student student) { // zs 23 xa
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String sql = "insert into student(sno, sname, sage, saddress) values(?, ?, ?, ?)";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,student.getSno());
            pstmt.setString(2, student.getSname());
            pstmt.setInt(3,student.getSage());
            pstmt.setString(4, student.getSaddress());
            int count = pstmt.executeUpdate();
            if (count > 0)
                return true;
            else
                return false;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 根据学号修改学生：根据sno知道待修改的人，把这个人修改成student
    public boolean updateStudentBySno(int sno, Student student) { // 3 -> zs 23 bj
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String sql = "update student set sname = ?, sage = ?, saddress = ? where sno = ?";
            pstmt = connection.prepareStatement(sql);
            // 修改后的内容
            pstmt.setString(1, student.getSname());
            pstmt.setInt(2, student.getSage());
            pstmt.setString(3, student.getSaddress());
            // 修改的那个人
            pstmt.setInt(4, sno);
            int count = pstmt.executeUpdate();
            if (count > 0)
                return true;
            else
                return false;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 根据学号删除学生
    public boolean deleteStudentBySno(int sno) { // zs 23 xa
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String sql = "delete from student where sno = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, sno);
            int count = pstmt.executeUpdate();
            if (count > 0)
                return true;
            else
                return false;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 查询全部学生（很多学生）
    public List<Student> queryAllStudents() {
        List<Student> students = new ArrayList<>();
        Student student = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String sql = "select * from student";
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int no = rs.getInt("sno");
                String name = rs.getString("sname");
                int age = rs.getInt("sage");
                String address = rs.getString("saddress");
                student = new Student(no, name, age, address);
                students.add(student);
            }
            return students;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 根据学号查询学生
    public Student queryStudentBySno(int sno) {
        Student student = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String sql = "select * from student where sno = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, sno);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                int no = rs.getInt("sno");
                String name = rs.getString("sname");
                int age = rs.getInt("sage");
                String address = rs.getString("saddress");
                student = new Student(no, name, age, address);
            }
            return student;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
