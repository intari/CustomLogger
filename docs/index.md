.. CustomLogger documentation master file, created by
   sphinx-quickstart on Sat Nov 18 13:53:58 2017.
   
   
Welcome to CustomLogger's documentation!
========================================


Helper functions to use NSLogger with Android Apps





Usage:
======

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


Call as:
======

```java
 import net.intari.CustomLogger.CustomLog;
...
 CustomLog.setIsDebug(BuildConfig.DEBUG);//do we also need logcat? not needed if crashlytics connection is active
 CustomLog.setUseCrashlytics(false);//do we need to use Crashlytics (or true if you configured it). If you don't this - crashlytics connection will not work
 CustomLog.setLogDestination(LOG_HOST,LOG_PORT);//host where NSLogger is running
 //MUST be called before using any function which send data to logger
 CustomLog.setContext(this); // use app's context - if you don't do this - NSLogger connection will not work. if IsDebug==true and setContext was not called - it will use logcat only

 ..
 CustomLog.e(TAG,"Message");
 CustomLog.w(TAG,"Message");
 CustomLog.i(TAG,"Message");
 CustomLog.v(TAG,"Message");
 CustomLog.l(TAG,"Message");
 CustomLog.setUserIdentifier("443r3tfvxLeila#17");
 CustomLog.setUserEmail("queen@palace.nabu.galaxyfarfaraway");
 CustomLog.setUserName("Princess Leia");
 CustomLog.setInt("UserAge",17);
 CustomLog.setString("Title","Queen of Nabu");
     
 CustomLog.logException(ex);//ex is Exception or Throwable
 CustomLog.logException(TAG,ex);//ex is Exception or Throwable
 CustomLog.logMark(mark);
...

```

Client app - get it from https://github.com/fpillet/NSLogger

See AndroidToolboxCore for Kotlin Extensions
