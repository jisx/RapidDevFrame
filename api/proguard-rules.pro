#retrofit
-keepattributes Signature, InnerClasses, EnclosingMethod
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontwarn javax.annotation.**
-dontwarn kotlin.Unit
-dontwarn retrofit2.-KotlinExtensions

#okHttp
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
-dontwarn org.codehaus.mojo.animal_sniffer.*
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn org.conscrypt.**

#接口需要的JavaBean不混淆
-keepclassmembers class com.zhenming.api.responseVo.**{*;}
-keepclassmembers class com.zhenming.api.requestVo.**{*;}