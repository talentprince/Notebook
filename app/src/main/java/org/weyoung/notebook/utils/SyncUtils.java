package org.weyoung.notebook.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.content.ContentResolver;
import android.os.Bundle;

import org.weyoung.notebook.data.NotebookProvider;
import org.weyoung.notebook.sync.AccountService;

public class SyncUtils {

    private static final long SYNC_FREQUENCY = 60 * 60;  // 1 hour (in seconds)
    private static final String CONTENT_AUTHORITY = NotebookProvider.AUTHORITY;
    private static Account account;


    public static void TriggerRefresh() {
        Bundle b = new Bundle();
        b.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        b.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        ContentResolver.requestSync(
                account,
                CONTENT_AUTHORITY,
                b);
    }

    public static void CheckAccount(Activity context) {
        final AccountManager accountManager = AccountManager.get(context);
        final Account availableAccounts[] = accountManager.getAccountsByType(AccountService.ACCOUNT_TYPE);
        //no account, create one
        if (availableAccounts.length == 0) {
            accountManager.addAccount(AccountService.ACCOUNT_TYPE, AccountService.TOKEN_TYPE, null, null, context, new AccountManagerCallback<Bundle>() {
                @Override
                public void run(AccountManagerFuture<Bundle> future) {
                    try {
                        Bundle result = future.getResult();
                        String name = result.getString(AccountManager.KEY_ACCOUNT_NAME);
                        String type = result.getString(AccountManager.KEY_ACCOUNT_TYPE);
                        account = new Account(name, type);
                        setSyncProperty(account);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, null);
        } else {//or get one
            accountManager.getAuthTokenByFeatures(AccountService.ACCOUNT_TYPE, AccountService.TOKEN_TYPE, null, context, null, null, new AccountManagerCallback<Bundle>() {
                @Override
                public void run(AccountManagerFuture<Bundle> future) {
                    try {
                        Bundle result = future.getResult();
                        String name = result.getString(AccountManager.KEY_ACCOUNT_NAME);
                        String type = result.getString(AccountManager.KEY_ACCOUNT_TYPE);
                        setSyncProperty(new Account(name, type));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, null);
        }
    }

    private static void setSyncProperty(Account account) {
        //set sync property
        ContentResolver.setIsSyncable(account, CONTENT_AUTHORITY, 1);
        ContentResolver.setSyncAutomatically(account, CONTENT_AUTHORITY, true);
        ContentResolver.addPeriodicSync(account, CONTENT_AUTHORITY, new Bundle(), SYNC_FREQUENCY);
        TriggerRefresh();
    }
}
