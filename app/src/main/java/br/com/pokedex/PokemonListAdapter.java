package br.com.pokedex;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

public class PokemonListAdapter extends ArrayAdapter {
    private static final String TAG = "PokemonListAdapter";
    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private List<Pokemon> pokemons;

    public PokemonListAdapter(Context context, int resource, List<Pokemon> pokemons) {
        super(context, resource);
        this.layoutResource = resource;
        this.layoutInflater = LayoutInflater.from(context);
        this.pokemons = pokemons;
    }

    @Override
    public int getCount() {
        return pokemons.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PokemonListAdapter.ViewHolder viewHolder;

        if (convertView == null) {
            Log.d(TAG, "getView: chamada com um convertView null");
            convertView = layoutInflater.inflate(layoutResource, parent, false);
            viewHolder = new PokemonListAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            Log.d(TAG, "getView: recebeu um convertView");
            viewHolder = (PokemonListAdapter.ViewHolder) convertView.getTag();
        }

        Pokemon pokemonAtual = pokemons.get(position);

        viewHolder.tvNome.setText(pokemonAtual.getNome());
        viewHolder.tvImage.setText(pokemonAtual.getImageUrl());
        viewHolder.tvNum.setText(pokemonAtual.getNumero());

        new PokemonListAdapter.DownloadImageTask(viewHolder.ivAppImg).execute(pokemonAtual.getImageUrl());

        return convertView;
    }

    private class ViewHolder {
        final TextView tvNome;
        final TextView tvImage;
        final TextView tvNum;
        final ImageView ivAppImg;

        ViewHolder(View v) {
            this.tvNome = v.findViewById(R.id.tvNome);
            this.tvImage = v.findViewById(R.id.tvImage);
            this.tvNum = v.findViewById(R.id.tvNum);
            this.ivAppImg = v.findViewById(R.id.ivAppImg);
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap bmp = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                bmp = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bmp;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
