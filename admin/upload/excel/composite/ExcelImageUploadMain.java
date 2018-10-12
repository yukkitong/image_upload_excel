package kr.or.visitkorea.admin.client.manager.upload.excel.composite;

import com.google.gwt.dom.client.Style.Position;

import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.TextAlign;
import gwt.material.design.client.ui.MaterialIcon;
import kr.or.visitkorea.admin.client.manager.upload.excel.ExcelImageUploadApplication;
import kr.or.visitkorea.admin.client.manager.widgets.AbstractContentPanel;
import kr.or.visitkorea.admin.client.manager.widgets.ContentMenuPanel;
import kr.or.visitkorea.admin.client.manager.widgets.ContentTable;
import kr.or.visitkorea.admin.client.widgets.window.MaterialExtentsWindow;

public class ExcelImageUploadMain extends AbstractContentPanel {

	public ExcelImageUploadMain(MaterialExtentsWindow materialExtentsWindow) {
		super(materialExtentsWindow);
	}

	@Override
	public void init() {
		this.setLayoutPosition(Position.RELATIVE);
		
		buildUploadHistoryList();
		buildUploadHistoryCount();
		buildUploadHistoryDetail();
	}
	
	private void buildUploadHistoryList() {
		ContentTable table = new ContentTable(ContentTable.TABLE_SELECT_TYPE.SELECT_SINGLE);
		table.setLayoutPosition(Position.ABSOLUTE);
		table.setHeight(575);
		table.setWidth("550px");
		table.setLeft(30);
		table.setTop(55);
		table.appendTitle("실행일자", 125, TextAlign.CENTER);
		table.appendTitle("파일명", 300, TextAlign.CENTER);
		table.appendTitle("전체건수", 125, TextAlign.CENTER);
		
		MaterialIcon uploadIcon = new MaterialIcon(IconType.CLOUD_UPLOAD); // FILE_UPLOAD
		uploadIcon.setTextAlign(TextAlign.CENTER);
		uploadIcon.addClickHandler(event-> {
			getMaterialExtentsWindow().openDialog(ExcelImageUploadApplication.UPLOAD_EXCEL_DIALOG, 720);
		});

		MaterialIcon searchIcon = new MaterialIcon(IconType.SEARCH);
		searchIcon.setTextAlign(TextAlign.CENTER);
		searchIcon.addClickHandler(event-> {
			getMaterialExtentsWindow().openDialog(ExcelImageUploadApplication.SEARCH_WITH_CALENDAR_DIALOG, 720);
		});
		table.getTopMenu().addIcon(uploadIcon, "업로드", com.google.gwt.dom.client.Style.Float.LEFT, "1.8em", "26px", 24, false);
		table.getTopMenu().addIcon(searchIcon, "검색", com.google.gwt.dom.client.Style.Float.RIGHT, "1.8em", "26px", 24, false);
		
		MaterialIcon nextRowsIcon = new MaterialIcon(IconType.ARROW_DOWNWARD);
		nextRowsIcon.setTextAlign(TextAlign.CENTER);
		nextRowsIcon.addClickHandler(event-> {
			// TODO: Handle event : to show details
		});
		table.getButtomMenu()
		     .addIcon(nextRowsIcon, "다음 20개", com.google.gwt.dom.client.Style.Float.RIGHT);
		
		this.add(table);
	}
	
	private void buildUploadHistoryCount() {
		ContentTable countTable = new ContentTable(ContentTable.TABLE_SELECT_TYPE.SELECT_SINGLE);
		countTable.setLayoutPosition(Position.ABSOLUTE);
		countTable.setHeight(190);
		countTable.setWidth("900px");
		countTable.setRight(40);
		countTable.setTop(55);
		countTable.appendTitle("전체건수", 225, TextAlign.CENTER);
		countTable.appendTitle("스킵건수", 225, TextAlign.CENTER);
		countTable.appendTitle("저장건수", 225, TextAlign.CENTER);
		countTable.appendTitle("실패건수", 255, TextAlign.CENTER);
		
		this.add(countTable);
	}
	
	private void buildUploadHistoryDetail() {
		// TODO: Change ContentMenuPanel -> ContentMultilineText
		ContentMenuPanel detailPanel = new ContentMenuPanel();
		detailPanel.setTitle("로그분석");
		detailPanel.setLayoutPosition(Position.ABSOLUTE);
		detailPanel.setWidth("900px");
		detailPanel.setHeight(385);
		detailPanel.setRight(40);
		detailPanel.setTop(245);
		
		this.add(detailPanel);
	}
}
