package tk.mybatis.simple.model;

/**
 * 角色权限关联
 * @author E-Kunt
 *
 */
public class SysRolePrivilege {

	private Long roleId;
	private Long privilegeId;
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getPrivilegeId() {
		return privilegeId;
	}
	public void setPrivilegeId(Long privilegeId) {
		this.privilegeId = privilegeId;
	}
	
	
}
