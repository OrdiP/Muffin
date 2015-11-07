package com.muffin.client.view;

import java.util.Arrays;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.IsWidget;
import com.muffin.shared.entity.JobPost;
import com.mvu.core.client.BaseActivity;
import com.mvu.core.client.JSONCallback;
import com.mvu.core.client.JsCellData;
import com.mvu.core.client.RemoteCall;
import com.mvu.core.client.ServerOps;
import com.mvu.core.client.style.ColumnSize;
import com.mvu.core.shared.CellData;
import com.mvu.core.shared.HasFields;
import com.mvu.core.shared.Place;
import com.mvu.core.shared.entity.Keyable;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCard;
import gwt.material.design.client.ui.MaterialCollection;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialContainer;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialRow;
import gwt.material.design.client.ui.MaterialToast;
import org.jcp.xml.dsig.internal.dom.DOMUtils;

/**
 * Created by Van on 11/7/15.
 */
public class SearchJobs implements BaseActivity{

  private MaterialPanel searchPanel;
  @UiField
  InputElement searchBox;
  @UiField
  MaterialButton searchBtn;
  @UiField
  MaterialContainer cardsPanel;

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
        new RemoteCall(ServerOps.FindOp).set(Keyable.kind, JobPost.TYPE.entityName())
                .set(JobPost.keywords, Arrays.asList(searchBox.getValue()))
                .execute(new JSONCallback<JsCellData>() {
                  @Override
                  protected void processJS(JsCellData result) {
                    createCards(result);
                  }
                });
      }
    });
    final MaterialButton moreBtn = new MaterialButton("More", "blue", "purpose");
    moreBtn.setWaves("light");
    moreBtn.setWaves("large");
    moreBtn.setTooltip("Load more results");

    searchPanel.add(moreBtn);
  }


  private void createCards(JsCellData data) {
    cardsPanel.clear();
    for (int i = 0 ; i < data.getRows().size() ; i ++) {
      cardsPanel.add(createCard(data.getRows().get(i)));
    }

  }

  public MaterialCard createCard(HasFields jobPost) {
    final MaterialCard card = new MaterialCard();
    card.setType("basic");
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