/*
 * Copyright © 2019-Now Human Horizons (Shanghai) Cloud Computing Technology Co., Ltd. All
 * Rights Reserved.
 */
package com.tsdl.common.sdk.tab;

import android.graphics.drawable.Drawable;

/**
 * @ClassName ITabItem
 * @Description This is a ITabItem
 * @Author zhaozhaoli
 * @E-mail : Zhaozhao_Li1@human-horizons.com
 * @Date 2021/11/24
 * @Version 1.0
 */
public interface ITabItem {

  /**
   * get segmentedControlItem count.
   *
   * @return int
   */
  int getCount();

  /**
   * get segmentedControlItem item.
   *
   * @param position
   * @return BaseSegmentedControlItem
   */
  BaseTabItem getItem(int position);

  /**
   * get item type is string or image.
   *
   * @param position
   * @return int
   */
  int getType(int position);

  /**
   * if item is string to git item text.
   *
   * @param position
   * @return string
   */
  String getName(int position);

  /**
   * if item is image to git item image select.
   *
   * @param position
   * @return Drawable
   */
  Drawable getItemDrawable(int position);

  /**
   * if item is image to git item image background.
   *
   * @param position
   * @return Drawable
   */
  Drawable getBackDrawable(int position);

  /**
   * if item is image to git item image disable.
   *
   * @param position
   * @return Drawable
   */

  Drawable getDisableDrawable(int position);

  /**
   * get item is can click.
   *
   * @param position
   * @return
   */
  boolean getItemEnable(int position);

  /**
   * get item text size
   *
   * @param position int position
   * @return int text size
   */
  int getItemTextSize(int position);

  /**
   * get item select text size
   *
   * @param position int position
   * @return int text size
   */
  int getItemSelectTextSize(int position);
}
