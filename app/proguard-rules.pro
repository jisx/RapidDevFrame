# greendao
-keep class com.fortune.jisx.sql.greendao.**{*;}
-keep class org.greenrobot.greendao.**{*;}
-keep public interface org.greenrobot.greendao.**
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
    public static java.lang.String TABLENAME;
}
-keep class **$Properties
-dontwarn org.greenrobot.greendao.database.**

-keep class com.fortune.jisx.sql.greendao.*$Properties {
    public static <fields>;
}
-keepclassmembers class com.fortune.jisx.sql.greendao.** {
    public static final <fields>;
}

#OkHttp 3.+
-dontwarn okio.**
-dontwarn javax.annotation.Nullable
-dontwarn javax.annotation.ParametersAreNonnullByDefault

#EventBus
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
