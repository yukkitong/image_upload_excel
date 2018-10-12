package kr.or.visitkorea.admin.client.manager.upload.excel;

import java.util.Map;

import kr.or.visitkorea.admin.client.application.ApplicationView;
import kr.or.visitkorea.admin.client.manager.ApplicationBase;
import kr.or.visitkorea.admin.client.manager.upload.excel.composite.ExcelImageUploadMain;
import kr.or.visitkorea.admin.client.widgets.window.MaterialExtentsWindow;

public class ExcelImageUploadApplication extends ApplicationBase {

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
