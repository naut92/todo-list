package com.consul_media.task_list.service;

import com.consul_media.task_list.mappers.TaskMapper;
import com.consul_media.task_list.model.Task;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * MyBatis Connection Factory, which reads the configuration data from a XML file.
 * 
 * @author naut
 *
 */
@Service
public class MyBatisConnectionFactory {
	private static SqlSessionFactory sqlSessionFactory;

	static {
		try {
			String resource = System.getProperty("user.dir") + "/src/main/resources/mybatis-config.xml";

			Reader reader = new FileReader(resource);
			if (sqlSessionFactory == null) {
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

				sqlSessionFactory.getConfiguration().addMapper(TaskMapper.class);
			}
		} catch (IOException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		}
	}

	static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}
