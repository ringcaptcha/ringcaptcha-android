# Using Ringcaptcha widget on your Android App

Register or Log-in to the [Ringcaptcha.com](http://ringcaptcha.com) site and create a new widget matching the application name it will be placed on. It is important that the name is exactly as shown on the Google Play Store for the widget to work correctly, any mistake can lead to unexpected errors. Select the default service to use for PIN code requests, and activate desired features. An embed code, with a unique app key, will be created for you automatically.

## Import the signed library into your project

To embed the SDK just drag&drop or copy&paste the library provided to you by Ringcaptcha into your libs folder in your project. Remember to merge the **assets** and **res** folders with your project's respective folders. Once the SDK has been included in your app, add the following permissions, activities and SMS Broadcaster to your application AndroidManifest.xml file:

	#!xml
	<uses-permission android:name="android.permission.INTERNET" />
	<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
	<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
	<uses-permission android:name="android.permission.READ_PHONE_STATE" />
	<uses-permission android:name="android.permission.RECEIVE_SMS" />
	<uses-permission android:name="android.permission.READ_SMS" />

	<activity android:name="com.thrivecom.ringcaptcha.SetNumberActivity" android:configChanges="orientation|keyboardHidden" android:screenOrientation="sensor"></activity>
	<activity android:name="com.thrivecom.ringcaptcha.HelpActivity" android:configChanges="orientation|keyboardHidden" android:screenOrientation="sensor"></activity>
	<activity android:name="com.thrivecom.ringcaptcha.CountryListActivity" android:configChanges="orientation|keyboardHidden" android:screenOrientation="sensor"></activity>
	<activity android:name="com.thrivecom.ringcaptcha.CodeActivity" android:configChanges="orientation|keyboardHidden" android:screenOrientation="sensor" android:windowSoftInputMode="stateVisible|adjustPan|adjustResize"></activity>

	<receiver android:name="com.thrivecom.ringcaptcha.RingcaptchaAPIController" android:enabled="true" android:exported="true" >
		<intent-filter android:priority="999">
			<action android:name="android.provider.Telephony.SMS_RECEIVED" android:scheme="sms" />
		</intent-filter>
	</receiver>

> _Note: The library is compatible with Android 2.2 – API LEVEL 8+ or greater._


Launch a fully functional widget by calling the following code:

	#!java
	//Create Ringcaptcha controller using your app key and secret key
	RingcaptchaApplication.verifyPhoneNumber(getApplicationContext(),"{APP_KEY}", “{SECRET_KEY}”, new RingcaptchaHandler() {
  		@Override
  		public void onSuccess(RingcaptchaVerification o) {
    		//Verification successful
  		}
		@Override
		public void onCancel(RingcaptchaVerification o) {
	    	//Decide what do do if user cancelled operation
	  	}	
	});

Or if you have access to the API directly, access the SDK directly with:

	#!java
	//Create Ringcaptcha controller using your app key
	RingcaptchaAPIController controller = new RingcaptchaAPIController("{APP_KEY}");
	//Send an initial SMS pin code
	controller.sendCaptchaCodeToNumber(context, phone, RingcaptchaService.SMS,new RingcaptchaAPIHandler() {
	  @Override
	  public void onSuccess(RingcaptchaResponse o) {
	    RingcaptchaAPIController.setSMSHandler(new RingcaptchaSMSHandler() {
	      @Override
	      public boolean handleMessage(String pin) {
	        //Automatically verify PIN code
	        return true;
	      }
	    });
	  }
	  @Override
	  public void onError(Exception e) {
	    //Display an error to the user
	  }
	}, "{API KEY}");
	//Send the pin code via a VOICE call. Ideal for retry attempts or landline phones
	controller.sendCaptchaCodeToNumber(context, phone, RingcaptchaService.VOICE, new RingcaptchaAPIHandler() {
	  @Override
	  public void onSuccess(RingcaptchaResponse o) {
	    //Clear SMS handler to avoid multiple verification attempts
	    RingcaptchaAPIController.setSMSHandler(null);
	  }
	  @Override
	  public void onError(Exception e) {
	    //Display an error to the user
	  }
	}, "{API KEY}"); 
	//Close the loop by verifying using the PIN code entered by the user or automatic SMS message
	controller.verifyCaptchaWithCode(context, pin, new RingcaptchaHandler() {
	  @Override
	  public void onSuccess(RingcaptchaResponse o) {
	    //Clear SMS handler to avoid multiple verification attempts
	    RingcaptchaAPIController.setSMSHandler(null);
	  }
	  @Override
	  public void onError(Exception e) {	
	    //Display an error to the user
	  }
	},"{API KEY}");


That's it. Download the [SDK](https://bitbucket.org/ringcaptcha/ringcaptcha-android/src) and you are ready to start verifying your community phone numbers!
