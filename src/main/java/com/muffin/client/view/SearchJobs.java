package com.muffin.client.view;

import java.util.Arrays;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Image;
import com.muffin.shared.entity.Company;
import com.muffin.shared.entity.JobPost;
import com.mvu.core.client.BaseActivity;
import com.mvu.core.client.JSONCallback;
import com.mvu.core.client.JsCellData;
import com.mvu.core.client.RemoteCall;
import com.mvu.core.client.ServerOps;
import com.mvu.core.client.designer.TableParams;
import com.mvu.core.client.style.ColumnSize;
import com.mvu.core.shared.Format;
import com.mvu.core.shared.HasFields;
import com.mvu.core.shared.Place;
import com.mvu.core.shared.entity.Keyable;
import com.mvu.core.shared.util.StringUtils;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCard;
import gwt.material.design.client.ui.MaterialContainer;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;
import gwt.material.design.client.ui.UnorderedList;

/**
 * Created by Van on 11/7/15.
 */
public class SearchJobs implements BaseActivity {

  private MaterialButton moreBtn;
  private MaterialPanel searchPanel;
  @UiField
  MaterialTextBox keywords;
  @UiField
  MaterialTextBox location;
  @UiField
  MaterialButton searchBtn;
  @UiField
  UnorderedList cardsPanel;

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
        loadJobs();
      }
    });
    initMoreBtn();
    searchPanel.add(initMoreBtn());
    loadJobs();
  }

  private MaterialButton initMoreBtn() {
    moreBtn = new MaterialButton("More", "blue", "purpose");
    moreBtn.setWaves("light");
    moreBtn.setTooltip("Load more results");
    moreBtn.setStyleName(ColumnSize.LG_12.getCssName());
    moreBtn.addStyleName("text-center");
    moreBtn.setVisible(false);
    return moreBtn;
  }

  private void loadJobs() {
    new RemoteCall(ServerOps.FindOp).input(getFilters())
            .execute(new JSONCallback<JsCellData>() {
              @Override
              protected void processJS(JsCellData result) {
                displayingItems = 0;
                createCards(result);
              }
            });
  }


  private void createCards(JsCellData data) {
    cardsPanel.clear();
    for (int i = 0; i < data.getRows().size(); i++) {
      cardsPanel.add(createCard(data.getRows().get(i)));
    }
    displayingItems += data.getRows().size();
    moreBtn.setVisible(displayingItems < data.getSize());
  }

  public JobPostCard createCard(HasFields jobPost) {
    final JobPostCard card = new JobPostCard();
    card.jobTitle(jobPost.get(JobPost.title))
            .companyLogoUrl(jobPost.get(JobPost.company.dot(Company.logo)))
            .companyName(jobPost.get(JobPost.company.dot(Company.name)))
            .jobDescription(jobPost.get(JobPost.description))
            .jobId(jobPost.getId());
    card.addStyleName(ColumnSize.LG_4.getCssName());
    return card;
  }

  public HasFields getFilters() {
    final HasFields value = Format.format.bean().set(Keyable.kind, JobPost.TYPE.entityName())
            .set(JobPost.active, true);
    if (!StringUtils.isEmpty(keywords.getValue())) {
      value.set(JobPost.keywords, Arrays.asList(keywords.getValue()));
    }
    value.set(com.mvu.core.shared.params.TableParams.COLUMNS,
            Arrays.asList(JobPost.title.name(),
                    JobPost.description.name(), JobPost.benefit.name(),
                    JobPost.company.dot(Company.logo).name(), JobPost.company.dot(Company.name).name(),
                    JobPost.positions.name()));
    return value;
  }

}