package com.muffin.theme.material.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.mvu.core.client.NavBarAppearance;
import com.mvu.core.client.widget.Navs;
import com.mvu.core.shared.Place;

/**
 * Created by minh on 10/15/15.
 */
public class MSideNavAP extends Navs implements NavBarAppearance.SideNavAP{

  private final HTMLPanel root;

  public Widget asWidget() {
    return root;
  }

  interface NavBarUiBinder extends UiBinder<HTMLPanel, MSideNavAP> {
  }

  private static NavBarUiBinder binder = GWT.create(NavBarUiBinder.class);

  @UiField
  UListElement items;

  public MSideNavAP(){
    root = binder.createAndBindUi(this);
  }

  @Override
  public MSideNavAP addItem(Enum section){
    Place place = new Place(section);
    final LIElement liElement = createItem(place.getTitle(), place);
    items.appendChild(liElement);
    return this;
  }
}
