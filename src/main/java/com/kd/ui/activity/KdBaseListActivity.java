package com.kd.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

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

import java.util.ArrayList;
import java.util.List;

/**
 * @author mac
 */
public abstract class KdBaseListActivity extends KdBaseActivity {
    public int page = 1;
    /**
     * keyword 子类的param 要添加
     */
    public String param_keyword = "";
    protected DropDownMenu dropDownMenu;
    protected LinearLayout rootView;
    protected FrameLayout mFilterContentView;
    protected SwipeRefreshLayout refreshView;
    protected RecyclerView rvData;
    protected SearchView searchView;
    private Toolbar toolbar;
    private MenuAdapter filterMenuAdapter;
    private BaseQuickAdapter recycleAdapter;
    private boolean canLoadMore;
    private boolean canRefresh;
    private MenuAdapter loacalMenuAdapter;

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

    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void hideNavigationBar() {
        super.hideNavigationBar();
        if (toolbar != null) {
            toolbar.setVisibility(View.GONE);
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

    private void initLoadMore(final boolean canLoadMore) {
        if (canLoadMore) {
            this.recycleAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    if (canLoadMore) {
                        LogUtils.d("load more");
                        if (recycleAdapter.getData().size() > 0) {
                            page++;
                            loadData();
                        }
                    }
                }
            });
        } else {
            recycleAdapter.setOnLoadMoreListener(null);
        }
    }

    public boolean isLoadOnSearchTextChanged() {
        return loadOnSearchTextChanged;
    }

    /**
     * searchView变化实时请求数据
     *
     * @param loadOnSearchTextChanged
     */
    public void setLoadOnSearchTextChanged(boolean loadOnSearchTextChanged) {
        this.loadOnSearchTextChanged = loadOnSearchTextChanged;
    }

    public boolean loadOnSearchTextChanged = false;

    protected void initViewDefauleConfig() {

        getSearchView().setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                LogUtils.e(s);
                param_keyword = s;
                if (isLoadOnSearchTextChanged()) {
                    return false;
                }
                loadData();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                LogUtils.e(s);
                param_keyword = s;
                if (isLoadOnSearchTextChanged()) {
                    loadData();
                }
                return false;
            }
        });
        getSearchView().setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                param_keyword = "";
                page = 1;
                loadData();
                return false;
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        this.rvData.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        this.recycleAdapter = createRecycleAdapter();
        initLoadMore(isCanLoadMore());
        this.rvData.setAdapter(this.recycleAdapter);
        if (isCanRefresh()) {
            initRefresh();
        } else {
            setCanRefresh(false);
        }
        this.filterMenuAdapter = createFilterMenuAdapter();
        loacalMenuAdapter = this.filterMenuAdapter;
        if (loacalMenuAdapter != null) {
            this.dropDownMenu.setMenuAdapter(loacalMenuAdapter);
        }
        this.recycleAdapter.setEmptyView(R.layout.layout_nodata, this.rvData);
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
                KdBaseListActivity localKdBaseListActivity = KdBaseListActivity.this;
                localKdBaseListActivity.page = 1;
                localKdBaseListActivity.getRecycleAdapter().getData().clear();
                KdBaseListActivity.this.loadData();
            }
        });
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
        BaseQuickAdapter recycleAdapter = createRecycleAdapter();
        return recycleAdapter;
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

    @Override
    public void loadData() {
        super.loadData();
    }

    @Override
    public void setLayout(Bundle paramBundle) {
        setContentView(R.layout.view_common_list);
        this.rvData = ((RecyclerView) findViewById(R.id.rv_data));
        this.refreshView = ((SwipeRefreshLayout) findViewById(R.id.refreshView));
        this.mFilterContentView = ((FrameLayout) findViewById(R.id.mFilterContentView));
        this.dropDownMenu = ((DropDownMenu) findViewById(R.id.dropDownMenu));
        this.searchView = ((SearchView) findViewById(R.id.searchView));
        rootView = (LinearLayout) findViewById(R.id.rootView);
        toolbar = findViewById(R.id.vToolBar);
        initViewDefauleConfig();
    }

    public void stopRefresh() {
        this.refreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                KdBaseListActivity.this.refreshView.setRefreshing(false);
            }
        }, 500L);
    }

    public FilterParamContainer getCurrentParamContainer() {
        return currentParamContainer;
    }

    private FilterParamContainer currentParamContainer;

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
            SingleListView<FilterParam> singleListView = new SingleListView<FilterParam>(KdBaseListActivity.this);
            singleListView.adapter(new SimpleTextAdapter<FilterParam>(null, KdBaseListActivity.this) {

                @Override
                protected void initCheckedTextView(FilterCheckedTextView paramAnonymousFilterCheckedTextView) {
                    int i = UIUtil.dp(KdBaseListActivity.this, 12);
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
                    KdBaseListActivity.this.dropDownMenu.setPositionIndicatorText(paramInteger.intValue(), paramAnonymousFilterParam.getNameStr());
                    if (paramAnonymousFilterParam != null) {
                        DropMenuListAdapter.this.filterParamContainer.put(paramAnonymousFilterParam.getKey(), paramAnonymousFilterParam.getParamValue());
                    }
                    currentParamContainer = filterParamContainer;
                    if (DropMenuListAdapter.this.onFilterDoneListener != null) {
                        DropMenuListAdapter.this.onFilterDoneListener.onMenuClick(paramInteger.intValue(), paramAnonymousFilterParam.getNameStr(), paramAnonymousFilterParam, DropMenuListAdapter.this.filterParamContainer);
                    }
                    KdBaseListActivity.this.dropDownMenu.close();
                    KdBaseListActivity.this.initRefreshParams();
                    page = 1;
                    KdBaseListActivity.this.refreshView.setRefreshing(true);
                    KdBaseListActivity.this.loadData();
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
            return UIUtil.dp(KdBaseListActivity.this.getApplicationContext(), 140);
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
