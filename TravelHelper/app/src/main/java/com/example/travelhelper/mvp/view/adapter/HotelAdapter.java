package com.example.travelhelper.mvp.view.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelhelper.R;
import com.example.travelhelper.mvp.repository.model.Hotels;
import com.example.travelhelper.mvp.view.HotelDetailsActivity;
import com.example.travelhelper.mvp.view.HotelEditActivity;
import com.example.travelhelper.utils.Constants;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder>{
    private static List<Hotels> hotels;

    public HotelAdapter(List<Hotels> hotels) {
        HotelAdapter.hotels = hotels;
    }

    @NonNull
    @Override
    public HotelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hotel_item,parent,false);
        view.findViewById(R.id.hotelImage).setClipToOutline(true);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HotelAdapter.ViewHolder holder, int position) {
        // TODO: 06.05.2021 Move logic to repo
        Hotels hotel = hotels.get(position);
        try{
            final File localFile = File.createTempFile("tmp", "jpg");
            FirebaseStorage.getInstance().getReference().child("hotels/"+ hotel.getId()).getFile(localFile)//hotel.getTitle() + "_" + hotel.getCity()
                    .addOnSuccessListener(taskSnapshot -> {
                        Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        holder.hotelImageView.setImageBitmap(bitmap);
                    })
                    .addOnFailureListener(e -> {
                        Log.e(Constants.appLog, e.getMessage());
                        holder.hotelImageView.setImageResource(R.drawable.camera_lens);
                    });
        }
        catch (IOException e){
            e.printStackTrace();
        }
        holder.nameView.setText(hotel.getTitle());
        holder.addressView.setText(hotel.getCity());
    }

    @Override
    public int getItemCount() {
        return hotels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final AppCompatImageView hotelImageView;
        final AppCompatTextView nameView, addressView;
        ViewHolder(View view){
            super(view);
            view.setOnClickListener(this);
            hotelImageView = view.findViewById(R.id.hotelImage);
            nameView = view.findViewById(R.id.title);
            addressView = view.findViewById(R.id.address);
        }

        @Override
        public void onClick(View v) {
            Intent intent;
            if(!Constants.isAdmin) {
                intent = new Intent(v.getContext(), HotelDetailsActivity.class);
            } else
                intent = new Intent(v.getContext(), HotelEditActivity.class);

            intent.putExtra("Id", hotels.get(getLayoutPosition()).getId());
            intent.putExtra("Title", hotels.get(getLayoutPosition()).getTitle());
            intent.putExtra("City", hotels.get(getLayoutPosition()).getCity());
            intent.putExtra("Address", hotels.get(getLayoutPosition()).getAddress());
            v.getContext().startActivity(intent);
        }
    }
}
