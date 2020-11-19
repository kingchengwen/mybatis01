package com.mi.test;

import com.mi.dao.EmployeeMapper;
import com.mi.pojo.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatis {
    /**
     *  1、根据xml配置文件（全局配置文件 mybatis-config.xml）创建一个SqlSessionFactory对象，有数据源等一些运行环境信息
     *  2、sql映射文件EmployeeMapper.xml，配置了每一个sql，以及sql的封装规则等。
     *  3、将sql映射文件注册在全局配置文件中 <mapper resource="mapper/EmployeeMapper.xml"/>
     *  4、写代码
     *      1）根据全局配置文件得到SqlSessionFactory；
     *      2）使用SqlSessionFactory工厂获取到SqlSession对象，使用它来执行增删改查
     *      一个SqlSession就是代表和数据库的一次会话，用完关闭。
     *      3）使用sql的唯一标志来告诉mybatis执行哪个sql，sql保存在sql映射文件的
     */
    /**
     * 1、接口式编程
     * 原生：    Dao     --> DaoImpl
     * mybatis： Mapper --> xxMapper.xml
     * 2、SqlSession代表和数据库的一次会话，用完必须关闭；
     * 3、SqlSession 和 connection一样都是非线程安全；每次使用都应该获取新的对象。
     * 4、mapper接口没有实现类，但是mybatis会为这个接口生成一个代理对象。
     *      （将接口和xml进行绑定）
     *      EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
     * 5、两个重要的配置文件：
     *      mybatis 的全局配置文件 mybatis-config.xml：包含数据库连接池信息、事务管理器等系统运行环境信息
     *      sql 映射文件 EmployeeMapper.xml：保存了每一个sql语句的映射信息；将sql 抽取出来。
     *
     * @return
     * @throws IOException
     */

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource  = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void test() throws IOException {
        // 1、获取SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        // 2、获取Sqlsesion对象 ,获取到的sqlsession 不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            // 3、获取接口的实现类对象
            // 会为接口自动创建一个代理对象，代理对象去执行增删改查方法
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            Employee employee = mapper.getEmployeeById(1);

            List<Employee> list = mapper.getEmployees();

//            Employee employee = new Employee(3, "zhoumin","女");
//            mapper.addEmp(employee);

//            Employee employee = new Employee(4, "jingzhe","女");
//            mapper.updateEmp(employee);

//            mapper.deleteEmp(4);

            sqlSession.commit();
            System.out.println(employee);
            System.out.println(list);
        }finally {
            sqlSession.close();
        }
    }


}
