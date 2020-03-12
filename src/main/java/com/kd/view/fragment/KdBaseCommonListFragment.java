package com.kd.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.xudeveframe.R;
import com.kd.component.filter.DropDownMenu;
import com.kd.component.filter.adapter.MenuAdapter;
import com.kd.component.filter.adapter.SimpleTextAdapter;
import com.kd.component.filter.interfaces.OnFilterItemClickListener;
import com.kd.component.filter.typeview.SingleListView;
import com.kd.component.filter.util.UIUtil;
import com.kd.component.filter.view.FilterCheckedTextView;
import com.kd.component.filter.vo.FilterParam;
import com.kd.component.filter.vo.FilterParamContainer;
import com.kd.net.param.BaseRequestParams;
import com.kd.view.activity.BaseListActivity;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mac
 */
public abstract class KdBaseCommonListFragment extends KdBaseFragment {
    protected SearchView searchView;
    protected RecyclerView rvData;
    protected SwipeRefreshLayout refreshView;
    protected FrameLayout mFilterContentView;
    protected DropDownMenu dropDownMenu;
    private MenuAdapter filterMenuAdapter;
    public int page = 1;
    private BaseQuickAdapter recycleAdapter;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.view_common_list, container, false);
        initView(rootView);
        loadData();
        return rootView;
    }

    private void initView(View rootView) {
        searchView = (SearchView) rootView.findViewById(R.id.searchView);
        rvData = (RecyclerView) rootView.findViewById(R.id.rv_data);
        refreshView = (SwipeRefreshLayout) rootView.findViewById(R.id.refreshView);
        mFilterContentView = (FrameLayout) rootView.findViewById(R.id.mFilterContentView);
        dropDownMenu = (DropDownMenu) rootView.findViewById(R.id.dropDownMenu);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        this.rvData.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        this.recycleAdapter = createRecycleAdapter();
        this.recycleAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page += 1;
                loadData();
            }
        });
        this.rvData.setAdapter(this.recycleAdapter);
    }

    MenuAdapter loacalMenuAdapter;

    public abstract MenuAdapter createFilterMenuAdapter();

    protected abstract BaseQuickAdapter createRecycleAdapter();

    public int getAutoPage() {
        return this.page;
    }

    public DropDownMenu getDropDownMenu() {
        return this.dropDownMenu;
    }

    public BaseQuickAdapter getRecycleAdapter() {
        return this.recycleAdapter;
    }

    public RecyclerView getRecycleView() {
        return this.rvData;
    }

    public SearchView getSearchView() {
        return this.searchView;
    }

    public void hideFilter(boolean paramBoolean) {
        if (paramBoolean) {
            this.dropDownMenu.hideFilterTab();
            return;
        }
        this.dropDownMenu.showFilterTab();
    }

    public void hideSearchView(boolean paramBoolean) {
        if (paramBoolean) {
            this.searchView.setVisibility(View.VISIBLE);
            return;
        }
        this.searchView.setVisibility(View.GONE);
    }

    protected abstract void initRefreshParams();

    @Override
    public void loadData() {
        super.loadData();
    }


    public void stopRefresh() {
        this.refreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshView.setRefreshing(false);
            }
        }, 500L);
    }

    public class DropMenuListAdapter
            implements MenuAdapter {
        private List<List<FilterParam>> filterList = new ArrayList();
        FilterParamContainer filterParamContainer = new FilterParamContainer();
        private BaseListActivity.OnDropDownMenuListener onFilterDoneListener;

        public DropMenuListAdapter(List<List<FilterParam>> filterList, BaseListActivity.OnDropDownMenuListener paramOnDropDownMenuListener) {
            this.filterList = filterList;
            this.onFilterDoneListener = paramOnDropDownMenuListener;
        }

        private View createSingleListView(List<FilterParam> paramList, final Integer paramInteger) {
            SingleListView<FilterParam> singleListView = new SingleListView<FilterParam>(getActivity());
            singleListView.adapter(new SimpleTextAdapter<FilterParam>(null, getActivity()) {

                @Override
                protected void initCheckedTextView(FilterCheckedTextView paramAnonymousFilterCheckedTextView) {
                    int i = UIUtil.dp(getActivity(), 15);
                    paramAnonymousFilterCheckedTextView.setPadding(i, i, 0, i);
                }

                @Override
                public String provideText(FilterParam paramAnonymousFilterParam) {
                    return paramAnonymousFilterParam.getNameStr();
                }
            });
            singleListView.onItemClick(new OnFilterItemClickListener<FilterParam>() {
                @Override
                public void onItemClick(FilterParam paramAnonymousFilterParam) {
                    dropDownMenu.setPositionIndicatorText(paramInteger.intValue(), paramAnonymousFilterParam.getNameStr());
                    if (paramAnonymousFilterParam != null) {
                        filterParamContainer.put(paramAnonymousFilterParam.getKey(), paramAnonymousFilterParam.getParamValue());
                    }
                    if (onFilterDoneListener != null) {
                        onFilterDoneListener.onMenuClick(paramInteger.intValue(), paramAnonymousFilterParam.getNameStr(), paramAnonymousFilterParam, filterParamContainer);
                    }
                    dropDownMenu.close();
                    initRefreshParams();
                    refreshView.setRefreshing(true);
                    loadData();
                }
            });
            singleListView.setList(paramList, 0);

            return singleListView;
        }

        public void addFilterParam(BaseRequestParams paramBaseRequestParams) {
            getFilterParamContainer().fillNetParam(paramBaseRequestParams);
        }

        @Override
        public int getBottomMargin(int paramInt) {
            return UIUtil.dp(x.app().getApplicationContext(), 140);
        }

        public FilterParamContainer getFilterParamContainer() {
            return this.filterParamContainer;
        }

        @Override
        public int getMenuCount() {
            return this.filterList.size();
        }

        @Override
        public String getMenuTitle(int paramInt) {
            FilterParam localFilterParam = (FilterParam) ((List) this.filterList.get(paramInt)).get(0);
            if (localFilterParam != null) {
                return localFilterParam.getNameStr();
            }
            return "";
        }

        @Override
        public View getView(int paramInt, FrameLayout paramFrameLayout) {
            paramFrameLayout.getChildAt(paramInt);
            return createSingleListView((List) this.filterList.get(paramInt), Integer.valueOf(paramInt));
        }
    }

    public interface OnDropDownMenuListener {
        void onMenuClick(int paramInt, String paramString, FilterParam paramFilterParam, FilterParamContainer paramFilterParamContainer);
    }
}
