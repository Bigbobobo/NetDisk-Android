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

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shiyan.netdisk_android.R;
import com.shiyan.netdisk_android.model.UserFile;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Contact shiyan233@hotmail.com
 * Blog    https://saltyx.github.io
 */

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.GridViewHolder>
                            implements BaseAdapter {

    static class GridViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.folder_name) TextView folderName;
        @BindView(R.id.folder_updated_time) TextView updatedTime;

        @OnClick(R.id.folder_name) void onItemClick() {
            callback.onItemClick(file);
        }
        @OnClick(R.id.folder_updated_time) void onItemClick1() {
            callback.onItemClick(file);
        }
        @OnClick(R.id.show_more) void onMoreClick() {
            callback.onMoreClick(file);
        }

        UserFile file;
        CallBack callback;

        GridViewHolder(View item, CallBack callback) {
            super(item);
            this.callback = callback;
            ButterKnife.bind(this, item);
        }
    }

    private List<UserFile> mData;
    private CallBack mCallback;
    private boolean mShowDetail;

    public FolderAdapter(List<UserFile> data, CallBack callback, boolean showDetail) {
        this.mData = data;
        this.mCallback = callback;
        this.mShowDetail = showDetail;
    }

    @Override public void changeData(List<UserFile> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override public void remove(UserFile file) {
        int fileId = file.getId();
        int index = -1;
        for (int i = 0; i < mData.size(); i++) {
            if (mData.get(i).getId() == fileId) {
                index = i; break;
            }
        }
        if (index != -1) {
            mData.remove(index);
            notifyDataSetChanged();
        }
    }

    @Override public void renameItem(UserFile file) {
        int id = file.getId();
        String newName = file.getFileName();
        for (int i = 0; i < mData.size(); i++) {
            if (mData.get(i).getId() == id) {
                mData.get(i).setFileName(newName);
                notifyDataSetChanged();
                break;
            }
        }
    }

    @Override public void addItem(UserFile file) {
        mData.add(file);
        notifyDataSetChanged();
    }

    @Override
    public GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_folder, parent, false);
        return new GridViewHolder(root, mCallback);
    }

    @Override
    public void onBindViewHolder(GridViewHolder holder, int position) {
        String folderName = mData.get(position).getFileName();
        holder.folderName.setText(folderName);
        holder.file = mData.get(position);
        holder.updatedTime.setText(mData.get(position).getUpdateAt());
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public interface CallBack {
        void onMoreClick(UserFile file);
        void onItemClick(UserFile file);
    }

}
