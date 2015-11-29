package com.muffin.theme.material.client;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.mvu.core.client.Core;
import com.mvu.core.client.appearance.PanelAP;
import com.mvu.core.client.form.BaseFormAppearance;
import com.mvu.core.client.form.ButtonAP;
import com.mvu.core.client.popup.BasePopup;
import com.mvu.core.client.style.ColumnSize;
import com.mvu.core.client.style.Size;
import com.mvu.core.client.style.Styles;
import com.mvu.core.shared.Action;
import com.mvu.core.shared.input.BaseInput;

/**
 * Class description...
 *
 * @author mvu
 */
public class ModalFormAppearance extends BaseFormAppearance {

  public BasePopup popup;

  public ModalFormAppearance(Size size) {
    popup = new BasePopup(size.of("modal"), "");
    main = popup.root;
    inputPanel = popup.body;
    inputPanel.addStyleName(Styles.ROW);
  }

  @Override
  public void makeInput(BaseInput input) {
    super.makeInput(input);
    if (input.size() == null) {
      input.style(ColumnSize.MD_6.getCssName());
    } else {
      input.style(input.size());
    }
    input.setFocus(true);
  }

  @Override
  public PanelAP ensureFooter() {
    footer = Core.CF.panelAppearance();
    return footer;
  }

  public void push(final Action<Boolean> onFinished) {
    popup.onShown(onFinished);
    popup.push();
  }

  @Override
  public void setHeading(SafeHtml heading) {
    popup.setHtmlTitle(heading.asString());
  }

  public void hide() {
    popup.hide();
  }

  @Override
  public void setHeading(String text) {
    popup.setTitle(text);
  }

  @Override
  public ButtonAP createCancelButton() {
    return popup.closeButton();
  }

  @Override
  public boolean isVisible() {
    return Core.CF.isReallyVisible(main);
  }
}
