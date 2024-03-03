package br.org.acal.apicore

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
    features = ["classpath:features"],
    glue = ["classpath:steps"],
    plugin = ["pretty", "html:target/cucumber-html-report"]
)
class CucumberIT