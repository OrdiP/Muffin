package com.muffin.theme.material.client;

import com.mvu.core.client.BaseRadioInputAppearance;
import com.mvu.core.client.Core;
import com.mvu.core.client.input.CheckBox;
import com.mvu.core.client.style.Size;

/**
 * Created by minh on 10/7/15.
 */
public class MRadioInputAppearance extends MInputAppearance implements BaseRadioInputAppearance.HasRadios{
  private String name;

  @Override
  public CheckBox createRadio(String label) {
    final CheckBox radio = Core.CF.createRadioButton(name, label, true);
    root.add(radio);
    return radio;
  }

  public MRadioInputAppearance(String name) {
    this.name = name;
    root = baseBinder.createAndBindUi(this);
    initWidget(root);
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setSize(Size size) {

  }
}