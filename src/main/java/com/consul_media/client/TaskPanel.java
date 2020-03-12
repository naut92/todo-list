package com.consul_media.client;

import com.consul_media.shared.Task;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;


class TaskPanel extends Composite {

    interface TestViewUiBinder extends UiBinder<HTMLPanel, TaskPanel> {}
    private static TestViewUiBinder ourUiBinder = GWT.create(TestViewUiBinder.class);

    private static final TaskService taskService = GWT.create(TaskService.class);

    @UiField
    FlowPanel taskList;

    @UiField
    TextBox taskTextBox;

    @UiField
    Button addTaskButton;

    public TaskPanel() {
        initWidget(ourUiBinder.createAndBindUi(this));
        refreshTask();

        addTaskButton.addClickHandler(event -> {
            final String taskText = taskTextBox.getText();
            if (!taskText.isEmpty()) {
                addTask(taskText);
            }
        });

        taskTextBox.getElement().setAttribute("placeholder", "Add a task");
        taskTextBox.addKeyUpHandler(event -> {
            if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                final String todoItemText = taskTextBox.getText();
                if (!todoItemText.isEmpty()) {
                    addTask(todoItemText);
                }
            }
        });
    }

    /**
     * Clear the todoItemsPanel and add all todoItems from the server.
     */
    private void refreshTask() {
        taskService.getTaskList("", new MethodCallback<List<Task>>() {
            @Override
            public void onFailure(final Method method, final Throwable exception) {

            }

            @Override
            public void onSuccess(final Method method, final List<Task> response) {
                taskList.clear();
                for (final Task task : response) {
                    final TaskLabel taskLabel = new TaskLabel(task);
                    taskLabel.addClickHandler(todoItemToRemove -> removeTodoItem(todoItemToRemove));
                    taskList.add(taskLabel);
                }
            }
        });
    }

    /**
     * Send a new todoItem to the server. On success refresh the todoItemsPanel.
     *
     * @param text the text of the todoItem
     */
    private void addTask(final String text) {
        taskService.addTask(new Task(/*text*/), new MethodCallback<Void>() {
            @Override
            public void onFailure(final Method method, final Throwable exception) {

            }

            @Override
            public void onSuccess(final Method method, final Void response) {
                taskTextBox.setText("");
                refreshTask();
            }
        });
    }

    /**
     * Remove a todoItem from the server. On success refresh the todoItemsPanel.
     *
     * @param task the todoItem to delete
     */
    public void removeTodoItem(final Task task) {
        taskService.deleteTask(task, new MethodCallback<Void>() {
            @Override
            public void onFailure(final Method method, final Throwable exception) {

            }

            @Override
            public void onSuccess(final Method method, final Void response) {
                refreshTask();
            }
        });
    }
}