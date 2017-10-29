package net.intari.CustomLogger;

import android.content.Context;
import android.util.Log;



/**
 * (c) Dmitriy Kazimirov, 2015-2016, e-mail:dmitriy.kazimirov@viorsan.com
 * This is helper class for usage with Crashlytics
 * see https://docs.fabric.io/android/crashlytics/enhanced-reports.html#custom-logging
 * and with NSLogger (based on https://github.com/fpillet/NSLogger/tree/master/Client%20Logger/Android )
 */
public class CustomLog {
    public static final String TAG = CustomLog.class.getSimpleName();
    public static final String NOTAG = "NoTag";

    private static String logHost=null;
    private static int logPort = 0;
    private static boolean isDebug=false;

    private static boolean logCrashlytics=true;
    private CustomLog() {
    }

    public static void setUserEmail(String userEmail) {
        //Crashlytics.setUserEmail(userEmail);
        v(TAG,"Setting user e-mail to "+userEmail);
    }
    public static void setUserName(String userName) {
        //Crashlytics.setUserName(userName);
        v(TAG,"Setting user name to "+userName);
    }

    public static void setUserIdentifier(String userIdentifier) {
        //Crashlytics.setUserIdentifier(userIdentifier);
    }

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
    public static void setIsDebug(boolean newIsDebug) {
        isDebug=newIsDebug;
    }
    public static void setLogCrashlytics(boolean newIsLogCrashlytics) {
        logCrashlytics=newIsLogCrashlytics;
    }
    public static void setContext(Context context) {
        if (isDebug) {
            Debug.setup(context,true,false);//logging, no flushing
            Debug.L.setRemoteHost(logHost, logPort, true);
            Debug.L.LOG_MARK("Logger startup (debug). Will use "+logHost+":"+logPort);
            logCrashlytics=false;//don't sent logs to crashlytics (especially crash logs, no need and I will get them anyway)
        }
        else {
            Debug.setup(context,true,false);//no logging, no flushing
            Debug.L.setRemoteHost(logHost, logPort, true);
            Debug.L.LOG_MARK("Logger startup (not debug). Will use "+logHost+":"+logPort);
        }
    }

    //just log message
    public static void l(String msg) {
        if (logCrashlytics) {
            //Crashlytics.log(msg);
        }
        //Log.i(NOTAG,msg);
        Debug.L.LOG_APP(0, msg);
        if (isDebug) {
            Log.v(NOTAG,msg);
        }
    }
    public static void l(String tag,String msg) {
        if (logCrashlytics) {
            //Crashlytics.log(msg);
        }
        Debug.L.taggedLog(Log.VERBOSE,tag,msg);
        //Log.v(tag,msg);
        if (isDebug) {
            Log.v(tag,msg);
        }
    }
    //replacement for Log.v
    public static int v(String tag, String msg) {
        if (logCrashlytics) {
            //Crashlytics.log(Log.VERBOSE, tag, msg);
        }
        Debug.L.taggedLog(Log.VERBOSE,tag,msg);
        if (isDebug) {
            Log.v(tag,msg);
        }
        return 0;//Log.v(tag,msg);
    }
    //replacement for Log.e
    public static int e(String tag, String msg) {
        if (logCrashlytics) {
            //Crashlytics.log(Log.ERROR, tag, msg);
        }
        Debug.L.taggedLog(Log.ERROR,tag,msg);
        if (isDebug) {
            Log.e(tag,msg);
        }
        return 0;//Log.e(tag,msg);
    }
    //replacement for Log.w
    public static int w(String tag, String msg) {
        if (logCrashlytics) {
            //Crashlytics.log(Log.WARN, tag, msg);
        }
        Debug.L.taggedLog(Log.WARN,tag,msg);
        if (isDebug) {
            Log.w(tag,msg);
        }
        return 0;//Log.w(tag,msg);
    }
    //replacement for Log.i
    public static int i(String tag, String msg) {
        if (logCrashlytics) {
            //Crashlytics.log(Log.INFO, tag, msg);
        }
        Debug.L.taggedLog(Log.INFO,tag,msg);
        if (isDebug) {
            Log.i(tag,msg);
        }
        return 0;//Log.i(tag,msg);
    }
    //replacement for Log.d
    public static int d(String tag, String msg) {
        if (logCrashlytics) {
            //Crashlytics.log(Log.DEBUG, tag, msg);
        }
        Debug.L.taggedLog(Log.DEBUG,tag,msg);
        if (isDebug) {
            Log.d(tag,msg);
        }
        return 0;//Log.d(tag,msg);
    }

    //replacement for "ex.printStackTrace" 'handling' of exceptions
    public static void logException(Exception ex) {
        Debug.L.LOG_EXCEPTION(ex);
        if (logCrashlytics) {
            //Crashlytics.logException(ex);
        }
        ex.printStackTrace();
    }
    public static void logException(Throwable t) {
        Debug.L.LOG_THROWABLE(t);
        if (logCrashlytics) {
            //Crashlytics.logException(t);
        }
        t.printStackTrace();
    }
    //Log image@debug level (makes no sense anyway
    public static void image(String tag,byte[] data) {
        Debug.L.taggedLogImageData(Log.DEBUG,tag, data);

    }

}
