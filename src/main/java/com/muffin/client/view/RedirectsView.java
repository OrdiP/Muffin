package com.muffin.client.view;

import com.google.gwt.cell.client.FieldUpdater;
import com.mvu.core.client.action.DeleteAction;
import com.mvu.core.client.column.LinkColumn;
import com.mvu.core.client.form.Form;
import com.mvu.core.shared.input.Input;
import com.mvu.core.shared.input.TextAreaInput;
import com.mvu.core.client.style.ColumnSize;
import com.mvu.core.client.widget.CheckedTable;
import com.mvu.core.client.widget.Notification;
import com.mvu.core.shared.HasFields;
import com.mvu.core.shared.Presenter;
import com.mvu.core.shared.entity.Keyable;
import com.muffin.shared.entity.Redirection;

/**
 * Created by m.vu on 9/2/15.
 */
public class RedirectsView extends CheckedTable {
  public RedirectsView() {
    super(Redirection.TYPE);

    addAddButton("Add");
    addCheckButton(new DeleteAction());
    addFilter(new Input<>(Keyable.keyName));

    addEditColumn();
    addColumn(new LinkColumn<>(Redirection.keyName, new FieldUpdater<HasFields, String>() {
      @Override
      public void update(int index, HasFields object, String value) {
        Notification.alert("https://redirect-0.appspot.com/r/" + value);
      }
    }));
    addColumn(Redirection.target);
  }

  @Override
  protected Form createEditView() {
    final Form form = super.createEditView();
    form.setHeading(Redirection.keyName, "New Redirection");
    form.addInputs(new Input<>(Redirection.keyName),
            new Input<>(Redirection.target).size(ColumnSize.MD_12.getCssName()),
            new TextAreaInput(Redirection.note));
    form.eventBus().on(Form.LOAD, new Presenter() {
      @Override
      public void present(HasFields data) {
        if (data == null) {
          form.set(Redirection.keyName, Long.toHexString(System.currentTimeMillis()));
        }
      }
    });
    return form;
  }
}
