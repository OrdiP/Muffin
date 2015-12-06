package com.muffin.shared.entity;

import com.mvu.core.shared.Type;
import com.mvu.core.shared.datatype.StringType;
import com.mvu.core.shared.entity.Address;
import com.mvu.core.shared.entity.Keyable;
import com.mvu.core.shared.field.FK;
import com.mvu.core.shared.field.Field;

/**
 * Created by Van on 11/7/15.
 */
public interface Company extends Keyable, Address{
  Type TYPE = Type.add(Company.class).addFields(id).addFields(street, city);

  Field<String> name = TYPE.f("name", true, StringType.instance());
  Field<String> description = TYPE.f("description", true, StringType.instance());
  Field<String> district = TYPE.f("district", true, StringType.instance());
  Field<String> logo = TYPE.f("logo", false, StringType.instance());
}
