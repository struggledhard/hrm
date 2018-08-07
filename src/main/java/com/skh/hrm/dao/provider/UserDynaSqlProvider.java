package com.skh.hrm.dao.provider;

import com.skh.hrm.common.HrmConstants;
import com.skh.hrm.domain.User;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * Created by skh on 2018/8/6.
 * 动态SQL提供类
 */
public class UserDynaSqlProvider {
    public String updateUser(User user) {
        SQL sql = new SQL();
        sql.UPDATE(HrmConstants.USERTABLE);
        if (user.getLoginName() != null) {
            sql.SET("loginname=#{loginName}");
        }
        if (user.getUserName() != null) {
            sql.SET("username=#{userName}");
        }
        if (user.getPassword() != null) {
            sql.SET("password=#{password}");
        }
        if (user.getStatus() != null) {
            sql.SET("status=#{status}");
        }
        if (user.getCreateDate() != null) {
            sql.SET("createdate=#{createDate}");
        }
        return sql.toString();
    }

    // 分页动态查询
    public String selectWithParam(Map<String, Object> param) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM(HrmConstants.USERTABLE);
        if (param.get("user") != null) {
            User user = (User) param.get("user");
            if (user.getUserName() != null && !user.getUserName().equals("")) {
                sql.WHERE("username like concat ('%',#{userName},'%')");
            }
            if (user.getStatus() != null) {
                sql.WHERE("status like concat ('%',#{status},'%')");
            }
        }
        String s = sql.toString();
        if (param.get("pageModel") != null) {
            s += "limit #{pageModel.firstLimitParam}, #{pageModel.pageSize}";
        }
        return s;
    }

    // 动态查询总数量
    public String count(Map<String, Object> param) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM(HrmConstants.USERTABLE);
        if (param.get("user") != null) {
            User user = (User) param.get("user");
            if (user.getUserName() != null && !user.getUserName().equals("")) {
                sql.WHERE("username like concat ('%',#{userName},'%')");
            }
            if (user.getStatus() != null) {
                sql.WHERE("status like concat ('%',#{status},'%')");
            }
        }
        return sql.toString();
    }

    public String insertUser(User user) {
        return new SQL() {
            {
                INSERT_INTO(HrmConstants.USERTABLE);
                if (user.getUserName() != null && !user.getUserName().equals("")) {
                    VALUES("username", "#{userName}");
                }
                if (user.getLoginName() != null && !user.getLoginName().equals("")) {
                    VALUES("loginname", "#{loginName}");
                }
                if (user.getPassword() != null && !user.getPassword().equals("")) {
                    VALUES("password", "#{password}");
                }
                if (user.getStatus() != null) {
                    VALUES("status", "#{status}");
                }
            }
        }.toString();
    }
}
