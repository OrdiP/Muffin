package com.muffin.client.view.admin;

import com.muffin.shared.entity.Company;
import com.muffin.shared.entity.JobPost;
import com.mvu.core.client.form.Form;
import com.mvu.core.client.input.ListInput;
import com.mvu.core.client.widget.CheckedTable;
import com.mvu.core.shared.input.Input;
import com.mvu.core.shared.input.TextAreaInput;

/**
 * Created by Van on 11/10/15.
 */
public class CompaniesView extends CheckedTable {
  public CompaniesView() {
    super("Companies");
    kind(Company.TYPE);
    addAddButton("Add");
    addEditColumn();
    addColumn(Company.name);
    addColumn(Company.description);
    addColumn(Company.street);
    addColumn(Company.district);
    addColumn(Company.city);
    addColumn(Company.created);
  }

  @Override
  protected Form createEditView() {
    Form form = super.createEditView();
    form.addInputs(new Input<>(Company.name)
            ,new TextAreaInput(Company.description)
            ,new Input<>(Company.street)
            ,new Input<>(Company.district)
            ,new Input<>(Company.city)
    );
    return form;
  }
}
