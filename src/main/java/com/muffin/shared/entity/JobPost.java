package com.muffin.shared.entity;

import java.util.Date;
import java.util.List;

import com.mvu.core.shared.Type;
import com.mvu.core.shared.datatype.DateType;
import com.mvu.core.shared.datatype.IntegerType;
import com.mvu.core.shared.datatype.LongType;
import com.mvu.core.shared.datatype.StringType;
import com.mvu.core.shared.entity.HasStatus;
import com.mvu.core.shared.entity.Keyable;
import com.mvu.core.shared.field.FK;
import com.mvu.core.shared.field.Field;

/**
 * Created by Van on 11/7/15.
 */
public interface JobPost extends Keyable, HasStatus {
  Type TYPE = Type.add(JobPost.class).addFields(id);

  Field<String> title = TYPE.f("title", true, StringType.instance());
  Field<String> benefit = TYPE.f("benefit", false, StringType.text());
  Field<String> salary = TYPE.f("salary", StringType.instance());
  Field<String> description = TYPE.f("description", false, StringType.text());
  Field<String> requirement = TYPE.f("requirement", false, StringType.instance());
  Field<Date> fromDate = TYPE.f("from_date", false, DateType.midnight());
  Field<Date> toDate = TYPE.f("to_date", false, DateType.midnight());
  FK<String> company = TYPE.fk("company", StringType.key()).ref(Company.TYPE);
  Field<Integer> positions = TYPE.f("positions", "Number Of Position", false, IntegerType.instance());

  Field<List<String>> keywords = TYPE.f("keywords", StringType.freeText().listType());
}
