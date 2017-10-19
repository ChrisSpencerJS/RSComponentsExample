package com.example;

import cucumber.api.junit.*;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@Cucumber.Options(features={"src/test/resources"},tags = {"@run"})
public class CukesRunner {}
