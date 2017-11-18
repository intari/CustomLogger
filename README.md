# CustomLogger
CustomLogger wrapper library for NSLogger

[![Twitter](https://img.shields.io/twitter/url/https/github.com/intari/CustomLogger.svg?style=social)](https://twitter.com/intent/tweet?text=Wow:&url=https%3A%2F%2Fgithub.com%2Fintari%2FCustomLogger)

[![GitHub license](https://img.shields.io/github/license/intari/CustomLogger.svg)](https://github.com/intari/CustomLogger/blob/master/LICENSE)
[![Release](https://jitpack.io/v/net.intari/CustomLogger.svg)](https://jitpack.io/#net.intari/CustomLogger)
<a href='https://travis-ci.org/intari/CustomLogger/builds'><img src='https://api.travis-ci.org/intari/CustomLogger.svg?branch=master'></a>
[![GitHub issues](https://img.shields.io/github/issues/intari/CustomLogger.svg)](https://github.com/intari/CustomLogger/issues)
[![GitHub forks](https://img.shields.io/github/forks/intari/CustomLogger.svg)](https://github.com/intari/CustomLogger/network)
[![GitHub stars](https://img.shields.io/github/stars/intari/CustomLogger.svg)](https://github.com/intari/CustomLogger/stargazers)

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

