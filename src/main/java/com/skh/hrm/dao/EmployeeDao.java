package com.skh.hrm.dao;

import com.skh.hrm.common.HrmConstants;
import com.skh.hrm.dao.provider.EmployeeDynaSqlProvider;
import com.skh.hrm.domain.Employee;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

/**
 * Created by skh on 2018/8/13.
 */
public interface EmployeeDao {
    // 根据参数查询员工总数
    @SelectProvider(type=EmployeeDynaSqlProvider.class,method="count")
    Integer count(Map<String, Object> params);

    // 根据参数动态查询员工
    @SelectProvider(type=EmployeeDynaSqlProvider.class,method="selectWhitParam")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="card_id",property="cardId"),
            @Result(column="post_code",property="postCode"),
            @Result(column="qq_num",property="qqNum"),
            @Result(column="birthday",property="birthday",javaType=java.util.Date.class),
            @Result(column="create_date",property="createDate",javaType=java.util.Date.class),
            @Result(column="dept_id",property="dept",
                    one=@One(select="org.skh.hrm.dao.DeptDao.selectById",
                            fetchType=FetchType.EAGER)),
            @Result(column="job_id",property="job",
                    one=@One(select="org.skh.hrm.dao.JobDao.selectById",
                            fetchType=FetchType.EAGER))
    })
    List<Employee> selectByPage(Map<String, Object> params);

    // 动态插入员工
    @SelectProvider(type=EmployeeDynaSqlProvider.class,method="insertEmployee")
    void save(Employee employee);

    // 根据id删除员工
    @Delete(" delete from "+HrmConstants.EMPLOYEETABLE+" where id = #{id} ")
    void deleteById(Integer id);

    // 根据id查询员工
    @Select("select * from "+HrmConstants.EMPLOYEETABLE +" where ID = #{id}")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="card_id",property="cardId"),
            @Result(column="post_code",property="postCode"),
            @Result(column="qq_num",property="qqNum"),
            @Result(column="birthday",property="birthday",javaType=java.util.Date.class),
            @Result(column="create_date",property="createDate",javaType=java.util.Date.class),
            @Result(column="dept_id",property="dept",
                    one=@One(select="org.skh.hrm.dao.DeptDao.selectById",
                            fetchType=FetchType.EAGER)),
            @Result(column="job_id",property="job",
                    one=@One(select="org.skh.hrm.dao.JobDao.selectById",
                            fetchType=FetchType.EAGER))
    })
    Employee selectById(Integer id);

    // 动态修改员工
    @SelectProvider(type=EmployeeDynaSqlProvider.class,method="updateEmployee")
    void update(Employee employee);
}
