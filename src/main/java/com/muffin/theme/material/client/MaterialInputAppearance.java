package com.muffin.theme.material.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.FlowPanel;
import com.mvu.core.client.input.BaseInputAppearance;
import com.mvu.core.shared.input.InputConfig;

import static com.mvu.core.client.Core.coreCss;

/**
 * Created by m.vu on 6/22/15.
 */
public class MaterialInputAppearance extends BaseInputAppearance {
  private FlowPanel helpBlock;
  private FlowPanel errorSpan;

  public MaterialInputAppearance(InputConfig config) {
    super();
    addStyleName("form-group");
    labelElement = Document.get().createLabelElement();
    labelElement.setInnerText(config.label());
    labelElement.setAttribute("for", config.name());
    getElement().appendChild(labelElement);
  }

  @Override
  public void markRequired(boolean required) {
    if (labelElement != null) {
      if (required) {
        labelElement.addClassName(coreCss.required());
      } else {
        labelElement.removeClassName(coreCss.required());
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
}
