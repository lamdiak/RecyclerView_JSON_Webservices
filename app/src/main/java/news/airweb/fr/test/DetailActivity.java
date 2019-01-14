package news.airweb.fr.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static news.airweb.fr.test.MainActivity.EXTRA_DESC;
import static news.airweb.fr.test.MainActivity.EXTRA_TITLE;
import static news.airweb.fr.test.MainActivity.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra("imageView");
        String title = intent.getStringExtra("textViewTitle");
        String description = intent.getStringExtra("textViewShortDesc");


        ImageView imageView = findViewById(R.id.image_view_detail);
        TextView textViewTitle = findViewById(R.id.text_view_creator_detail);
        TextView textViewDesc = findViewById(R.id.text_view_like_detail);

        Picasso.get().load(imageUrl).into(imageView);
        textViewTitle.setText(title);
        textViewDesc.setText(description);
    }
}
