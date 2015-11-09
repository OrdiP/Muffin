package com.muffin.theme.material.client;

import com.mvu.core.client.builder.ButtonBuilder;
import com.mvu.core.client.style.ButtonType;

/**
 * Date: 12/4/2014 3:51 PM
 */
public class MaterialButtonBuilder extends ButtonBuilder {
  public MaterialButtonBuilder(){
    super();
    button.setStyleName("btn");
    type = ButtonType.DEFAULT;
  }
}
