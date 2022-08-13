package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import base.BaseUtility;

public class Listener extends BaseUtility implements ITestListener {

	public void onTestSuccess(ITestResult result) {
		System.out.println(result.getName());
	}

	public void onTestFailure(ITestResult result) {
		screenshots();
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println(result.getName());
	}

}
