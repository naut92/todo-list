package com.consul_media.client;

import com.consul_media.shared.Task;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

import java.util.ArrayList;
import java.util.List;

class TaskLabel extends Composite {

    interface TaskLabelUiBinder extends UiBinder<Label, TaskLabel> {}
    private static TaskLabelUiBinder ourUiBinder = GWT.create(TaskLabelUiBinder.class);

    public interface TaskLabelClickHandler {
        void onClick(final Task task);
    }

    private final List<TaskLabelClickHandler> clickHandlers;

    @UiField
    Label label;

    public TaskLabel(final Task task) {
        initWidget(ourUiBinder.createAndBindUi(this));
        label.setText(task.getText());
        clickHandlers = new ArrayList<>();

        addDomHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                for (final TaskLabelClickHandler clickHandler : clickHandlers) {
                    clickHandler.onClick(task);
                }
            }
        }, ClickEvent.getType());
    }

    public void addClickHandler(final TaskLabelClickHandler clickHandler) {
        clickHandlers.add(clickHandler);
    }
}