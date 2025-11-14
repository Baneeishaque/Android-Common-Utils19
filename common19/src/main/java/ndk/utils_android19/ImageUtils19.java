package ndk.utils_android19;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Base64;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtils19 {

    /**
     * Decode a bitmap from a file path with sample size to reduce memory usage
     * @param filePath path to the image file
     * @param reqWidth required width
     * @param reqHeight required height
     * @return decoded bitmap
     */
    public static Bitmap decodeSampledBitmapFromFile(String filePath, int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    /**
     * Calculate sample size for bitmap decoding
     * @param options BitmapFactory.Options containing the original dimensions
     * @param reqWidth required width
     * @param reqHeight required height
     * @return calculated sample size
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    /**
     * Resize a bitmap to the specified dimensions
     * @param bitmap the bitmap to resize
     * @param newWidth new width
     * @param newHeight new height
     * @return resized bitmap
     */
    public static Bitmap resizeBitmap(Bitmap bitmap, int newWidth, int newHeight) {
        return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);
    }

    /**
     * Rotate a bitmap by the specified degrees
     * @param bitmap the bitmap to rotate
     * @param degrees rotation angle in degrees
     * @return rotated bitmap
     */
    public static Bitmap rotateBitmap(Bitmap bitmap, float degrees) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degrees);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /**
     * Create a circular bitmap from a square bitmap
     * @param bitmap the bitmap to make circular
     * @return circular bitmap
     */
    public static Bitmap getCircularBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(bitmap.getWidth() / 2f, bitmap.getHeight() / 2f,
                bitmap.getWidth() / 2f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    /**
     * Create a bitmap with rounded corners
     * @param bitmap the bitmap to round
     * @param cornerRadius radius of the corners in pixels
     * @return bitmap with rounded corners
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float cornerRadius) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    /**
     * Convert bitmap to Base64 string
     * @param bitmap the bitmap to convert
     * @param compressFormat compression format (JPEG, PNG, etc.)
     * @param quality compression quality (0-100)
     * @return Base64 encoded string
     */
    public static String bitmapToBase64(Bitmap bitmap, Bitmap.CompressFormat compressFormat, int quality) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, quality, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    /**
     * Convert bitmap to Base64 string with default PNG format and 100% quality
     * @param bitmap the bitmap to convert
     * @return Base64 encoded string
     */
    public static String bitmapToBase64(Bitmap bitmap) {
        return bitmapToBase64(bitmap, Bitmap.CompressFormat.PNG, 100);
    }

    /**
     * Convert Base64 string to bitmap
     * @param base64String the Base64 string to convert
     * @return decoded bitmap
     */
    public static Bitmap base64ToBitmap(String base64String) {
        byte[] decodedBytes = Base64.decode(base64String, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    /**
     * Save bitmap to file
     * @param bitmap the bitmap to save
     * @param file the file to save to
     * @param compressFormat compression format
     * @param quality compression quality (0-100)
     * @return true if save was successful, false otherwise
     */
    public static boolean saveBitmapToFile(Bitmap bitmap, File file, Bitmap.CompressFormat compressFormat, int quality) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bitmap.compress(compressFormat, quality, fos);
            fos.flush();
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    // Ignore
                }
            }
        }
    }

    /**
     * Save bitmap to file with default PNG format and 100% quality
     * @param bitmap the bitmap to save
     * @param file the file to save to
     * @return true if save was successful, false otherwise
     */
    public static boolean saveBitmapToFile(Bitmap bitmap, File file) {
        return saveBitmapToFile(bitmap, file, Bitmap.CompressFormat.PNG, 100);
    }

    /**
     * Get bitmap from ImageView
     * @param imageView the ImageView
     * @return bitmap from the ImageView
     */
    public static Bitmap getBitmapFromImageView(ImageView imageView) {
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(imageView.getDrawingCache());
        imageView.setDrawingCacheEnabled(false);
        return bitmap;
    }

    /**
     * Compress bitmap to a target size in KB
     * @param bitmap the bitmap to compress
     * @param maxSizeKB maximum size in kilobytes
     * @return compressed bitmap as byte array
     */
    public static byte[] compressBitmapToSize(Bitmap bitmap, int maxSizeKB) {
        int quality = 100;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        
        while (baos.toByteArray().length / 1024 > maxSizeKB && quality > 0) {
            baos.reset();
            quality -= 5;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        }
        
        return baos.toByteArray();
    }

    /**
     * Create a bitmap from a view
     * @param context the context
     * @param layoutResId the layout resource ID
     * @param width desired width
     * @param height desired height
     * @return bitmap of the view
     */
    public static Bitmap createBitmapFromLayout(Context context, int layoutResId, int width, int height) {
        android.view.LayoutInflater inflater = android.view.LayoutInflater.from(context);
        android.view.View view = inflater.inflate(layoutResId, null);
        view.measure(
                android.view.View.MeasureSpec.makeMeasureSpec(width, android.view.View.MeasureSpec.EXACTLY),
                android.view.View.MeasureSpec.makeMeasureSpec(height, android.view.View.MeasureSpec.EXACTLY)
        );
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        
        return bitmap;
    }
}
