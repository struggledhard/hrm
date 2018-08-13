package com.skh.hrm.dao;

import com.skh.hrm.common.HrmConstants;
import com.skh.hrm.dao.provider.DocumentDynaSqlProvider;
import com.skh.hrm.domain.Document;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

/**
 * Created by skh on 2018/8/13.
 */
@Mapper
public interface DocumentDao {
    // 动态查询
    @SelectProvider(type=DocumentDynaSqlProvider.class,method="selectWhitParam")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="create_date",property="createDate",javaType=java.util.Date.class),
            @Result(column="user_id",property="user",
                    one=@One(select="org.skh.hrm.dao.UserDao.selectById",
                            fetchType=FetchType.EAGER))
    })
    List<Document> selectByPage(Map<String, Object> params);

    @SelectProvider(type=DocumentDynaSqlProvider.class,method="count")
    Integer count(Map<String, Object> params);

    // 动态插入文档
    @SelectProvider(type=DocumentDynaSqlProvider.class,method="insertDocument")
    void save(Document document);

    @Select("select * from "+HrmConstants.DOCUMENTTABLE +" where ID = #{id}")
    Document selectById(int id);

    // 根据id删除文档
    @Delete(" delete from "+HrmConstants.DOCUMENTTABLE+" where id = #{id} ")
    void deleteById(Integer id);

    // 动态修改文档
    @SelectProvider(type=DocumentDynaSqlProvider.class,method="updateDocument")
    void update(Document document);
}
