package tk.mybatis.simple.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import tk.mybatis.simple.model.SysRole;

public interface RoleMapper {

	/**
	 * 采用注解方式配置SQL
	 * @param id
	 * @return
	 */
	/*
	 @Select({"select id, role_name roleName, enabled, create_by createBy, create_time createTime ",
			 "from sys_role ",
			 "where id = #{id}"})
	 */
	@Select({"select id, role_name, enabled, create_by, create_time "+
			 "from sys_role " +
			 "where id = #{id}"})
	SysRole selectById(Long id);
	
	
	@Results(value = {
		@Result(property = "id", column = "id", id = true),
		@Result(property = "roleName", column="role_name"),
		@Result(property = "enabled", column="enabled"),
		@Result(property = "createBy", column="create_by"),
		@Result(property = "createTime", column="create_Time"),
	})//3.3.1后的版本@Results增加了id属性，可通过@ResultMap("@Results的id属性")，引用这个配置
	@Select({"select id, role_name, enabled, create_by, create_time "+
			 "from sys_role " +
			 "where id = #{id}"})
	SysRole selectById2(Long id);
	
	/*@ResultMap("XML中<resultMap>的id属性")
	//或@ResultMap("@Results的id属性") //3.3.1后的版本
	@Select("select * from sys_role")
	List<SysRole> selectAll();*/
	
	@Insert({"insert into sys_role(id,role_name,enabled,create_by,create_time)",
			 "values(#{id}, #{roleName}, #{enabled}, #{createBy},",
			 "#{createTime, jdbcType=TIMESTAMP})"}) //无自增 id
	int insert(SysRole sysRole);
	
	@Insert({"insert into sys_role(role_name,enabled,create_by,create_time)",
		 "values(#{roleName}, #{enabled}, #{createBy},",
		 "#{createTime, jdbcType=TIMESTAMP})"})
	@Options(useGeneratedKeys=true, keyProperty="id") //自增id
	int insert2(SysRole sysRole);
	
	@Insert({"insert into sys_role(role_name,enabled,create_by,create_time)",
		 "values(#{roleName}, #{enabled}, #{createBy},",
		 "#{createTime, jdbcType=TIMESTAMP})"})
	@SelectKey(statement="select last_insert_id()",
		keyProperty="id",
		resultType=Long.class,
		before=false) //返回非自增id(mysql)
	/*
	@SelectKey(statement="select SEQ_ID.nextval from dual",
		keyProperty="id",
		resultType=Long.class,
		before=true) //返回非自增id(Oracle)
	 */
	int insert3(SysRole sysRole);
	
	@Update({"update sys_role",
			 "set role_name = #{roleName},",
			 	 "enabled = #{enabled},",
			 	 "create_by = #{createBy},",
			 	 "create_time = #{createTime, jdbcType = TIMESTAMP}",
		 	 "where id = #{id}"})
	int updateById(SysRole sysRole);
	
	@Delete("delete from sys_role where id = #{id}")
	int deleteById(Long id);
	
	/**
	 * 查询所有角色和对应的权限
	 * @return
	 */
	List<SysRole> selectAllRoleAndPrivileges();
	
	/**
	 * 根据用户ID获取用户的角色信息
	 * @param userId
	 * @return
	 */
	List<SysRole> selectRoleByUserIdChoose(Long userId);
}
