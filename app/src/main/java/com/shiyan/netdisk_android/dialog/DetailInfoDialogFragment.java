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

package com.shiyan.netdisk_android.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.shiyan.netdisk_android.R;
import com.shiyan.netdisk_android.model.UserFile;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Contact shiyan233@hotmail.com
 * Blog    https://saltyx.github.io
 */

public class DetailInfoDialogFragment extends AttachDialogFragment {

    public static final String KEY_USER_FILE = "USER_FILE";
    private UserFile file;
    private OnMoreCallBack callback;
    final String TAG = getClass().getName();

    @BindView(R.id.name) TextView name;
    @BindView(R.id.lock) TextView lock;
    @BindView(R.id.rename) TextView rename;
    @BindView(R.id.share) TextView share;
    @BindView(R.id.delete) TextView delete;
    @BindView(R.id.lock_switch) SwitchCompat lockSwitch;
    @BindView(R.id.imageView5) ImageView deleteImage;
    @BindView(R.id.imageView6) ImageView lockImage;
    @BindView(R.id.imageView3) ImageView nameImage;
    @BindView(R.id.imageView7) ImageView renameImage;
    @BindView(R.id.imageView8) ImageView shareImage;
    @BindView(R.id.imageView2) ImageView dividerName;
    @BindView(R.id.imageView4) ImageView dividerDelete;
    @BindView(R.id.imageView9) ImageView dividerLock;
    @BindView(R.id.imageView10) ImageView dividerRename;

    @OnClick(R.id.delete) public void deleteFile() {
        if (file != null && callback != null) {
            callback.onDeletedClick(file);
        }
    }

    @OnClick(R.id.rename) public void renameFile() {
        if (file != null && callback != null) {
            callback.onRenameClick(file);
        }
    }

    @OnClick(R.id.share) public void shareFile() {
        if (file != null && callback != null) {
            callback.onShareClick(file);
        }
    }

    @OnClick (R.id.lock) public void encryptOrDecryptFile() {
        if (file != null && callback != null) {
            callback.onEncryptOrDecryptClick(file);
        }
    }

    public static DetailInfoDialogFragment newInstance(UserFile userFile) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_USER_FILE, userFile);
        DetailInfoDialogFragment fragment = new DetailInfoDialogFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.horizontalMargin = 0;
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setAttributes(params);
        View root = inflater.inflate(R.layout.fragment_detail_info_dialog, container);
        ButterKnife.bind(this, root);
        lockSwitch.setEnabled(false);
        if (getArguments() != null) {
            this.file = getArguments().getParcelable(KEY_USER_FILE);
            if (file!=null) {
                name.setText(file.getFileName());
                lockSwitch.setChecked(file.isEncrypted());
                //Log.i(TAG, "onCreateView: ".concat(String.valueOf(file.toString())));
            }
        }

        name.setVisibility(showName);
        nameImage.setVisibility(showName);
        dividerName.setVisibility(showName);

        rename.setVisibility(showRename);
        renameImage.setVisibility(showRename);
        dividerRename.setVisibility(showRename);

        lock.setVisibility(showLock);
        dividerLock.setVisibility(showLock);
        lockImage.setVisibility(showLock);

        lockSwitch.setVisibility(showLock);

        share.setVisibility(showShare);
        shareImage.setVisibility(showShare);

        delete.setVisibility(showDelete);
        deleteImage.setVisibility(showDelete);
        dividerDelete.setVisibility(showDelete);
        return root;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    public DetailInfoDialogFragment setCallBack(OnMoreCallBack callback) {
        this.callback = callback;
        return this;
    }

    public DetailInfoDialogFragment hideOption(int... options) {
        for (int option:options) {
            switch (option) {
                case NAME:
                    showName =GONE;
                    break;
                case RENAME:
                    showRename = GONE;
                    break;
                case LOCK:
                    showLock = GONE;
                    break;
                case SHARE:
                    showShare = GONE;
                    break;
                case DELETE:
                    showDelete = GONE;
                    break;

            }
        }
        return this;
    }

    public interface OnMoreCallBack {
        void onDeletedClick(UserFile file);
        void onRenameClick(UserFile file);
        void onEncryptOrDecryptClick(UserFile file);
        void onShareClick(UserFile file);
    }

    public static final int NAME = 0xF001;
    public static final int RENAME= 0xF002;
    public static final int LOCK = 0xF003;
    public static final int SHARE = 0xF004;
    public static final int DELETE= 0xF005;

    private int showName =   VISIBLE;
    private int showRename =VISIBLE;
    private int showLock = VISIBLE;
    private int showShare =VISIBLE;
    private int showDelete =VISIBLE;
}
