package com.muffin.theme.material.client;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.HeadElement;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.mvu.core.client.Core;
import com.mvu.core.client.form.BaseFormAppearance;
import com.mvu.core.client.appearance.PanelAP;
import com.mvu.core.client.form.FormType;
import com.mvu.core.client.style.ColumnSize;
import com.mvu.core.client.style.Styles;
import com.mvu.core.shared.Action;
import com.mvu.core.shared.util.StringUtils;

/**
 * Date: 3/26/14 12:33 PM
 */
public class StandardFormAppearance extends BaseFormAppearance {

  private FlowPanel panel;
  protected FlowPanel headingPanel;
  private HeadingElement heading;
  protected String sectionSize;
  protected int sectionPerRow = 1;
  protected int numberOfSection = 0;
  private int headingSize;
  private String subHeading;

  private PanelAP currentRow;

  public void setSubType(int type) {
    inputSize = type == FormType.LARGE_CENTER || type == FormType.SMALL_CENTER ? ColumnSize.MD_12.getCssName() : ColumnSize.MD_6.getCssName();
    sectionSize = type == FormType.FULL ? ColumnSize.MD_6.getCssName() : ColumnSize.MD_12.getCssName();
    sectionPerRow = type == FormType.FULL ? 2 : 1;
    setButtonStyle("btn-lg");
    headingSize = 4;
    if (type == FormType.HALF) {
      root.setStyleName(ColumnSize.MD_6.getCssName());
    } else if (type == FormType.HALF_CENTER) {
      root.setStyleName("col-lg-6 col-lg-offset-3 col-md-8 col-md-offset-2");
      setButtonStyle("btn-lg btn-block");
    } else if (type == FormType.SMALL_CENTER) {
      root.setStyleName("col-md-4 col-md-offset-4");
      setButtonStyle("btn-lg btn-block");
    } else if (type == FormType.MEDIUM_CENTER) {
      root.setStyleName("col-md-8 col-md-offset-2");
    } else if (type == FormType.LARGE_CENTER) {
      root.setStyleName("col-md-10 col-md-offset-1");
      setButtonStyle(null);
    } else if (type == FormType.SMALL_LEFT) {
      root.setStyleName("col-md-5 col-lg-4");
      setButtonStyle("btn-lg btn-block");
      headingSize = 6;
    } else if (type == FormType.SMALL_RIGHT) {
      root.setStyleName("col-lg-5 col-lg-offset-6 col-md-7 col-md-offset-4");
      setButtonStyle("btn-lg btn-block");
      headingSize = 6;
    }
  }

  public StandardFormAppearance() {
    panel = new FlowPanel();
    panel.setStyleName("panel panel-default");
    inputPanel = new FlowPanel();
    inputPanel.setStyleName("panel-body row");
    panel.add(inputPanel);
    main.add(panel);
    setSubType(FormType.FULL);
    ensureHeadingPanel();
  }

  public void push(Action<Boolean> onFinished) {
    push();
    super.push(onFinished);
  }

  public PanelAP ensureFooter() {
    if (footer == null) {
      footer = Core.CF.panelAppearance();
      footer.setStyleName("panel-footer");
      panel.add(footer);
    }
    return footer;
  }

  public void setHeading(String text) {
    heading.setInnerHTML(text);
    if (!StringUtils.isEmpty(subHeading)) {
      appendSubHeading(heading, subHeading);
    }
  }

  public FlowPanel ensureHeadingPanel() {
    if (headingPanel == null) {
      headingPanel = new FlowPanel();
      headingPanel.setStyleName("panel-heading");
      headingPanel.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
      panel.insert(headingPanel, 0);
      heading = Document.get().createHElement(headingSize);
      headingPanel.getElement().appendChild(heading);
    }
    return headingPanel;
  }

  public void setSize(String mySize, String sectionSize, String inputSize) {
    root.setStyleName(mySize);
    this.sectionSize = sectionSize;
    this.inputSize = inputSize;
    if (inputSize == null) {
      inputPanel.removeStyleName(Styles.ROW);
    }
  }

  @Override
  protected void addSection(Widget section) {
    if(numberOfSection == 0){
      currentRow = addRow();
    }
    if (sectionSize != null) {
      section.setStyleName(sectionSize);
      currentRow.add(section);
    }
    numberOfSection++;
    if(numberOfSection == sectionPerRow){
      numberOfSection = 0;
    }
  }

  public void setSubHeading(String text) {
    subHeading = text;
    appendSubHeading(heading, text);
  }

  private void appendSubHeading(HeadingElement heading, String text) {
    final com.google.gwt.dom.client.Element small = Document.get().createElement("small");
    small.setInnerHTML(text);
    heading.appendChild(Document.get().createBRElement());
    heading.appendChild(small);
  }
}
