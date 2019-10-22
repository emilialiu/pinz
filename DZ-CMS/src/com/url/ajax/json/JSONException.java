// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   JSONException.java

package com.url.ajax.json;


public class JSONException extends Exception
{

	private Throwable cause;

	public JSONException(String message)
	{
		super(message);
	}

	public JSONException(Throwable t)
	{
		super(t.getMessage());
		cause = t;
	}

	public Throwable getCause()
	{
		return cause;
	}
}
