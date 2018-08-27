package com.bmsmart.mes.mesDemo.export;

import com.bmsmart.mes.mesDemo.export.request.TestRequest;
import com.bmsmart.mes.mesDemo.export.response.TestResponse;

/**
 * 
 * TODO(这里用一句话描述这个类的作用) 
 * @author Malan
 * @date 2017年5月24日 下午2:57:18  
 *
 */
public interface TestServiceExport {

    public TestResponse queryTestExport(TestRequest testRequest);
}
