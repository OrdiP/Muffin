package com.muffin.client.view.job;

import java.util.Date;
import java.util.List;

import com.muffin.client.bundle.MuffinMessage;
import com.muffin.shared.entity.JobPost;
import com.muffin.shared.entity.User;
import com.mvu.core.client.Core;
import com.mvu.core.client.FieldValidator;
import com.mvu.core.client.form.Form;
import com.mvu.core.client.form.FormType;
import com.mvu.core.client.input.HTMLInput;
import com.mvu.core.client.input.HiddenInput;
import com.mvu.core.client.input.ListInput;
import com.mvu.core.client.style.ColumnSize;
import com.mvu.core.shared.input.DateInput;
import com.mvu.core.shared.input.Input;

/**
 * Created by khacsinhcs on 11/15/15.
 */
public class AdminPostJobForm extends Form {
  public AdminPostJobForm() {
    setHeading("Post new Job");
    setType(FormType.MEDIUM_CENTER);
    kind(JobPost.TYPE);
    final FieldValidator<List<String>> keywordsValidate = new FieldValidator<List<String>>() {
      @Override
      public String validate(List<String> value) {
        if (value.size() > 3) {
          return "You can add at most three keywords";
        }
        return null;
      }
    };
    addInputs(new Input<>(JobPost.title).style(ColumnSize.LG_12.getCssName()),
            new ListInput(JobPost.keywords).is(keywordsValidate),
            new Input<>(JobPost.salary),
            new DateInput(JobPost.fromDate).defaultValue(new Date()),
            new DateInput(JobPost.toDate).defaultValue(new Date()),
            new HTMLInput(JobPost.requirement),
            new HTMLInput(JobPost.description).setHelpBlock(MuffinMessage.messages.jobDescriptionHelpBlock()),
            new HTMLInput(JobPost.benefit),
            new HiddenInput<>(JobPost.company).defaultValue(Core.currentUser().get(User.Company)));
    addUpdateButton("Save");
  }
}
