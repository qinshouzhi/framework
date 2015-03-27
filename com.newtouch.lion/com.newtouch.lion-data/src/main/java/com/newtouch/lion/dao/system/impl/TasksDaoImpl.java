package com.newtouch.lion.dao.system.impl;

import org.springframework.stereotype.Repository;

import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.dao.system.TasksDao;
import com.newtouch.lion.model.system.Tasks;

@Repository("tasksDao")
public class TasksDaoImpl extends BaseDaoImpl<Tasks, Long> implements TasksDao {

	
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -1494476134711376183L;
	
}
