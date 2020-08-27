package com.kd.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blankj.utilcode.util.LogUtils;
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
import com.kd.ui.listener.OnDropDownMenuListener;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mac
 */
public abstract class KdBaseListFragment extends KdBaseFragment {
    public int page = 1;
    public LinearLayout rootView;
    protected SearchView searchView;
    protected RecyclerView rvData;
    protected SwipeRefreshLayout refreshView;
    protected FrameLayout mFilterContentView;
    protected DropDownMenu dropDownMenu;
    protected Toolbar vToolBar;
    private MenuAdapter loacalMenuAdapter;
    private MenuAdapter filterMenuAdapter;
    private BaseQuickAdapter recycleAdapter;
    private boolean canLoadMore;
    private boolean canRefresh;

    public FilterParamContainer getCurrentParamContainer() {
        return currentParamContainer;
    }

    private FilterParamContainer currentParamContainer;

    public boolean isCanRefresh() {
        return canRefresh;
    }

    public void setCanRefresh(boolean canRefresh) {
        this.canRefresh = canRefresh;
        if (canRefresh) {
            initRefresh();
        } else {
            if (refreshView != null) {
                refreshView.setEnabled(false);
                refreshView.setOnRefreshListener(null);
            }
        }
    }

    public boolean isCanLoadMore() {
        return canLoadMore;
    }

    public void setCanLoadMore(boolean canLoadMore) {
        this.canLoadMore = canLoadMore;
        if (recycleAdapter != null) {
            initLoadMore(canLoadMore);
        }
    }

    protected void initViewDefauleConfig() {
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
        this.recycleAdapter.setEmptyView(R.layout.layout_nodata, this.rvData);
        this.rvData.setAdapter(this.recycleAdapter);
// TODO: 2020/8/10 待支持配置
        refreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                initRefreshParams();
                loadData();
            }
        });
        this.filterMenuAdapter = createFilterMenuAdapter();
        loacalMenuAdapter = this.filterMenuAdapter;
        if (loacalMenuAdapter != null) {
            this.dropDownMenu.setMenuAdapter(loacalMenuAdapter);
        }
    }

    private void initLoadMore(final boolean canLoadMore) {
        if (canLoadMore) {
            this.recycleAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    if (canLoadMore) {
                        LogUtils.e("onLoadMore");
                        if (recycleAdapter.getData().size() > 0) {
                            page++;
                            loadData();
                        }
                    }
                }
            });
        } else {
            recycleAdapter.setOnLoadMoreListener(null);
            recycleAdapter.setEnableLoadMore(false);
        }

    }

    private void initRefresh() {
        if (refreshView == null) {
            return;
        }
        this.refreshView.setEnabled(true);
        this.refreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LogUtils.d("do refresh");
                page = 1;
                getRecycleAdapter().getData().clear();
                loadData();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (LinearLayout) inflater.inflate(R.layout.view_common_list, container, false);
        initView(rootView);
        setLayout();
        loadData();
        return rootView;
    }


    protected abstract void loadData();

    protected abstract void setLayout();

    public Toolbar getToolBar() {
        return vToolBar;
    }

    @Override
    public void hideNavigationBar() {
        super.hideNavigationBar();
        if (vToolBar != null) {
            vToolBar.setVisibility(View.GONE);
        }
    }

    private void initView(View rootView) {
        searchView = (SearchView) rootView.findViewById(R.id.searchView);
        rvData = (RecyclerView) rootView.findViewById(R.id.rv_data);
        refreshView = (SwipeRefreshLayout) rootView.findViewById(R.id.refreshView);
        mFilterContentView = (FrameLayout) rootView.findViewById(R.id.mFilterContentView);
        dropDownMenu = (DropDownMenu) rootView.findViewById(R.id.dropDownMenu);
        initViewDefauleConfig();
        vToolBar = (Toolbar) rootView.findViewById(R.id.vToolBar);
        rootView = (LinearLayout) rootView.findViewById(R.id.rootView);
    }

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
        if (!paramBoolean) {
            this.searchView.setVisibility(View.VISIBLE);
            return;
        }
        this.searchView.setVisibility(View.GONE);
    }

    protected abstract void initRefreshParams();


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
        FilterParamContainer filterParamContainer = new FilterParamContainer();
        private List<List<FilterParam>> filterList = new ArrayList();
        private OnDropDownMenuListener onFilterDoneListener;

        public DropMenuListAdapter(List<List<FilterParam>> filterList, OnDropDownMenuListener paramOnDropDownMenuListener) {
            this.filterList = filterList;
            this.onFilterDoneListener = paramOnDropDownMenuListener;
        }

        private View createSingleListView(List<FilterParam> paramList, final Integer paramInteger) {
            SingleListView<FilterParam> singleListView = new SingleListView<FilterParam>(getActivity());
            singleListView.adapter(new SimpleTextAdapter<FilterParam>(null, getActivity()) {

                @Override
                protected void initCheckedTextView(FilterCheckedTextView paramAnonymousFilterCheckedTextView) {
                    int i = UIUtil.dp(getActivity(), 12);
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
                    currentParamContainer = filterParamContainer;
                    if (onFilterDoneListener != null) {
                        onFilterDoneListener.onMenuClick(paramInteger.intValue(), paramAnonymousFilterParam.getNameStr(), paramAnonymousFilterParam, filterParamContainer);
                    }
                    dropDownMenu.close();
                    initRefreshParams();
                    page = 1;
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


}
