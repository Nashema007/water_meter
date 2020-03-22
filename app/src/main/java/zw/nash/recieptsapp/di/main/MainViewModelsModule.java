package zw.nash.recieptsapp.di.main;

import androidx.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import zw.nash.recieptsapp.di.ViewModelKey;
import zw.nash.recieptsapp.view_model.ProfileViewModel;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileFragment(ProfileViewModel viewModel);

//    @Binds
//    @IntoMap
//    @ViewModelKey(PostsViewModel.class)
//    public abstract ViewModel bindPostsFragment(PostsViewModel viewModel);
}
