package com.muffin.theme.material.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;

/**
 * Created by minh on 10/15/15.
 */
public class Brand {
  private final DivElement root;

  public Node getElement() {
    return root;
  }

  interface BrandUiBinder extends UiBinder<DivElement, Brand> {
  }

  private static BrandUiBinder ourUiBinder = GWT.create(BrandUiBinder.class);
  @UiField
  DivElement text;

  public Brand(String logoSVG, String... texts) {
    root = ourUiBinder.createAndBindUi(this);
    root.getStyle().setBackgroundImage("url(data:image/png;base64," + logoSVG + ")");
    for(String t : texts){
      final DivElement div = Document.get().createDivElement();
      div.setInnerText(t);
      text.appendChild(div);
      text.getStyle().setFontSize(30, Style.Unit.PX);
    }
  }
}