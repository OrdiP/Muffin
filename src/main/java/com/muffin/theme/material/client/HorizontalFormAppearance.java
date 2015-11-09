package com.muffin.theme.material.client;

import com.mvu.core.client.form.FormType;
import com.mvu.core.client.style.ColumnSize;
import com.mvu.core.shared.input.BaseInput;

/**
 * Date: 2/25/14 11:10 AM
 */
public class HorizontalFormAppearance extends StandardFormAppearance {

  public HorizontalFormAppearance() {
    super();
    inputSize = ColumnSize.MD_12.getCssName();
    sectionSize = ColumnSize.MD_12.getCssName();
    main.addStyleName("form-horizontal");
  }

  @Override
  public void setSubType(int type) {
    super.setSubType(type);
    if(type == FormType.SMALL_LEFT){
      main.addStyleName("col-md-4");
      inputSize = ColumnSize.MD_12.getCssName();
    }
  }

  public void makeInput(BaseInput input){
    super.makeInput(input);
    input.labelStyle("col-md-6 control-label");
  }
}
