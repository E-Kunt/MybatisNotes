package tk.mybatis.simple.model;

import java.util.Date;
import java.util.List;

/**
 * 角色
 * @author E-Kunt
 *
 */
public class SysRole {
	private Long id;
	private String roleName;
	private Integer enabled;
	private Long createBy;
	private Date createTime;
	private SysUser user;
	//角色包含的权限（加此字段为了演示多对多 多层嵌套写法）
	private List<SysPrivilege> privilegeList;
	/*//创建信息（封装）（加此字段为了演示多对多 多层嵌套写法）
	private CreateInfo createInfo;*/
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public Long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public SysUser getUser() {
		return user;
	}
	public void setUser(SysUser user) {
		this.user = user;
	}
	
	public List<SysPrivilege> getPrivilegeList() {
		return privilegeList;
	}
	
	public void setPrivilegeList(List<SysPrivilege> privilegeList) {
		this.privilegeList = privilegeList;
	}
	
	/*public CreateInfo getCreateInfo() {
		return createInfo;
	}
	
	public void setCreateInfo(CreateInfo createInfo) {
		this.createInfo = createInfo;
	}*/
}
