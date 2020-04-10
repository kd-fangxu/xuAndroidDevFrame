package com.kd.view.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

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

import java.util.ArrayList;
import java.util.List;

/**
 * @author mac
 */
public abstract class BaseListActivity extends KdBaseActivity {
    protected DropDownMenu dropDownMenu;
    protected LinearLayout rootView;
    private MenuAdapter filterMenuAdapter;
    protected FrameLayout mFilterContentView;
    public int page = 1;
    private BaseQuickAdapter recycleAdapter;
    protected SwipeRefreshLayout refreshView;
    protected RecyclerView rvData;
    protected SearchView searchView;

    private void initView() {
        this.rvData = ((RecyclerView) findViewById(R.id.rv_data));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        this.rvData.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        this.recycleAdapter = createRecycleAdapter();
        this.recycleAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                BaseListActivity localBaseListActivity = BaseListActivity.this;
                localBaseListActivity.page += 1;
                BaseListActivity.this.loadData();
            }
        });
        this.rvData.setAdapter(this.recycleAdapter);
        this.refreshView = ((SwipeRefreshLayout) findViewById(R.id.refreshView));
        this.refreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                BaseListActivity localBaseListActivity = BaseListActivity.this;
                localBaseListActivity.page = 1;
                localBaseListActivity.getRecycleAdapter().getData().clear();
                BaseListActivity.this.loadData();
            }
        });
        this.mFilterContentView = ((FrameLayout) findViewById(R.id.mFilterContentView));
        this.dropDownMenu = ((DropDownMenu) findViewById(R.id.dropDownMenu));
        this.searchView = ((SearchView) findViewById(R.id.searchView));
        this.filterMenuAdapter = createFilterMenuAdapter();
        loacalMenuAdapter = this.filterMenuAdapter;
        if (loacalMenuAdapter != null) {
            this.dropDownMenu.setMenuAdapter(loacalMenuAdapter);
        }
        searchView = (SearchView) findViewById(R.id.searchView);
        rvData = (RecyclerView) findViewById(R.id.rv_data);
        refreshView = (SwipeRefreshLayout) findViewById(R.id.refreshView);
        mFilterContentView = (FrameLayout) findViewById(R.id.mFilterContentView);
        dropDownMenu = (DropDownMenu) findViewById(R.id.dropDownMenu);
        rootView = (LinearLayout) findViewById(R.id.rootView);
    }

   private  MenuAdapter loacalMenuAdapter;

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

    @Override
    public void loadData() {
        super.loadData();
    }

    @Override
    public void setLayout(Bundle paramBundle) {
        setContentView(R.layout.view_common_list);
        initView();
    }

    /**
     * 资源id
     *
     * @param color
     */
    public void setRootViewBackGroundColor(int color) {
//        rootView.setBackgroundResource(color);
        rootView.setBackgroundColor(getResources().getColor(color));
    }

    public void stopRefresh() {
        this.refreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                BaseListActivity.this.refreshView.setRefreshing(false);
            }
        }, 500L);
    }

    public class DropMenuListAdapter
            implements MenuAdapter {
        private List<List<FilterParam>> filterList = new ArrayList();
        FilterParamContainer filterParamContainer = new FilterParamContainer();
        private OnDropDownMenuListener onFilterDoneListener;

        public DropMenuListAdapter(List<List<FilterParam>> filterList, OnDropDownMenuListener paramOnDropDownMenuListener) {
            this.filterList = filterList;
            this.onFilterDoneListener = paramOnDropDownMenuListener;
        }

        private View createSingleListView(List<FilterParam> paramList, final Integer paramInteger) {
            SingleListView<FilterParam> singleListView = new SingleListView<FilterParam>(BaseListActivity.this);
            singleListView.adapter(new SimpleTextAdapter<FilterParam>(null, BaseListActivity.this) {

                @Override
                protected void initCheckedTextView(FilterCheckedTextView paramAnonymousFilterCheckedTextView) {
                    int i = UIUtil.dp(BaseListActivity.this, 15);
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
                    BaseListActivity.this.dropDownMenu.setPositionIndicatorText(paramInteger.intValue(), paramAnonymousFilterParam.getNameStr());
                    if (paramAnonymousFilterParam != null) {
                        DropMenuListAdapter.this.filterParamContainer.put(paramAnonymousFilterParam.getKey(), paramAnonymousFilterParam.getParamValue());
                    }
                    if (DropMenuListAdapter.this.onFilterDoneListener != null) {
                        DropMenuListAdapter.this.onFilterDoneListener.onMenuClick(paramInteger.intValue(), paramAnonymousFilterParam.getNameStr(), paramAnonymousFilterParam, DropMenuListAdapter.this.filterParamContainer);
                    }
                    BaseListActivity.this.dropDownMenu.close();
                    BaseListActivity.this.initRefreshParams();
                    BaseListActivity.this.refreshView.setRefreshing(true);
                    BaseListActivity.this.loadData();
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
            return UIUtil.dp(BaseListActivity.this.getApplicationContext(), 140);
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
