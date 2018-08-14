package com.skh.hrm.dao;

import com.skh.hrm.common.HrmConstants;
import com.skh.hrm.dao.provider.NoticeDyanSqlProvider;
import com.skh.hrm.domain.Notice;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

/**
 * Created by skh on 2018/8/13.
 */
@Mapper
public interface NoticeDao {
    // 动态查询
    @SelectProvider(type=NoticeDyanSqlProvider.class,method="selectWhitParam")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="create_date",property="createDate",javaType=java.util.Date.class),
            @Result(column="user_id",property="user",
                    one=@One(select="org.skh.hrm.dao.UserDao.selectById",
                            fetchType=FetchType.EAGER))
    })
    List<Notice> selectByPage(Map<String, Object> params);

    @SelectProvider(type=NoticeDyanSqlProvider.class,method="count")
    Integer count(Map<String, Object> params);

    @Select("select * from "+HrmConstants.NOTICETABLE +" where ID = #{id}")
    Notice selectById(int id);

    // 根据id删除公告
    @Delete(" delete from "+HrmConstants.NOTICETABLE+" where id = #{id} ")
    void deleteById(Integer id);

    // 动态插入公告
    @SelectProvider(type=NoticeDyanSqlProvider.class,method="insertNotice")
    void save(Notice notice);

    // 动态修改公告
    @SelectProvider(type=NoticeDyanSqlProvider.class,method="updateNotice")
    void update(Notice notice);
}
