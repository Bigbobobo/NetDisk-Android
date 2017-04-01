/*
 * MIT License
 *
 * Copyright (c) 2017 石岩
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.shiyan.netdisk_android.adapter;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.shiyan.netdisk_android.utils.ImageLoader;

/**
 * Contact shiyan233@hotmail.com
 * Blog    https://saltyx.github.io
 */

class DownLoadImageTask extends AsyncTask<Integer,Void,Bitmap> {

    private ImageView imageView;

    DownLoadImageTask(ImageView view) {
        imageView = view;
    }

    @Override protected Bitmap doInBackground(Integer... params) {
        try {
            return ImageLoader.getInstance().getImage(params[0]);
        } catch (Exception e) {
            return null;
        }
    }

    @Override protected void onPostExecute(Bitmap bitmap) {
        if (bitmap == null) return;
        imageView.setImageBitmap(bitmap);
    }

}
