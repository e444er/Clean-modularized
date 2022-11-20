import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.provideDelegate

object Versions {
    const val coreVer = "1.7.0"
    const val appcompatVer = "1.5.1"
    const val androidMaterialVer = "1.7.0"
    const val constraintLayoutVer = "2.1.4"
    const val dynamicVersion = "2.5.3"

    const val testImplJunit = "4.13.2"
    const val androidTestImplJunit = "1.1.4"
    const val androidTestEspresso = "3.5.0"

    const val retrofitVer = "2.9.0"
    const val okHttpVer = "4.9.0"

    const val kotlinCoroutines = "1.6.1"

    const val coroutineLifecycleScope = "2.5.1"

    const val glideVer = "4.12.0"

    const val dagger = "2.44"
    const val hiltCompiler = "1.0.0"

    const val koinVersion = "3.3.0"

    const val pagingVer = "3.1.1"

    const val roomVersion = "2.4.3"

    const val swipeRefreshVer = "1.1.0"

    const val lottieAnimationsVer = "3.4.2"
}

object Paging {
    const val runtime =
        "androidx.paging:paging-runtime:${Versions.pagingVer}"
}

object Deps {

    const val core = "androidx.core:core-ktx:${Versions.coreVer}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompatVer}"
    const val androidMaterial =
        "com.google.android.material:material:${Versions.androidMaterialVer}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVer}"

}

object Navigation {

    const val navRuntime =
        "androidx.navigation:navigation-runtime-ktx:${Versions.dynamicVersion}"
    const val navFra = "androidx.navigation:navigation-fragment-ktx:${Versions.dynamicVersion}"
    const val navUi = "androidx.navigation:navigation-ui-ktx:${Versions.dynamicVersion}"
    const val dynamic =
        "androidx.navigation:navigation-dynamic-features-fragment:${Versions.dynamicVersion}"
}

object TestImplementation {

    const val junit = "junit:junit:${Versions.testImplJunit}"
}

object AndroidTestImplementation {

    const val junit = "androidx.test.ext:junit:${Versions.androidTestImplJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.androidTestEspresso}"
}


object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVer}"
    const val gsonConvertor = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVer}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttpVer}"
    const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpVer}"
}

object Coroutines {
    const val coroutineCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
    const val coroutineAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}"
}

object CoroutinesLifecycleScope {
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.coroutineLifecycleScope}"
    const val lifeCycleRuntime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.coroutineLifecycleScope}"
    const val lifecycleKapt =
        "androidx.lifecycle:lifecycle-compiler:${Versions.coroutineLifecycleScope}"

}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glideVer}"
    const val annotationProcessor = "com.github.bumptech.glide:compiler:${Versions.glideVer}"
}


object DaggerHilt {
    const val hilt = "com.google.dagger:hilt-android:${Versions.dagger}"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.dagger}"
    const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltCompiler}"
}

object Koin {
    const val koin = "io.insert-koin:koin-android:${Versions.koinVersion}"
}

object Room {

    const val runtime = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    const val room = "androidx.room:room-ktx:${Versions.roomVersion}"
}


object CircularProgressBar {
    const val swipeRefresh =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshVer}"
}

object LottieAnimations {
    const val lottieAnimations = "com.airbnb.android:lottie:${Versions.lottieAnimationsVer}"
}

fun Project.importDependencies() {
    dependencies {

        val implementation by configurations
        val testImplementation by configurations
        val androidTestImplementation by configurations
        val kapt by configurations

        implementation(Deps.core)
        implementation(Deps.appCompat)
        implementation(Deps.androidMaterial)
        implementation(Deps.constraintLayout)
        testImplementation(TestImplementation.junit)
        androidTestImplementation(AndroidTestImplementation.junit)
        androidTestImplementation(AndroidTestImplementation.espresso)

        implementation(Navigation.navFra)
        implementation(Navigation.navRuntime)
        implementation(Navigation.navUi)

        implementation(Glide.glide)

        implementation(Koin.koin)

        implementation(Paging.runtime)

        implementation(Coroutines.coroutineAndroid)
        implementation(Coroutines.coroutineCore)

        implementation(CoroutinesLifecycleScope.lifeCycleRuntime)
        implementation(CoroutinesLifecycleScope.lifecycleViewModel)
        kapt(CoroutinesLifecycleScope.lifecycleKapt)
    }
}