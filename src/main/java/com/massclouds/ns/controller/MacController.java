package com.massclouds.ns.controller;

import javax.enterprise.inject.New;

import org.aspectj.weaver.ast.Var;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tempuri1.FRSServiceStub.MacHistoryInfo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.massclouds.ns.util.WebServiceServe;

/**
 * mac相关控制器
 * @author xielipeng
 * @date 2016年07月18日
 */

@Controller
@RequestMapping("/mac")
public class MacController {

	// 摄像机列表(按用户)
	@RequestMapping(value = "/searchMac", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String searchMac(@RequestParam String mac,
			@RequestParam String dateFrm, @RequestParam String dateTo,
			@RequestParam String place,@RequestParam Integer pnum, @RequestParam Integer psize) {
		try {
			
			MacHistoryInfo[] mhArr = WebServiceServe.getObj()
					.GetMacHistoryInfo(mac, dateFrm, dateTo, place);
			JSONObject jb = new JSONObject();
			jb.put("total",mhArr.length);
			
			int size;
			if(psize>mhArr.length){
				size = mhArr.length;
			}else{
				size = psize;
			}
			MacHistoryInfo[] newArr = new MacHistoryInfo[size];
			
			for(int i = (pnum-1)*psize,j = 0 ;i<(pnum-1)*psize+psize&&i<mhArr.length&&j<psize;i++,j++){
				newArr[j] = mhArr[i];
			}
			
			JSONArray ja = (JSONArray) JSON.toJSON(newArr);
			jb.put("rows", ja);

			return jb.toJSONString();
		} catch (Exception e) {
//			e.printStackTrace();
			JSONObject jb = new JSONObject();
			jb.put("total",0);
			jb.put("rows",new JSONArray());
			return jb.toJSONString();
		}

	}

}
