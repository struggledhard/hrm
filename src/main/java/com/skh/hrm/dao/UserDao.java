package com.skh.hrm.dao;

import com.skh.hrm.common.HrmConstants;
import com.skh.hrm.dao.provider.UserDynaSqlProvider;
import com.skh.hrm.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

/**
 * Created by skh on 2018/8/6.
 */
public interface UserDao {
    @Select("select * from" + HrmConstants.USERTABLE + "where loginname=#{loginname} and password=#{password}")
    User selectByLoginnameAndPassword(@Param("loginname") String loginName, @Param("password") String password);

    @Select("select * from" + HrmConstants.USERTABLE + "where id=#{id}")
    User selectById(Integer id);

    @Delete("delete from" + HrmConstants.USERTABLE + "where id=#{id}")
    void deleteByID(Integer id);

    @SelectProvider(type = UserDynaSqlProvider.class, method = "updateUser")
    void update(User user);

}