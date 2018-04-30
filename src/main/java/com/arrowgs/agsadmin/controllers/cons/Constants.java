package com.arrowgs.agsadmin.controllers.cons;

public interface Constants {

	static interface Views{
		static final String Root	= "/";
	}
	
	static interface Mappings{
		//static final String Login		= "/login";
		static final String Logout		= "/logout";
		
		
		// /admin
		static final String rootadmin			= "";
		
		//admins mapings
		static final String adminLogin			= rootadmin+"/login";
		static final String adminCreateU 		= rootadmin+"/createU";
		static final String adminModifyU 		= rootadmin+"/modifyU";
		static final String adminDeleteU 		= rootadmin+"/deleteU";
		static final String adminMain 			= rootadmin+"/main";
		static final String adminHome 			= rootadmin+"/home";
		static final String adminUsers 			= rootadmin+"/users";
		static final String adminItems 			= rootadmin+"/items";
		static final String adminReports 		= rootadmin+"/reports";
		static final String adminSales			= rootadmin+"/sales";
		static final String adminTopFive		= rootadmin+"/top-five";
		static final String orders				= rootadmin+"/orders";
		static final String order				= rootadmin+"/order";
		static final String config				= rootadmin+"/config";
		
	}
	
	static interface ApiMappings{
		static final String ServiceContext		= "/rest";
		
		//User
		static final String UsersFilter		  = ServiceContext + "/users/filter";
		static final String UsersFilterCount  = ServiceContext + "/users/filter-count";
		static final String UsersRol	  	  = ServiceContext + "/users/rol";
		
		//Products
		static final String Product			  		= ServiceContext + "/product";
		static final String ProductInfo				= ServiceContext + "/product-info";
		static final String ProductSku				= ServiceContext + "/product_sku";
		static final String ProductDetail	  		= ServiceContext + "/product_detail";	
		static final String ProductByFilter	  		= ServiceContext + "/product_filter";
		static final String ProductCountFilter		= ServiceContext + "/product_filter/count";
		static final String ProductSizes	  		= ServiceContext + "/product_sizes";
		static final String ProductSizesDescription = ProductSizes 	 + "/description";
		static final String Departments				= ServiceContext + "/departamentos";
		
		//Report
		static final String ReportSchema			= ServiceContext + "/reports";
		static final String SalesReportConstructor2	= ServiceContext + "/sales2";
		static final String SalesReportConstructor	= ServiceContext + "/sales";
		static final String TopFive					= ServiceContext + "/top-five";
		
		//Message
		static final String MessageProperties		= ServiceContext + "/messages";
		
		
		//Order
		static final String UpdateState		  	   = ServiceContext + "/update_status";
		static final String	OrdersByFilter		   = ServiceContext + "/orders-filter";
		static final String GetOrderCountByFilter  = ServiceContext + "/get_count_order/filter";
		static final String StatusOrder			   = ServiceContext + "/status";
		static final String OrderDetail			   = ServiceContext + "/order-detail";
		static final String OrderContact		   = ServiceContext + "/order-contact";
		
		//Config
		static final String Config				   = ServiceContext + "/basic-config";
		
		//Address Maping
		static final String Address 		  = ServiceContext + "/address";
		static final String AllAddress		  = ServiceContext + "/address/user/all";
		static final String UserAddress		  = ServiceContext + "/address/user";
		static final String Countries		  = ServiceContext + "/country";
	}
}
