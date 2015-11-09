package com.muffin.theme.material.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.mvu.core.client.form.BaseFormAppearance;
import com.mvu.core.client.style.ButtonSize;
import com.mvu.core.client.style.ColumnSize;
import com.mvu.core.shared.Action;

import static com.mvu.core.client.Core.VIEW_STACK;


public class PageFormAppearance extends BaseFormAppearance {

  private final HeadingElement heading;

  @Override
  public void setHeading(String text) {
    heading.setInnerHTML(text);
  }

  public PageFormAppearance() {
    FlowPanel panel = new FlowPanel();
    panel.getElement().setAttribute("role", "form");
    heading = Document.get().createHElement(3);
    panel.getElement().appendChild(heading);
    main = panel;
    inputPanel = new FlowPanel();
    panel.add(inputPanel);
    inputSize = ColumnSize.MD_12.getCssName();
    buttonStyle = ButtonSize.LARGE.getCssName();
  }

  @Override
  public void push(Action<Boolean> onFinished) {
    super.push(onFinished);
    VIEW_STACK.push(this, heading == null ? "Details" : heading.getInnerHTML());
  }

  @Override
  public ComplexPanel ensureFooter() {
    if(footer == null) {
      footer = new FlowPanel();
      Style style = footer.getElement().getStyle();
      style.setClear(Style.Clear.BOTH);
      style.setPadding(15, Style.Unit.PX);
      main.add(footer);
    }
    return footer;
  }
}