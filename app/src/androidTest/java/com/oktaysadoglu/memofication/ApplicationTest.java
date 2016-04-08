package com.oktaysadoglu.memofication;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    protected void runTest() throws Throwable {
        super.runTest();


        int a = 5;

        int b =4;

        assertEquals(a,b);
    }
}