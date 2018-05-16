package tk.mybatis.simple.provider;

import org.apache.ibatis.jdbc.SQL;

/**
 * 使用@XXXProvider四个注解时需要此类，并在此类编写sql语句
 * @author E-Kunt
 *
 */
public class PrivilegeProvider {

	public String selectById(final Long id) {
		//方式1：
		return new SQL() {
			{
				SELECT("id, privilege_name, privilege_url");
				FROM("sys_privilege");
				WHERE("id = #{id}");
			}
		}.toString();
		
		//方式2：
		/*return "select id, privilege_name, privilege_url "
				+ "from sys_privilege "
				+ "where id = #{id}";*/
	}
}
