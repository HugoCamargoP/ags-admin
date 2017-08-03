package com.arrowgs.agsadmin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arrowgs.agsadmin.controllers.cons.Constants.Mappings;
import com.arrowgs.agsadmin.controllers.cons.Constants.Views;

@Controller
@RequestMapping(Views.Root)
public class RootController {

	@RequestMapping(path={Mappings.Logout,
	                      Mappings.adminLogin,
	                      Mappings.adminHome,
	                      Mappings.adminMain,
	                      Mappings.adminCreateU,
	                      Mappings.adminUsers,
	                      Mappings.adminModifyU})
	public void show(){
		
	}

}
