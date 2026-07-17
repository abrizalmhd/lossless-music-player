-keep class com.losslessmusic.player.** { *; }
-keep class androidx.** { *; }
-keep class com.google.dagger.** { *; }
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
-keep public class * extends java.lang.Exception
-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}
-keep class androidx.room.** { *; }
-keep @androidx.room.Entity class * { *; }
-keep @androidx.room.Dao class * { *; }
-keep interface androidx.room.** { *; }
-keep class androidx.media3.** { *; }
-keepattributes RuntimeVisibleAnnotations
-dontwarn java.lang.invoke.StringConcatFactory
