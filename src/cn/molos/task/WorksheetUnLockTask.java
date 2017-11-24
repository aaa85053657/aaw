package cn.molos.task;

import java.util.Date;
import java.util.TimerTask;

import cn.molos.listener.SpringInit;

import com.aaw.bean.FactoryStatus;
import com.aaw.bean.Worksheet;
import com.aaw.bean.WorksheetDetail;
import com.aaw.bean.WorksheetDetailTemp;
import com.aaw.sys.service.IFactoryStatusService;
import com.aaw.sys.service.IWorksheetDetailService;
import com.aaw.sys.service.IWorksheetDetailTempService;
import com.aaw.sys.service.IWorksheetService;

public class WorksheetUnLockTask extends TimerTask {

	private IWorksheetDetailService worksheetDetailService = (IWorksheetDetailService) SpringInit
			.getApplicationContext().getBean("worksheetDetailService");
	private IWorksheetService worksheetService = (IWorksheetService) SpringInit
			.getApplicationContext().getBean("worksheetService");
	private IWorksheetDetailTempService worksheetDetailTempService = (IWorksheetDetailTempService) SpringInit
			.getApplicationContext().getBean("worksheetDetailTempService");
	private IFactoryStatusService factoryStatusService = (IFactoryStatusService) SpringInit
			.getApplicationContext().getBean("factoryStatusService");
	private int id;

	public WorksheetUnLockTask(int id) {
		super();
		this.id = id;
	}

	@Override
	public void run() {
		WorksheetDetail wsd = worksheetDetailService.query(this.id);
		if (wsd != null && (wsd.getStatus() == 9 || wsd.getStatus() == 10)) {
			wsd.setIsRead(1);
			wsd.setStatus(1);
			wsd.setCreationTime(null);
			wsd.setModificationTime(new Date());
			worksheetDetailService.update(wsd);
			// worksheetDetailService.updateStatus(this.id, 1);
			// worksheetDetailService.updateIsRead(this.id, 1);
		}
		if (wsd != null && wsd.getWorksheet() != null) {
			Worksheet worksheet = wsd.getWorksheet();
			worksheet.setOpr(null);
			worksheetService.update(worksheet);

			FactoryStatus factoryStatus = factoryStatusService
					.findByWorksheet(worksheet.getId());
			if (factoryStatus != null) {
				factoryStatus.setOptStatus(10);
				factoryStatusService.update(factoryStatus);
			}

			WorksheetDetailTemp detailTemp = worksheetDetailTempService
					.findByDetail(id);
			if (detailTemp != null && detailTemp.getId() != null) {
				worksheetDetailTempService.delete(detailTemp);
			}
		}

	}
}
