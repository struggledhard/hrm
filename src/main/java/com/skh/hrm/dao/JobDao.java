package com.skh.hrm.dao;

import com.skh.hrm.common.HrmConstants;
import com.skh.hrm.dao.provider.JobDynaSqlProvider;
import com.skh.hrm.domain.Job;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * Created by skh on 2018/8/13.
 *
 */
public interface JobDao {
    @Select("select * from "+HrmConstants.JOBTABLE +" where ID = #{id}")
    Job selectById(int id);

    @Select("select * from "+HrmConstants.JOBTABLE+" ")
    List<Job> selectAllJob();

    // 动态查询
    @SelectProvider(type=JobDynaSqlProvider.class,method="selectWhitParam")
    List<Job> selectByPage(Map<String, Object> params);

    @SelectProvider(type=JobDynaSqlProvider.class,method="count")
    Integer count(Map<String, Object> params);

    // 根据id删除部门
    @Delete(" delete from "+HrmConstants.JOBTABLE+" where id = #{id} ")
    void deleteById(Integer id);

    // 动态插入部门
    @SelectProvider(type=JobDynaSqlProvider.class,method="insertJob")
    void save(Job job);

    // 动态修改用户
    @SelectProvider(type=JobDynaSqlProvider.class,method="updateJob")
    void update(Job job);
}
