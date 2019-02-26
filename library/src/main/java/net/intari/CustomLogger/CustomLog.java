package net.intari.CustomLogger;

import android.content.Context;
import android.util.Log;

import com.crashlytics.android.Crashlytics;

import org.apache.commons.text.StringEscapeUtils;


/**
 * (c) Dmitriy Kazimirov, 2015-2018, e-mail:dmitriy.kazimirov@viorsan.com
 * This is helper class for usage with Crashlytics
 * see https://docs.fabric.io/android/crashlytics/enhanced-reports.html#custom-logging
 * and with NSLogger (based on https://github.com/fpillet/NSLogger/tree/master/Client%20Logger/Android )
 *
 */
public class CustomLog {
    public static final String TAG = CustomLog.class.getSimpleName();
    public static final String NOTAG = "NoTag";

    private static String logHost=null;
    private static int logPort = 0;
    private static boolean isDebug=false;

    private static boolean logCrashlytics=false;
    private static boolean logExceptions=true;

    private static boolean automaticUnescape=false;

    public static String unescapeString(String source) {
        return StringEscapeUtils.unescapeJava(source);
    }
    //Log levels (to sync with iOS version and NSLogger)
    private static final int LOGLEVEL_ERROR=0;         //Error produced. Criticality doesn't matter. It's enough we knew it's error
    private static final int LOGLEVEL_WARN=1;         // Something strange happens. May lead to error eventuallyy
    private static final int LOGLEVEL_INFO=2;         // Normal operational information
    private static final int LOGLEVEL_TRACE=3;        // Internal operations, debugging purposes, aka LOGLEVEL_DEBUG
    private static final int LOGLEVEL_VERBOSE=4;      // Large amount of information like full dumps
    private static final int LOGLEVEL_VERY_VERBOSE=5; // Very large amount of information

    /*
     Android use inverted priority levels.
     They are good but we are constrained by existing NSLogger.app code and iOS counterpart of this library
     public static final int VERBOSE = 2;
     public static final int DEBUG = 3;
     public static final int INFO = 4;
     public static final int WARN = 5;
     public static final int ERROR = 6;
     public static final int ASSERT = 7;
     */


    private CustomLog() {
    }

    /**
     * Support for https://docs.fabric.io/android/crashlytics/enhanced-reports.html#user-information
     * @param userEmail
     */
    public static void setUserEmail(String userEmail) {
        if (logCrashlytics) {
            Crashlytics.setUserEmail(userEmail);
        }
        v(TAG,"Setting user e-mail to "+userEmail);
    }

    /**
     * Support for https://docs.fabric.io/android/crashlytics/enhanced-reports.html#user-information
     * @param userName
     */
    public static void setUserName(String userName) {
        if (logCrashlytics) {
            Crashlytics.setUserName(userName);
        }
        v(TAG,"Setting user name to "+userName);
    }

    /**
     * Support for https://docs.fabric.io/android/crashlytics/enhanced-reports.html#user-information
     * @param userIdentifier
     */
    public static void setUserIdentifier(String userIdentifier) {
        if (logCrashlytics) {
            Crashlytics.setUserIdentifier(userIdentifier);
        }
        v(TAG,"Setting user identifier to "+userIdentifier);

    }

    /**
     * Support for https://docs.fabric.io/android/crashlytics/enhanced-reports.html#custom-keys
     * Crashlytics supports a maximum of 64 key/value pairs.
     * Once you reach this threshold, additional values are not saved. Each key/value pair can be up to 1 KB in size.
     * @param key
     * @param value
     */
    public static void setBool(String key, boolean value) {
        if (logCrashlytics) {
            Crashlytics.setBool(key,value);
        }
        v(TAG,"Setting "+key+" to "+value);
    }

    /**
     * Support for https://docs.fabric.io/android/crashlytics/enhanced-reports.html#custom-keys
     * Crashlytics supports a maximum of 64 key/value pairs.
     * Once you reach this threshold, additional values are not saved. Each key/value pair can be up to 1 KB in size.
     * @param key
     * @param value
     */
    public static void setDouble(String key, double value) {
        if (logCrashlytics) {
            Crashlytics.setDouble(key,value);
        }
        v(TAG,"Setting "+key+" to "+value);
    }

    /**
     * Support for https://docs.fabric.io/android/crashlytics/enhanced-reports.html#custom-keys
     * Crashlytics supports a maximum of 64 key/value pairs.
     * Once you reach this threshold, additional values are not saved. Each key/value pair can be up to 1 KB in size.
     * @param key
     * @param value
     */
    public static void setFloat(String key, float value) {
        if (logCrashlytics) {
            Crashlytics.setFloat(key,value);
        }
        v(TAG,"Setting "+key+" to "+value);
    }

    /**
     * Support for https://docs.fabric.io/android/crashlytics/enhanced-reports.html#custom-keys
     * Crashlytics supports a maximum of 64 key/value pairs.
     * Once you reach this threshold, additional values are not saved. Each key/value pair can be up to 1 KB in size.
     * @param key
     * @param value
     */
    public static void setInt(String key, int value) {
        if (logCrashlytics) {
            Crashlytics.setInt(key,value);
        }
        v(TAG,"Setting "+key+" to "+value);

    }

    /**
     * Support for https://docs.fabric.io/android/crashlytics/enhanced-reports.html#custom-keys
     * @param key
     * @param value
     */
    public static void setLong(String key, long value) {
        if (logCrashlytics) {
            Crashlytics.setLong(key,value);
        }
        v(TAG,"Setting "+key+" to "+value);

    }
    /**
     * Support for https://docs.fabric.io/android/crashlytics/enhanced-reports.html#custom-keys
     * @param key
     * @param value
     */
    public static void setString(String key, String value) {
        if (logCrashlytics) {
            Crashlytics.setString(key,value);
        }
        v(TAG,"Setting "+key+" to "+value);

    }

    /**
     * Where NSLogger.app is running
     * WARNING: This mean all application logs will be sent to this host.
     * It is not recommended to enable this in production builds.
     * SSL support depends on host (we try to use it..if possible)
     * This function can only called ONCE
     * @param newLogHost hostname like host.domain.com
     * @param newLogPort port number
     * @return false if this function was arleady called
     */
    public static boolean setLogDestination(String newLogHost,int newLogPort) {
        if (logHost==null) {
            logHost=newLogHost;
            logPort=newLogPort;
            return true;
        } else {
            Log.w(TAG,"Debug logger arleady initialized with  "+logHost+":"+logPort+", ignoring attempt to initialize with "+newLogHost+":"+newLogPort);
            return false;
        }
    }


    /**
     * Should unicode escapes uXXX be automatically converted to real unicode chars
     * False by default (was false in previous versions)
     * @param unescape
     */
    public static void setAutomaticUnescape(boolean unescape) {
        automaticUnescape=unescape;
    }
    /**
     * Should logException actually send logs to Crashlytics even if isLogCrashlytics is true?
     * Default is true
     * @param newLog
     */
    public static void setLogExceptions(boolean newLog) {
        logExceptions=newLog;
    }

    /**
     * Are should we use extra debugging features (like dump to logcat)
     * Use BuildConfig.DEBUG
     * If isDebug is true - logs also sent to logcat
     * @param newIsDebug
     */
    public static void setIsDebug(boolean newIsDebug) {
        isDebug=newIsDebug;
    }

    /**
     *
     * Should we use Crashlytics?
     * Support  https://docs.fabric.io/android/crashlytics/enhanced-reports.html#custom-logging
     * To make sure that sending crash reports has the smallest impact on your user’s devices,
     * Crashlytics logs have a maximum size of 64 KB. When a log exceeds 64 KB,
     * the earliest logged values will be dropped in order to maintain this threshold.
     * Please note that in current implementation Crashlytics will log to logcat automatically so setIsDebug doesn't make a lot of sense
     * @param newIsLogCrashlytics
     */
    public static void setLogCrashlytics(boolean newIsLogCrashlytics) {
        logCrashlytics=newIsLogCrashlytics;
    }

    /**
     * Setup necessary context and init connection with NSLogger
     * call all setX before this function
     * If you don't call it - no NSLogger support will be available
     * @param context
     */
    public static void setContext(Context context) {
        if (isDebug) {
            Debug.setup(context,true,false);//logging, no flushing
            Debug.L.setRemoteHost(logHost, logPort, true);
            Debug.L.LOG_MARK("Logger startup (debug). Will use "+logHost+":"+logPort);
            //logCrashlytics=false;//don't sent logs to crashlytics (especially crash logs, no need and I will get them anyway)
        }
        else {
            Debug.setup(context,true,false);//no logging, no flushing
            Debug.L.setRemoteHost(logHost, logPort, true);
            Debug.L.LOG_MARK("Logger startup (not debug). Will use "+logHost+":"+logPort);
        }
    }

    //just log message
    public static void l(String msg) {
        if (automaticUnescape) {
            msg=unescapeString(msg);
        }
        if (logCrashlytics) {
            Crashlytics.log(msg);
        }
        if (Debug.L!=null) {
            Debug.L.LOG_APP(LOGLEVEL_VERY_VERBOSE, msg);
        }
        if (isDebug) {
            Log.v(NOTAG,msg);
        }
    }
    public static void l(String tag,String msg) {
        if (automaticUnescape) {
            msg=unescapeString(msg);
        }
        if (logCrashlytics) {
            Crashlytics.log(msg);
        }
        if (Debug.L!=null) {
            Debug.L.taggedLog(LOGLEVEL_VERY_VERBOSE, tag, msg);
        }
        if (isDebug) {
            Log.v(tag,msg);
        }
    }
    //replacement for Log.v
    public static int v(String tag, String msg) {
        if (automaticUnescape) {
            msg=unescapeString(msg);
        }
        if (logCrashlytics) {
            Crashlytics.log(Log.VERBOSE, tag, msg);
        }
        if (Debug.L!=null) {
            Debug.L.taggedLog(LOGLEVEL_VERBOSE, tag, msg);
        }
        if (isDebug) {
            Log.v(tag,msg);
        }
        return 0;//Log.v(tag,msg);
    }
    //replacement for Log.e
    public static int e(String tag, String msg) {
        if (automaticUnescape) {
            msg=unescapeString(msg);
        }
        if (logCrashlytics) {
            Crashlytics.log(Log.ERROR, tag, msg);
        }
        if (Debug.L!=null) {
            Debug.L.taggedLog(LOGLEVEL_ERROR, tag, msg);
        }
        if (isDebug) {
            Log.e(tag,msg);
        }
        return 0;//Log.e(tag,msg);
    }
    //replacement for Log.w
    public static int w(String tag, String msg) {
        if (automaticUnescape) {
            msg=unescapeString(msg);
        }
        if (logCrashlytics) {
            Crashlytics.log(Log.WARN, tag, msg);
        }
        if (Debug.L!=null) {
            Debug.L.taggedLog(LOGLEVEL_WARN, tag, msg);
        }
        if (isDebug) {
            Log.w(tag,msg);
        }
        return 0;//Log.w(tag,msg);
    }
    //replacement for Log.i
    public static int i(String tag, String msg) {
        if (automaticUnescape) {
            msg=unescapeString(msg);
        }
        if (logCrashlytics) {
            Crashlytics.log(Log.INFO, tag, msg);
        }
        if (Debug.L!=null) {
            Debug.L.taggedLog(LOGLEVEL_INFO, tag, msg);
        }
        if (isDebug) {
            Log.i(tag,msg);
        }
        return 0;//Log.i(tag,msg);
    }
    //replacement for Log.d
    public static int d(String tag, String msg) {
        if (automaticUnescape) {
            msg=unescapeString(msg);
        }
        if (logCrashlytics) {
            Crashlytics.log(Log.DEBUG, tag, msg);
        }
        if (Debug.L!=null) {
            Debug.L.taggedLog(LOGLEVEL_TRACE, tag, msg);
        }
        if (isDebug) {
            Log.d(tag,msg);
        }
        return 0;//Log.d(tag,msg);
    }

    //replacement for "ex.printStackTrace" 'handling' of exceptions
    public static void logException(Exception ex) {
        if (Debug.L!=null) {
            Debug.L.LOG_EXCEPTION(ex);
        }
        if (logCrashlytics) {
            if (logExceptions) {
                Crashlytics.logException(ex);
            }
        }
        try {
            if (isDebug) {
                ex.printStackTrace();
            }
        } catch (StackOverflowError e) {
            CustomLog.e(TAG,"Failed to print stack trace");
        }
    }
    public static void logException(Throwable t) {
        if (Debug.L!=null) {
            Debug.L.LOG_THROWABLE(t);
        }
        if (logCrashlytics) {
            if (logExceptions) {
                Crashlytics.logException(t);
            }
        }
        try {
            if (isDebug) {
                t.printStackTrace();
            }
        } catch (StackOverflowError e) {
            CustomLog.e(TAG,"Failed to print stack trace");
        }
    }
    public static void logException(String tag,Exception ex) {
        if (Debug.L!=null) {
            Debug.L.LOG_EXCEPTION(tag,ex);
        }
        if (logCrashlytics) {
            if (logExceptions) {
                Crashlytics.logException(ex);
            }
        }
        try {
            if (isDebug) {
                ex.printStackTrace();
            }
        } catch (StackOverflowError e) {
            CustomLog.e(TAG,"Failed to print stack trace");
        }
    }
    public static void logException(String tag,Throwable t) {
        if (Debug.L!=null) {
            Debug.L.LOG_THROWABLE(tag,t);
        }
        if (logCrashlytics) {
            if (logExceptions) {
                Crashlytics.logException(t);
            }
        }
        try {
            if (isDebug) {
                t.printStackTrace();
            }
        } catch (StackOverflowError e) {
            CustomLog.e(TAG,"Failed to print stack trace");
        }
    }

    //Log image@debug level (makes no sense anyway
    public static void image(String tag,byte[] data) {
        if (Debug.L!=null) {
            Debug.L.taggedLogImageData(LOGLEVEL_INFO,tag, data);
        }

    }
    public static void logMark(String mark) {
        if (Debug.L!=null) {
            Debug.L.LOG_MARK(mark);
        }
    }

}
