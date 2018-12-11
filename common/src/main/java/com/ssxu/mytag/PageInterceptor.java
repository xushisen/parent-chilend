package com.ssxu.mytag;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

/**
 * 类描述：分页sql拦截
 * 创建人：徐石森
 * 创建时间：2018/11/5  10:46
 *
 * @version 1.0
 */
@Intercepts(@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}))
@Component
public class PageInterceptor implements Interceptor {
    // 数据库类型, pagehleper , url = jdbc:mysql
    private static String dialect = "mysql";

    // 分页关键字, .*ByPage$ = 已List结尾的方法就进行分页
    //private static String pageSqlId = ".*List$";
    private static String pageSqlId = ".*getList";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();

        // 引入Mybatis已经实现了的对象：MetaObject,把StatementHandler的实例，变为MetaObject的实例
        MetaObject metaObject = MetaObject.forObject(statementHandler,
                SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,
                new DefaultReflectorFactory());

        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        // mapper 方法名
        String id = mappedStatement.getId();
        // 根据自定义标识匹配需要分页方法[以List字符串结尾]
        if (id.matches(pageSqlId)) {
            // 获取sql的包装类BoundSql
            BoundSql boundSql = statementHandler.getBoundSql();
            Map<String, Object> params = (Map<String, Object>) boundSql.getParameterObject();
            if (params == null) {
                throw new NullPointerException("parameterObject error");
            } else {
                // 拿到原始sql
                String sql = boundSql.getSql();
                // 原始sql封装：count
                String countSql = "select count(0) from (" + sql + ") a";
                // mybatis执行查询总数sql
                Connection connection = (Connection) invocation.getArgs()[0];
                // jdbc
                PreparedStatement countStatement = connection.prepareStatement(countSql);
                // 获取查询条件的参数
                ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
                // 经过set方法，就可以正确的执行sql语句
                parameterHandler.setParameters(countStatement);
                ResultSet rs = countStatement.executeQuery();
                // 当结果集中有值时，表示页面数量大于等于1
                if (rs.next()) {
                    params.put("count", rs.getInt(1));
                }
                rs.close();
                countStatement.close();

                // 拼装分页sql
                // select * from user order by #{id} limit 0,5;
                String pageSql = generatePageSql(sql, params);
                // MetaObject对象来替换原来的查询语句
                metaObject.setValue("delegate.boundSql.sql", pageSql);
            }
        }
        // 默认mybatis
        return invocation.proceed();
    }

    /**
     * 生成分页sql   select * from user limit 0,5;
     *
     * @param sql sql
     * @return 返回分页sql
     */
    private String generatePageSql(String sql, Map<String, Object> pageMap) {
        StringBuffer pageSql = new StringBuffer();
        if ("mysql".equals(dialect)) {
            pageSql.append(sql);
            int pagesize = Integer.parseInt(pageMap.get("pagesize").toString());
            int pagebegin = (Integer.parseInt(pageMap.get("pagebegin").toString()) - 1) * pagesize;
            pageSql.append(" limit " + pagebegin + "," + pagesize);
        } else if ("oracle".equals(dialect)) {

        } //.....
        return pageSql.toString();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        dialect = properties.getProperty("dialect");
        pageSqlId = properties.getProperty("pageSqlId");
    }
}
