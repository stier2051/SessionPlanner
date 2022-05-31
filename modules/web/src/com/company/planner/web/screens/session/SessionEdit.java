package com.company.planner.web.screens.session;

import com.company.planner.service.SessionService;
import com.haulmont.cuba.gui.Dialogs;
import com.haulmont.cuba.gui.screen.*;
import com.company.planner.entity.Session;

import javax.inject.Inject;

@UiController("planner_Session.edit")
@UiDescriptor("session-edit.xml")
@EditedEntityContainer("sessionDc")
@LoadDataBeforeShow
public class SessionEdit extends StandardEditor<Session> {

    @Inject
    private SessionService sessionService;
    @Inject
    private Dialogs dialogs;


    @Subscribe
    public void onInitEntity(InitEntityEvent<Session> event) {
        event.getEntity().setDuration(1);
    }

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        if (sessionService.checkSessionsCount(getEditedEntity(), getEditedEntity().getStartDate())) {
            dialogs.createMessageDialog().withCaption("Information").withMessage("У докладчика на указанную дату уже есть два доклада!").show();
            event.preventCommit();
        }
    }
}