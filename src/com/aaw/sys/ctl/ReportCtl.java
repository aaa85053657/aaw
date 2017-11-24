package com.aaw.sys.ctl;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import me.quanli.report.entity.Report;
import me.quanli.report.entity.ReportColumn;
import me.quanli.report.entity.ReportParameter;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import test.ReportURLHelper;

import com.aaw.base.ctl.BaseCtl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

@Controller
@RequestMapping("reportCtl")
public class ReportCtl extends BaseCtl<Report> {

	private static final Log LOGGER = LogFactory.getLog(ReportCtl.class);

	static {
		String path = ReportURLHelper.get("init.path");
		ReportServiceProxy.init(path);
	}

	@RequestMapping(params = "goto=view")
	public ModelAndView view() {
		return new ModelAndView("sys/report/report");
	}

	@RequestMapping(params = "goto=list")
	@ResponseBody
	public Object list(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int rows) {
		// List<Report> list = ReportServiceProxy.fetchReportInPage(page,
		// rows).getData();
		PageData<Report> temp = ReportServiceProxy
				.fetchReportInPage(page, rows);
		List<Report> list = temp.getData();
		Integer total = temp.getTotal();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}

	@RequestMapping(params = "goto=reportMain")
	public ModelAndView reportMain(@RequestParam(defaultValue = "0") int rid) {
		Map<String, Object> map = new HashMap<String, Object>();
		Report report = ReportServiceProxy.retrieveReport(rid);
		List<ReportColumn> rclist = ReportServiceProxy.fetchReportColumns(rid);
		List<ReportParameter> rplist = ReportServiceProxy
				.fetchReportParameters(rid);
		map.put("report", report);
		map.put("rclist", rclist);
		map.put("rplist", rplist);
		return new ModelAndView("sys/report/reportMain", map);
	}

	@RequestMapping(params = "goto=findReport")
	@ResponseBody
	public Object findReport(@RequestParam(defaultValue = "0") int rid,
			@RequestParam String params,
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int rows) {
		PageData<Map<String, Object>> temp = ReportServiceProxy
				.getExecutionResultInPage(rid, params, page, rows);
		List<Map<String, Object>> templist = temp.getData();
		List<ReportColumn> rclist = ReportServiceProxy.fetchReportColumns(rid);
		List<List<Object>> list = new ArrayList<List<Object>>();
		for (Map<String, Object> map : templist) {
			List<Object> ls = new ArrayList<Object>();
			for (ReportColumn column : rclist) {
				Object o = map.get(column.getName());
				if (column.getType() == 4) {
					// Date tempDate = (Date) o;
					Date tempDate = new Date((Long) o);
					SimpleDateFormat df = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					String strDate = df.format(tempDate);
					ls.add(strDate);
				} else {
					ls.add(o);
				}

			}
			list.add(ls);
		}
		Integer total = temp.getTotal();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}

	public static class ReportServiceProxy {

		public static String reportServiceUrl;

		public static void init(String url) {
			reportServiceUrl = url;
		}

		public static Report retrieveReport(Integer reportId) {
			Map<String, String> params = new HashMap<String, String>();
			String responseStr = httpRequest("/report/report/" + reportId,
					params);
			return JSON.parseObject(responseStr, Report.class);
		}

		public static List<ReportColumn> fetchReportColumns(Integer reportId) {
			Map<String, String> params = new HashMap<String, String>();
			String responseStr = httpRequest("/report/report/" + reportId
					+ "/columns", params);
			return JSON.parseArray(responseStr, ReportColumn.class);
		}

		public static List<ReportParameter> fetchReportParameters(
				Integer reportId) {
			Map<String, String> params = new HashMap<String, String>();
			String responseStr = httpRequest("/report/report/" + reportId
					+ "/parameters", params);
			return JSON.parseArray(responseStr, ReportParameter.class);
		}

		public static PageData<Report> fetchReportInPage(Integer pageNo,
				Integer pageSize) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("pageNo", Objects.toString(pageNo, "1"));
			params.put("pageSize", Objects.toString(pageSize, "10"));
			String responseStr = httpRequest("/report/fetchReportInPage",
					params);
			return JSON.parseObject(responseStr,
					new TypeReference<PageData<Report>>() {
					});
		}

		public static PageData<Map<String, Object>> getExecutionResultInPage(
				Integer reportId, String paramJson, Integer pageNo,
				Integer pageSize) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("reportId", Objects.toString(reportId, ""));
			params.put("params", paramJson);
			params.put("pageNo", Objects.toString(pageNo, "1"));
			params.put("pageSize", Objects.toString(pageSize, "10"));
			String responseStr = httpRequest(
					"/report/getExecutionResultInPage", params);
			return JSON.parseObject(responseStr,
					new TypeReference<PageData<Map<String, Object>>>() {
					});
		}

		private static String httpRequest(String path,
				Map<String, String> params) {
			// Create an instance of HttpClient.
			HttpClient client = new HttpClient();

			// Create a method instance.
			GetMethod method = new GetMethod(reportServiceUrl + path);

			List<NameValuePair> paramList = new ArrayList<NameValuePair>();
			for (Entry<String, String> entry : params.entrySet()) {
				paramList.add(new NameValuePair(entry.getKey(), entry
						.getValue()));
			}
			method.setQueryString(paramList.toArray(new NameValuePair[paramList
					.size()]));

			String responseStr = "";
			try {
				// Execute the method.
				int statusCode = client.executeMethod(method);

				if (statusCode != HttpStatus.SC_OK) {
					LOGGER.error("Method failed: " + method.getStatusLine());
				}

				// Read the response stream.
				InputStream responseStream = method.getResponseBodyAsStream();
				responseStr = IOUtils.toString(responseStream, "utf-8");
			} catch (HttpException e) {
				throw new RuntimeException(e);
			} catch (IOException e) {
				throw new RuntimeException(e);
			} finally {
				// Release the connection.
				method.releaseConnection();
			}
			return responseStr;
		}

	}

	public static class PageData<T> {

		public Integer total;

		public Integer pageNo;

		public Integer pageSize;

		public List<T> data;

		public Integer getTotal() {
			return total;
		}

		public void setTotal(Integer total) {
			this.total = total;
		}

		public Integer getPageNo() {
			return pageNo;
		}

		public void setPageNo(Integer pageNo) {
			this.pageNo = pageNo;
		}

		public Integer getPageSize() {
			return pageSize;
		}

		public void setPageSize(Integer pageSize) {
			this.pageSize = pageSize;
		}

		public List<T> getData() {
			return data;
		}

		public void setData(List<T> data) {
			this.data = data;
		}

	}

}
