package utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import baseClass.BaseClass;

public class Listeners implements ITestListener{
	
	public ExtentSparkReporter spark;
	public ExtentReports extent;
	public ExtentTest test;
	
	String fileName;

	
	@Override
	public void onStart(ITestContext context) {
		String timeStamp=new SimpleDateFormat("dd.MM.yyyy.hh.mm.ss").format(new Date());
		fileName="myReport"+timeStamp+".html";
		
		spark=new ExtentSparkReporter(System.getProperty("user.dir")+"\\reports\\"+fileName);
		spark.config().setDocumentTitle("QA automation report");
		spark.config().setReportName("Functional test");
		spark.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Env", "QA");
		extent.setSystemInfo("OS", "Windows10");
		extent.setSystemInfo("browser", "chrome");
		
		
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
	test=extent.createTest(result.getName());
	test.log(Status.PASS,"this is pass"+ result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getName());
		test.log(Status.FAIL,"failed"+result.getName());
		test.log(Status.INFO,"reason of fail"+ result.getThrowable());
		try {
			String imgPath=new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getName());
		test.log(Status.SKIP,"skipped" + result.getName());
		test.log(Status.INFO,"reason"+ result.getThrowable());
	}

	

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
	
	

}
