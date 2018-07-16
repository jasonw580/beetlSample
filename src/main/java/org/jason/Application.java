package org.jason;

import org.beetl.sql.core.*;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.jason.beans.Config;
import org.jason.beans.Party;
import org.jason.beans.User;
import org.jason.dao.UserDao;
import org.jason.service.InsertSample;
import org.jason.test.GeneCodeSample;
import org.jason.test.PartySample;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class Application {

    public static void main(String args[]) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

        Config config = (Config) ctx.getBean("config");
        ConnectionSource source = ConnectionSourceHelper.getSimple(config.getDriver(),
                config.getUrl(),
                config.getName(), config.getPasswd());
        DBStyle mysql = new MySqlStyle();
        // sql语句放在classpath的/sql 目录下
        SQLLoader loader = new ClasspathLoader("/sql");
        // 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion，下划线风格的，
        UnderlinedNameConversion nc = new UnderlinedNameConversion();
        // 最后，创建一个SQLManager,DebugInterceptor 不是必须的，但可以通过它查看sql执行情况
        SQLManager sqlManager = new SQLManager(mysql, loader, source, nc, new Interceptor[]{new DebugInterceptor()});
//        SQLManager sqlManager = new SQLManager(mysql, loader, source, nc, new Interceptor[]{});


        InsertSample insertSample = new InsertSample(sqlManager);

        insertSample.insert();

        //使用内置sql查询用户
        int id = 4;
        User user = sqlManager.unique(User.class, id);

        //模板更新,仅仅根据id更新值不为null的列
        User newUser = new User();
        newUser.setId(1);
        newUser.setAge(20);
        sqlManager.updateTemplateById(newUser);

        //模板查询
        User query = new User();
        query.setName("xiandafu1");
        List<User> list = sqlManager.template(query);

        //Query查询
//        Query userQuery = sqlManager.query(User.class);
//        List<User> users = userQuery.lambda().andEq(User::getName, "xiandafy").select();
        List<User> users = sqlManager.lambdaQuery(User.class).andEq(User::getName, "xiandafu2").select();

        //使用user.md 文件里的select语句，参考下一节。
        User query2 = new User();
        query.setName("xiandafu" + Math.abs(new Random().nextInt()) % 10);
        List<User> list2 = sqlManager.select("user.select", User.class, query2);

        // 这一部分需要参考mapper一章
//        UserDao dao = sqlManager.getMapper(UserDao.class);
//        List<User> list3 = dao.select(query2);

        new Thread(new Runnable() {
            @Override
            public void run() {
                GeneCodeSample geneCodeSample = new GeneCodeSample(sqlManager);
                System.out.println("开始执行线程。。。。");
                // 生成指定表对应的bean类
                geneCodeSample.generateCode("party");
//                geneCodeSample.generateSqlTemplateCode("party");

                PartySample partySample = new PartySample(sqlManager);

                // insert
                Party party = partySample.generateParty(1,2);
//                partySample.insertParty(party);
                partySample.insertParty();

                // query all
                List<Party> parties = partySample.queryAll();
                for (Party p : parties) {
                    System.out.println(p.toString());
                }
                // query unique
                party = partySample.queryById(1, 2);
                System.out.println(party.toString());



                User u2 = new User();
                u2.setName("baidu");
                u2.setAge(24);
                u2.setUserName("ba");
                sqlManager.getMapper(UserDao.class).insert(u2);

                List<User> users = sqlManager.getMapper(UserDao.class).select("xiandafu2");
                for (User u : users){
                    System.out.println(u.toString());
                }

                int count = sqlManager.getMapper(UserDao.class).getCount();
                System.out.println("所有记录：" +count);
            }
        }).start();
        ctx.close();
    }
}
