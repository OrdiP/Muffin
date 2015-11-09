package com.muffin.theme.material.client;

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;
import com.mvu.core.client.NavBarAppearance;
import com.mvu.core.client.style.Styles;

/**
 * Created by minh on 10/15/15.
 */
public class BrandBuilderImpl implements NavBarAppearance.BrandBuilder {
  private final AnchorElement root;

  public BrandBuilderImpl(AnchorElement element) {
    this.root = element;
  }

  @Override
  public void withLogo(ImageResource resource) {
    final Image logo = new Image(resource);
    logo.getElement().getStyle().setMarginTop(-18, Style.Unit.PX);
    logo.getElement().getStyle().setMarginBottom(-18, Style.Unit.PX);
    logo.setStyleName(Styles.IMG_RESPONSIVE);
    root.appendChild(logo.getElement());
  }

  @Override
  public void withHtml(String html) {
    root.setInnerHTML(html);
    root.addClassName("white-text");
    root.getStyle().setMarginLeft(100, Style.Unit.PX);
  }

  @Override
  public void withLogoAndText(String logo, String... texts) {
    root.appendChild(new Brand(logo, texts).getElement());
  }
}
