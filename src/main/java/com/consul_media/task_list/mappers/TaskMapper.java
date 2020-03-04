package com.consul_media.task_list.mappers;

import com.consul_media.task_list.model.Task;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface TaskMapper {

    String GET_ALL_TASK = "SELECT * FROM task";
    String SELECT_BY_ID = "SELECT * FROM task WHERE id = #{id}";
    String INSERT_TASK = "INSERT into task(heading, text, date_added, edit_date, status) VALUES(#{heading}, #{text}, #{date_added}, #{edit_date}, #{status})";
    String UPDATE_TASK = "UPDATE task SET heading=#{heading}, text=#{text}, date_added=#{date_added}, edit_date=#{edit_date}, status=#{status} WHERE id = #{id}";
    String DELETE_TASK = "DELETE FROM task WHERE id = #{id}";

    /**
     * Returns the list of all Task instances from the database.
     * @return the list of all Task instances from the database.
     */
    @Select(GET_ALL_TASK)
    List <Task> getTaskList();

    /**
     * Returns a Task instance from the database.
     * @param id primary key value used for lookup.
     * @return A Task instance with a primary key value equals to pk. null if there is no matching row.
     */

    @Select(SELECT_BY_ID)
    Task getTaskById(Long id);

    /**
     * Insert an instance of Task into the database.
     * @param task the instance to be persisted.
     * @return true if transaction successful.
     */
    @Insert(INSERT_TASK)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn="id")
    Task insertTask(Task task);

    /**
     * Updates an instance of Task in the database.
     * @param task the instance to be updated.
     * @return true if transaction successful.
     */
    @Update(UPDATE_TASK)
    Task updateTask(Long taskId, Task task);

    /**
     * Delete an instance of Task from the database.
     * @param id primary key value of the instance to be deleted.
     * @return true if transaction successful.
     */
    @Delete(DELETE_TASK)
    void deleteTask(Long id);
}