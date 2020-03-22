package zw.nash.recieptsapp.di;

import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import zw.nash.recieptsapp.view_model.ViewModelProviderFactory;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);

}
