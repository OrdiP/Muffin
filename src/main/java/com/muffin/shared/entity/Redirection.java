package com.muffin.shared.entity;

import com.mvu.core.shared.Type;
import com.mvu.core.shared.datatype.StringType;
import com.mvu.core.shared.entity.Keyable;
import com.mvu.core.shared.field.Field;

public interface Redirection extends Keyable {
  Type TYPE = Type.add(Redirection.class).addFields(keyName);

  Field<String> target = TYPE.f("target", "Target", false, StringType.instance());
  Field<String> note = TYPE.f("note", "Note", false, StringType.text());
}
