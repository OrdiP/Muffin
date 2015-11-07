package com.muffin.shared.entity;

import com.mvu.core.shared.Type;
import com.mvu.core.shared.datatype.StringType;
import com.mvu.core.shared.entity.Keyable;
import com.mvu.core.shared.field.FK;
import com.mvu.core.shared.field.Field;

/**
 * Created by Van on 11/7/15.
 */
public interface Company extends Keyable {
  Type TYPE = Type.add(Company.class).addFields(id);

  Field<String> name = TYPE.f("title", true, StringType.instance());
  Field<String> description = TYPE.f("description", true, StringType.instance());
}
