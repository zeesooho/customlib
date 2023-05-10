# customlib
https://jitpack.io/#zeesooho/customlib

old version
build.gradle(Project: ...)
```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
latest version
setting.gradle(Project Settings)
```
dependencyResolutionManagement {
  repositories {
  ...
    maven { url 'https://jitpack.io' }
  }
}
```
 
 
build.gradle(Module: app)
```
dependencies {
  implementation 'com.github.User:Repo:Tag'
}
```
