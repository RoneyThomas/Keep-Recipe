package com.keeprecipes.android.presentation.photoDetail;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.keeprecipes.android.presentation.addRecipe.PhotoDTO;

import java.util.List;

public class ScreenSlidePagerAdapter extends FragmentStateAdapter {
    private final List<PhotoDTO> photoDTOList;

    public ScreenSlidePagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, List<PhotoDTO> photoDTOList) {
        super(fragmentManager, lifecycle);
        this.photoDTOList = photoDTOList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        new PhotoDetailImageFragment();
        return PhotoDetailImageFragment.newInstance(photoDTOList.get(position).uri);
    }

    @Override
    public int getItemCount() {
        return photoDTOList.size();
    }
}
