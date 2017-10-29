package com.viorsan.CustomLogger;
/*
  Originally part of NSLogger example code at https://github.com/fpillet/NSLogger/tree/master/Client%20Logger/Android
  Modified by Dmitriy Kazimirov, e-mail:dmitriy.kazimirov@viorsan.com, 2015-2016
 */

import android.content.Context;

import java.util.Formatter;

public final class DroidLogger extends NSLoggerClient
{
    private boolean loggingEnabled=false;
	public DroidLogger(Context ctx,Boolean loggingEnabled)
	{
		super(ctx);
        this.loggingEnabled=loggingEnabled;
	}

	public void taggedLog(final int level, final String tag, final String message)
	{
        if (!loggingEnabled) {
            return;
        }
		final StackTraceElement[] st = Thread.currentThread().getStackTrace();
		if (st != null && st.length > 4)
		{
			// log with originating source code info
			final StackTraceElement e = st[4];
            //use originating info in same way iOS version use. it looks like first 3 params doesn't matter at all. dkzm
            String stringOriginating=String.format("%s [Line %d] %s.%s() %s",e.getFileName(),e.getLineNumber(),e.getClassName(),e.getMethodName(),message);

			log(e.getFileName(), e.getLineNumber(), e.getClassName() + "." + e.getMethodName() + "()", tag, level, stringOriginating);
		}
		else
		{
			// couldn't obtain stack trace? log without source code info
			log(tag, level, message);
		}
	}
    public void taggedLogImageData(final int level, final String tag, final byte[] data)
    {

        if (!loggingEnabled) {
            return;
        }
        final StackTraceElement[] st = Thread.currentThread().getStackTrace();
        if (st != null && st.length > 4)
        {
            // log with originating source code info
            final StackTraceElement e = st[4];
            //use originating info in same way iOS version use. it looks like first 3 params doesn't matter at all. dkzm
            //String stringOriginating=String.format("%s [Line %d] %s.%s() %s",e.getFileName(),e.getLineNumber(),e.getClassName(),e.getMethodName(),message);

            logData(e.getFileName(), e.getLineNumber(), e.getClassName() + "." + e.getMethodName() + "()", tag, level, data);
        }
        else
        {
            // couldn't obtain stack trace? log without source code info
            logData(null, 0,null, null,0,data);
        }
    }

	public void LOG_MARK(String mark)
	{
        if (!loggingEnabled) {
            return;
        }
        logMark(mark);
	}

	public void LOG_THROWABLE(Throwable t) {
		if (!loggingEnabled) {
			return;
		}
		final StackTraceElement[] st = t.getStackTrace();
		if (st != null && st.length != 0)
		{
			// a stack trace was attached to exception, report the most recent callsite in file/line/function
			// information, and append the full stack trace to the message
			StringBuilder sb = new StringBuilder(256);
			sb.append("Throwable (Exception): ");
			sb.append(t.toString());
			sb.append("\n\nStack trace:\n");
			for (int i=0, n=st.length; i < n; i++)
			{
				final StackTraceElement e = st[i];
				sb.append(e.getFileName());
				if (e.getLineNumber() < 0)
					sb.append(" (native)");
				else
				{
					sb.append(":");
					sb.append(Integer.toString(e.getLineNumber()));
				}
				sb.append(" ");
				sb.append(e.getClassName());
				sb.append(".");
				sb.append(e.getMethodName());
				sb.append("\n");
			}
			final StackTraceElement e = st[0];
			log(e.getFileName(), e.getLineNumber(), e.getClassName() + "." + e.getMethodName() + "()", "throwable", 0, sb.toString());
		}
		else
		{
			// no stack trace attached to exception (should not happen)
			taggedLog(0, "throwable", t.toString());
		}
	}

	public void LOG_EXCEPTION(Exception exc)
	{
        if (!loggingEnabled) {
            return;
        }
		final StackTraceElement[] st = exc.getStackTrace();
		if (st != null && st.length != 0)
		{
			// a stack trace was attached to exception, report the most recent callsite in file/line/function
			// information, and append the full stack trace to the message
			StringBuilder sb = new StringBuilder(256);
			sb.append("Exception: ");
			sb.append(exc.toString());
			sb.append("\n\nStack trace:\n");
			for (int i=0, n=st.length; i < n; i++)
			{
				final StackTraceElement e = st[i];
				sb.append(e.getFileName());
				if (e.getLineNumber() < 0)
					sb.append(" (native)");
				else
				{
					sb.append(":");
					sb.append(Integer.toString(e.getLineNumber()));
				}
				sb.append(" ");
				sb.append(e.getClassName());
				sb.append(".");
				sb.append(e.getMethodName());
				sb.append("\n");
			}
			final StackTraceElement e = st[0];
			log(e.getFileName(), e.getLineNumber(), e.getClassName() + "." + e.getMethodName() + "()", "exception", 0, sb.toString());
		}
		else
		{
			// no stack trace attached to exception (should not happen)
			taggedLog(0, "exception", exc.toString());
		}
	}

	public void LOG_APP(int level, String format, Object ... args)
	{
		taggedLog(level, "app", new Formatter().format(format, args).toString());
	}

	public void LOG_DYNAMIC_IMAGES(int level, String format, Object ... args)
	{
		taggedLog(level, "images", new Formatter().format(format, args).toString());
	}

	public void LOG_WEBVIEW(int level, String format, Object ... args)
	{
		taggedLog(level, "webview", new Formatter().format(format, args).toString());
	}

	public void LOG_CACHE(int level, String format, Object ... args)
	{
		taggedLog(level, "cache", new Formatter().format(format, args).toString());
	}

	public void LOG_NETWORK(int level, String format, Object ... args)
	{
		taggedLog(level, "network", new Formatter().format(format, args).toString());
	}

	public void LOG_SERVICE(int level, String format, Object ... args)
	{
		taggedLog(level, "service", new Formatter().format(format, args).toString());
	}

	public void LOG_UI(int level, String format, Object ... args)
	{
		taggedLog(level, "ui", new Formatter().format(format, args).toString());
	}
}
