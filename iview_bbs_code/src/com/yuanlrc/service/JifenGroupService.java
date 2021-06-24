package com.yuanlrc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuanlrc.dao.JifenGroupDao;
import com.yuanlrc.model.JifenGroup;
import com.yuanlrc.tool.Tool;


@Service
public class JifenGroupService{
	@Autowired
	JifenGroupDao groupDao;
	
	public void insert(JifenGroup jifenGroup) {
		jifenGroup.setCreatetime(Tool.getyyyyMMddHHmmss());
		groupDao.insert(jifenGroup);
	}

	public String delete(Integer id) {
		String result="1";
		groupDao.delete(id);
		return result;
	}
	public void update(JifenGroup jifenGroup) {
		groupDao.update(jifenGroup);
	}

	public List<JifenGroup> find(JifenGroup jifenGroup) {
		return groupDao.find(jifenGroup);
	}
	

}
