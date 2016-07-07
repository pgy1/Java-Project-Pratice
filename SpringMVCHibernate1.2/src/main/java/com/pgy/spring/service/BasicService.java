package com.pgy.spring.service;

import java.util.List;
import java.util.Map;

/**
 * Created by pengguangyu on 2016/1/21.
 */

public interface BasicService {

//    public JdbcTemplate getJdbcTemplate();


    /**
     * ��ҳ��ѯ
     */
    public List pagedQuery(String sql, Object[] args, int[] argsType,
                           int pageNum, int pageSize);

    /**
     * ֧�ִ�������
     */
    public int update(String sql, Object[] args, int[] argsType);

    /**
     * ���ڷ�ҳ������ǵ�ǰҳ���ʼ���������Լ�����������
     *
     * @param sql
     * @param args
     * @param argsType
     * @param startIndex
     * @param lastIndex
     * @return
     */
    public List queryPage(String sql, Object[] args, int[] argsType,
                          int startIndex, int lastIndex);

    /**
     * ���ڷ��������������Ĳ���
     *
     * @param sql
     *            ��sql���
     * @param args
     *            ������
     * @param argsType
     *            ����������
     * @return
     */
    public int getCntQuery(String sql, Object[] args, int[] argsType);

    /**
     * �ҵ�������
     */
    public Map queryForSingle(String sql, Object[] args, int[] argsType);

    /**
     * ���б��ز��ҽ��
     */
    public List queryForList(String sql, Object[] args, int[] argsType);

}
