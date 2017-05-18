package testexample.piotr.com.gitbrowser.app.list;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Fade;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import javax.inject.Inject;

import testexample.piotr.com.gitbrowser.App;
import testexample.piotr.com.gitbrowser.R;
import testexample.piotr.com.gitbrowser.app.details.UserDetailFragment;
import testexample.piotr.com.gitbrowser.app.model.ModelUser;
import testexample.piotr.com.gitbrowser.data.component.DaoComponent;
import testexample.piotr.com.gitbrowser.databinding.FragmentListBinding;
import testexample.piotr.com.gitbrowser.util.DetailsTransition;

/**
 * Created by piotr on 18/05/17.
 */
public class UsersListFragment extends Fragment implements UsersListContract.View {

    @Inject
    UsersListPresenter fragmentListPresenter;
    private FragmentListBinding fragmentListBinding;
    private UsersListAdapter adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaoComponent daoComponent = ((App) getActivity().getApplication()).getDaoComponent();
        DaggerUsersListComponent.builder().daoComponent(daoComponent).usersListModule(new
                UsersListModule(this)).build().inject(this);

        adapter = new UsersListAdapter(new ModelUser[0]);
        fragmentListPresenter.loadData();


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        fragmentListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, parent, false);
        return fragmentListBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentListBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fragmentListBinding.recyclerView.setHasFixedSize(true);
        fragmentListBinding.recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new UsersListAdapter.OnUserClickListener() {

            @Override
            public void onUserClick(ModelUser user, ImageView profile) {
                showDetails(user, profile);
            }
        });
    }

    @Override
    public void publishData(ModelUser[] data) {
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showDetails(ModelUser user, ImageView image) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        UserDetailFragment fragment = new UserDetailFragment();
        fragment.setArguments(bundle);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fragment.setSharedElementEnterTransition(new DetailsTransition());
            fragment.setEnterTransition(new Fade());
            setExitTransition(new Fade());

            fragment.setAllowEnterTransitionOverlap(false);
            fragment.setAllowReturnTransitionOverlap(true);
            fragment.setSharedElementReturnTransition(new DetailsTransition());

        }

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addSharedElement(image, "test")
                .replace(R.id.fragments_container, fragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();

    }
}
