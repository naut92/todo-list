package com.consul_media.client;


import com.consul_media.shared.Task;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.*;
import java.util.List;

@Path("todos")
interface TaskService extends RestService {

    /**
     * Get all todoItems from the server.
     *
     * @param text optional text to only get the todoItems which contain the given text
     * @param callback callback
     */
    @GET
    @Path("?text={text}")
    void getTaskList(@PathParam("text") final String text, MethodCallback<List<Task>> callback);

    /**
     * Add a todoItem to the server.
     *
     * @param task the todoItem to add
     * @param callback callback
     */
    @PUT
    void addTask(final Task task, final MethodCallback<Void> callback);

    /**
     * Delete a todoItem from the server.
     *
     * @param task the todoItem to delete
     * @param callback callback
     */
    @DELETE
    void deleteTask(final Task task, final MethodCallback<Void> callback);
}
