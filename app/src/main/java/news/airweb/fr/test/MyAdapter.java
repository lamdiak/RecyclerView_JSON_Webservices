package news.airweb.fr.test;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


    private List<Aiweb> list;
    private Context context;



    public MyAdapter(List<Aiweb> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.list_item,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final Aiweb aiweb = list.get(position);
        // Cas ou l'image est dans le dossier Res
       // viewHolder.imageView.setImageDrawable(context.getResources().getDrawable(aiweb.getImage()));
        viewHolder.textViewTitle.setText(aiweb.getTitle());
        viewHolder.textViewShortDesc.setText(aiweb.getShortdesc());

        Picasso.get().
                load(aiweb.getImage()).
                into(viewHolder.imageView);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("imageView", aiweb.getImage());
                intent.putExtra("textViewTitle", aiweb.getTitle());
                intent.putExtra("textViewShortDesc", aiweb.getShortdesc());

                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewTitle;
        public TextView textViewShortDesc;
        public ImageView imageView;
        RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewTitle = (TextView)itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = (TextView)itemView.findViewById(R.id.textViewShortDesc);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relative);
        }
    }
}
