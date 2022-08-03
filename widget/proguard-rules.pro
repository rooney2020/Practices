# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#-keep class com.tmec.common.widget.** {*;}

#表示混淆时不使用大小写混合类名
-dontusemixedcaseclassnames
#表示不跳过library中的非public的类
-dontskipnonpubliclibraryclasses
#打印混淆的详细信息
-verbose
-keep public class * extends android.view.View {
*** get*();
void set*();
void set*(int , int , int );
void set*(int , int);
void show*(int);
public <init> (android.content.Context);
public <init> (android.content.Context,android.util.AttributeSet);
public <init> (android.content.Context,android.util.AttributeSet,int);
}
-keep public class * extends android.view.ViewGroup {
*** get*();
void set*();
void set*(int , int);
void show*(int);
public <init> (android.content.Context);
public <init> (android.content.Context,android.util.AttributeSet);
public <init> (android.content.Context,android.util.AttributeSet,int);
}
-keep class com.tmec.common.sdk.grouphead.** {*;}
-keep class com.tmec.common.sdk.tablayout.** {*;}

-keep class com.tmec.common.sdk.toast.** {*;}
-keep class com.tmec.common.sdk.popup.** {*;}
-keep class com.tmec.common.sdk.viewtext.** {*;}
-keep class com.tmec.common.sdk.base.SkinManager {*;}
-keep class ** {*;}
