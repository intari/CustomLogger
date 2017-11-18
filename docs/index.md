.. CustomLogger documentation master file, created by
   sphinx-quickstart on Sat Nov 18 13:53:58 2017.
   You can adapt this file completely to your liking, but it should at least
   contain the root `toctree` directive.

Welcome to CustomLogger's documentation!
========================================

.. toctree::
   :maxdepth: 2
   :caption: Contents:

Helper functions to use NSLogger with Android Apps


Indices and tables
==================

* :ref:`genindex`
* :ref:`modindex`
* :ref:`search`



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
 CustomLog.setIsDebug(BuildConfig.DEBUG);//do we also need logcat?
 CustomLog.setLogDestination(LOG_HOST,LOG_PORT);//host where NSLogger is running
 CustomLog.setContext(this); // use app's context

 ..
 CustomLog.e(TAG,"Message");
 CustomLog.w(TAG,"Message");
 CustomLog.i(TAG,"Message");
 CustomLog.v(TAG,"Message");
 CustomLog.l(TAG,"Message");
     
 CustomLog.logException(ex);//ex is Exception or Throwable
 CustomLog.logException(TAG,ex);//ex is Exception or Throwable
 CustomLog.logMark(mark);
...

```

Client app - get it from https://github.com/fpillet/NSLogger

See AndroidToolbox for Kotlin Extensions
