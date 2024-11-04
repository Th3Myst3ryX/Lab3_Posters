package com.example.lab_posters;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PosterListener{

    private Button buttonWatchlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView posterRecyclerView = findViewById((R.id.postersRView));
        buttonWatchlist = findViewById(R.id.buttonWatchlist);

        //prepare data

        List<Poster> posterList = new ArrayList<>();

        Poster django = new Poster();

        django.image = R.drawable.movieposter1;
        django.name = "Django Unchained";
        django.creator = "Quentin Tarantino";
        django.rating = 4.3f;
        django.story = "This is a movie about a slave bounty hunter.";

        posterList.add(django);

        Poster spirit = new Poster();

        spirit.image = R.drawable.movieposter2;
        spirit.name = "Spirited Away";
        spirit.creator = "Hayao Miyazaki";
        spirit.rating = 4.3f;
        spirit.story = "This is a movie about a girl who goes on a spiritual adventure.";

        posterList.add(spirit);


        Poster titan = new Poster();

        titan.image = R.drawable.movieposter3;
        titan.name = "Remember The Titans";
        titan.creator = "Boaz Yakin";
        titan.rating = 3.9f;
        titan.story = "A football team learns to overcome race and hatred.";

        posterList.add(titan);

        Poster nemo = new Poster();

        nemo.image = R.drawable.movieposter4;
        nemo.name = "Finding Nemo";
        nemo.creator = "Andrew Stanton & Lee Unkrich";
        nemo.rating = 4.1f;
        nemo.story = "A father looks for his son.";

        posterList.add(nemo);

        Poster shreck = new Poster();

        shreck.image = R.drawable.movieposter5;
        shreck.name = "Shrek";
        shreck.creator = "Andrew Adamson & Vicky Jenson";
        shreck.rating = 4f;
        shreck.story = "An ogre saves a princess.";

        posterList.add(shreck);

        Poster sm1 = new Poster();

        sm1.image = R.drawable.movieposter6;
        sm1.name = "Spider-Man";
        sm1.creator = "Sam Raimi";
        sm1.rating = 3.6f;
        sm1.story = "A boy becomes a hero.";

        posterList.add(sm1);

        Poster sm2 = new Poster();

        sm2.image = R.drawable.movieposter7;
        sm2.name = "Spider-Man 2";
        sm2.creator = "Sam Raimi";
        sm2.rating = 3.8f;
        sm2.story = "A boy struggles to stay a hero.";

        posterList.add(sm2);

        Poster sm3 = new Poster();

        sm3.image = R.drawable.movieposter8;
        sm3.name = "Spider-Man 3";
        sm3.creator = "Sam Raimi";
        sm3.rating = 3.2f;
        sm3.story = "A boy becomes a villain.";

        posterList.add(sm3);

        Poster mTR = new Poster();

        mTR.image = R.drawable.movieposter9;
        mTR.name = "Meet the Robinsons";
        mTR.creator = "Stephen J. Anderson";
        mTR.rating = 3.4f;
        mTR.story = "A boy travels to the future to learn how to conquer the past and save the world.";

        posterList.add(mTR);

        Poster encanto = new Poster();

        encanto.image = R.drawable.movieposter10;
        encanto.name = "Encanto";
        encanto.creator = "Jared Bush, Byron Howard, Charise Castro Smith";
        encanto.rating = 3.6f;
        encanto.story = "A normal girl finds her place in a magical family.";

        posterList.add(encanto);





        final PosterAdapter posterAdapter = new PosterAdapter(posterList,this);
        posterRecyclerView.setAdapter(posterAdapter);

        /**
         * A method that sets the watchlist button visible and gone depending on selected items
         */
        buttonWatchlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Poster> selectedPoster = posterAdapter.getSelectedPosters();

                StringBuilder posterNames = new StringBuilder();
                for (int i =0;i<selectedPoster.size();i++){
                    if(i==0) {
                        posterNames.append(selectedPoster.get(i).name);
                    }else{
                        posterNames.append("\n").append(selectedPoster.get(i).name);
                    }
                }
                Toast.makeText(MainActivity.this,posterNames.toString(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onPosterAction(Boolean isSelected) {
        if(isSelected){
            buttonWatchlist.setVisibility(View.VISIBLE);
        }else{
            buttonWatchlist.setVisibility(View.GONE);
        }


    }

}