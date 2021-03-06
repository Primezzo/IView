package com.yuanlrc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuanlrc.dao.TieziDao;
import com.yuanlrc.model.Bankuai;
import com.yuanlrc.model.InnerLink;
import com.yuanlrc.model.PageBean;
import com.yuanlrc.model.Tiezi;
import com.yuanlrc.tool.PublicStatic;
import com.yuanlrc.tool.Tool;


@Service("tieziService")
public class TieziService{
	@Autowired
	TieziDao tieziDao;
	
	public PageBean<Tiezi> findpage(Tiezi tiezi,PageBean<Tiezi> page) {
		page.setBean(tiezi);
		int count = tieziDao.findpagecount(page);
		page.setTotalRecords(count);
		List<Tiezi> list = tieziDao.findpage(page);
		page.setList(list);
		return page ;
	}
	

	public void insert(Tiezi tiezi) {
		tieziDao.insert(tiezi);
	}

	public String delete(Integer id) {
		String result="1";
//		Bankuai bankuai=new Bankuai();
//		bankuai.setParentId(id);
//		List<Bankuai> list = tieziDao.find(bankuai);
//		if(list.size()>0){
//			result="0";
//		}else{
			tieziDao.delete(id);
//		}
		return result;
	}
	public void update(Tiezi tiezi) {
		tieziDao.update(tiezi);
		
	}
	public Tiezi findbyid(Tiezi tiezi) {
		List<Tiezi> list= tieziDao.find(tiezi);
		if(list.size()>0){
			tiezi=list.get(0);
		}
		return tiezi;
	}
	
	public List<Tiezi> find(Tiezi tiezi) {
		return tieziDao.find(tiezi);
	}


	public PageBean<Tiezi> findpagebybankuanid(Tiezi tiezi,
			PageBean<Tiezi> page) {
		String orderby = tiezi.getOrderby();
		if("0".equals(orderby)){
			orderby=" t1.id  ";
		}else if("1".equals(orderby)){
			orderby=" t1.huifuid ";
		}else if("2".equals(orderby)){
			orderby=" t1.jinghua ";
		}else if("3".equals(orderby)){
			orderby=" t1.findcount ";
		}else{
			orderby=" t1.id ";
		}
		tiezi.setOrderby(orderby);
		page.setBean(tiezi);
		int count = tieziDao.findpagecount(page);
		page.setTotalRecords(count);
		List<Tiezi> list = tieziDao.findpage(page);
		page.setList(list);
		return page ;
	}
	
	public PageBean<Tiezi> findpagebyCreateuserid(Tiezi tiezi,
			PageBean<Tiezi> page) {
		page.setBean(tiezi);
		int count = tieziDao.findpagecount(page);
		page.setTotalRecords(count);
		List<Tiezi> list = tieziDao.findpage(page);
		page.setList(list);
		return page ;
	}


	public int findnowcount(String bankuaiid) {
		Tiezi tiezi=new Tiezi();
		tiezi.setBankuaiId(Integer.parseInt(bankuaiid));
		tiezi.setCreatetime(Tool.getyyyy_MM_dd());
		return tieziDao.findnowcount(tiezi);
	}


	public List<Tiezi> findzhiding(String bankuaiid) {
		Tiezi tiezi=new Tiezi();
		tiezi.setBankuaiId(Integer.parseInt(bankuaiid));
		List<Tiezi> find = tieziDao.find(tiezi);
		return find;
	}


	public Map<String, Object> findbytieziid(String id) {
		Map<String, Object> map=new HashMap<String, Object>();
		Tiezi tiezi=new Tiezi();
		tiezi.setId(Integer.parseInt(id));
		Map<String, Object> findbytieziid = tieziDao.findbytieziid(tiezi);
		String contenttxt=findbytieziid.get("contenttxt")+"";
		if(contenttxt!=null){
			contenttxt=contenttxt.replaceAll("<", "");
			contenttxt=contenttxt.replaceAll(">", "");
			findbytieziid.put("contenttxt", contenttxt);
		}
		if(contenttxt!=null&&contenttxt.length()>200){
			//???????????????(<meta name="description" content="${map.tiezi.contenttxt}" /> )???????????????????????????<>
			contenttxt=contenttxt.substring(0, 190)+"...";
			findbytieziid.put("contenttxt", contenttxt);
		}
		String contenthtml=findbytieziid.get("contenthtml")+"";
		if(contenthtml!=null&&contenthtml.length()>0){
			//????????????????????????????????????
			contenthtml=contenthtml.replace("<a", "<a rel=\"nofollow\" ");
			List<InnerLink> innerLinklist = PublicStatic.innerLink;
			for (InnerLink innerLink : innerLinklist) {
				String name = innerLink.getName();
				String innerLinkurl=innerLink.getInnerlink();
				contenthtml=contenthtml.replaceAll(name, "<a href=\""+innerLinkurl+"\" title=\""+name+"\" target=\"_blank\">"+name+"</a>");
			}
			findbytieziid.put("contenthtml", contenthtml);
		}
		
		map.put("tiezi", findbytieziid);
		map.put("findgroup", findbytieziid.get("findgroup"));
		map.put("fatiegroup", findbytieziid.get("fatiegroup"));
		map.put("huifugroup", findbytieziid.get("huifugroup"));
		map.put("createuserid", findbytieziid.get("createuserid"));
		map.put("contenttxt", findbytieziid.get("contenttxt"));
		map.put("name", findbytieziid.get("name"));
		map.put("bankuai_id", findbytieziid.get("bankuai_id"));
		map.put("id", findbytieziid.get("id"));
		return map;
	}


	public void updatefindcount(int id) {
		tieziDao.updatefindcount(id);
	}
	
	
	public Tiezi findbytieziwhere(Tiezi tiezi){
		return tieziDao.findbytieziwhere(tiezi);
	}
	
	public List<Tiezi> select(Tiezi tiezi) {
		List<Tiezi> find = tieziDao.select(tiezi);
		return find;
	}
	
	public List<Tiezi> equalstiezi(Map<String, Object> tiezi) {
		List<Tiezi> find = tieziDao.equalstiezi(tiezi);
		return find;
	}
	
	public List<Tiezi> nextandlast(Map<String, Object> tiezi) {
		List<Tiezi> find = tieziDao.nextandlast(tiezi);
		return find;
//		return new ArrayList<Tiezi>();
	}
	
}
