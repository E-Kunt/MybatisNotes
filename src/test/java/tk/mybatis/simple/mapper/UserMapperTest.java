package tk.mybatis.simple.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

public class UserMapperTest extends BaseMapperTest {

	@Test
	public void testSelectById() {
		//获取sqlSession
		SqlSession sqlSession = getSqlSession();
		try {
			//获取UserMapper接口（注：Mybatis采用【动态代理】的方法，参考XML的配置为该接口创建实例）
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//调用selectById方法，查询
			SysUser user = userMapper.selectById(1L);
			//user不为空
			Assert.assertNotNull(user);
			//userName=admin
			Assert.assertEquals("admin", user.getUserName());
		} finally {
			//不要忘记关闭sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectAll() {
		//获取sqlSession
		SqlSession sqlSession = getSqlSession();
		try {
			//获取UserMapper接口
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//调用selectAll方法，查询
			List<SysUser> userList = userMapper.selectAll();
			//user不为空
			Assert.assertNotNull(userList);
			//用户数量大于0
			Assert.assertTrue(userList.size() > 0);
		} finally {
			//不要忘记关闭sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectRolesByUserId() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<SysRole> roleList = userMapper.selectRolesByUserId(1L);
			Assert.assertNotNull(roleList);
			Assert.assertTrue(roleList.size() > 0);
			Assert.assertEquals("管理员", roleList.get(0).getRoleName());
			Assert.assertEquals("admin",roleList.get(0).getUser().getUserName());
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void testInsert() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//创建对象
			SysUser user = new SysUser();
			user.setUserName("test1");
			user.setUserPassword("123456");
			user.setUserEmail("test@mybatis.tk");
			user.setUserInfo("test info");
			//正常情况下应该读入一张图片存到byte数组中
			user.setHeadImg(new byte[] {1,2,3});
			user.setCreateTime(new Date());
			//插入数据，返回值是SQL影响行数
			int result = userMapper.insert(user);
			Assert.assertEquals(1, result);
			//id没配置写值，为空
			Assert.assertNull(user.getId());
		} finally {
			//openSession()不会自动提交事务，可回滚，使不影响其他测试
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	
	@Test
	public void testInsert2() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//创建对象
			SysUser user = new SysUser();
			user.setUserName("test1");
			user.setUserPassword("123456");
			user.setUserEmail("test@mybatis.tk");
			user.setUserInfo("test info");
			//正常情况下应该读入一张图片存到byte数组中
			user.setHeadImg(new byte[] {1,2,3});
			user.setCreateTime(new Date());
			//插入数据，返回值是SQL影响行数
			int result = userMapper.insert2(user);
			Assert.assertEquals(1, result);
			//使用useGeneratedKeys方式,id不为空
			Assert.assertNotNull(user.getId());
		} finally {
			//openSession()不会自动提交事务，可回滚，使不影响其他测试
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	
	@Test
	public void testInsert3() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//创建对象
			SysUser user = new SysUser();
			user.setUserName("test1");
			user.setUserPassword("123456");
			user.setUserEmail("test@mybatis.tk");
			user.setUserInfo("test info");
			//正常情况下应该读入一张图片存到byte数组中
			user.setHeadImg(new byte[] {1,2,3});
			user.setCreateTime(new Date());
			//插入数据，返回值是SQL影响行数
			int result = userMapper.insert3(user);
			Assert.assertEquals(1, result);
			//使用useGeneratedKeys方式,id不为空
			Assert.assertNotNull(user.getId());
		} finally {
			//openSession()不会自动提交事务，可回滚，使不影响其他测试
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	
	@Test
	public void testUpdateById() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//先从数据库获取对象，判断名称为admin
			SysUser user = userMapper.selectById(1L);
			Assert.assertEquals("admin", user.getUserName());
			//更新字段的值
			user.setUserName("admin_test");
			user.setUserEmail("test@mybatis.tk");
			//更新，返回SQL影响行数
			int result = userMapper.updateById(user);
			Assert.assertEquals(1, result);
			//从数据库获取修改后的对象，判断名称修改成admin_test
			user = userMapper.selectById(1L);
			Assert.assertEquals("admin_test", user.getUserName());
		} finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	
	@Test
	public void testDeleteById() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//传入主键参数删除的方式
			SysUser user1 = userMapper.selectById(1L);
			Assert.assertNotNull(user1);
			Assert.assertEquals(1, userMapper.deleteById(1L));
			Assert.assertNull(userMapper.selectById(1L));
			
			//传入对象参数删除的方式(重载函数)
			SysUser user2 = userMapper.selectById(1001L);
			Assert.assertNotNull(user2);
			Assert.assertEquals(1, userMapper.deleteById(user2));
			Assert.assertNull(userMapper.selectById(1001L));
		} finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	
	/**
	 * 通过用户id和角色enabled状态查用户的角色信息。
	 */
	@Test
	public void testSelectRolesByUserIdAndRoleEnabled() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<SysRole> roleList = userMapper.selectRolesByUserIdAndRoleEnabled(1L, 1);
			Assert.assertNotNull(roleList);
			Assert.assertTrue(roleList.size() > 0);
		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * 通过用户id和角色enabled状态查用户的角色信息。多个参数是javabean的时候。
	 */
	@Test
	public void testSelectRolesByUserAndRole() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<SysRole> roleList = userMapper.selectRolesByUserAndRole(
					userMapper.selectById(1L),
					userMapper.selectRolesByUserId(1L).get(1));
			Assert.assertNotNull(roleList);
			Assert.assertTrue(roleList.size() > 0);
		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * 根据动态条件查询用户信息
	 */
	@Test
	public void testSelectByUser() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//仅搜索用户名
			SysUser query = new SysUser();
			query.setUserName("ad");
			List<SysUser> userList = userMapper.selectByUser(query);
			Assert.assertTrue(userList.size() > 0);
			//仅搜索邮箱
			query = new SysUser();
			query.setUserEmail("test@mybatis.tk");
			userList = userMapper.selectByUser(query);
			Assert.assertTrue(userList.size() > 0);
			//同时按用户名和邮箱查询
			query = new SysUser();
			query.setUserName("ad");
			query.setUserEmail("test@mybatis.tk");
			userList = userMapper.selectByUser(query);
			Assert.assertTrue(userList.size() == 0);//没有满足的，所以是结果数0
		} finally {
			sqlSession.close();
		}
	}
}
