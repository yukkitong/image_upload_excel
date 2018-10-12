package kr.or.visitkorea.admin.client.manager.tourapi.dialog;

import gwt.material.design.client.MaterialDesignBase;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.ui.MaterialButton;
import kr.or.visitkorea.admin.client.manager.dialogs.DialogsBundle;
import kr.or.visitkorea.admin.client.widgets.dialog.DialogContent;
import kr.or.visitkorea.admin.client.widgets.window.MaterialExtentsWindow;

public class SearchDialog extends DialogContent {

    static {
        MaterialDesignBase.injectCss(DialogsBundle.INSTANCE.contentCss());
    }

    public SearchDialog(MaterialExtentsWindow window) {
        super(window);
    }

    @Override
    public void init() {
        addDefaultButtons();

        MaterialButton button = new MaterialButton("검색");
        button.setBackgroundColor(Color.RED_LIGHTEN_2);
        button.addClickHandler(event -> {
            // TODO: handle click event to search
        });
        this.addButton(button);
    }

    @Override
    public int getHeight() {
        return 480;
    }
}
