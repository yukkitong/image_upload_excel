package kr.or.visitkorea.admin.client.manager.upload.excel.dialog;

import com.google.gwt.dom.client.Style;
import gwt.material.design.client.MaterialDesignBase;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialLabel;
import kr.or.visitkorea.admin.client.manager.dialogs.DialogsBundle;
import kr.or.visitkorea.admin.client.widgets.dialog.DialogContent;
import kr.or.visitkorea.admin.client.widgets.window.MaterialExtentsWindow;

public class UploadDialog extends DialogContent {

    static {
        MaterialDesignBase.injectCss(DialogsBundle.INSTANCE.contentCss());
    }

    public UploadDialog(MaterialExtentsWindow window) {
        super(window);
    }

    @Override
    public void init() {
        // dialog title define
        MaterialLabel dialogTitle = new MaterialLabel("액셀파일 업로드");
        dialogTitle.setFontSize("1.4em");
        dialogTitle.setFontWeight(Style.FontWeight.BOLD);
        dialogTitle.setTextColor(Color.BLUE);
        dialogTitle.setPaddingTop(10);
        dialogTitle.setPaddingLeft(30);

        this.add(dialogTitle);
        addDefaultButtons();

        MaterialButton button = new MaterialButton("실행");
        button.setBackgroundColor(Color.RED_LIGHTEN_2);
        button.addClickHandler(event -> {
            // TODO: handle upload and remote-execution
        });
        this.addButton(button);
    }

    @Override
    public int getHeight() {
        return 480;
    }
}
