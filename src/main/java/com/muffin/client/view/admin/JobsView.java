package com.muffin.client.view.admin;

import com.muffin.shared.entity.Company;
import com.muffin.shared.entity.JobPost;
import com.mvu.core.client.LazyOptions;
import com.mvu.core.client.form.Form;
import com.mvu.core.client.input.ListInput;
import com.mvu.core.client.widget.CheckedTable;
import com.mvu.core.shared.input.Input;
import com.mvu.core.shared.input.SelectInput;
import com.mvu.core.shared.input.TextAreaInput;

/**
 * Created by Van on 11/10/15.
 */
public class JobsView extends CheckedTable {
  public JobsView() {
    super("Jobs");
    kind(JobPost.TYPE);
    addAddButton("Add");
    addEditColumn();
    addColumn(JobPost.company);
    addColumn(JobPost.description);
    addColumn(JobPost.keywords);
    addColumn(JobPost.created);
  }

  @Override
  protected Form createEditView() {
    Form form = super.createEditView();
    final SelectInput companiesInput = new SelectInput(JobPost.company);
    companiesInput.options().add(new LazyOptions<>(Company.TYPE, Company.id, Company.name));
    form.addInputs(new Input<>(JobPost.title)
            , companiesInput
            ,new TextAreaInput(JobPost.description)
            ,new ListInput(JobPost.keywords)
    );
    return form;
  }
}
