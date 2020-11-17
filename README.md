# mybatis01
Mybatis helloworld


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
