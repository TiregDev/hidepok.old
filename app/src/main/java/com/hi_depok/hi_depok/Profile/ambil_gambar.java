package com.hi_depok.hi_depok.Profile;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hi_depok.hi_depok.Akses;
import com.hi_depok.hi_depok.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Azmi Muhammad on 5/12/2017.
 */

public class ambil_gambar extends AppCompatActivity implements View.OnClickListener {
    private Button buttonChoose;
    private Button buttonUpload;
    private Button buttonTake;
    private ImageView imageView;
    private Bitmap bitmap;
    private Uri tempuri;

    private int PICK_IMAGE_REQUEST = 1;
    private int TAKE_IMAGE_REQUEST = 2;

    private String UPLOAD_URL = "";
    private File imageFile;
    String filename;
    private String KEY_IMAGE = "image";

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambil_gambar);

        if (shouldAskPermissions()) {
            verifyStoragePermissions(this);
        }

        buttonChoose = (Button) findViewById(R.id.buttonChoose);
        buttonUpload = (Button) findViewById(R.id.buttonUpload);
        buttonTake = (Button) findViewById(R.id.buttonTake);

        imageView = (ImageView) findViewById(R.id.imageView);

        buttonTake.setOnClickListener(this);
        buttonChoose.setOnClickListener(this);
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
        if(view == buttonTake){
            takePicture();
        }

        if (view == buttonChoose) {
            showFileChooser();
        }

        if(view == buttonUpload){
            if(null!=imageView.getDrawable()) {
                //imageview have image
                uploadImage();
                finish();
            }else{
                //imageview have no image
                Toast.makeText(this, "Gambar belum ada", Toast.LENGTH_SHORT).show();
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

    // Direktori Gambar
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // requestCode == ambil gambar di direktori
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // requestCode == ambil gambar dari kamera
        else if (requestCode == TAKE_IMAGE_REQUEST && resultCode == RESULT_OK){
            String mCurrentPhotoPath = imageFile.getAbsolutePath();
            Toast.makeText(this, "File saved on " + mCurrentPhotoPath, Toast.LENGTH_SHORT).show();
            imageView.setImageBitmap(lessResolution(mCurrentPhotoPath, 200, 200));
        }
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

    public String getStringImage() {
        bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage() {
        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Uploading...", "Please wait...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(ambil_gambar.this, s, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ambil_gambar.this, setprofile.class));
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(ambil_gambar.this, "Terjadi kesalahan . . .", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                String image = getStringImage();

                //Getting Image Name

                //Creating parameters
                Map<String, String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put(KEY_IMAGE, image);

                //returning parameters
                return params;
            }
        };
        Akses.getInstance(this).addtoRequestQueue(stringRequest);
    }
}

