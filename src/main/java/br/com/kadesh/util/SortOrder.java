package br.com.kadesh.util;

/**
 * Created by rmpestano on 10/31/14.
 */
public enum SortOrder {

    ASCENDING, DESCENDING, UNSORTED;

    public boolean isAscending() {
        return ASCENDING.equals(this);
    }
}
