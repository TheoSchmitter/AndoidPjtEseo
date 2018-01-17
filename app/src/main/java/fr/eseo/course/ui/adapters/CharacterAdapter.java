package fr.eseo.course.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.course.R;
import fr.eseo.course.data.models.ImageCharactersModel;
import fr.eseo.course.ui.characters.CharacterDetailsShowActivity;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by amin on 11/01/2018.
 */

public class CharacterAdapter extends ArrayAdapter<ImageCharactersModel> {

    /**
     * Declare an inner interface to listen click event on device items
     */
    public interface OnCharacterSelectedListener {
        void handle(final ImageCharactersModel device);
    }

    private final OnCharacterSelectedListener onDeviceSelectedListener;

    public CharacterAdapter(@NonNull final Context context, final List<ImageCharactersModel> devices, final OnCharacterSelectedListener listener) {
        super(context, R.layout.characters_list_item, devices);
        onDeviceSelectedListener = listener;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull final ViewGroup parent) {
        View holder = convertView;
        if (convertView == null) {
            final LayoutInflater vi = LayoutInflater.from(getContext());
            holder = vi.inflate(R.layout.characters_list_item, null);
        }

        final ImageCharactersModel character = getItem(position);
        if (character == null) {
            return holder;
        }

        // display the name
        final ImageView characterImg = holder.findViewById(R.id.imageview1);
        if (characterImg != null) {
            characterImg.setImageResource(character.getSrc());
        }

        // When this device item is clicked, trigger the listener
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (onDeviceSelectedListener != null) {
                    onDeviceSelectedListener.handle(character);

                }
            }
        });

        return holder;
    }
}