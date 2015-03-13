# Android SDK

## Overview

`ringcaptcha/ringcaptcha-android` is an Android SDK for working with RingCaptcha API and RingCaptcha user interface components, which streamline and simplify the Onboarding and Verification of Users

## Getting Started

Import the Ringcaptcha sdk into your Android project, adding gradle dependencies in build.gradle inside your app module
```gradle
repositories {
// ...
 maven { url 'http://ringcaptcha.github.io/ringcaptcha-android/Ringcaptcha'}
// ...
}

dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile 'com.android.support:appcompat-v7:21.0.3'
    // ...
    compile 'com.thrivecom.ringcaptcha:ringcaptcha:1.0.5@aar'
}
```

## Learn More
- Read the [Android Guides](https://my.ringcaptcha.com/docs/android)
