package tk.mybatis.simple.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import tk.mybatis.simple.model.Country;
import tk.mybatis.simple.model.CountryExample;

public class CountryMapperTest extends BaseMapperTest {

	/**
	 * 利用Eclipse生成的代码，利用EXAMPLE进行数据库操作
	 */
	@Test
	public void testExample() {
		SqlSession sqlSession = getSqlSession();
		try {
			CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
			CountryExample example = new CountryExample();
			example.setOrderByClause("id desc, countryname asc");
			example.setDistinct(true);
			CountryExample.Criteria criteria = example.createCriteria();
			criteria.andIdGreaterThanOrEqualTo(1);
			criteria.andIdLessThan(4);
			criteria.andCountrycodeLike("%U%");//注意写上通配符
			CountryExample.Criteria or = example.or();
			or.andCountrynameEqualTo("中国");
			List<Country> countryList = countryMapper.selectByExample(example);
			
			for(Country c : countryList) {
				System.out.println(c);
			}
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void testUpdateByExampleSelective() {
		SqlSession sqlSession = getSqlSession();
		try {
			CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
			//创建条件
			CountryExample example = new CountryExample();
			CountryExample.Criteria criteria = example.createCriteria();
			criteria.andIdGreaterThan(2);
			//设置对象要更新的值
			Country country = new Country();
			country.setCountryname("China");
			//执行更新
			countryMapper.updateByExampleSelective(country, example);
			//查看结果
			List<Country> countryList = countryMapper.selectByExample(example);
			for(Country c : countryList) {
				System.out.println(c);
			}
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void testDeleteByExample() {
		SqlSession sqlSession = getSqlSession();
		try {
			CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
			//创建条件
			CountryExample example = new CountryExample();
			CountryExample.Criteria criteria = example.createCriteria();
			criteria.andIdGreaterThan(2);
			//执行删除
			countryMapper.deleteByExample(example);
			//查看结果
			Assert.assertEquals(0, countryMapper.countByExample(example));
		} finally {
			sqlSession.close();
		}
	}
}
