package cn.molos.timer.impl;

import java.io.IOException;
import java.util.Date;

import cn.molos.timer.DateDeal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DateTimeSerl extends DateDeal {

	@Override
	public void serialize(Date value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		jgen.writeString(tool.date2Str(value));
	}
}