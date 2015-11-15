package com.muffin.client.view.job;

import java.util.List;

import com.muffin.shared.entity.JobPost;
import com.muffin.shared.entity.User;
import com.mvu.core.client.Core;
import com.mvu.core.client.FieldValidator;
import com.mvu.core.client.form.Form;
import com.mvu.core.client.form.FormType;
import com.mvu.core.client.input.HiddenInput;
import com.mvu.core.client.input.ListInput;
import com.mvu.core.shared.input.Input;
import com.mvu.core.shared.input.TextAreaInput;

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
    addInputs(new Input<>(JobPost.title),
            new ListInput(JobPost.keywords).is(keywordsValidate),
            new Input<>(JobPost.salary),
            new TextAreaInput(JobPost.description),
            new TextAreaInput(JobPost.benefit),
            new HiddenInput<>(JobPost.company).defaultValue(Core.currentUser().get(User.Company)));
    addUpdateButton("Save");
  }
}
