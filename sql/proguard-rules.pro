# greendao
-keep class org.greenrobot.greendao.**{*;}
-keep public interface org.greenrobot.greendao.**
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
    public static java.lang.String TABLENAME;
}
-keep class **$Properties
-dontwarn org.greenrobot.greendao.database.**
-dontwarn rx.**

-keep class com.zhenming.sql.greendao.**{*;}
-keep class com.zhenming.sql.greendao.*$Properties {
    public static <fields>;
}
-keepclassmembers class com.zhenming.sql.greendao.** {
    public static final <fields>;
}
