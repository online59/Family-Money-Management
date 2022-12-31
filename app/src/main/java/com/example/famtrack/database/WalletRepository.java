package com.example.famtrack.database;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.famtrack.R;
import com.example.famtrack.api.Wallet;
import com.example.famtrack.utils.Utils;

import java.util.List;

public class WalletRepository {

    private final WalletDao walletDao;
    private static WalletRepository instance;

    private WalletRepository(Application application) {
        WalletDatabase walletDatabase = WalletDatabase.getInstance(application);
        walletDao = walletDatabase.walletDao();
    }

    public static WalletRepository getInstance(Application application) {
        if (instance == null) {
            instance = new WalletRepository(application);
        }
        return instance;
    }

    public LiveData<List<WalletDatabaseModel>> getAllWallet() {
        return walletDao.getAllWallet();
    }

    public void insetWalletData(List<Wallet> walletList) {
        new InsetNewWalletTask(walletDao).execute(walletList);
    }

    public void deleteWallet() {
        new DeleteWalletTask(walletDao).execute();
    }

    private static class InsetNewWalletTask extends AsyncTask<List<Wallet>, Void, Void> {

        private final WalletDao walletDao;

        public InsetNewWalletTask(WalletDao walletDao) {
            this.walletDao = walletDao;
        }

        @Override
        protected Void doInBackground(List<Wallet>... wallet) {

            for (int position = 0; position < wallet[0].size(); position++) {

                Wallet walletData = wallet[0].get(position);

                WalletDatabaseModel walletDatabaseModel = new WalletDatabaseModel();
                walletDatabaseModel.setUid(walletData.getGroupId());
                walletDatabaseModel.setIvWallet(R.drawable.united_states);
                walletDatabaseModel.setTvWalletName(walletData.getGroupName());
                walletDatabaseModel.setTvCurrentBalance(String.valueOf(walletData.getGroupBalance()));
                walletDatabaseModel.setTvLastActive(Utils.getDate(walletData.getGroupActiveTime()));
                walletDatabaseModel.setTvMemberCount(walletData.getGroupMember());

                walletDao.insetNewWallet(walletDatabaseModel);
            }

            return null;
        }
    }

    private static class DeleteWalletTask extends AsyncTask<Void, Void, Void> {

        private final WalletDao walletDao;

        public DeleteWalletTask(WalletDao walletDao) {
            this.walletDao = walletDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            walletDao.deleteAllWallet();
            return null;
        }
    }
}
