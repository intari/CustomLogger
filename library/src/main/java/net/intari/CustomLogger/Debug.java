package com.viorsan.CustomLogger;/*
  Originally part of NSLogger example code at https://github.com/fpillet/NSLogger/tree/master/Client%20Logger/Android
  Modified by Dmitriy Kazimirov, e-mail:dmitriy.kazimirov@viorsan.com
 */


import android.content.Context;

public class Debug
{
	public static final boolean D = true;		// set to false to disable debug
	public static DroidLogger L = null;

	public static void setup(Context ctx, boolean enableLogging, boolean flushEachMessage)
	{
		if (L == null)
		{
			L = new DroidLogger(ctx,enableLogging);
			L.setMessageFlushing(flushEachMessage);
		}
	}
}
