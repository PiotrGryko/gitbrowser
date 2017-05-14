package testexample.piotr.com.gitbrowser.app.list;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import testexample.piotr.com.gitbrowser.BR;
import testexample.piotr.com.gitbrowser.R;
import testexample.piotr.com.gitbrowser.app.model.ModelUser;

/**
 * Created by piotr on 12/05/17.
 */

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.UserViewHolder> {

    public interface OnUserClickListener {
        void onUserClick(ModelUser user);
    }

    private ModelUser[] data;
    private OnUserClickListener onItemClickListener;

    public UsersListAdapter(ModelUser[] data)
    {
        this.data=data;
    }

    public void setData(ModelUser[] data)
    {
        this.data=data;
    }

    public void setOnItemClickListener(OnUserClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_user, parent,false);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, final int position) {
        holder.viewDataBinding.setVariable(BR.user, data[position]);
        holder.viewDataBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) onItemClickListener.onUserClick(data[position]);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class UserViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding viewDataBinding;

        public UserViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            viewDataBinding = DataBindingUtil.bind(itemLayoutView);
        }
    }
}
