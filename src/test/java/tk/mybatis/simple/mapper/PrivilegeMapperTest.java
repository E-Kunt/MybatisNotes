package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import tk.mybatis.simple.model.SysPrivilege;

public class PrivilegeMapperTest extends BaseMapperTest {

	@Test
	public void testSelectById() {
		SqlSession sqlSession = getSqlSession();
		try {
			PrivilegeMapper privilegeMapper = sqlSession.getMapper(PrivilegeMapper.class);
			SysPrivilege sysPrivilege = privilegeMapper.selectById(1L);
			Assert.assertNotNull(sysPrivilege);
			Assert.assertEquals("用户管理",sysPrivilege.getPrivilegeName());
		} finally {
			sqlSession.close();
		}
	}
}
