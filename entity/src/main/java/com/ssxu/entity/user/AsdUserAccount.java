package com.ssxu.entity.user;

import java.util.Date;

/**
 * 
 * 类描述:   登录表
 * 创建人:   徐石森
 * 创建时间: 2018-07-26
 *
 * @version  1.0
 */
public class AsdUserAccount {

    private String userId;
    private String userLogin;
    private String userPwd;
    private String orgId;
    private String uPhone;
    private String userName;
    private String openid;
    private String tenantId;
    private Date createTime;
    private String userRole;
    private String userSex;
    private String userIdcar;
    private String terminal;
    private Date lastLoginDate;
    private int total;
    private String isEnable;
    private String deptId;
    private String school;
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId(){
        return userId;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserLogin(){
        return userLogin;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserPwd(){
        return userPwd;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgId(){
        return orgId;
    }

    public void setUPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public String getUPhone(){
        return uPhone;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName(){
        return userName;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOpenid(){
        return openid;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantId(){
        return tenantId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime(){
        return createTime;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserRole(){
        return userRole;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserSex(){
        return userSex;
    }

    public void setUserIdcar(String userIdcar) {
        this.userIdcar = userIdcar;
    }

    public String getUserIdcar(){
        return userIdcar;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getTerminal(){
        return terminal;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Date getLastLoginDate(){
        return lastLoginDate;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal(){
        return total;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public String getIsEnable(){
        return isEnable;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptId(){
        return deptId;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSchool(){
        return school;
    }


}