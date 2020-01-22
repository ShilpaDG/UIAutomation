package com.hellofresh.factory;

import com.hellofresh.dataprovider.ConfigDataProvider;
import com.hellofresh.dataprovider.ExcelDataProvider;

public class DataProviderFactory {
	
	public static ConfigDataProvider getCofig() {
		
		ConfigDataProvider config = new ConfigDataProvider();
		
		return config;
	}
	
public static ExcelDataProvider getExcelconfig() {
		
	ExcelDataProvider getExcelconfig = new ExcelDataProvider();
		
		return getExcelconfig;
	}

}
