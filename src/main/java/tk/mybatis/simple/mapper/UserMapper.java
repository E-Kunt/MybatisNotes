package tk.mybatis.simple.mapper;

import java.util.List;
import java.util.Map;

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
	
	/**
	 * 根据ID或用户名查询
	 * @param sysUser
	 * @return
	 */
	SysUser selectByIdOrUserName(SysUser sysUser);
	
	/**
	 * 根据用户ID集合查询。
	 * 【若有多个参数，每个参数前使用注解@Param】
	 * @param idList
	 * @return
	 */
	List<SysUser> selectByIdList(List<Long> idList);
	
	/**
	 * 批量插入数据
	 * @param userList
	 * @return
	 */
	int insertList(List<SysUser> userList);
	
	/**
	 * 通过MAP更新列
	 * @param map
	 * @return
	 */
	int updateByMap(Map<String,Object> map);
	
	/**
	 * 根据USERID查USER和ROLE（发出1条查询语句）
	 * @param id
	 * @return
	 */
	SysUser selectUserAndRoleById(Long id);
	
	/**
	 * 根据USERID查USER和ROLE（发出1条查询语句）
	 * @param id
	 * @return
	 */
	SysUser selectUserAndRoleById2(Long id);
	
	
	/**
	 * 根据USERID查USER和ROLE（发出2条查询语句）
	 * @param id
	 * @return
	 */
	SysUser selectUserAndRoleByIdSelect(Long id);
	
	/**
	 * 查所有用户和对应角色信息
	 * @return
	 */
	List<SysUser> selectAllUserAndRoles();
	
	/**
	 * 通过嵌套查询获取指定用户的信息，以及用户的角色和权限信息
	 * 
	 * @param id
	 * @return
	 */
	SysUser selectAllUserAndRolesSelect(Long id);
	

}
