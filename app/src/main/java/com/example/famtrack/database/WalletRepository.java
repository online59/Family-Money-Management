package com.example.famtrack.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

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

    public LiveData<List<WalletModel>> getAllWallet() {
        return walletDao.getAllWallet();
    }

    public void insetNewWallet(WalletModel walletModel) {
        new InsetNewWalletTask(walletDao).execute(walletModel);
    }

    public void deleteWallet() {
        new DeleteWalletTask(walletDao).execute();
    }

    private static class InsetNewWalletTask extends AsyncTask<WalletModel, Void, Void> {

        private final WalletDao walletDao;

        public InsetNewWalletTask(WalletDao walletDao) {
            this.walletDao = walletDao;
        }

        @Override
        protected Void doInBackground(WalletModel... walletModels) {
            walletDao.insetNewWallet(walletModels[0]);
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
