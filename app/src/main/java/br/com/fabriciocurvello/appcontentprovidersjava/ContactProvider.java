package br.com.fabriciocurvello.appcontentprovidersjava;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class ContactProvider extends ContentProvider {

    public static final String AUTHORITY = "br.com.fabriciocurvello.provider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/contacts");

    private static final int CONTACTS = 1;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(AUTHORITY, "contacts", CONTACTS);
    }

    private String[] contacts = { "John Doe", "Jane Smith", "Emily Johnson" };

    @Override
    public boolean onCreate() {
        //Implement this to initialize your content provider on startup.
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // Implement this to handle query requests from clients.
        // throw new UnsupportedOperationException("Not yet implemented");

        if (uriMatcher.match(uri) == CONTACTS) {
            MatrixCursor cursor = new MatrixCursor(new String[]{BaseColumns._ID, "name"});
            for (int cont = 0; cont < contacts.length; cont ++) {
                cursor.addRow(new Object[]{cont, contacts[cont]});
            }
            return cursor;
        } else {
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public String getType(Uri uri) {
        // Implement this to handle requests for the MIME type of the data
        // at the given URI.
        // throw new UnsupportedOperationException("Not yet implemented");

        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Insert not supported");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Delete not supported");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("Update not supported");
    }

    public ContactProvider() {
    }

}