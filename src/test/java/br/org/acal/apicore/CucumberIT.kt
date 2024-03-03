package br.org.acal.apicore

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(plugin = ["pretty"], features = ["src/test/resources/features"], glue = ["br.org.acal.apicore.steps"])
class CucumberIT
