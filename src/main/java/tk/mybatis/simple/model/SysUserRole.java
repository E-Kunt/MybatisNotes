package tk.mybatis.simple.model;

/**
 * 用户角色关联
 * @author E-Kunt
 *
 */
public class SysUserRole {

	private Long userId;
	private Long roleId;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
}
