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

package com.shiyan.netdisk_android.data.source.local;

import android.provider.BaseColumns;

/**
 * Contact shiyan233@hotmail.com
 * Blog    https://saltyx.github.io
 */

final class DataPersistenceContract {

    private DataPersistenceContract() {}

    static abstract class FilesEntry implements BaseColumns {

        static final String TABLE_NAME = "files";
        static final String COLUMN_NAME_ID = "id";
        static final String COLUMN_NAME_NAME = "file_name";
        static final String COLUMN_NAME_FILE_SIZE = "file_size";
        static final String COLUMN_NAME_IS_FOLDER = "is_folder";
        static final String COLUMN_NAME_FROM_FOLDER = "from_folder";
        static final String COLUMN_NAME_IS_SHARED = "is_shared";
        static final String COLUMN_NAME_IS_ENCRYPTED = "is_encrypted";
        static final String COLUMN_NAME_DOWNLOAD_LINK = "download_link";
        static final String COLUMN_NAME_DOWNLOAD_TIMES = "download_times";
        static final String COLUMN_NAME_CREATE_AT = "create_at";
        static final String COLUMN_NAME_UPDATE_AT = "update_at";
        static final String COLUMN_NAME_IV = "iv";
        static final String COLUMN_NAME_SHA256 = "sha256";

    }
}
