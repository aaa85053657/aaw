package cn.molos.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.molos.listener.SpringInit;
import cn.molos.task.TaskManager;
import cn.molos.task.WorksheetUnLockTask;

import com.aaw.bean.WorksheetDetail;
import com.aaw.sys.service.IWorksheetDetailService;

/**
 * Servlet implementation class TimeTaskStart
 */
public class TimeTaskStart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IWorksheetDetailService worksheetDetailService = (IWorksheetDetailService) SpringInit
			.getApplicationContext().getBean("worksheetDetailService");

	@Override
	public void init() throws ServletException {
		worksheetDetailService.updateStatus(new Date());
		List<WorksheetDetail> list = worksheetDetailService.findByStatus(9);
		List<WorksheetDetail> list2 = worksheetDetailService.findByStatus(10);
		list.addAll(list2);
		TaskManager manager = TaskManager.newInstance();
		if (list != null) {
			for (WorksheetDetail detail : list) {
				long time1 = new Date().getTime();
				long time2 = new Date().getTime();
				if (detail.getUnlockTime() != null) {
					// time2 = detail.getEndTime().getTime();
					time2 = detail.getUnlockTime().getTime();
				}
				if (time2 >= time1) {
					try {
						manager.schedule(
								new WorksheetUnLockTask(detail.getId()),
								(time2 - time1));
					} catch (Exception e) {
						TaskManager manager2 = TaskManager.newInstance();
						manager2.schedule(
								new WorksheetUnLockTask(detail.getId()),
								(time2 - time1));
					}
				}
			}
		}
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TimeTaskStart() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
