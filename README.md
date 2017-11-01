# CustomLogger
CustomLogger wrapper library for NSLogger

[![Release](https://jitpack.io/v/net.intari/CustomLogger.svg)](https://jitpack.io/#net.intari/CustomLogger)

<a href='https://travis-ci.org/intari/CustomLogger/builds'><img src='https://api.travis-ci.org/intari/CustomLogger.svg?branch=master'></a>

Usage:

Add it to your build.gradle with:
```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```
and:

```gradle
dependencies {
    compile 'net.intari:CustomLogger:{latest version}'
}
```


Call
```java
 import net.intari.CustomLogger.CustomLog;
...
 CustomLog.setIsDebug(BuildConfig.DEBUG);//do we also need logcat?
 CustomLog.setLogDestination(LOG_HOST,LOG_PORT);//host where NSLogger is running
 CustomLog.setContext(this); // use app's context

 ..
 CustomLog.v(TAG,"Message");
 CustomLog.logException(ex);
```

Client app - get it from https://github.com/fpillet/NSLogger

