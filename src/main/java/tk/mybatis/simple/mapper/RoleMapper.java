package tk.mybatis.simple.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

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
	
}
