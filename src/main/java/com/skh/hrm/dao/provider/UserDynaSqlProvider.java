package com.skh.hrm.dao.provider;

import com.skh.hrm.common.HrmConstants;
import com.skh.hrm.domain.User;
import org.apache.ibatis.jdbc.SQL;

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
}
