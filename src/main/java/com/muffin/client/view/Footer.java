package com.muffin.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialFooter;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;

/**
 * Created by Van on 11/26/15.
 */
public class Footer implements IsWidget{

  private MaterialFooter footer;

  @UiField
  MaterialButton contact_us;

  @UiField
  MaterialButton tell_me;

  @Override
  public Widget asWidget() {
    return footer;
  }

  interface FooterUiBinder extends UiBinder<MaterialFooter, Footer> {
  }

  private static FooterUiBinder ourUiBinder = GWT.create(FooterUiBinder.class);

  public Footer() {
    footer = ourUiBinder.createAndBindUi(this);
    tell_me.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        MaterialToast.alert("YOU'RE GAY!");
      }
    });
  }
}