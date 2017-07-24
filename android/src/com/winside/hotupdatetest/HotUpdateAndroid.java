package com.winside.hotupdatetest;

import com.badlogic.gdx.Gdx;

import dalvik.system.DexClassLoader;
import android.content.Context;
import com.winside.hotupdatetest.loading.HotUpdate;

public class HotUpdateAndroid implements HotUpdate {
	final String TAG = "HotUpdateAndroid";
	private String assetsDir = null;//游戏资源文件目录
    private Context ctx;//AndroidContext

    public HotUpdateAndroid(Context ctx) {
        this.ctx = ctx;
        //Android的data目录，可以随apk卸载一起删除，并且资源文件的图片不会出现在相册中
//        assetsDir = ctx.getExternalFilesDir("").getAbsolutePath() + "/";
//        assetsDir = Gdx.files.getExternalStoragePath() + "/";
    }

    @Override
    public String getAssetsDir() {
    	if(assetsDir == null){
    		assetsDir = Gdx.files.getExternalStoragePath();
    		Gdx.app.log(TAG, "externalStoragePath = " + assetsDir);
    	}
        return assetsDir;
    }

    @Override
    public Class loadDex(String dexPath, String className) throws Exception {
    	Gdx.app.log("cache dir", ctx.getCacheDir().getAbsolutePath());
        DexClassLoader loader = new DexClassLoader(dexPath, ctx.getCacheDir().getAbsolutePath(), null, ctx.getClassLoader());
        Class claz = loader.loadClass(className);
        return claz;
    }

}
