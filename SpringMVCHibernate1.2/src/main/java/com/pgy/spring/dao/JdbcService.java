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
     * ��ҳ��ѯ
     * @param pageNum ҳ�룬������ڻ����1
     * @param pageSize ÿҳ��С����ÿҳ����Ŀ�������벻С��1
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
     * ��ҳ��ѯ(���ν���һҳ�����һ��)	--chenjianhua
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
        paginationSQL.append("��) temp where ROWNUM <= " + lastIndex);
        paginationSQL.append(" ) WHERE��num >= " + startIndex);
        return this.jdbcTemplate.queryForList(paginationSQL.toString(), args,
                argsType);
    }

    /**
     * ��д�Ϸ��������ڷ�ҳ������ǵ�ǰҳ���ʼ���������Լ�����������
     * @param sql ������sql��䣬��Ŀ�����в�ѯ
     * @param args ��ѯ����
     * @param argsType ��������
     * @param startIndex ��ʼ����������С��1
     * @param lastIndex ��ֹ����������С����ʼ����
     * @return ��ѯ�����
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
        paginationSQL.append("��) temp where ROWNUM <= " + lastIndex);
        paginationSQL.append(" ) WHERE��num >= " + startIndex);
        return this.jdbcTemplate.queryForList(paginationSQL.toString(), args,
                argsType);
    }

    /**
     * ���ڷ��������������Ĳ���
     * @param sql��sql���
     * @param args������
     * @param argsType����������
     * @return
     */
    public int getCntQuery(String sql, Object[] args, int[] argsType) {
        return this.jdbcTemplate.queryForInt(sql, args, argsType);
    }

    /**
     * �ҵ�������
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
     * ���б��ز��ҽ��
     */
    public List queryForList(String sql,Object[] args, int[] argsType){
        return this.jdbcTemplate.queryForList(sql, args, argsType);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
