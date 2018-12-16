package com.ssxu.sso;

/**
 * 封装的外部系统需要的session数据
 * @author 石森
 */
public class SessionUser {
	/** 当前登录用户的id*/
	private String userId;
	/** 当前登录用户的名称*/
	private String userName;
	/** 当前登录用户企业id*/
	private String tenantId;
	/** 当前登录用户企业名称*/
	private String tenantName;
	/** 当前登录用户的组织机构id*/
	private String orgId;
	/** 当前登录用户的组织机构名称*/
	private String orgName;
	/** 权限系统校验的令牌*/
	private String token;
	/**  数据权限 逗号分隔*/
	private String userOrgDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserOrgDate() {
		return userOrgDate;
	}

	public void setUserOrgDate(String userOrgDate) {
		this.userOrgDate = userOrgDate;
	}
}
