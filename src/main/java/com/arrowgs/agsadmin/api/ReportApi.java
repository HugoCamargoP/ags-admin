package com.arrowgs.agsadmin.api;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arrowgs.agsadmin.entities.Report;
import com.arrowgs.agsadmin.controllers.cons.Constants.ApiMappings;

@RestController
public class ReportApi {


	@Resource
	List<Report> reports;
	
	@RequestMapping(path = ApiMappings.ReportSchema, method = RequestMethod.GET)
	public List<Report> getReports(){
		return reports;
	}
}
