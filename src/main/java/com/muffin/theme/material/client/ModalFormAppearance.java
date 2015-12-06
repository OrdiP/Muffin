package com.muffin.theme.material.client;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.mvu.core.client.Core;
import com.mvu.core.client.appearance.PanelAP;
import com.mvu.core.client.form.BaseFormAppearance;
import com.mvu.core.client.form.BasePanelAP;
import com.mvu.core.client.form.ButtonAP;
import com.mvu.core.client.popup.BasePopupAP;
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

  public BasePopupAP popup;

  public ModalFormAppearance(Size size) {
    popup = new BasePopupAP("").size(Size.lg);
    main = popup.root;
    inputPanel = popup.body;
    inputPanel.addStyleName(Styles.ROW);
    footer = new BasePanelAP(popup.footer);
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
    return footer;
  }

  public void push(final Action<Boolean> onFinished) {
    popup.onShown(onFinished);
    popup.push();
  }

  @Override
  public void setHeading(SafeHtml heading) {
    popup.title.setInnerSafeHtml(heading);
  }

  public void hide() {
    popup.hide();
  }

  @Override
  public void setHeading(String text) {
    popup.title.setInnerText(text);
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
