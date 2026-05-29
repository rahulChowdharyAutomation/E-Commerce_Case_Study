package qa.util;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class testRetryLogic implements IRetryAnalyzer {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 10;

	private int count = 0;
	private final int retryCount = 1;

	@Override
	public boolean retry(ITestResult result) {
		if (count < retryCount) {
			count++;
			return true;
		}
		return false;
	}

}
