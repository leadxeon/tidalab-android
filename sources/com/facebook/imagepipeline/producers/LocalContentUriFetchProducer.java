package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class LocalContentUriFetchProducer extends LocalFetchProducer {
    public static final String[] PROJECTION = {"_id", "_data"};
    public final ContentResolver mContentResolver;

    public LocalContentUriFetchProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, ContentResolver contentResolver) {
        super(executor, pooledByteBufferFactory);
        this.mContentResolver = contentResolver;
    }

    @Override // com.facebook.imagepipeline.producers.LocalFetchProducer
    public EncodedImage getEncodedImage(ImageRequest imageRequest) throws IOException {
        InputStream inputStream;
        Uri uri = imageRequest.mSourceUri;
        if (UriUtil.isLocalContentUri(uri) && "com.android.contacts".equals(uri.getAuthority()) && !uri.getPath().startsWith(UriUtil.LOCAL_CONTACT_IMAGE_URI.getPath())) {
            if (uri.toString().endsWith("/photo")) {
                inputStream = this.mContentResolver.openInputStream(uri);
            } else if (uri.toString().endsWith("/display_photo")) {
                try {
                    inputStream = this.mContentResolver.openAssetFileDescriptor(uri, "r").createInputStream();
                } catch (IOException unused) {
                    throw new IOException("Contact photo does not exist: " + uri);
                }
            } else {
                InputStream openContactPhotoInputStream = ContactsContract.Contacts.openContactPhotoInputStream(this.mContentResolver, uri);
                if (openContactPhotoInputStream != null) {
                    inputStream = openContactPhotoInputStream;
                } else {
                    throw new IOException("Contact photo does not exist: " + uri);
                }
            }
            return getByteBufferBackedEncodedImage(inputStream, -1);
        }
        if (UriUtil.isLocalCameraUri(uri)) {
            Cursor query = this.mContentResolver.query(uri, PROJECTION, null, null, null);
            EncodedImage encodedImage = null;
            if (query != null) {
                try {
                    if (query.getCount() != 0) {
                        query.moveToFirst();
                        String string = query.getString(query.getColumnIndex("_data"));
                        if (string != null) {
                            encodedImage = getByteBufferBackedEncodedImage(new FileInputStream(string), (int) new File(string).length());
                        }
                    }
                } finally {
                    query.close();
                }
            }
            if (encodedImage != null) {
                return encodedImage;
            }
        }
        return getByteBufferBackedEncodedImage(this.mContentResolver.openInputStream(uri), -1);
    }

    @Override // com.facebook.imagepipeline.producers.LocalFetchProducer
    public String getProducerName() {
        return "LocalContentUriFetchProducer";
    }
}
