package com.bmsmart.mes.mesDemo.domain.bean;

import java.io.Serializable;

/**
 * 
 * TODO(这里用一句话描述这个类的作用) 
 * @author Malan
 * @date 2017年5月24日 下午2:45:55  
 *
 */
public class Result implements Serializable {

    private static final long serialVersionUID = 1572939099756121849L;

    public static final String SUCCESSED = "1";
    public static final String FAILURE = "2";
    public static final String PROGRESS = "3";

    /** 调用是否成功 **/
    private boolean isSuccess;
    /** 返回结果码 **/
    private String code;
    /** 返回结果信息 **/
    private String info;
    /** 返回状态 1：成功；2：失败；3：处理中 **/
    private String txnStatus;

    public Result(){
    }

    public Result(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isSuccess() {
        return isSuccess;
    }
    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getTxnStatus() {
        return txnStatus;
    }

    public void setTxnStatus(String txnStatus) {
        this.txnStatus = txnStatus;
    }


    public String toString() {

        return "{\"isSuccess\":"+isSuccess+",\"info\":\""+info+"\",\"code\":\""+code+"\"}";
    }

}
