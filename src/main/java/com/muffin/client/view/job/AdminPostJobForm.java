package com.muffin.client.view.job;

import com.muffin.shared.entity.JobPost;
import com.mvu.core.client.form.Form;
import com.mvu.core.client.form.FormType;
import com.mvu.core.client.input.ListInput;
import com.mvu.core.shared.input.Input;
import com.mvu.core.shared.input.TextAreaInput;

/**
 * Created by khacsinhcs on 11/15/15.
 */
public class AdminPostJobForm extends Form {
  public AdminPostJobForm() {
    setType(FormType.MEDIUM_CENTER);
    kind(JobPost.TYPE);
    addInputs(new Input<>(JobPost.title),
            new ListInput(JobPost.keywords),
            new TextAreaInput(JobPost.description));
    addUpdateButton("Save");
  }
}
