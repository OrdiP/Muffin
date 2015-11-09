package com.muffin.theme.material.client;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.mvu.core.client.form.BaseFormAppearance;
import com.mvu.core.shared.input.BaseInput;
import com.mvu.core.client.widget.Span;

/**
 * Date: 2/25/14 11:10 AM
 */
public class InlineFormAppearance extends BaseFormAppearance {

  @Override
  public void setHeading(String text) {
  }

  public InlineFormAppearance() {
    main = new FlowPanel();
    main.setStyleName("form-inline");
    main.setVisible(false);
    main.getElement().getStyle().setMarginBottom(10, Style.Unit.PX);
    main.getElement().getStyle().setClear(Style.Clear.BOTH);
    inputPanel = new FlowPanel();
    inputPanel.getElement().getStyle().setDisplay(Style.Display.INLINE);
    main.add(inputPanel);
  }

  public void makeInput(BaseInput input){
    setVisible(true);
    input.inline();
  }

  @Override
  public void hide() {
  }

  @Override
  public ComplexPanel ensureFooter() {
    if(footer == null){
      footer = new Span();
      main.add(footer);
    }
    return footer;
  }
}
