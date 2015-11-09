package com.muffin.theme.material.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.mvu.core.client.InputGroupAppearance;
import com.mvu.core.client.JsUtil;
import com.mvu.core.shared.Attempt;

/**
 * Created by minh on 9/28/15.
 */
public class MInputGroupAppearance extends MTextInputAppearance implements InputGroupAppearance {

  protected SpanElement prefix;

  @Override
  public void addPickHandler(Attempt<String> pickHandler) {
    JsUtil.addClickHandler(prefix, pickHandler);
  }

  interface MInputGroupAppearanceUiBinder extends UiBinder<HTMLPanel, MTextInputAppearance> {
  }

  private static MInputGroupAppearanceUiBinder uiBinder = GWT.create(MInputGroupAppearance.class);

  public MInputGroupAppearance(String type, String pre, String post) {
    this(type, uiBinder, pre, post);
  }

  protected MInputGroupAppearance(String type, UiBinder<HTMLPanel, MTextInputAppearance> uiBinder,
                                  String pre, String post) {
    super(type, uiBinder);
    addOn(pre, post);
  }

  protected MInputGroupAppearance() {
    super();
  }

  protected void addOn(String pre, String post) {
    if(pre != null){
      prefix = createAddOn(pre);
      textBox.getElement().getParentElement().insertFirst(prefix);
    }
    if(post != null){
      textBox.getElement().getParentElement().appendChild(createAddOn(post));
    }
  }

  protected SpanElement createAddOn(String pre) {
    // <span class="input-group-addon" ui:field="prefix"/>
    SpanElement prefix = Document.get().createSpanElement();
    prefix.setClassName("input-group-addon");
    prefix.setInnerHTML(pre);
    return prefix;
  }
}