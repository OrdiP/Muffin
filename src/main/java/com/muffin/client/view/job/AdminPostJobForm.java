package com.muffin.client.view.job;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.ui.HTML;
import com.muffin.client.bundle.MuffinMessage;
import com.muffin.shared.entity.JobPost;
import com.muffin.shared.entity.User;
import com.mvu.core.client.Core;
import com.mvu.core.client.FieldValidator;
import com.mvu.core.client.JsCallback;
import com.mvu.core.client.form.Form;
import com.mvu.core.client.form.FormType;
import com.mvu.core.client.input.HTMLInput;
import com.mvu.core.client.input.HiddenInput;
import com.mvu.core.client.input.ListInput;
import com.mvu.core.client.style.ColumnSize;
import com.mvu.core.shared.HasFields;
import com.mvu.core.shared.input.DateInput;
import com.mvu.core.shared.input.Input;
import com.mvu.core.shared.util.DateOps;

/**
 * Created by khacsinhcs on 11/15/15.
 */
public class AdminPostJobForm extends Form {

  private HasFields data;
  private Form requirementForm;
  private Form descriptionForm;
  private Form benefitForm;

  public AdminPostJobForm() {
    setHeading("POST NEW JOB");
    setSubHeading("Free and always like this");
    initForm(this);
    final FieldValidator<List<String>> keywordsValidate = new FieldValidator<List<String>>() {
      @Override
      public String validate(List<String> value) {
        if (value.size() > 3) {
          return "You can add at most three keywords";
        }
        return null;
      }
    };
    addInputs(new Input<>(JobPost.title).style(ColumnSize.LG_12.getCssName()).required(true),
            new ListInput(JobPost.keywords).is(keywordsValidate).style(ColumnSize.LG_12.getCssName()).required(true),
            new Input<>(JobPost.salary).required(true),
            new Input<>(JobPost.positions).required(true),
            new DateInput(JobPost.fromDate).defaultValue(new Date()).required(true),
            new DateInput(JobPost.toDate).defaultValue(DateOps.instance.addDays(new Date(), 30)).required(true),
            new HiddenInput<>(JobPost.company).defaultValue(Core.currentUser().get(User.Company)));
    addButton("Save", new JsCallback() {
      @Override
      protected void processBean(HasFields result) {
        data = result;
        ensureDescriptionForm().push(data);
      }
    });
  }

  private Form ensureDescriptionForm() {
    if (descriptionForm == null) {
      descriptionForm = new Form("Description");
      initForm(descriptionForm);
      final HTML html = new HTML(MuffinMessage.messages.jobDescriptionHelpBlock());
      html.addStyleName(ColumnSize.LG_12.getCssName());
      descriptionForm.inputPanel().add(html);
      descriptionForm.addInputs(new HTMLInput(JobPost.description));
      descriptionForm.addButton("Next", new JsCallback() {
        @Override
        protected void processBean(HasFields result) {
          ensureRequirementForm().push(result);
        }
      });
    }
    return descriptionForm;
  }

  private Form ensureRequirementForm() {
    if (requirementForm == null) {
      requirementForm = new Form("Requirement");
      initForm(requirementForm);
      requirementForm.addInputs(new HTMLInput(JobPost.requirement));
      requirementForm.addButton("Next", new JsCallback() {
        @Override
        protected void processBean(HasFields result) {
          ensureBenefitForm().push(result);
        }
      });
    }
    return requirementForm;
  }

  private Form ensureBenefitForm() {
    if (benefitForm == null) {
      benefitForm = new Form("Benefit");
      initForm(benefitForm);
      benefitForm.addInputs(new HTMLInput(JobPost.benefit));
      benefitForm.addButton("Done", new JsCallback() {
        @Override
        protected void processBean(HasFields result) {
          //TODO need more information to fixed here
        }
      });
    }
    return benefitForm;
  }

  private void initForm(Form form) {
    form.kind(JobPost.TYPE);
    form.setType(FormType.MEDIUM_CENTER);
  }

}
