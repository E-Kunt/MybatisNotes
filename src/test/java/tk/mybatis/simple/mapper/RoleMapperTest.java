package tk.mybatis.simple.mapper;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import tk.mybatis.simple.model.SysRole;


public class RoleMapperTest extends BaseMapperTest {

	@Test
	public void testSelectById() {
		SqlSession sqlSession = getSqlSession();
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			SysRole sysRole = roleMapper.selectById(1L);
			Assert.assertNotNull(sysRole);
			Assert.assertEquals("管理员",sysRole.getRoleName());
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectById2() {
		SqlSession sqlSession = getSqlSession();
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			SysRole sysRole = roleMapper.selectById2(1L);
			Assert.assertNotNull(sysRole);
			Assert.assertEquals("管理员",sysRole.getRoleName());
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void testInsert() {
		SqlSession sqlSession = getSqlSession();
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			SysRole sysRole = new SysRole();
			sysRole.setId(99L);
			sysRole.setRoleName("test1");
			sysRole.setEnabled(1);
			sysRole.setCreateTime(new Date());
			Assert.assertEquals(1,roleMapper.insert(sysRole));
		} finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	
	@Test
	public void testInsert2() {
		SqlSession sqlSession = getSqlSession();
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			SysRole sysRole = new SysRole();
			sysRole.setRoleName("test2");
			sysRole.setEnabled(1);
			sysRole.setCreateTime(new Date());
			Assert.assertEquals(1,roleMapper.insert2(sysRole));
		} finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	
	@Test
	public void testInsert3() {
		SqlSession sqlSession = getSqlSession();
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			SysRole sysRole = new SysRole();
			sysRole.setRoleName("test3");
			sysRole.setEnabled(1);
			sysRole.setCreateTime(new Date());
			Assert.assertEquals(1,roleMapper.insert3(sysRole));
		} finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}
}
