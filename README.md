Verge Tor Wallet for Android 
============================

- Requires orbot to be running in background (https://gitlab.com/guardianproject/orbot)
- App starts up with socks5 proxy on 127.0.0.1:9050
- Current API 35

<p align="left">
  <a href="https://github.com/vergecurrency/tordroid/actions/workflows/android.yml">
  <img src="https://github.com/vergecurrency/tordroid/actions/workflows/android.yml/badge.svg">
  </a>
</p>

## Building the app

Install [Android Studio](https://developer.android.com/sdk/installing/studio.html). Once it is
running, import tordroid by navigating to where you cloned or downloaded it and selecting
settings.gradle. When it is finished importing, click on the SDK Manager ![SDK Manager](https://developer.android.com/images/tools/sdk-manager-studio.png). 
You will want to install Android 15 / SDK version 35 (API 35).
<br/><br/>
Make sure that you have JDK 17 installed before building. 
You can get it [here from Oracle (login required)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html). 
Once you have that installed, navigate to File > Project Structure > SDK Location and change the path of your current JDK to 
the path of the new JDK.
<br/><br/>
To work with on ChromeOS flex, download chromeOS here: https://dl.google.com/chromeos-flex/images/latest.bin.zip
<br/><br/>
To test, open a virtual phone in your Android Studio Device Manager or, to test on a physical device, 
you will need to enable developer options on your phone. To do so, go into settings, About Phone, 
locate your Build Number, and tap it 7 times, or until it says "You are now a Developer". Then, 
go back to the main Settings screen and scroll once again to the bottom. Select Developer options 
and enable USB Debugging.
<br/><br/>
Then plug your phone into your computer and hit the green play button at the top of
Android Studio. It will load for a moment before prompting you to select which device to install
it on. Select your device from the list, and hit continue.
<br/><br/>
**NOTE**
If you are attempting to build on a Lollipop emulator, please ensure that you are using *Android 5.*.* armeabi-v7*. 
It will not build on an x86/x86_64 emulator.

Original code by Coinomi 2017<br>
Continued and updated from 2017 to now, by justinvforvendetta
