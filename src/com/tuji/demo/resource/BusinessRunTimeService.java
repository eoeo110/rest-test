package com.tuji.demo.resource;



public class BusinessRunTimeService {
	private BusinessRunTimeService() {
    }

    public static BusinessRunTimeService getInstance() {
            return service;
    }
   
    
    
    private static BusinessRunTimeService service = new BusinessRunTimeService();
}
