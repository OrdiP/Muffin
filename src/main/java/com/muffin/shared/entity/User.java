package com.muffin.shared.entity;

import com.mvu.core.shared.Type;
import com.mvu.core.shared.datatype.EnumType;
import com.mvu.core.shared.field.Field;

/**
 * Created by Van on 11/10/15.
 */
public interface User extends com.mvu.core.shared.entity.User {
  Type TYPE = com.mvu.core.shared.entity.User.TYPE.trackCreation(true);
  Field<UserType> role = TYPE.f("type", false, EnumType.instance(UserType.class));
}
