package com.muffin.theme.material.client;

import com.mvu.core.client.builder.ButtonAPBuilder;
import com.mvu.core.client.config.ClickableConfig;
import com.mvu.core.client.form.ButtonAP;

/**
 * Created by minh on 10/24/15.
 */
public class MButtonAPBuilder extends ButtonAPBuilder {
  @Override
  protected void styleButton(ButtonAP buttonAP, ClickableConfig config) {
    buttonAP.setStyleName("btn " + config.buttonType());
  }
}
