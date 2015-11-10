package com.muffin.shared.entity;

import java.util.List;

import com.mvu.core.shared.Type;
import com.mvu.core.shared.datatype.LongType;
import com.mvu.core.shared.datatype.StringType;
import com.mvu.core.shared.entity.Keyable;
import com.mvu.core.shared.field.FK;
import com.mvu.core.shared.field.Field;

/**
 * Created by Van on 11/7/15.
 */
public interface JobPost extends Keyable {
  Type TYPE = Type.add(JobPost.class).addFields(id);

  Field<String> title = TYPE.f("title", true, StringType.instance());
  Field<String> description = TYPE.f("description", true, StringType.instance());
  FK<Long> company = TYPE.fk("company", LongType.instance()).ref(Company.TYPE);
  Field<List<String>> keywords = TYPE.f("keywords", StringType.label().listType());
}
