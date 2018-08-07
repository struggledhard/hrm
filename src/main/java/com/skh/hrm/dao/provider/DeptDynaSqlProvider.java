package com.skh.hrm.dao.provider;

import com.skh.hrm.common.HrmConstants;
import com.skh.hrm.domain.Dept;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * Created by skh on 2018/8/7.
 */
public class DeptDynaSqlProvider {
    // 分页动态查询
    public String selectWithParam(Map<String, Object> param) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM(HrmConstants.DEPTTABLE);
        if (param.get("dept") != null) {
            Dept dept = (Dept) param.get("dept");
            if (dept.getName() != null && !dept.getName().equals("")) {
                sql.WHERE("name like concat ('%',#{dept.name},'%')");
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
        sql.SELECT("count(*)").FROM(HrmConstants.DEPTTABLE);
        if (param.get("dept") != null) {
            Dept dept = (Dept) param.get("dept");
            if (dept.getName() != null && !dept.getName().equals("")) {
                sql.WHERE("name like concat ('%',#{dept.name},'%')");
            }
        }
        return sql.toString();
    }

    public String insertDept(Dept dept) {
        return new SQL() {
            {
                INSERT_INTO(HrmConstants.DEPTTABLE);
                if (dept.getName() != null && !dept.getName().equals("")) {
                    VALUES("name", "#{name}");
                }
                if (dept.getRemark() != null && !dept.getRemark().equals("")) {
                    VALUES("remark", "#{remark}");
                }
            }
        }.toString();
    }

    public String updateDept(Dept dept) {
        SQL sql = new SQL();
        sql.UPDATE(HrmConstants.DEPTTABLE);
        if (dept.getName() != null) {
            sql.SET("name=#{name}");
        }
        if (dept.getRemark() != null) {
            sql.SET("remark=#{remark}");
        }
        return sql.toString();
    }
}
