package com.pgy.spring.dao;

import com.pgy.spring.service.BasicService;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by pengguangyu on 2016/1/22.
 */

public class JdbcService implements BasicService {

    private JdbcTemplate jdbcTemplate;

    /**
     * 分页查询
     * @param pageNum 页码，必须大于或等于1
     * @param pageSize 每页大小，即每页的条目数，必须不小于1
     */
    public List pagedQuery(String sql, Object[] args, int[] argsType,
                           int pageNum, int pageSize) {
        if (pageNum <= 0) {
            throw new RuntimeException("Page number must start from 1.");
        }
        if (pageSize <= 0) {
            throw new RuntimeException("Page size must NOT be less than 1.");
        }
        int startIndex = (pageNum - 1) * pageSize + 1;
        int lastIndex = pageNum * pageSize;
        return queryPage(sql, args, argsType, startIndex, lastIndex);
    }

    @Override
    public int update(String sql, Object[] args, int[] argsType) {
        return 0;
    }

    /**
     * 分页查询(不衔接上一页的最后一条)	--chenjianhua
     */
    public List pagedQueryNotTransition(String sql, Object[] args, int[] argsType,
                                        int pageNum, int pageSize) {
        if(0>=pageNum){
            throw new RuntimeException("pageNum start from 1.");
        }
        int startIndex = ((pageNum - 1) * pageSize)==0?1:((pageNum - 1) * pageSize+1);
        int lastIndex = startIndex + pageSize - 1;
        StringBuffer paginationSQL = new StringBuffer(" SELECT * FROM ( ");
        paginationSQL.append(" SELECT temp.* ,ROWNUM num FROM ( ");
        paginationSQL.append(sql);
        paginationSQL.append("　) temp where ROWNUM <= " + lastIndex);
        paginationSQL.append(" ) WHERE　num >= " + startIndex);
        return this.jdbcTemplate.queryForList(paginationSQL.toString(), args,
                argsType);
    }

    /**
     * 重写上方法，对于分页传入的是当前页的最开始数据量，以及结束数据量
     * @param sql 基本的sql语句，对目标表进行查询
     * @param args 查询参数
     * @param argsType 参数类型
     * @param startIndex 起始索引，不能小于1
     * @param lastIndex 终止索引，不能小于起始索引
     * @return 查询结果集
     */
    public List queryPage(String sql, Object[] args, int[] argsType,
                          int startIndex, int lastIndex) {
        if (startIndex < 1) {
            throw new RuntimeException("Start index must be positive and start from 1.");
        }
        if (lastIndex < startIndex) {
            throw new RuntimeException("Last index must NOT be less than start index");
        }
        StringBuffer paginationSQL = new StringBuffer(" SELECT * FROM ( ");
        paginationSQL.append(" SELECT temp.* ,ROWNUM num FROM ( ");
        paginationSQL.append(sql);
        paginationSQL.append("　) temp where ROWNUM <= " + lastIndex);
        paginationSQL.append(" ) WHERE　num >= " + startIndex);
        return this.jdbcTemplate.queryForList(paginationSQL.toString(), args,
                argsType);
    }

    /**
     * 用于返回所有数据量的操作
     * @param sql：sql语句
     * @param args：参数
     * @param argsType：参数类型
     * @return
     */
    public int getCntQuery(String sql, Object[] args, int[] argsType) {
        return this.jdbcTemplate.queryForInt(sql, args, argsType);
    }

    /**
     * 找单个对象
     */
    public Map queryForSingle(String sql,Object[] args, int[] argsType){
        Map map = null;
        JdbcTemplate jdbcTemplate1 = this.jdbcTemplate;
        List list = jdbcTemplate1.queryForList(sql, args, argsType);
        if(list.size()>0){
            map = (Map)list.get(0);
        }
        return map;
    }
    /**
     * 按列表返回查找结果
     */
    public List queryForList(String sql,Object[] args, int[] argsType){
        return this.jdbcTemplate.queryForList(sql, args, argsType);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
