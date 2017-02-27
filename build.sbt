
name := "commercetools-sunrise-java-training"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.11.8"

val sunriseFrameworkVersion = "1.0.0-M1-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayJava, SunriseThemeImporterPlugin)
  .configs(IntegrationTest, TestCommon.PlayTest)
  .settings(TestCommon.defaultSettings)
  .settings(sunriseThemeJarName in Compile := "commercetools-sunrise-theme")

resolvers ++= Seq (
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"),
  Resolver.bintrayRepo("commercetools", "maven"),
  Resolver.mavenLocal
)

libraryDependencies ++= Seq(
  cache,
  javaWs,
  "com.commercetools.sunrise" %% "product-catalog" % sunriseFrameworkVersion,
  "com.commercetools.sunrise" %% "shopping-cart" % sunriseFrameworkVersion,
  "com.commercetools.sunrise" %% "my-account" % sunriseFrameworkVersion,
  "com.commercetools.sunrise" %% "test-lib" % sunriseFrameworkVersion % "test,it,pt",
  "com.commercetools.sunrise" % "commercetools-sunrise-theme" % "0.64.0",
  "org.webjars" %% "webjars-play" % "2.5.0-2"
)
