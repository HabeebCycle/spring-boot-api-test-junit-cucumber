package com.habeebcycle.unitintegrationtest.integration;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/boxbasics",
        plugin = {"pretty", "html:target/cucumber/bagbasics"}, extraGlue = "")
public class BoxIntegrationTest {
}
