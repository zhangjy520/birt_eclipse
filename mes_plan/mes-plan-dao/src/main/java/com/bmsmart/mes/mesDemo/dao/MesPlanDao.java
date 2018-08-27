package com.bmsmart.mes.mesDemo.dao;

import java.util.List;

import com.bmsmart.mes.mesDemo.dao.mybatis.MyBatisRepository;
import com.bmsmart.mes.mesDemo.domain.po.DemoTest;
import com.bmsmart.mes.mesDemo.domain.vo.response.MesPlanVo;
@MyBatisRepository
public interface MesPlanDao {
	 public List<MesPlanVo> selectList(MesPlanVo mesPlanVo);
	    public void insert(MesPlanVo d);
}
