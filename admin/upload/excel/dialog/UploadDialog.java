package kr.or.visitkorea.admin.client.manager.upload.excel.dialog;

import gwt.material.design.client.MaterialDesignBase;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.ui.MaterialButton;
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
