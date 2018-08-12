package tk.mybatis.generator;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * 代码生成器
 * @author E-Kunt
 *
 */
public class Generator {
	public static void main(String[] args) throws Exception {
		//MBG执行产生的警告信息
		List<String> warnings = new ArrayList<String> ();
		//标志生成代码重复时覆盖
		boolean overwrite = true;
		//读取MBG配置文件
		InputStream is = Generator.class.getResourceAsStream("/generator/generatorConfig.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(is);
		is.close();
		
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new  MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
		for(String warning : warnings) {
			System.out.println(warning);
		}
	}
}
