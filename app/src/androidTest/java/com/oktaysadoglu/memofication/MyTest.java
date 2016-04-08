package com.oktaysadoglu.memofication;

import android.test.AndroidTestCase;

/**
 * Created by oktaysadoglu on 16/02/16.
 */
public class MyTest extends AndroidTestCase{

    @Override
    public void testAndroidTestCaseSetupProperly() {
        super.testAndroidTestCaseSetupProperly();

        int a = 4;

        int b = 5;

        assertEquals(a,b);

    }
}
