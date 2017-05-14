package testexample.piotr.com.gitbrowser;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import javax.inject.Inject;

import testexample.piotr.com.gitbrowser.app.list.DaggerUsersListComponent;
import testexample.piotr.com.gitbrowser.app.list.UsersListAdapter;
import testexample.piotr.com.gitbrowser.app.list.UsersListContract;
import testexample.piotr.com.gitbrowser.app.list.UsersListModule;
import testexample.piotr.com.gitbrowser.app.list.UsersListPresenter;
import testexample.piotr.com.gitbrowser.app.model.ModelUser;
import testexample.piotr.com.gitbrowser.data.component.DaoComponent;
import testexample.piotr.com.gitbrowser.databinding.ActivityListBinding;

public class ListActivity extends AppCompatActivity implements UsersListContract.View {

    private String TAG = ListActivity.class.getName();

    @Inject
    UsersListPresenter fragmentListPresenter;

    private ActivityListBinding activityListBinding;
    private UsersListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityListBinding = DataBindingUtil.setContentView(this, R.layout.activity_list);
        activityListBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        activityListBinding.recyclerView.setHasFixedSize(true);
        DaoComponent daoComponent = ((App) getApplication()).getDaoComponent();
        DaggerUsersListComponent.builder().daoComponent(daoComponent).usersListModule(new UsersListModule(this)).build().inject(this);
        fragmentListPresenter.loadData();

        adapter = new UsersListAdapter(new ModelUser[0]);
        activityListBinding.recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new UsersListAdapter.OnUserClickListener() {
            @Override
            public void onUserClick(ModelUser user) {
                loadDetails(user);
            }
        });


    }

    @Override
    public void publishData(ModelUser[] data) {
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadDetails(ModelUser user) {
        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);
    }
}
