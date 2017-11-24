package cn.molos.timer;

import java.util.Date;

import molos.plugins.tool.ToolFactory;
import molos.plugins.tool.common.IDateTool;

import com.fasterxml.jackson.databind.JsonSerializer;

public abstract class DateDeal extends JsonSerializer<Date> {

	protected IDateTool tool = ToolFactory.getDateTool();
}
