package com.auto.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

//创建一个自定义监听器，在测试执行期间存储上下文
public class ContextListener implements ITestListener {
    private static ITestContext testContext;

    @Override
    public void onStart(ITestContext context) {
        // 存储上下文
        testContext = context;
    }

    // 获取存储的上下文
    public static ITestContext getContext() {
        return testContext;
    }

    // 其他监听器方法...
    @Override public void onTestStart(ITestResult result) {}
    @Override public void onTestSuccess(ITestResult result) {}
    @Override public void onTestFailure(ITestResult result) {}
    @Override public void onTestSkipped(ITestResult result) {}
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onFinish(ITestContext context) {}
}