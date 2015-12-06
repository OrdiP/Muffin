package com.muffin.theme.material.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.Node;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.mvu.core.client.PlaceController;
import com.mvu.core.client.widget.DropDownMenuAP;
import com.mvu.core.client.widget.DropdownMenu;
import com.mvu.core.shared.Action;
import com.mvu.core.shared.Attempt;
import com.mvu.core.shared.Place;
import gwt.material.design.client.ui.MaterialDropDown;
import gwt.material.design.client.ui.MaterialLink;

/**
 * Created by Van on 12/5/15.
 */
public class MDropdownMenu extends MaterialDropDown implements DropDownMenuAP {

  @Override
  public LIElement addItem(String name, Action<String> command) {
    return null;
  }

  @Override
  public LIElement addItem(String name, final Place command) {
    return null;
  }

  @Override
  public void addItem(Enum section) {

  }

  @Override
  public LIElement addDivider() {
    return null;
  }

  @Override
  public void addItem(Place place) {

  }

  @Override
  public LIElement addItem(String name) {
    return null;
  }

  @Override
  public void removeLastItem() {

  }

  @Override
  public void insertItem(String label, Place place, Node divider) {

  }

  @Override
  public void addItem(final String[] session) {
    final MaterialLink link = new MaterialLink(session[1]);
    link.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        PlaceController.placeController().goTo(session[0]);

      }
    });
    this.addWidget(link);
  }

  @Override
  public void addItem(Element element) {

  }

  @Override
  public void addCheckBox(Attempt<Boolean> onClick, Boolean value) {

  }
}
