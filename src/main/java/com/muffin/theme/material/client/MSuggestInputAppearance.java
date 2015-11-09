package com.muffin.theme.material.client;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.mvu.core.client.SuggestInputAppearance;

/**
 * Created by minh on 9/28/15.
 */
public class MSuggestInputAppearance extends MTextInputAppearance implements SuggestInputAppearance{
  private final SuggestBox suggestBox;

  public MSuggestInputAppearance(SuggestOracle oracle){
    super("text");
    this.suggestBox = new SuggestBox(oracle, textBox);
    textBox.setStyleName("form-control");
    root.add(suggestBox);
  }

  @Override
  public void addValueChangeHandler(final ValueChangeHandler<String> changeHandler) {
    super.addValueChangeHandler(changeHandler);
    suggestBox.addSelectionHandler(new SelectionHandler<SuggestOracle.Suggestion>() {
      @Override
      public void onSelection(SelectionEvent<SuggestOracle.Suggestion> event) {
        changeHandler.onValueChange(null);
      }
    });
  }
}