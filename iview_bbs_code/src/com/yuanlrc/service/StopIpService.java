package com.yuanlrc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuanlrc.dao.StopIpDao;
import com.yuanlrc.model.PageBean;
import com.yuanlrc.model.StopIp;
import com.yuanlrc.tool.PublicStatic;


@Service
public class StopIpService{
	@Autowired
	StopIpDao StopIpDao;
	
	public PageBean<StopIp> findpage(StopIp StopIp,PageBean<StopIp> page) {
		page.setBean(StopIp);
		int count = StopIpDao.findpagecount(page);
		page.setTotalRecords(count);
		List<StopIp> list = StopIpDao.findpage(page);
		page.setList(list);
		return page ;
	}
	

	public void insert(StopIp StopIp) {
		StopIpDao.insert(StopIp);
	}

	public String delete(Integer id) {
		String result="1";
		StopIpDao.delete(id);
		return result;
	}
	public void update(StopIp StopIp) {
		StopIpDao.update(StopIp);
		
	}

	public List<StopIp> select(StopIp StopIp) {
		return StopIpDao.select(StopIp);
	}


	public StopIp findbyid(StopIp stopIp) {
		List<StopIp> list = StopIpDao.select(stopIp);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	public void staticstopip() {
		StopIpDao.staticstopip();
	}
}
