package com.example.lab_posters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterViewHolder> {
    private List<Poster> posterList;

    private PosterListener pListener;

    public PosterAdapter(List<Poster> posterList, PosterListener pListener) {
        this.posterList = posterList;
        this.pListener = pListener;
    }

    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PosterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_poster, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        holder.bindPoster(posterList.get(position));
    }

    @Override
    public int getItemCount() {
        return posterList.size();
    }
    public List<Poster> getSelectedPosters(){
        List<Poster> selectedPosters = new ArrayList<>();
        for( Poster poster: this.posterList){
            selectedPosters.add(poster);
        }
        return selectedPosters;
    }
    class PosterViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout layoutPosters;

        View viewBackground;

        RoundedImageView imagePoster;

        TextView textName, textCreator, textStory;

        RatingBar ratingBar;

        ImageView imageSelected;

        /**
         * the view holder for the individual posters added to the List.
         * @param itemView
         */
        public PosterViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutPosters = itemView.findViewById(R.id.layoutPoster);
            viewBackground = itemView.findViewById(R.id.viewBackground);
            imagePoster = itemView.findViewById(R.id.imagePosters);
            textName = itemView.findViewById(R.id.textName);
            textCreator = itemView.findViewById(R.id.textCreator);
            textStory = itemView.findViewById(R.id.textStory);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            imageSelected = itemView.findViewById(R.id.imageSelected);

        }

        /**
         * The method that sets all of the different fields for a poster
         * @param poster the poster that is being set to a specific field in the poster list
         */
        void bindPoster(final Poster poster){
            imagePoster.setImageResource(poster.image);
            textName.setText(poster.name);
            textCreator.setText(poster.creator);
            textStory.setText(poster.story);
            ratingBar.setRating(poster.rating);
            if(poster.isSelected){
                viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                imageSelected.setVisibility(View.VISIBLE);
            }else{
                viewBackground.setBackgroundResource(R.drawable.poster_background);
                imageSelected.setVisibility(View.GONE);
            }
            layoutPosters.setOnClickListener(new View.OnClickListener() {
                @Override
                /**
                 * A method that changes the Poster selected background
                 * depending on whether a poster is selected or not.
                 */
                public void onClick(View view) {
                    if(poster.isSelected){
                        viewBackground.setBackgroundResource(R.drawable.poster_background);
                        imageSelected.setVisibility(View.GONE);
                        poster.isSelected = false;
                        if(getSelectedPosters().isEmpty()){
                            pListener.onPosterAction(false);
                        }
                    }else{
                        viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                        imageSelected.setVisibility(View.VISIBLE);
                        poster.isSelected=true;
                        pListener.onPosterAction(true);
                    }
                }
            });
        }


    }
}
