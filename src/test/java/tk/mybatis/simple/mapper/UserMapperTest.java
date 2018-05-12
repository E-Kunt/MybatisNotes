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
			//获取UserMapper接口
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
}
