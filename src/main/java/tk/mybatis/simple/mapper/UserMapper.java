package tk.mybatis.simple.mapper;

import java.util.List;

import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

public interface UserMapper {

	SysUser selectById(Long id);
	
	List<SysUser> selectAll();
	
	/**
	 * 根据用户ID查角色信息
	 * @param userId
	 * @return
	 */
	List<SysRole> selectRolesByUserId(Long userId);
	
	int insert(SysUser sysUser);
	
	/**
	 * 使用useGeneratedKeys方式
	 * @param sysUser
	 * @return
	 */
	int insert2(SysUser sysUser);
	
	/**
	 * 使用selectKey方式
	 * @param sysUser
	 * @return
	 */
	int insert3(SysUser sysUser);
	
	int updateById(SysUser sysUser);
	
	/**
	 * 传入主键删除
	 * @param id
	 * @return
	 */
	int deleteById(Long id);
	
	/**
	 * 传入对象删除（重载同名函数，XML可以不修改，共用同一SQL语句）
	 * @param sysUser
	 * @return
	 */
	int deleteById(SysUser sysUser);
}
