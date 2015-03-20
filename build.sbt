name := "Example project serving sbt-web output from within SBT"

organization in ThisBuild := "com.martinsnyder"

version in ThisBuild := "0.0.1"

scalaVersion in ThisBuild := "2.11.6"

lazy val example_webjar =
  project
    .in(file("example_webjar"))
    .settings(libraryDependencies ++= Seq("org.webjars" % "requirejs" % "2.1.16"))
    .enablePlugins(SbtWeb)

lazy val example_webapp =
  project
    .in(file("example_webapp"))
    .dependsOn(example_webjar)
    .settings(libraryDependencies ++= Seq(
      "javax.servlet" % "servlet-api" % "2.5" % "provided",
      "org.eclipse.jetty" % "jetty-webapp" % "9.3.0.M2" % "container",
      "org.eclipse.jetty" % "jetty-plus"   % "9.3.0.M2" % "container",
      "commons-logging" % "commons-logging" % "1.2" % "container"
    ))
    .enablePlugins(SbtWeb)
    .settings(jetty(): _*)
    .settings(webappSrc in webapp <<= WebKeys.assets in Assets)
