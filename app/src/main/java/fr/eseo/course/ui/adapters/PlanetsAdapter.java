package fr.eseo.course.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fr.eseo.course.R;
import fr.eseo.course.data.models.ImageCharactersModel;
import fr.eseo.course.data.models.Planet;

/**
 * Created by amin on 17/01/2018.
 */

public class PlanetsAdapter extends ArrayAdapter<Planet> {

    /**
     * Declare an inner interface to listen click event on device items
     */
    public interface OnPlanetSelectedListener {
        void handle(final Planet planet);
    }

    private final OnPlanetSelectedListener onPlanetSelectedListener;

    public PlanetsAdapter(@NonNull final Context context, final List<Planet> devices, final OnPlanetSelectedListener listener) {
        super(context, R.layout.characters_list_item, devices);
        onPlanetSelectedListener = listener;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull final ViewGroup parent) {
        View holder = convertView;
        if (convertView == null) {
            final LayoutInflater vi = LayoutInflater.from(getContext());
            holder = vi.inflate(R.layout.planets_list_item, null);
        }

        final Planet planets = getItem(position);
        if (planets == null) {
            return holder;
        }

        // display the name
        final TextView planetTxtName = holder.findViewById(R.id.textPlanetView);
        if(planetTxtName!=null)
        {
            planetTxtName.setText(planets.getName());
        }

        // When this device item is clicked, trigger the listener
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (onPlanetSelectedListener != null) {
                    onPlanetSelectedListener.handle(planets);

                }
            }
        });

        return holder;
    }
}
