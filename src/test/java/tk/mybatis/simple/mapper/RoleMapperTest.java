package tk.mybatis.simple.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import tk.mybatis.simple.model.SysPrivilege;
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
	
	@Test
	public void testUpdate() {
		SqlSession sqlSession = getSqlSession();
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			SysRole sysRole = roleMapper.selectById(1L);
			sysRole.setRoleName("updateName");
			Assert.assertEquals(1,roleMapper.updateById(sysRole));
			Assert.assertEquals("updateName", roleMapper.selectById(1L).getRoleName());
		} finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	
	@Test
	public void testDelete() {
		SqlSession sqlSession = getSqlSession();
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			Assert.assertNotNull(roleMapper.selectById(1L));
			Assert.assertEquals(1,roleMapper.deleteById(1L));
			Assert.assertNull(roleMapper.selectById(1L));
		} finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectAllRoleAndPrivileges() {
		// 获取 sqlSession
		SqlSession sqlSession = getSqlSession();
		try {
			// 获取 UserMapper 接口
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			List<SysRole> roleList = roleMapper.selectAllRoleAndPrivileges();
			System.out.println("角色数：" + roleList.size());
			for (SysRole role : roleList) {
				System.out.println("角色名：" + role.getRoleName());
				for (SysPrivilege privilege : role.getPrivilegeList()) {
					System.out.println("权限名：" + privilege.getPrivilegeName());
				}
			}
		} finally {
			// 不要忘记关闭 sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectRoleByUserIdChoose(){
		//获取 sqlSession
		SqlSession sqlSession = getSqlSession();
		try {
			//获取 RoleMapper 接口
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			//由于数据库数据 enable 都是 1，所以我们给其中一个角色的 enable 赋值为 0
			SysRole role = roleMapper.selectById(2L);
			role.setEnabled(0);
			roleMapper.updateById(role);
			//获取用户 1 的角色
			List<SysRole> roleList = roleMapper.selectRoleByUserIdChoose(1L);
			for(SysRole r: roleList){
				System.out.println("角色名：" + r.getRoleName());
				if(r.getId().equals(1L)){
					//第一个角色存在权限信息
					Assert.assertNotNull(r.getPrivilegeList());
				} else if(r.getId().equals(2L)){
					//第二个角色的权限为 null
					Assert.assertNull(r.getPrivilegeList());
					continue;
				}
				for(SysPrivilege privilege : r.getPrivilegeList()){
					System.out.println("权限名：" + privilege.getPrivilegeName());
				}
			}
		} finally {
			sqlSession.rollback();
			//不要忘记关闭 sqlSession
			sqlSession.close();
		}
	}
}
