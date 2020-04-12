package zw.nash.recieptsapp.view_model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import zw.nash.recieptsapp.api.AccountApi;
import zw.nash.recieptsapp.model.Account;
import zw.nash.recieptsapp.util.Resource;
import zw.nash.recieptsapp.util.SessionManager;

public class WaterBillViewModel extends ViewModel {

    private SessionManager sessionManager;
    private AccountApi accountApi;
    private MediatorLiveData<Resource<Account>> waterBill;
    private static final String TAG = "WaterBillViewModel";

    @Inject
    public WaterBillViewModel(SessionManager sessionManager, AccountApi accountApi) {
        this.sessionManager = sessionManager;
        this.accountApi = accountApi;
    }

    public LiveData<Resource<Account>> observeAccount(){
        if(waterBill == null) {
            waterBill = new MediatorLiveData<>();
            waterBill.setValue(Resource.loading(null));

            final LiveData<Resource<Account>> source =
                    LiveDataReactiveStreams.fromPublisher(
                            accountApi.getAccount(
                                    "nashema")//sessionManager.getAuthUser().getValue().data.getUsername()
                    .onErrorReturn(throwable -> {
                        Account accountError = new Account();
                        accountError.setAccountNumber("");
                        return accountError;
                    }).map((Function<Account, Resource<Account>>) account -> {
                                if (account == null) {
                            return Resource.error("Something went wrong..", null);
                        }
                        return Resource.success(account);
                    }).subscribeOn(Schedulers.io()));

            waterBill.addSource(source, accountResource -> {
                Log.d(TAG, "observeAccount: " + accountResource.data.toString());
                waterBill.setValue(accountResource);
                waterBill.removeSource(source);
            });


        }

        return waterBill;
    }
}
