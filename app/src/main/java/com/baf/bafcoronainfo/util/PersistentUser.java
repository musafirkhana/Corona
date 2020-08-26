/*
 * Copyright (C) 2010 Mathieu Favez - http://mfavez.com
 *
 *
 * This file is part of FeedGoal.
 * 
 * FeedGoal is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FeedGoal is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FeedGoal.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.baf.bafcoronainfo.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public  class PersistentUser {

	private static final String LOG_TAG = "SharedPreferencesHelper";
	public static final String REMEMBER = "remember";
	private static final String PREFS_FILE_NAME = "Bafcoronainfo";
	private static final String USERPASSWORD = "userpassword";
	private static final String COUNTRY_CONF = "country_conf_today";
	private static final String COUNTRY_DEATH = "country_death_today";



	public static String getUserpassword(final Context ctx) {
		return ctx.getSharedPreferences(PersistentUser.PREFS_FILE_NAME,
				Context.MODE_PRIVATE).getString(PersistentUser.USERPASSWORD, "");
	}

	public static void setUserpassword(final Context ctx, final String data) {
		final SharedPreferences prefs = ctx.getSharedPreferences(
				PersistentUser.PREFS_FILE_NAME, Context.MODE_PRIVATE);
		final Editor editor = prefs.edit();
		editor.putString(PersistentUser.USERPASSWORD, data);
		editor.commit();
	}

	public static String getCountryConfToday(final Context ctx) {
		return ctx.getSharedPreferences(PersistentUser.PREFS_FILE_NAME,
				Context.MODE_PRIVATE).getString(PersistentUser.COUNTRY_CONF, "");
	}

	public static void setCountryConfToday(final Context ctx, final String data) {
		final SharedPreferences prefs = ctx.getSharedPreferences(
				PersistentUser.PREFS_FILE_NAME, Context.MODE_PRIVATE);
		final Editor editor = prefs.edit();
		editor.putString(PersistentUser.COUNTRY_CONF, data);
		editor.commit();
	}



	public static String getCountryDeath(final Context ctx) {
		return ctx.getSharedPreferences(PersistentUser.PREFS_FILE_NAME,
				Context.MODE_PRIVATE).getString(PersistentUser.COUNTRY_DEATH, "");
	}

	public static void setCountryDeath(final Context ctx, final String data) {
		final SharedPreferences prefs = ctx.getSharedPreferences(
				PersistentUser.PREFS_FILE_NAME, Context.MODE_PRIVATE);
		final Editor editor = prefs.edit();
		editor.putString(PersistentUser.COUNTRY_DEATH, data);
		editor.commit();
	}









}
