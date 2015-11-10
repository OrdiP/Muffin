package com.muffin.client.view;

import java.util.Arrays;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.muffin.shared.entity.JobPost;
import com.mvu.core.client.BaseActivity;
import com.mvu.core.client.JSONCallback;
import com.mvu.core.client.JsCellData;
import com.mvu.core.client.RemoteCall;
import com.mvu.core.client.ServerOps;
import com.mvu.core.client.style.ColumnSize;
import com.mvu.core.shared.HasFields;
import com.mvu.core.shared.Place;
import com.mvu.core.shared.entity.Keyable;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCard;
import gwt.material.design.client.ui.MaterialContainer;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialToast;
import gwt.material.design.client.ui.animate.MaterialAnimator;
import gwt.material.design.client.ui.animate.Transition;

/**
 * Created by Van on 11/7/15.
 */
public class SearchJobs implements BaseActivity{

  private final MaterialButton moreBtn;
  private MaterialPanel searchPanel;
  @UiField
  InputElement searchBox;
  @UiField
  MaterialButton searchBtn;
  @UiField
  MaterialContainer cardsPanel;

  int displayingItems = 0;

  @Override
  public void start(AcceptsOneWidget panel, Place place) {
    panel.setWidget(searchPanel);
  }

  interface SearchJobsUiBinder extends UiBinder<MaterialPanel, SearchJobs> {
  }

  private static SearchJobsUiBinder ourUiBinder = GWT.create(SearchJobsUiBinder.class);

  public SearchJobs() {
    searchPanel = ourUiBinder.createAndBindUi(this);
    searchPanel.add(cardsPanel);
    searchBtn.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        new RemoteCall(ServerOps.FindOp)
                .set(Keyable.kind, JobPost.TYPE.entityName())
                .set(JobPost.keywords, Arrays.asList(searchBox.getValue()))
                .execute(new JSONCallback<JsCellData>() {
                  @Override
                  protected void processJS(JsCellData result) {
                    displayingItems = 0;
                    createCards(result);
                  }
                });
      }
    });
    moreBtn = new MaterialButton("More", "blue", "purpose");
    moreBtn.setWaves("light");
    moreBtn.setTooltip("Load more results");
    moreBtn.setStyleName(ColumnSize.LG_12.getCssName());
    moreBtn.setVisible(false);
    searchPanel.add(moreBtn);
  }


  private void createCards(JsCellData data) {
    cardsPanel.clear();
    for (int i = 0 ; i < data.getRows().size() ; i ++) {
      cardsPanel.add(createCard(data.getRows().get(i)));
    }
    displayingItems += data.getSize();
    moreBtn.setVisible(displayingItems > 0);
  }

  public MaterialCard createCard(HasFields jobPost) {
    final MaterialCard card = new MaterialCard();
    card.setType("reveal");
    card.setTitle(jobPost.get(JobPost.title));
    card.setDescription(jobPost.get(JobPost.description));
    card.addStyleName(ColumnSize.LG_4.getCssName());
    MaterialButton apply = new MaterialButton("Apply", "blue", "purple");
    apply.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        MaterialToast.alert("Burn!!");
      }
    });
    card.getActionPanel().add(apply);
    return card;
  }



}