package DriverFactory;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunLib.FunctionLibrary;
import Utilities.ExcelFileUtil;
import Utilities.ScreenShot;

public class DriverScript {
	WebDriver driver;
    ExtentReports report;
    ExtentTest test;
	
	public void startTest() throws Throwable
	{
		// creating reference object for excel util methods to reuse the objects that contains
		
		
	ExcelFileUtil excel= new ExcelFileUtil();
	// iterating all the row in master test cases  excel sheet
	for (int i=1; i<=excel.rowCount("MasterTestCases");i++)
	{
		// to store ture or false we are creating Modulestatus 
		String ModuleStatus= "";
		if(excel.getData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
			{
			
			
			// storing module name into TCModule
			// report statements are given to generate reports 
			 
			 String  TCModule=excel.getData("MasterTestCases", i,1 );
			 report= new ExtentReports("./Reports/"+TCModule+FunctionLibrary.generateDate()+".html");
			 
			 // iterate all rows in Tcmodule Sheet
			 
			 for (int j=1; j<=excel.rowCount(TCModule);j++)
			 {
				 //  test is used to create the log files
				 test=report.startTest(TCModule);
				 
				 // reading ALL THEE columns in TcModule TC 
				 String Description =excel.getData(TCModule, j, 0);
				 String Object_Type =excel.getData(TCModule, j, 1);
				 String Locator_Type =excel.getData(TCModule, j, 2);
				 String Locator_value =excel.getData(TCModule, j, 3);
				 String Test_Data =excel.getData(TCModule, j, 4);
				 // calling methods from Functions Library 
				 try
				 {
					 if(Object_Type.equalsIgnoreCase("startBrowser"))
					 {
						 driver = FunctionLibrary.startBrowser(driver);
						System.out.println("Executing start Browser");
						 test.log(LogStatus.INFO,Description);
					 }else if(Object_Type.equalsIgnoreCase("openApplication"))
					 {
						 FunctionLibrary.openApplication(driver);
						 test.log(LogStatus.INFO,Description);
						 System.out.println("Executing open Application");
						 
					 }
					 else if(Object_Type.equalsIgnoreCase("waitForElement"))
					 {
						 FunctionLibrary.waitForElement(driver, Locator_Type, Locator_value, Test_Data);
						 test.log(LogStatus.INFO,Description);
						 System.out.println("Executing waitForElement");
						 					 
				 }
					 else if(Object_Type.equalsIgnoreCase("typeAction"))
					 {
						 FunctionLibrary.typeAction(driver, Locator_Type, Locator_value, Test_Data);
						 System.out.println("Executing typeAction");
						 					 
				 }
					 else if(Object_Type.equalsIgnoreCase("clickAction"))
					 {
						 FunctionLibrary.clickAction(driver, Locator_Type, Locator_value);
						 System.out.println("Executing typeAction");
						 						 					 
				 }
					 else if(Object_Type.equalsIgnoreCase("closeBrowser"))
					 {
						 FunctionLibrary.closeBrowser(driver);
						 System.out.println("Executing closeBrowser");
												 					 
			          }
					 
					 else if(Object_Type.equalsIgnoreCase("captureData"))
					 {
						 FunctionLibrary.captureData(driver, Locator_Type, Locator_value);
						 test.log(LogStatus.INFO, Description);
						 }
					 
					 else if(Object_Type.equalsIgnoreCase("tableValidation"))
					 {
						 
						 FunctionLibrary.tableValidation(driver, Test_Data);
						 test.log(LogStatus.INFO,Description);
					 }
					 else if(Object_Type.equalsIgnoreCase("stockCategories"))
					 {
						 FunctionLibrary.stockCategories(driver);
						 System.out.println("Executing stockCategories");
						 test.log(LogStatus.INFO,Description);
					 }
					 
					 else if(Object_Type.equalsIgnoreCase("tableValidation1"))
					 {
						FunctionLibrary. tableValidation1(driver, Test_Data);
						System.out.println("Executing Tablevalidation 1");
						// here description is used to proint all tyhe steps of execution
						test.log(LogStatus.INFO,Description);
					 }
					 
					 
					 
					 
					 
					 
					 // write as pass into statuscolumn
					 excel.setCellData(TCModule, j, 5, "PASS");
					 test.log(LogStatus.PASS,Description);
					 ScreenShot.TakesScreen(driver,Description);
					 ModuleStatus ="true";
					 
			    					 
			 } catch (Exception e)
				 {
				 excel.setCellData(TCModule, j, 5, "FALSE");
				 test.log(LogStatus.FAIL,Description);
				 ScreenShot.TakesScreen(driver,Description);
				 ModuleStatus ="fail";
				 System.out.println(e.getMessage());
				 break;
				 }
				 if(ModuleStatus.equalsIgnoreCase("TRUE"))
				 {
					 excel.setCellData("MasterTestCases", i, 3, "pass");
				 }
				 else if(ModuleStatus.equalsIgnoreCase("FALSE"))
				 {
					 excel.setCellData("MasterTestCases", i, 3, "fail");
				 }
			
		
		}
			 report.flush(); // to push the html reports
			 report.endTest(test);
		
	}
		
		else
		{
			// write as not executed in status column for flag N
			excel.setCellData("MasterTestCases", i, 3, "NotExecuted");
		}
	
	}
	
		
	}
}
	


