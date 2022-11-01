package androidx.cursoradapter.widget;

import android.database.Cursor;
import android.widget.Filter;
import androidx.appcompat.widget.SuggestionsAdapter;
/* loaded from: classes.dex */
public class CursorFilter extends Filter {
    public CursorFilterClient mClient;

    /* loaded from: classes.dex */
    public interface CursorFilterClient {
    }

    public CursorFilter(CursorFilterClient cursorFilterClient) {
        this.mClient = cursorFilterClient;
    }

    @Override // android.widget.Filter
    public CharSequence convertResultToString(Object obj) {
        return ((SuggestionsAdapter) this.mClient).convertToString((Cursor) obj);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0049  */
    @Override // android.widget.Filter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.widget.Filter.FilterResults performFiltering(java.lang.CharSequence r5) {
        /*
            r4 = this;
            androidx.cursoradapter.widget.CursorFilter$CursorFilterClient r0 = r4.mClient
            androidx.appcompat.widget.SuggestionsAdapter r0 = (androidx.appcompat.widget.SuggestionsAdapter) r0
            java.util.Objects.requireNonNull(r0)
            if (r5 != 0) goto L_0x000c
            java.lang.String r5 = ""
            goto L_0x0010
        L_0x000c:
            java.lang.String r5 = r5.toString()
        L_0x0010:
            androidx.appcompat.widget.SearchView r1 = r0.mSearchView
            int r1 = r1.getVisibility()
            r2 = 0
            if (r1 != 0) goto L_0x0038
            androidx.appcompat.widget.SearchView r1 = r0.mSearchView
            int r1 = r1.getWindowVisibility()
            if (r1 == 0) goto L_0x0022
            goto L_0x0038
        L_0x0022:
            android.app.SearchableInfo r1 = r0.mSearchable     // Catch: RuntimeException -> 0x0030
            r3 = 50
            android.database.Cursor r5 = r0.getSearchManagerSuggestions(r1, r5, r3)     // Catch: RuntimeException -> 0x0030
            if (r5 == 0) goto L_0x0038
            r5.getCount()     // Catch: RuntimeException -> 0x0030
            goto L_0x0039
        L_0x0030:
            r5 = move-exception
            java.lang.String r0 = "SuggestionsAdapter"
            java.lang.String r1 = "Search suggestions query threw an exception."
            android.util.Log.w(r0, r1, r5)
        L_0x0038:
            r5 = r2
        L_0x0039:
            android.widget.Filter$FilterResults r0 = new android.widget.Filter$FilterResults
            r0.<init>()
            if (r5 == 0) goto L_0x0049
            int r1 = r5.getCount()
            r0.count = r1
            r0.values = r5
            goto L_0x004e
        L_0x0049:
            r5 = 0
            r0.count = r5
            r0.values = r2
        L_0x004e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.cursoradapter.widget.CursorFilter.performFiltering(java.lang.CharSequence):android.widget.Filter$FilterResults");
    }

    @Override // android.widget.Filter
    public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
        CursorFilterClient cursorFilterClient = this.mClient;
        Cursor cursor = ((CursorAdapter) cursorFilterClient).mCursor;
        Object obj = filterResults.values;
        if (obj != null && obj != cursor) {
            ((SuggestionsAdapter) cursorFilterClient).changeCursor((Cursor) obj);
        }
    }
}
