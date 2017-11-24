package cn.molos.task;

import java.util.Timer;

public class TaskManager extends Timer {

	private static TaskManager instance;

	private TaskManager() {

	}

	public static synchronized TaskManager newInstance() {
		if (instance == null) {
			instance = new TaskManager();
		}
		return instance;
	}

}
