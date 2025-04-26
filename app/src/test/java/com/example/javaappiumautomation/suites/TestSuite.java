package com.example.javaappiumautomation.suites;

import com.example.javaappiumautomation.tests.ArticleTests;
import com.example.javaappiumautomation.tests.ChangeAppConditionTests;
import com.example.javaappiumautomation.tests.GetStartedTest;
import com.example.javaappiumautomation.tests.MyListsTests;
import com.example.javaappiumautomation.tests.OnboardingTests;
import com.example.javaappiumautomation.tests.SearchTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArticleTests.class,
        ChangeAppConditionTests.class,
        GetStartedTest.class,
        MyListsTests.class,
        OnboardingTests.class,
        SearchTests.class
})
public class TestSuite {

}
