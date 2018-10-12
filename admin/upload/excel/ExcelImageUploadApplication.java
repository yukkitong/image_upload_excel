package kr.or.visitkorea.admin.client.manager.upload.excel;

import java.util.Map;

import kr.or.visitkorea.admin.client.application.ApplicationView;
import kr.or.visitkorea.admin.client.manager.ApplicationBase;
import kr.or.visitkorea.admin.client.manager.upload.excel.composite.ExcelImageUploadMain;
import kr.or.visitkorea.admin.client.manager.upload.excel.dialog.SearchDialog;
import kr.or.visitkorea.admin.client.manager.upload.excel.dialog.UploadDialog;
import kr.or.visitkorea.admin.client.widgets.window.MaterialExtentsWindow;

public class ExcelImageUploadApplication extends ApplicationBase {

	public static final String UPLOAD_EXCEL_DIALOG = "UPLOAD_EXCEL_DILAOG";
	public static final String SEARCH_WITH_CALENDAR_DIALOG = "SEARCH_WITH_CALENDAR_DIALOG";

	public ExcelImageUploadApplication(ApplicationView applicationView) {
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
		this.window.addDialog(UPLOAD_EXCEL_DIALOG, new UploadDialog(this.window));
		this.window.addDialog(SEARCH_WITH_CALENDAR_DIALOG, new SearchDialog(this.window));
	}

	@Override
	public void start() {
		start(null);
	}

	@Override
	public void start(Map<String, Object> params) {
		this.params = params;
		this.window.add(new ExcelImageUploadMain(this.window));
		this.window.open(this.window);
	}
}
