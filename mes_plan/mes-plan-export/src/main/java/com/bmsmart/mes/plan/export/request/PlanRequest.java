package com.bmsmart.mes.plan.export.request;

import com.bmsmart.mes.commons.export.request.BaseRequest;

public class PlanRequest<T> extends BaseRequest {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private T requestObj;

	public T getRequestObj() {
		return requestObj;
	}

	public void setRequestObj(T requestObj) {
		this.requestObj = requestObj;
	}
	
	
}
