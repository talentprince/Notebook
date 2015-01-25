package org.weyoung.notebook;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.weyoung.notebook.sync.AccountService;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class AuthenticatorActivity extends AccountAuthenticatorActivity {
    public static final String ARG_ACCOUNT_NAME = "account_name";
    @InjectView(R.id.accountName)
    EditText accountName;
    @InjectView(R.id.accountPassword)
    EditText accountPassword;
    @InjectView(R.id.submit)
    Button submit;
    private AccountManager accountManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticator);
        ButterKnife.inject(this);

        accountManager = AccountManager.get(this);

        if(getIntent().getStringExtra(ARG_ACCOUNT_NAME) != null) {
            accountName.setText(getIntent().getStringExtra(ARG_ACCOUNT_NAME));
        }
    }

    @OnClick(R.id.submit)
    void submit() {
        if(TextUtils.isEmpty(accountName.getText()) || TextUtils.isEmpty(accountPassword.getText())) {
            Toast.makeText(this, R.string.notenough, Toast.LENGTH_SHORT).show();
            return;
        }
        String name = accountName.getText().toString();
        String password = accountPassword.getText().toString();
        String authToken = name + password;
        final Account account = new Account(name, AccountService.ACCOUNT_TYPE);

        //add account to manager
        accountManager.addAccountExplicitly(account, password, null);
        //add auth token to manager for getAuthToken
        accountManager.setAuthToken(account, AccountService.TOKEN_TYPE, authToken);

        //return data for AccountManager#addAccount & getAuthToken~~~
        Bundle data = new Bundle();
        data.putString(AccountManager.KEY_ACCOUNT_NAME, name);
        data.putString(AccountManager.KEY_ACCOUNT_TYPE, AccountService.ACCOUNT_TYPE);
        data.putString(AccountManager.KEY_AUTHTOKEN, authToken);
        Intent intent = new Intent();
        intent.putExtras(data);
        //set needed data for result, if not, it means error(canceled)
        setAccountAuthenticatorResult(data);
        finish();
    }

}
