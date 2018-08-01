package tk.mybatis.simple.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

/**
 * 用户表接口（注：Mybatis采用【动态代理】的方法，参考XML的配置为该接口创建实例）
 * @author E-Kunt
 *
 */
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
	
	/**
	 * 通过用户id和角色enabled状态查用户的角色信息。
	 * 【每个参数前使用注解@Param！！】
	 * @param userId
	 * @param enabled
	 * @return
	 */
	List<SysRole> selectRolesByUserIdAndRoleEnabled(
			@Param("userId") Long userId,
			@Param("enabled") Integer enabled);
	
	/**
	 * 通过用户id和角色enabled状态查用户的角色信息。
	 * 多个参数是javabean的时候。
	 * 【每个参数前使用注解@Param！！】
	 * @param sysUser
	 * @param SysRole
	 * @return
	 */
	List<SysRole> selectRolesByUserAndRole(
			@Param("sysUser") SysUser sysUser,
			@Param("sysRole") SysRole sysRole);
	
	/**
	 * 根据动态条件查询用户信息
	 * @param sysUser
	 * @return
	 */
	List<SysUser> selectByUser(SysUser sysUser);
	
	/**
	 * 根据主键选择性更新
	 * @param sysUser
	 * @return
	 */
	int updateByIdSelective(SysUser sysUser);
}
