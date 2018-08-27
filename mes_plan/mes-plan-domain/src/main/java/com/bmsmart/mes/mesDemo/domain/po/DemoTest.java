package com.bmsmart.mes.mesDemo.domain.po;

import java.io.Serializable;

/**
 * 
 * TODO(这里用一句话描述这个类的作用) 
 * @author Malan
 * @date 2017年5月24日 下午2:46:08  
 *
 */
public class DemoTest implements Serializable{

    private static final long serialVersionUID = -8881574583673928417L;

    private String id;

    private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
