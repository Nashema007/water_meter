package zw.nash.recieptsapp.di.auth;

import androidx.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import zw.nash.recieptsapp.di.ViewModelKey;
import zw.nash.recieptsapp.view_model.AuthViewModel;

@Module
public abstract class AuthViewModelsModule {


    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel authViewModel);

}
