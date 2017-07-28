name := "msgapp"

organization := "com.github.chenmawson"

version := "0.0.1"

scalaVersion := "2.11.11"

libraryDependencies ++= List(
    "org.seleniumhq.selenium" % "selenium-server" % "3.4.0",
    "junit" % "junit" % "4.12" % "test",
    "com.novocode" % "junit-interface" % "0.10" % "test"
)

testOptions += Tests.Argument(TestFrameworks.JUnit)

