package com.example.onlinecourseampe_learningapp;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineSeasonampe_learningapp.R;

import java.util.List;

public class SeasonAdapter extends RecyclerView.Adapter<SeasonAdapter.SeasonViewHolder> {
    private Context context;
    private String user;
    private List<Season> seasonList;
    private final My_View_Model viewModel;
    private OnSeasonClickListener listener;

    public void setOnSeasonClickListener(OnSeasonClickListener listener) {
        this.listener = listener;
    }

    public SeasonAdapter(Context context, List<Season> seasonList, String user) {
        this.context = context;
        this.seasonList = seasonList;
        this.user = user;
        this.viewModel = new ViewModelProvider((AppCompatActivity) context).get(My_View_Model.class);
    }

    @NonNull
    @Override
    public SeasonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_season, parent, false);
        return new SeasonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeasonViewHolder holder, int position) {
        Season season = seasonList.get(position);
        holder.tvSeasonName.setText(season.getSeason_NAME());
        holder.tvCategorie.setText(season.getCategorie());
        holder.tvPrice.setText(String.format("$%d", season.getPrice()));

        byte[] imageBytes = season.getImage();
        if (imageBytes != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            holder.ivSeasonImage.setImageBitmap(bitmap);
        } else {
            holder.ivSeasonImage.setImageResource(R.drawable.sample_course_image);
        }

        viewModel.getBookmarkedSeasonsByFarmer1(user, season.getSeason_ID()).observe((AppCompatActivity) context, farmerSeasons -> {


            if (farmerSeasons.isEmpty() || farmerSeasons == null) {
                holder.BookmarkIcon.setImageResource(R.drawable.bookmark);
            } else {
                holder.BookmarkIcon.setImageResource(R.drawable.bookmarked);
            }
        });


        holder.BookmarkIcon.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                showCustomDialog(season, season.getImage(), season.getSeason_NAME(), season.getCategorie(), season.getPrice());


                return true;
            }
        });
        holder.BookmarkIcon.setOnClickListener(v -> {
            viewModel.isFarmerSeasonExistsC(user, season.getSeason_ID(), true).observe((AppCompatActivity) context, isHadc -> {
                viewModel.isFarmerSeasonExistsB(user, season.getSeason_ID(), true).observe((AppCompatActivity) context, isHadb -> {
                    viewModel.isFarmerSeasonExists(user, season.getSeason_ID(), true).observe((AppCompatActivity) context, isHad -> {
                        if (!isHadb && !isHad && !isHadc) {


                            Farmer_Seasons farmerSeason = new Farmer_Seasons(user, season.getSeason_ID(), true, false, false, 0);
                            viewModel.insertFarmerSeason(farmerSeason);
                            holder.BookmarkIcon.setImageResource(R.drawable.bookmarked);


                        } else if (isHad || isHadc) {
                            Farmer_Seasons farmerSeason = new Farmer_Seasons(user, season.getSeason_ID(), true, isHad, isHadc, 0);

                            viewModel.updateSeasonFarmer(farmerSeason);

                            holder.BookmarkIcon.setImageResource(R.drawable.bookmarked);


                        } else if (isHadb) {
                            Farmer_Seasons farmerSeason = new Farmer_Seasons(user, season.getSeason_ID(), false, isHad, isHadc, 0);

                            viewModel.updateSeasonFarmer(farmerSeason);

                            holder.BookmarkIcon.setImageResource(R.drawable.bookmark);


                        }


                    });
                });
            });
        });


        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onSeasonClick(season);
            }
        });
    }

    @Override
    public int getItemCount() {
        return seasonList.size();
    }

    public Season getSeasonAt(int position) {
        return seasonList.get(position);
    }

    public void setSeasonList(List<Season> seasonList) {
        this.seasonList = seasonList;
        notifyDataSetChanged();
    }

    public interface OnSeasonClickListener {
        void onSeasonClick(Season season);
    }

    static class SeasonViewHolder extends RecyclerView.ViewHolder {
        TextView tvSeasonName, tvPrice, tvCategorie;
        ImageView ivSeasonImage, cartIcon, BookmarkIcon;

        public SeasonViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSeasonName = itemView.findViewById(R.id.tv_Season_name);
            tvCategorie = itemView.findViewById(R.id.tv_categorie);
            tvPrice = itemView.findViewById(R.id.tv_price);
            ivSeasonImage = itemView.findViewById(R.id.iv_Season_image);
            BookmarkIcon = itemView.findViewById(R.id.bookmarkIcon);
            cartIcon = itemView.findViewById(R.id.cart);
        }
    }

    private void showCustomDialog(Season season, byte[] image, String name, String catigories, int prise) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog);
        dialog.setCancelable(true);


        TextView mainText = dialog.findViewById(R.id.dialog_main_text1);
        Button viewSeasonButton = dialog.findViewById(R.id.btn_view_Season1);
        Button cancelButton = dialog.findViewById(R.id.btn_cancel1);
        ImageView iv_season_image = dialog.findViewById(R.id.iv_Season_imageD);
        TextView tv_categorie = dialog.findViewById(R.id.tv_categorieD);
        TextView tv_season_name = dialog.findViewById(R.id.tv_Season_nameD);
        TextView tv_price = dialog.findViewById(R.id.tv_priceD);

        byte[] imageBytes = image;
        if (imageBytes != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            iv_season_image.setImageBitmap(bitmap);
            tv_categorie.setText(catigories);
            tv_season_name.setText(name);
            tv_price.setText(prise + "");
            mainText.setText("Remove from Bookmark?");


            viewSeasonButton.setOnClickListener(v -> {

                viewModel.deleteFarmerSeasonByUserAndSeason(user, season.getSeason_ID());

                dialog.dismiss();
            });

            Window window = dialog.getWindow();
            if (window != null) {
                WindowManager.LayoutParams params = window.getAttributes();
                params.gravity = Gravity.BOTTOM;
                params.width = WindowManager.LayoutParams.MATCH_PARENT;
                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
                window.setAttributes(params);
                window.setBackgroundDrawableResource(android.R.color.transparent);
                window.setBackgroundDrawableResource(R.drawable.rounded_dialog_background);

            }


            cancelButton.setOnClickListener(v -> {
                dialog.dismiss();
            });

            dialog.show();
        }

    }


}
