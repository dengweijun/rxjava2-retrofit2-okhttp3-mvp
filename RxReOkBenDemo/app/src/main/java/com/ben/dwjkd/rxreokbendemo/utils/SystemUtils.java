package com.ben.dwjkd.rxreokbendemo.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

/**
 * 系统工具类
 * 
 * @author xiaojiao_li
 *
 */
public class SystemUtils {

	public static PackageInfo getApplicationPackage(Context context) throws NameNotFoundException {
		PackageManager pm = context.getPackageManager();
		PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
		return pi;
	}

	public static TelephonyManager getTelephonyManager(Context context) {
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return tm;
	}

	public static String getMacAddress(Context context) {
		String macAddress = "";
		WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		if (manager != null && manager.getConnectionInfo() != null) {
			macAddress = manager.getConnectionInfo().getMacAddress();
		}
		return macAddress;
	}

	/**
	 * 打开一个应用的信息页
	 * 
	 * @param context
	 * @param packageName
	 */
	public static void startAppDetails(Context context, String packageName) {
		Intent intent = new Intent();
		final int apiLevel = Build.VERSION.SDK_INT;
		if (apiLevel >= 9) { // above 2.3
			intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
			Uri uri = Uri.fromParts("package", packageName, null);
			intent.setData(uri);
		} else { // below 2.3
			final String appPkgName = (apiLevel == 8 ? "pkg" : "com.android.settings.ApplicationPkgName");
			intent.setAction(Intent.ACTION_VIEW);
			intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
			intent.putExtra(appPkgName, packageName);
		}
		context.startActivity(intent);
	}

}
