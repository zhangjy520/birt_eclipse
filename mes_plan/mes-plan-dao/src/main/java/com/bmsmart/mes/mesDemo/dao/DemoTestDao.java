package com.bmsmart.mes.mesDemo.dao;

import java.util.List;

import com.bmsmart.mes.mesDemo.dao.mybatis.MyBatisRepository;
import com.bmsmart.mes.mesDemo.domain.po.DemoTest;

/**
 * 
 * TODO(这里用一句话描述这个类的作用) 
 * @author Malan
 * @date 2017年5月24日 下午2:46:39  
 *
 */
 
public interface DemoTestDao {

    public List<DemoTest> selectDemoTest ();
    public void insert(DemoTest d);
}
