package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.simple.model.SysPrivilege;
import tk.mybatis.simple.provider.PrivilegeProvider;

public interface PrivilegeMapper {

	//Provider类的注解，需要新建Provider类并在里面编写sql语句，在此注解中调用.
	//@SelectProvider、@InsertProvider、@UpdateProvider、@DeleteProvider 用法相似
	@SelectProvider(type = PrivilegeProvider.class, method = "selectById")
	SysPrivilege selectById(Long id);
	
}
