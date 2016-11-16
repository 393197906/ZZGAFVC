package com.massclouds.ns.controller;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.massclouds.ns.common.Constants;
import com.massclouds.ns.model.PoliceResponseModel;
import com.massclouds.ns.service.CameraPlaceService;
import com.massclouds.ns.service.PeopleBaseService;
import com.massclouds.ns.service.PoliceCompanyService;
import com.massclouds.ns.service.PoliceResponseService;
import com.massclouds.ns.util.DateUtil;
import com.massclouds.ns.util.WebServiceServe;

/**
 * 公安相关控制器
 * 
 * @author xielipeng
 * @date 2016年06月29日
 */

@Controller
@RequestMapping("/police")
public class policeController {

	@Autowired
	private PoliceCompanyService policeCompanyBase;

	@Autowired
	private PoliceResponseService policeResponseBase;

	// @Autowired
	// private PolicemanService policemanBase;

	// 公安单位列表
	@RequestMapping(value = "/companyList", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
	@ResponseBody
	public String companyList() {
		return policeCompanyBase.companyList();
	}

	// 取得警员列表
	@RequestMapping(value = "/policemanListByCompany", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
	@ResponseBody
	public String policemanListByCompany(@RequestParam Integer cid) {
		return policeCompanyBase.policemanListByCompany(cid);
	}

	// 存审核单
	@RequestMapping(value = "/addNoResponse", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
	@ResponseBody
	public String addNoResponse(@RequestParam Integer hitId,@RequestParam String hitTime,
			@RequestParam Integer auditPersonId, @RequestParam String auditTime) {
		
		PoliceResponseModel prm = new PoliceResponseModel();
		prm.setHitId(hitId);
		prm.setHitTime(Long.parseLong(DateUtil.date2TimeStamp(hitTime,"yyyy/MM/dd HH:mm:ss")));
		prm.setAuditPolicemanId(auditPersonId);
		prm.setAuditTime(auditTime);
		return policeResponseBase.savePoliceResponse(prm);
	}

	// 存出警单
	@RequestMapping(value = "/addResponse", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
	@ResponseBody
	public String addResponse(@RequestParam Integer hitId,@RequestParam String hitTime,
			@RequestParam Integer auditPersonId,
			@RequestParam String auditTime,
			@RequestParam Integer pResCompanyId,
			@RequestParam Integer pResPersonId, @RequestParam String pResTime,
			@RequestParam String pResReason, @RequestParam String pResResult) {
		PoliceResponseModel prm = new PoliceResponseModel();
		prm.setHitId(hitId);
		prm.setHitTime(Long.parseLong(DateUtil.date2TimeStamp(hitTime,"yyyy/MM/dd HH:mm:ss")));
		prm.setAuditPolicemanId(auditPersonId);
		prm.setAuditTime(auditTime);
		prm.setResponseCompanyId(pResCompanyId);
		prm.setResponsePolicemanId(pResPersonId);
		prm.setResponseTime(pResTime);
		prm.setResponseReason(pResReason);
		prm.setResponseResult(pResResult);
		return policeResponseBase.savePoliceResponse(prm);
	}

	// 查出警单（by hitid）
	@RequestMapping(value = "/getPoliceResponseByHitId", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
	@ResponseBody
	public String getPoliceResponseByHitId(@RequestParam Integer hitId) {

		return policeResponseBase.getPoliceResponseByHitId(hitId);
	}
	
	// 研判信息（三天）
		@RequestMapping(value = "/getResponseIndex", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
		@ResponseBody
		public String getPoliceResponseIndex() {
			Date date = new Date();
			String todayTo = new SimpleDateFormat(Constants.FORMAT_DATE_YMDHMS)
					.format(date);
			String todayFrom = new SimpleDateFormat("yyyy-MM-dd").format(date)
					+ " 00:00:00";
			String timeString = DateUtil.date2TimeStamp(todayFrom,
					Constants.FORMAT_DATE_YMDHMS);
			String yesterdayFrom = DateUtil.timeStamp2Date(
					(Long.parseLong(timeString) - 24 * 3600) + "",
					Constants.FORMAT_DATE_YMDHMS);
			String threedayFrom = DateUtil.timeStamp2Date(
					(Long.parseLong(timeString) - 2 * 24 * 3600) + "",
					Constants.FORMAT_DATE_YMDHMS);
			
			JSONObject today = policeResponseBase.getAuditTotalAndNotAuditTotalByTimeZones(todayFrom, todayTo);
			JSONObject yesterday = policeResponseBase.getAuditTotalAndNotAuditTotalByTimeZones(yesterdayFrom,todayFrom);
			JSONObject beforeYesterday = policeResponseBase.getAuditTotalAndNotAuditTotalByTimeZones(threedayFrom,yesterdayFrom);
			
			JSONObject jb = new JSONObject();
			jb.put("today",today);
			jb.put("yesterday",yesterday);
			jb.put("beforeYesterday",beforeYesterday);
			
			return jb.toJSONString();
			
		}

}
