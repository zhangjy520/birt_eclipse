package com.bmsmart.mes.mesDemo.domain.vo.response;

import com.bmsmart.mes.mesDemo.domain.bean.Result;

public class ResponseData {
	    
		public Result result = new Result();
	    private Object data;
	    
	  
		public Object getData() {
			return data;
		}
		public void setData(Object data) {
			this.data = data;
		}
	  
	
	
}
