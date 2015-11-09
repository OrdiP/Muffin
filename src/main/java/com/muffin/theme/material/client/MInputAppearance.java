package com.muffin.theme.material.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.mvu.core.client.Core;
import com.mvu.core.shared.input.InputAppearance;

import static com.mvu.core.client.Core.coreCss;

/**
 * Created by minh on 9/27/15.
 */
public abstract class MInputAppearance extends Composite implements InputAppearance {
  protected HTMLPanel root;
  @UiField
  public Element label;
  private FlowPanel helpBlock;
  private FlowPanel errorSpan;

  @UiTemplate("MInputAppearance.ui.xml")
  interface MRadioInputAppearanceUiBinder extends UiBinder<HTMLPanel, MRadioInputAppearance> {
  }

  protected static MRadioInputAppearanceUiBinder baseBinder = GWT.create(MRadioInputAppearanceUiBinder.class);

  @Override
  public void add(IsWidget widget) {
    root.add(widget);
  }

  @Override
  public void insert(IsWidget child, int beforeIndex) {
    add(child);
  }

  public FlowPanel createHelpBlock() {
    FlowPanel helpBlock = new FlowPanel();
    helpBlock.setStyleName("help-block");
    add(helpBlock);
    return helpBlock;
  }

  @Override
  public void markValid(boolean valid) {
    if(valid){
      root.removeStyleName("has-error");
    }else{
      root.addStyleName("has-error");
    }
  }

  @Override
  public void markChanged(boolean changed) {
    if (changed) {
      addStyleName("has-success");
    } else {
      removeStyleName("has-success");
    }
  }

  @Override
  public void markRequired(boolean required) {
    if (label != null) {
      if (required) {
        label.addClassName(coreCss.required());
      } else {
        label.removeClassName(coreCss.required());
      }
    }
  }

  @Override
  public void setHelpText(String message) {
    helpBlock = setTextForSpan(message, helpBlock);
  }

  private FlowPanel setTextForSpan(String message, FlowPanel span) {
    if (message != null) {
      if (span == null) {
        span = createHelpBlock();
      }
      span.getElement().setInnerHTML(message);
    } else if (span != null) {
      span.removeFromParent();
      return null;
    }
    return span;
  }

  @Override
  public void setError(String error) {
    errorSpan = setTextForSpan(error, errorSpan);
  }

  @Override
  public String label() {
    return label.getInnerText();
  }

  @Override
  public void label(String text) {
    label.setInnerHTML(text);
  }

  @Override
  public void labelStyle(String style) {
    label.addClassName(style);
  }

  @Override
  public void tooltip(String text) {
    Core.CF.makeTooltip(label, text);
  }

  @Override
  public void inline() {
    labelStyle("sr-only");
    setTitle(label());
  }
}
