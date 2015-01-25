package org.weyoung.notebook.sync;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;

import org.weyoung.notebook.AuthenticatorActivity;
import org.weyoung.notebook.FailActivity;

public class AccountService extends Service {
    private Authenticator authenticator;
    public static final String ACCOUNT_TYPE = "org.weyoung.notebook";
    public static final String TOKEN_TYPE = "org.weyoung.notebook.admin";

    @Override
    public void onCreate() {
        authenticator = new Authenticator(this);
    }
    @Override
    public IBinder onBind(Intent intent) {
        return authenticator.getIBinder();
    }


    class Authenticator extends AbstractAccountAuthenticator {
        private Context context;
        private AccountManager accountManager;

        public Authenticator(Context context) {
            super(context);
            this.context = context;
            accountManager = AccountManager.get(context);

        }

        @Override
        public Bundle editProperties(AccountAuthenticatorResponse accountAuthenticatorResponse,
                                     String s) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Bundle addAccount(AccountAuthenticatorResponse response, String accountType, String authTokenType, String[] requiredFeatures, Bundle options)
                throws NetworkErrorException {
            final Bundle bundle = new Bundle();
            //only one account permitted
            if(accountManager.getAccountsByType(ACCOUNT_TYPE).length > 0) {
                final Intent intent = new Intent(context, FailActivity.class);
                intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
                bundle.putParcelable(AccountManager.KEY_INTENT, intent);
                return bundle;
            }
            final Intent intent = new Intent(context, AuthenticatorActivity.class);
            intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);

            bundle.putParcelable(AccountManager.KEY_INTENT, intent);
            return bundle;
        }

        @Override
        public Bundle confirmCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse,
                                         Account account, Bundle bundle)
                throws NetworkErrorException {
            return null;
        }

        @Override
        public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String s, Bundle bundle)
                throws NetworkErrorException {
            //get one existing token
            String authToken = accountManager.peekAuthToken(account, TOKEN_TYPE);

            //if not, might be expired, register again
            if (TextUtils.isEmpty(authToken)) {
                final String password = accountManager.getPassword(account);
                if (password != null) {
                    try {
                        //get new token
                        authToken = account.name + password;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            //without password, need to sign again
            if (!TextUtils.isEmpty(authToken)) {
                final Bundle result = new Bundle();
                result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
                result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);
                result.putString(AccountManager.KEY_AUTHTOKEN, authToken);
                return result;
            }

            //no account data at all, need to do a sign
            final Intent intent = new Intent(context, AuthenticatorActivity.class);
            intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
            intent.putExtra(AuthenticatorActivity.ARG_ACCOUNT_NAME, account.name);
            final Bundle b = new Bundle();
            b.putParcelable(AccountManager.KEY_INTENT, intent);
            return b;
        }

        @Override
        public String getAuthTokenLabel(String s) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Bundle updateCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse,
                                        Account account, String s, Bundle bundle)
                throws NetworkErrorException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Bundle hasFeatures(AccountAuthenticatorResponse accountAuthenticatorResponse,
                                  Account account, String[] strings)
                throws NetworkErrorException {
            throw new UnsupportedOperationException();
        }
    }
}
