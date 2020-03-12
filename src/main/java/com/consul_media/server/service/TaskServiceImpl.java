package com.consul_media.server.service;

import com.consul_media.server.mappers.TaskMapper;
import com.consul_media.server.model.Task;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskMapper {

    private final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    private SqlSessionFactory sqlSessionFactory;
    private SqlSession session;
    private TaskMapper mapper;

    public TaskServiceImpl(/*SqlSessionFactory sqlSessionFactory*/) {
        this.sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
    }

    @Override
    public List<Task> getTaskList() {
        session = sqlSessionFactory.openSession();
        try {
            mapper = session.getMapper(TaskMapper.class);
            return mapper.getTaskList();
        } finally {
            session.close();
        }
    }

    @Override
    public Task getTaskById(Long id) {
        session = sqlSessionFactory.openSession();
        try {
            mapper = session.getMapper(TaskMapper.class);
            return mapper.getTaskById(id);
        } finally {
            session.close();
        }
    }

    @Override
    public void insertTask(Task task) {
        session = sqlSessionFactory.openSession();
        try {
            mapper = session.getMapper(TaskMapper.class);
            mapper.insertTask(task);
            session.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void updateTask(Task task/*, Long taskId*/) {
        session = sqlSessionFactory.openSession();
        try {
            mapper = session.getMapper(TaskMapper.class);
            mapper.updateTask(task/*, taskId*/);
            session.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteTask(Long id) {
        session = sqlSessionFactory.openSession();
        try {
            mapper = session.getMapper(TaskMapper.class);
            mapper.deleteTask(id);
            session.commit();
        } finally {
            session.close();
        }
    }
}