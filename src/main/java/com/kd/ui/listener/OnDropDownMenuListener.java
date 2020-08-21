package com.kd.ui.listener;

import com.kd.component.filter.vo.FilterParam;
import com.kd.component.filter.vo.FilterParamContainer;

/**
 * @author mac
 */
public interface OnDropDownMenuListener {
    /**
     * 筛选框选中事件
     *
     * @param paramInt
     * @param paramString
     * @param paramFilterParam
     * @param paramFilterParamContainer
     */
    void onMenuClick(int paramInt, String paramString, FilterParam paramFilterParam, FilterParamContainer paramFilterParamContainer);
}