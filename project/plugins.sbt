addSbtPlugin("com.lightbend.cinnamon" % "sbt-cinnamon" % "2.9.4-20180803-3d11d80")

credentials += Credentials(Path.userHome / ".lightbend" / "commercial.credentials")

resolvers += "com-mvn" at "https://repo.lightbend.com/commercial-releases/"

resolvers += Resolver.url("com-ivy", url("https://repo.lightbend.com/commercial-releases/"))(Resolver.ivyStylePatterns)
