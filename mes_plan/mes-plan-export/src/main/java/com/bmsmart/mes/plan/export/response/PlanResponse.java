package com.bmsmart.mes.plan.export.response;

import com.bmsmart.mes.commons.export.response.BaseResponse;

public class PlanResponse<T> extends BaseResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private T retOb;

	public T getRetOb() {
		return retOb;
	}

	public void setRetOb(T retOb) {
		this.retOb = retOb;
	}
	
	public static <T> PlanResponse<T> getInstance(Boolean isSuccess , String info , T retOb){
		PlanResponse<T> planReponse = new PlanResponse<T>();
		planReponse.setSuccess(isSuccess);
		planReponse.setInfo(info);
		planReponse.setRetOb(retOb);
		return planReponse;
	}
	
}
