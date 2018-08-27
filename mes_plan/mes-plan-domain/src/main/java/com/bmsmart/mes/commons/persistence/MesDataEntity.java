/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.commons.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import javax.xml.bind.annotation.XmlTransient;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import com.bmsmart.mes.base.util.UUID;
import com.bmsmart.mes.base.util.prop.PropertyReader;
import com.bmsmart.mes.common.SessionUserInfo;
import com.bmsmart.mes.platform.export.vo.UserVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;

/**
 * 数据Entity类
 * @author ThinkGem
 * @version 2014-05-16
 */
public abstract class MesDataEntity<T>  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected String remarks;	// 备注
	protected String createUser;	// 创建者
	protected Date createTime;	// 创建日期
	protected String updateUser;	// 更新者
	protected Date updateTime;	// 更新日期
	protected String delFlag; 	// 删除标记（0：正常；1：删除；2：审核）

	private String createUserName;
	private String updateUserName;
	/**
	 * 实体编号（唯一标识）
	 */
	protected String id;
	
	/**
	 * 当前用户
	 */
	protected String currentUser;
	protected String currentEmpNo;//员工编号
	protected String currentUserName;
	
	/**
	 * 当前实体分页对象
	 */
	protected PageInfo<T> page;
	
	/**
	 * 自定义SQL（SQL标识，SQL内容）
	 */
	protected Map<String, String> sqlMap;
	
	/**
	 * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
	 * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
	 */
	protected boolean isNewRecord = false;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	


	@JsonIgnore
	@XmlTransient
	public PageInfo<T> getPage() {
		if (page == null){
			page = new PageInfo<T>();
		}
		return page;
	}
	
	public PageInfo<T> setPage(PageInfo<T> page) {
		this.page = page;
		return page;
	}

	@JsonIgnore
	@XmlTransient
	public Map<String, String> getSqlMap() {
		if (sqlMap == null){
			sqlMap = Maps.newHashMap();
		}
		return sqlMap;
	}

	public void setSqlMap(Map<String, String> sqlMap) {
		this.sqlMap = sqlMap;
	}
	
	
	
    /**
	 * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
	 * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
     * @return
     */
	public boolean getIsNewRecord() {
        return isNewRecord || StringUtils.isBlank(getId());
    }

	/**
	 * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
	 * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
	 */
	public void setIsNewRecord(boolean isNewRecord) {
		this.isNewRecord = isNewRecord;
	}

	
	/**
	 * 获取数据库名称
	 */
	@JsonIgnore
	public String getDbName(){
		return PropertyReader.getContextProperty("jdbc.type");
	}
	
    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        BaseEntity<?> that = (BaseEntity<?>) obj;
        return null == this.getId() ? false : this.getId().equals(that.getId());
    }
    
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
    
	/**
	 * 删除标记（0：正常；1：删除；2：审核；）
	 */
	public static final String DEL_FLAG_NORMAL = "0";
	public static final String DEL_FLAG_DELETE = "1";
	public static final String DEL_FLAG_AUDIT = "2";
	public MesDataEntity() {
		super();
		this.delFlag = DEL_FLAG_NORMAL;
	}
	
	public MesDataEntity(String id) {
		this();
		this.id = id;
	}
	
	/**
	 * 插入之前执行方法，需要手动调用
	 */
	public void preInsert(){
		// 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
		if (!this.isNewRecord){
			setId(UUID.getUUID());
		}
		this.updateTime = new Date();
		this.createTime = this.updateTime;
	}
	
	/**
	 * 更新之前执行方法，需要手动调用
	 */
	public void preUpdate(){
	/*	User user = UserUtils.getUser();
		if (StringUtils.isNotBlank(user.getId())){
			this.updateUser = user;
		}*/
		this.updateTime = new Date();
	}
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getCurrentUser() {
		if (StringUtils.isNotEmpty(currentUser)) {
			return currentUser;
		}
		Object userVo = SessionUserInfo.SessionInfo.get();
		if (userVo != null){
			 UserVo userVo2 = (UserVo)userVo;
			 currentEmpNo = userVo2.getNo();
			 currentUser = userVo2.getLoginName();
			 currentUserName =  userVo2.getName();
			 return currentUser;
		}else{
			return "";
		}
	}

	public String getCurrentUserName() {
		if (StringUtils.isNotEmpty(currentUserName)) {
			return currentUserName;
		}
		Object userVo = SessionUserInfo.SessionInfo.get();
		if (userVo != null){
			 UserVo userVo2 = (UserVo)userVo;
			 currentEmpNo = userVo2.getNo();
			 currentUser = userVo2.getLoginName();
			 currentUserName =  userVo2.getName();
			 return currentUserName;
		}else{
			return "";
		}
	}
	
	public String getCurrentEmpNo() {
		if (StringUtils.isNotEmpty(currentEmpNo)) {
			return currentEmpNo;
		}
		Object userVo = SessionUserInfo.SessionInfo.get();
		if (userVo != null){
			 UserVo userVo2 = (UserVo)userVo;
			 currentEmpNo = userVo2.getNo();
			 currentUser = userVo2.getLoginName();
			 currentUserName =  userVo2.getName();
			 return currentEmpNo;
		}else{
			return "";
		}
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}
	
	
}
