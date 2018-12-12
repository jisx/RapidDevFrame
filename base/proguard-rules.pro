#gson
-keepattributes Signature
-keepattributes *Annotation*

-dontwarn sun.misc.**
-keep class com.google.gson.examples.android.model.** { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

#eventbus
-keepattributes *Annotation*
-keepclassmembers class * {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}
-keepattributes *Annotation*
-keepclassmembers class * {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

#EasyPermissions避免混淆
-keepclassmembers class ** {
    @com.hjq.permissions.HasPermission <methods>;
}
-keepclassmembers class ** {
    @com.hjq.permissions.NoPermission <methods>;
}


#接口需要的JavaBean不混淆
-keepclassmembers class com.rapid.base.model.**{*;}
#保证枚举
-keepclassmembers enum * { *; }