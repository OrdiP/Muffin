package com.muffin.theme.material.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.mvu.core.client.BaseSelectInputAppearance;

/**
 * Created by minh on 10/3/15.
 */
public class MSelectInputAppearance extends MInputAppearance implements BaseSelectInputAppearance.HasListBox {
  @Override
  public ListBox getListBox() {
    return listBox;
  }

  @Override
  public void setName(String name) {
    listBox.setName(name);
  }

  @Override
  public String getName() {
    return listBox.getName();
  }

  interface MSelectInputAppearanceUiBinder extends UiBinder<HTMLPanel, MSelectInputAppearance> {
  }

  private static MSelectInputAppearanceUiBinder ourUiBinder = GWT.create(MSelectInputAppearanceUiBinder.class);
  @UiField
  ListBox listBox;

  public MSelectInputAppearance() {
    root = ourUiBinder.createAndBindUi(this);
    initWidget(root);
  }
}