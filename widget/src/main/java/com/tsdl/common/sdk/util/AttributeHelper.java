/*
 * Copyright © 2019-2020 Human Horizons (Shanghai) Cloud Computing Technology Co., Ltd. All Rights Reserved.
 */

package com.tsdl.common.sdk.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseIntArray;

import java.util.Arrays;

public class AttributeHelper {

    /**
     * read target attribute values.
     *
     * @param context          the context
     * @param attrs            the AttributeSet
     * @param targetAttributes the targetAttributes array
     * @param defStyleAttr     the default style attribute
     * @param defStyleRes      the default resource
     * @return the values
     */
    public static SparseIntArray readAttributeValues(Context context, AttributeSet attrs,
                                                     int[] targetAttributes,
                                                     int defStyleAttr, int defStyleRes) {
        if (attrs == null || targetAttributes == null || targetAttributes.length == 0) {
            return new SparseIntArray();
        }
        //  important action sort Attribute
        Arrays.sort(targetAttributes);
        SparseIntArray result = new SparseIntArray(targetAttributes.length);
        TypedArray appearance = context.obtainStyledAttributes(attrs, targetAttributes,
            defStyleAttr, defStyleRes);
        for (int i = 0; i < targetAttributes.length; i++) {
            int targetResourceId = appearance.getResourceId(i, -1);
            result.put(targetAttributes[i], targetResourceId);
        }
        appearance.recycle();
        return result;
    }

    /**
     * read target attribute values.
     *
     * @param context          the context
     * @param resId            the AttributeSet
     * @param targetAttributes the targetAttributes array
     * @return the values
     */
    public static SparseIntArray readAttributeValues(Context context, int resId,
                                                     int[] targetAttributes) {
        if (targetAttributes == null || targetAttributes.length == 0) {
            return new SparseIntArray();
        }
        //  important action sort Attribute
        Arrays.sort(targetAttributes);
        SparseIntArray result = new SparseIntArray(targetAttributes.length);
        TypedArray appearance = context.obtainStyledAttributes(resId, targetAttributes);
        for (int i = 0; i < targetAttributes.length; i++) {
            int targetResourceId = appearance.getResourceId(i, -1);
            result.put(targetAttributes[i], targetResourceId);
        }
        appearance.recycle();
        return result;
    }

    /**
     * is resource id is valid.
     *
     * @param resourceId the resource id
     * @return the result
     */
    public static boolean isValid(int resourceId) {
        if (resourceId == 0 || resourceId == -1) {
            return false;
        }
        return true;
    }
}
