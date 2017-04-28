package com.hi_depok.hi_depok.Lapok;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.hi_depok.hi_depok.Lapok.fragment.DescriptionForm;
import com.hi_depok.hi_depok.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Date;

public class lapok_ambil_kejadian extends AppCompatActivity implements View.OnClickListener {
    private Button buttonUpload;
    private Button buttonTake;
    private ImageView imageView;
    private Bitmap bitmap;
    private Uri tempuri;

    private int TAKE_IMAGE_REQUEST = 2;

    private String UPLOAD_URL = "http://hidepok.id/include/uploadGambar.php";
    private File imageFile;
    String filename;
    private String KEY_IMAGE = "image";
    private String KEY_NAME = "name";

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lapok_ambil_kejadian);

        if (shouldAskPermissions()) {
            verifyStoragePermissions(this);
        }

        buttonUpload = (Button) findViewById(R.id.buttonUpload);
        buttonTake = (Button) findViewById(R.id.buttonTake);

        imageView = (ImageView) findViewById(R.id.imageView);

        buttonTake.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);
    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    protected boolean shouldAskPermissions() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonTake) {
            takePicture();
        }

        if (view == buttonUpload) {
            //uploadImage();
            if(null!=imageView.getDrawable())
            {
                String image = getStringImage();
                Intent intent = new Intent(this, DescriptionForm.class);
                intent.putExtra("image", image);
                startActivity(intent);
                //imageview have image
            }else{
                Toast.makeText(this, "Gambar belum ada", Toast.LENGTH_SHORT).show();
                //imageview have no image
            }
        }
    }

    // Kamera
    private void takePicture() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        filename = "image_" + new Date().getTime() + ".jpg";

        imageFile = new File(Environment.getExternalStorageDirectory()
                + "/DCIM/", filename);
        tempuri = Uri.fromFile(imageFile);
        camera.putExtra(MediaStore.EXTRA_OUTPUT, tempuri);
        camera.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

        startActivityForResult(camera, TAKE_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TAKE_IMAGE_REQUEST && resultCode == RESULT_OK) {
            String mCurrentPhotoPath = imageFile.getAbsolutePath();
            Toast.makeText(this, "File saved on " + mCurrentPhotoPath, Toast.LENGTH_SHORT).show();
            imageView.setImageBitmap(lessResolution(mCurrentPhotoPath, 200, 200));
        }
    }

    public String getStringImage() {
        bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public static Bitmap lessResolution (String filePath, int width, int height) {
        int reqHeight = height;
        int reqWidth = width;
        BitmapFactory.Options options = new BitmapFactory.Options();

        // First decode with inJustDecodeBounds=true to check dimensions
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath, options);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            // Calculate ratios of height and width to requested height and width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }
}
