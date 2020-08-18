package com.xwzx.equipmanager.mvp.views.activitys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.lcw.library.imagepicker.ImagePicker;
import com.oden.syd_camera.camera.CameraInterface;
import com.oden.syd_camera.camera.CameraParaUtil;
import com.oden.syd_camera.utils.BitmapUtils;
import com.orhanobut.logger.Logger;
import com.xwzx.equipmanager.R;
import com.xwzx.equipmanager.base.BaseActivity;
import com.xwzx.equipmanager.databinding.ActivitySettingsBinding;
import com.xwzx.equipmanager.framwork.ClickProxy;
import com.xwzx.equipmanager.framwork.GlideLoader;
import com.xwzx.equipmanager.utils.PreUtil;

import java.io.File;
import java.util.ArrayList;


public class SettingsActivity extends BaseActivity<ActivitySettingsBinding> implements CameraInterface.CameraListener {
    private static final String TAG = SettingsActivity.class.getSimpleName();
    public static final int REQUEST_CODE_IMAGE = 0000;
    @Override
    public int setContentView() {
        return R.layout.activity_settings;
    }

    @Override
    protected void initView() {
        this.binding.headLayout.rightTitle.setVisibility(View.GONE);
        this.binding.headLayout.rightBtn.setVisibility(View.GONE);
        this.binding.headLayout.headTitle.setText(getResources().getString(R.string.settings));
        CameraInterface.getInstance().setCameraListener(this);
    }

    @Override
    protected void initListener() {
        this.binding.headLayout.back.setOnClickListener(new ClickProxy(v -> finish()));
        this.binding.ivCamera.setOnClickListener(new ClickProxy(v -> {
            ImagePicker.getInstance()
                    .showCamera(true)
                    .setTitle("本地相册")
                    .setSingleType(false)
                    .showImage(true)
                    .showVideo(false)
                    .setImageLoader(new GlideLoader())
                    .start(this, REQUEST_CODE_IMAGE);
        }));
    }

    @Override
    protected void initComponent() {

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK) {
            ArrayList<String> mImagePaths = data.getStringArrayListExtra(ImagePicker.EXTRA_SELECT_IMAGES);
            String s = mImagePaths.get(0);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("当前选中图片路径：\n\n");
            for (int i = 0; i < mImagePaths.size(); i++) {
                stringBuffer.append(mImagePaths.get(i) + "\n\n");
            }
            Logger.e("-----------------------------" + s);
        }
    }
    @Override
    protected void initData() {
        Glide.with(this)
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1588847280973&di=58c4db7897ffcc9576fe66e252529a40&imgtype=0&src=http%3A%2F%2Fpics1.baidu.com%2Ffeed%2Fb90e7bec54e736d12fa67dcb0d3268c4d4626920.jpeg%3Ftoken%3Dd92b368c528c0d0591e59251102b1c73%26s%3Df22fb044c4d80bc43db2fd1b03008099")
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(this.binding.ivUserHeadImg);
    }
    private int picQuality = 70;
    private int picWidth = 1536;
    private int previewWidth = 1280;
    private void saveLastTimePic(String uri) {
        PreUtil.put(PreUtil.LAST_PIC, uri);
    }
    @Override
    public void onTakePictureSuccess(File pictureFile) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap = BitmapUtils.rotateBitmap(BitmapFactory.decodeFile(pictureFile.getPath(), options), CameraInterface.getInstance().getmCameraId(), cameraOrientation);
//        Bitmap bitmap = BitmapUtils.rotateBitmap(BitmapFactory.decodeFile(pictureFile.getPath(), options), 45);
//        Bitmap waterBitmap = WaterMaskUtils.drawTextToRightBottom(this, bitmap, "waterMaskTest123", 16, Color.RED, 0, 0);
//        Bitmap smallBitmap = BitmapUtils.bitmapCompress(bitmap, 120);
//        bitmap = BitmapUtils.compressToSizeByQuality(bitmap, 120);
        Log.i(TAG, "onTakePictureSuccess bitmap.getWidth: " + bitmap.getWidth() + ", bitmap.getHeight():" + bitmap.getHeight());
        Log.i(TAG, "onTakePictureSuccess picQuality: " + picQuality + ", bitmap.getByteCount():" + bitmap.getByteCount());
//        Log.d(TAG, "onTakePictureSuccess picQuality: " + picQuality + ", smallBitmap.getByteCount():" + smallBitmap.getByteCount());
        BitmapUtils.saveBitmapToSd(bitmap, pictureFile.getPath(), picQuality);

        //更新本地相册
        Uri uri = Uri.fromFile(pictureFile);
        Intent localIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(pictureFile));
        sendBroadcast(localIntent);
        this.saveLastTimePic(uri.toString());
        Glide.with(this).load(uri.toString()).into(this.binding.ivCamera);
        try {
            String path = pictureFile.getPath();//private
            String newPath = path.substring(0, path.lastIndexOf(".")) + ".jpg";//jpg
            renameFile(path, newPath);
            //    renameFile(pathName,path);
            Logger.d("pathName=======" + newPath);
        } catch (Exception e) {
            Log.i(TAG, "Exception" + e);
        }
    }
    public void renameFile(String file, String toFile) {
        File toBeRenamed = new File(file);
        //检查要重命名的文件是否存在，是否是文件
        if (!toBeRenamed.exists() || toBeRenamed.isDirectory()) {
            Logger.e("File does not exist: " + file);
            return;
        }
        File newFile = new File(toFile);

        if (toBeRenamed.renameTo(newFile)) {
            Logger.e("File has been renamed.");
        } else {
            Logger.e("Error renmaing file");
        }

        Logger.e("newFile====" + newFile.getPath());
    }
    private int cameraOrientation = 0;
    @Override
    public void onTakePictureFail(byte[] data) {
        Log.e(TAG, "拍照失败，请检查权限设置!"); //三星A8出现无法创建文件夹的提示，重启恢复正常
        Toast.makeText(this, "拍照失败，请检查权限设置!", Toast.LENGTH_SHORT).show();
        CameraParaUtil.pictureBitmap = BitmapUtils.rotateBitmap(BitmapUtils.Bytes2Bitmap(data), CameraInterface.getInstance().getmCameraId(), cameraOrientation);
    }
}