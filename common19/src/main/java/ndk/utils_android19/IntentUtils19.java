package ndk.utils_android19;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;

public class IntentUtils19 {

    /**
     * Create an intent to open a URL in a browser
     * @param url the URL to open
     * @return intent to open the URL
     */
    public static Intent openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        return intent;
    }

    /**
     * Create an intent to make a phone call
     * @param phoneNumber the phone number to call
     * @return intent to make a phone call
     */
    public static Intent makePhoneCall(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        return intent;
    }

    /**
     * Create an intent to dial a phone number
     * @param phoneNumber the phone number to dial
     * @return intent to dial the phone number
     */
    public static Intent dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        return intent;
    }

    /**
     * Create an intent to send an SMS
     * @param phoneNumber the phone number to send SMS to
     * @param message the message content
     * @return intent to send SMS
     */
    public static Intent sendSms(String phoneNumber, String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + phoneNumber));
        intent.putExtra("sms_body", message);
        return intent;
    }

    /**
     * Create an intent to send an email
     * @param email the email address
     * @param subject the email subject
     * @param body the email body
     * @return intent to send email
     */
    public static Intent sendEmail(String email, String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + email));
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        return intent;
    }

    /**
     * Create an intent to send an email to multiple recipients
     * @param emails array of email addresses
     * @param subject the email subject
     * @param body the email body
     * @return intent to send email
     */
    public static Intent sendEmail(String[] emails, String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, emails);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        return intent;
    }

    /**
     * Create an intent to share text
     * @param text the text to share
     * @param subject the subject (optional)
     * @return intent to share text
     */
    public static Intent shareText(String text, String subject) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        if (subject != null) {
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        }
        intent.putExtra(Intent.EXTRA_TEXT, text);
        return Intent.createChooser(intent, "Share via");
    }

    /**
     * Create an intent to share text
     * @param text the text to share
     * @return intent to share text
     */
    public static Intent shareText(String text) {
        return shareText(text, null);
    }

    /**
     * Create an intent to share an image
     * @param imageUri the URI of the image to share
     * @return intent to share image
     */
    public static Intent shareImage(Uri imageUri) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, imageUri);
        return Intent.createChooser(intent, "Share image via");
    }

    /**
     * Create an intent to open the camera
     * @return intent to open camera
     */
    public static Intent openCamera() {
        return new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    }

    /**
     * Create an intent to pick an image from gallery
     * @return intent to pick image from gallery
     */
    public static Intent pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        return intent;
    }

    /**
     * Create an intent to pick a file
     * @param mimeType the MIME type of files to pick (e.g., "application/pdf", "*/*")
     * @return intent to pick a file
     */
    public static Intent pickFile(String mimeType) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType(mimeType);
        return intent;
    }

    /**
     * Create an intent to open app settings
     * @param context the context
     * @return intent to open app settings
     */
    public static Intent openAppSettings(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        return intent;
    }

    /**
     * Create an intent to open WiFi settings
     * @return intent to open WiFi settings
     */
    public static Intent openWifiSettings() {
        return new Intent(Settings.ACTION_WIFI_SETTINGS);
    }

    /**
     * Create an intent to open Bluetooth settings
     * @return intent to open Bluetooth settings
     */
    public static Intent openBluetoothSettings() {
        return new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
    }

    /**
     * Create an intent to open location settings
     * @return intent to open location settings
     */
    public static Intent openLocationSettings() {
        return new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
    }

    /**
     * Create an intent to open app in Play Store
     * @param context the context
     * @return intent to open app in Play Store
     */
    public static Intent openAppInPlayStore(Context context) {
        String packageName = context.getPackageName();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=" + packageName));
        return intent;
    }

    /**
     * Create an intent to open app in Play Store (fallback to browser if Play Store not available)
     * @param context the context
     * @return intent to open app in Play Store or browser
     */
    public static Intent openAppInPlayStoreOrBrowser(Context context) {
        String packageName = context.getPackageName();
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=" + packageName));
            return intent;
        } catch (android.content.ActivityNotFoundException e) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + packageName));
            return intent;
        }
    }

    /**
     * Create an intent to search on Google
     * @param query the search query
     * @return intent to search on Google
     */
    public static Intent searchOnGoogle(String query) {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(android.app.SearchManager.QUERY, query);
        return intent;
    }

    /**
     * Create an intent to open Google Maps with a location
     * @param latitude latitude of the location
     * @param longitude longitude of the location
     * @param label label for the location marker
     * @return intent to open Google Maps
     */
    public static Intent openGoogleMaps(double latitude, double longitude, String label) {
        String uri = String.format("geo:%f,%f?q=%f,%f(%s)", latitude, longitude, latitude, longitude, label);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(uri));
        return intent;
    }

    /**
     * Create an intent to open Google Maps with an address
     * @param address the address to locate
     * @return intent to open Google Maps
     */
    public static Intent openGoogleMaps(String address) {
        String uri = "geo:0,0?q=" + Uri.encode(address);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(uri));
        return intent;
    }

    /**
     * Create an intent to install an APK
     * @param apkUri the URI of the APK file
     * @return intent to install APK
     */
    public static Intent installApk(Uri apkUri) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    /**
     * Create an intent to uninstall an app
     * @param packageName the package name of the app to uninstall
     * @return intent to uninstall app
     */
    public static Intent uninstallApp(String packageName) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:" + packageName));
        return intent;
    }

    /**
     * Create an intent to view a PDF file
     * @param pdfUri the URI of the PDF file
     * @return intent to view PDF
     */
    public static Intent viewPdf(Uri pdfUri) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(pdfUri, "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        return intent;
    }

    /**
     * Create an intent to play a video
     * @param videoUri the URI of the video file
     * @return intent to play video
     */
    public static Intent playVideo(Uri videoUri) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(videoUri, "video/*");
        return intent;
    }

    /**
     * Create an intent to play audio
     * @param audioUri the URI of the audio file
     * @return intent to play audio
     */
    public static Intent playAudio(Uri audioUri) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(audioUri, "audio/*");
        return intent;
    }
}
