package com.momohooker;

import android.app.Application;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class MainHook implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.equals("com.maimemo.android.momo")) return;
        Class<?> ActivityThread = XposedHelpers.findClass("android.app.ActivityThread", lpparam.classLoader);
        XposedBridge.hookAllMethods(ActivityThread, "performLaunchActivity", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                Object mInitialApplication = (Application) XposedHelpers.getObjectField(param.thisObject,"mInitialApplication");
                ClassLoader classLoader = (ClassLoader) XposedHelpers.callMethod(mInitialApplication,"getClassLoader");
                // XposedBridge.log("found classload is => "+classLoader.toString());
                Class<?> a = XposedHelpers.findClass("com.maimemo.android.momo.f0", classLoader);
                Class<?> b = XposedHelpers.findClass("kc.m0$b", classLoader);
                XposedBridge.hookAllMethods(a, "W", new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        param.setResult(999999);
                    }
                });
                XposedBridge.hookAllMethods(b, "d", new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        param.setResult(99);
                    }
                });
            }
        });
    }

}