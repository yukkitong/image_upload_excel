package kr.or.visitkorea.admin.client.manager.tourapi;

import kr.or.visitkorea.admin.client.application.ApplicationView;
import kr.or.visitkorea.admin.client.manager.ApplicationBase;
import kr.or.visitkorea.admin.client.manager.tourapi.dialog.SearchDialog;
import kr.or.visitkorea.admin.client.widgets.window.MaterialExtentsWindow;

import java.util.Map;

public class BatchExecuteApplication extends ApplicationBase {

    public static final String SEARCH_WITH_CALENDAR_DIALOG = "SEARCH_WITH_CALENDAR_DIALOG";

    public BatchExecuteApplication(ApplicationView applicationView) {
        super(applicationView);
    }

    @Override
    public void setWindow(MaterialExtentsWindow materialExtentsWindow, String divisionName) {
        this.setDivisionName(divisionName);
        this.windowLiveFlag = true;
        this.window = materialExtentsWindow;
        this.window.addCloseHandler(event -> {
            windowLiveFlag = false;
        });
        this.window.addDialog(SEARCH_WITH_CALENDAR_DIALOG, new SearchDialog(this.window));
    }

    @Override
    public void start() {

    }

    @Override
    public void start(Map<String, Object> params) {

    }
}
