package com.muffin.client.companywizard;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.uibinder.client.UiBinder;

/**
 * Created by Van on 11/10/15.
 */
public class CompanyWizard {
  interface CompanyWizardUiBinder extends UiBinder<DivElement, CompanyWizard> {
  }

  private static CompanyWizardUiBinder ourUiBinder = GWT.create(CompanyWizardUiBinder.class);

  public CompanyWizard() {
    DivElement rootElement = ourUiBinder.createAndBindUi(this);
  }
}