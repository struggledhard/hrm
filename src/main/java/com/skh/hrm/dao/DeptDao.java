package com.skh.hrm.dao;

import com.skh.hrm.common.HrmConstants;
import com.skh.hrm.dao.provider.DeptDynaSqlProvider;
import com.skh.hrm.domain.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * Created by skh on 2018/8/7.
 */
public interface DeptDao {
    @Select("select * from" + HrmConstants.DEPTTABLE)
    List<Dept> selectAllDept();

    @Select("select * from" + HrmConstants.DEPTTABLE + "where id=#{id}")
    Dept selectById(Integer id);

    @Delete("delete from" + HrmConstants.DEPTTABLE + "where id=#{id}")
    void deleteByID(Integer id);

    @SelectProvider(type = DeptDynaSqlProvider.class, method = "updateDept")
    void update(Dept dept);

    @SelectProvider(type = DeptDynaSqlProvider.class, method = "selectWithParam")
    List<Dept> selectByPage(Map<String, Object> param);

    @SelectProvider(type = DeptDynaSqlProvider.class, method = "count")
    Integer count(Map<String, Object> param);

    @SelectProvider(type = DeptDynaSqlProvider.class, method = "insertDept")
    void save(Dept dept);
}
