package tinfoilxd.criminalintent;

import android.annotation.TargetApi;
import android.support.v4.app.DialogFragment;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by TinfoilxD on 4/10/15.
 */
@TargetApi(11)
public class ImageFragment extends DialogFragment
{
    public static final String EXTRA_IMAGE_PATH = "tinfoilxd.android.criminalintent.image_path";

    private ImageView mImageView;

    public static ImageFragment newInstance(String imagePath)
    {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_IMAGE_PATH,imagePath);

        ImageFragment fragment = new ImageFragment();
        fragment.setArguments(args);
        fragment.setStyle(DialogFragment.STYLE_NO_TITLE,0);

        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mImageView = new ImageView(getActivity());
        String path = (String)getArguments().getSerializable(EXTRA_IMAGE_PATH);
        BitmapDrawable image = PictureUtils.getScaledDrawable(getActivity(),path);

        mImageView.setImageDrawable(image);

        return mImageView;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        PictureUtils.cleanImageView(mImageView);
    }
}
