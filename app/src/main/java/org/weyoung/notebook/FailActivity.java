package org.weyoung.notebook;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class FailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, R.string.oneaccount, Toast.LENGTH_LONG).show();
        finish();
    }
}
