package testexample.piotr.com.gitbrowser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import testexample.piotr.com.gitbrowser.app.list.UsersListFragment;
public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        if (savedInstanceState == null) {
            if (findViewById(R.id.fragment_list) == null)
                getSupportFragmentManager().beginTransaction().add(R.id.fragments_container, new
                        UsersListFragment()).commit();
        }
    }
}
