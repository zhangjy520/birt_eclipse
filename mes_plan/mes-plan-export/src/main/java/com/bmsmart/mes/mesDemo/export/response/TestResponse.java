package com.bmsmart.mes.mesDemo.export.response;


import java.util.List;

import com.bmsmart.mes.mesDemo.domain.bean.Result;
import com.bmsmart.mes.mesDemo.domain.po.DemoTest;

/**
 * 
 * TODO(这里用一句话描述这个类的作用) 
 * @author Malan
 * @date 2017年5月24日 下午2:57:06  
 *
 */
public class TestResponse {

    private Result result;

    private List<DemoTest> demoTestList;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

	public List<DemoTest> getDemoTestList() {
		return demoTestList;
	}

	public void setDemoTestList(List<DemoTest> demoTestList) {
		this.demoTestList = demoTestList;
	}

}
