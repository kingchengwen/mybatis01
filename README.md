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

# mybatis02-config
    2、mybatis-config.xml中的属性配置
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!--
        1、mybatis 可以使用properties来引入外部properties配置文件的内容；
        resource ：引入类路径下资源
        url ：引入网络路径或者磁盘路径下的资源
    -->
    <properties resource="properties/dbconfig.properties"></properties>

    <!--
        2、settings 包含很多重要的设置项
        setting：用来设置每一个设置项
        name：设置项名
        value：设置项取值
    -->
    <settings>
        <!--启动驼峰命名法-->
        <setting name="mapUnderscoreToCamelCase" value="true"/>
    </settings>

    <!--
        3、typeAliases 别名处理器，可以为我们的java类型起别名
        typeAlias 为某个java类型起别名
        type 指定要起别名的类型全类名，默认别名就是类名小写；
        alias 指定新的别名

        package：为某个包下所有的所有类批量起别名(批量起别名)
        name：指定包名（为当前包以及下面所有的后代包的每一个类都起一个默认别名，类名小写）
        批量起别名的情况下，使用@Alias注解为某个类型指定新的别名
    -->
    <typeAliases>
        <typeAlias type="com.mi.pojo.Employee" alias="employee"></typeAlias>
        <package name="com.mi.pojo"/>
    </typeAliases>

    <!--
        4、environments 环境，mybatis可以配置多种环境，default指定使用某种环境，可以达到快速切换环境的目的
        environment：配置一个具体的环境信息；必须有两个标签，id代表当前环境的唯一标识
        transactionManager：事务管理器
            type：事务管理器的类型：JDBC (JdbcTransactionFactory)| MANAGED (ManagedTransactionFactory)
            自定义事务管理器：实现TransactionFactory接口，type 指定为全类名
        dataSource：数据源
            type：数据源类型 UNPOOLED（UnpooledDataSourceFactory）｜
                            POOLED （PooledDataSourceFactory）|
                            JNDI（JndiDataSourceFactory）
            自定义数据源：实现DataSourceFactory接口，type是全类名
    -->
    <environments default="development">
        <environment id="test">
            <transactionManager type="JDBC"></transactionManager>
            <dataSource type="POOLED">
                <property name="driver" value="${jdbc.dirver}"/>
                <property name="url" value="${jdbc.url}"/>
                <property name="username" value="${jdbc.username}"/>
                <property name="password" value="${jdbc.password}"/>
            </dataSource>
        </environment>
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${jdbc.dirver}"/>
                <property name="url" value="${jdbc.url}"/>
                <property name="username" value="${jdbc.username}"/>
                <property name="password" value="${jdbc.password}"/>
            </dataSource>
        </environment>
    </environments>
    <!--
        5、mappers：将sql映射注册到全局配置中
        mapper：注册一个sql映射
            resource：引用类路径下的sql映射文件
            url：引用网络路径或者磁盘路径下的sql映射文件
            class：引用（注册）接口
                有sql映射文件，映射文件名必须和接口同名，并且放在与接口同一目录下；
                没有sql映射文件，所有的sql都是利用注解写在接口上；

        package：批量注册,需要映射文件xml和dao接口同一包下
        <package name="com.mi.dao"/>

    -->
    <!--将我们写好的sql映射文件EmployeeMapper.xml一定要注册到全局配置文件mybatis-config.xml-->
    <mappers>
        <mapper resource="mapper/EmployeeMapper.xml"/>
        <!--<package name="com.mi.dao"/>-->
    </mappers>
</configuration>


