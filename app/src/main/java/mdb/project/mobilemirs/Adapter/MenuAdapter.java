package mdb.project.mobilemirs.Adapter;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mdb.project.mobilemirs.Interface.IMenu;
import mdb.project.mobilemirs.Model.MenuModel;
import mdb.project.mobilemirs.R;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private Context context;
    private ArrayList<MenuModel> menuList;
    private IMenu adapterCallback;

    public MenuAdapter(Context context, ArrayList<MenuModel> menuList, IMenu adapterCallback) {
        this.context = context;
        this.menuList = menuList;
        this.adapterCallback = adapterCallback;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false);
        return new MenuViewHolder(view, adapterCallback, menuList);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MenuModel currentItem = menuList.get(position);
        holder.menuImage.setImageResource(currentItem.getMenuImage());
        holder.menuText.setText(currentItem.getMenuName());
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView menuImage;
        private TextView menuText;
        private IMenu adapterCallback;
        private ArrayList<MenuModel> menuList;

        private MenuViewHolder(@NonNull View itemView, IMenu adapterCallback, ArrayList<MenuModel> menuList) {
            super(itemView);
            this.adapterCallback = adapterCallback;
            this.menuList = menuList;
            menuImage = itemView.findViewById(R.id.item_image_view_menu);
            menuText = itemView.findViewById(R.id.item_text_view_menu);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            adapterCallback.selectMenu(menuList.get(getAdapterPosition()).getMenuName(), getAdapterPosition());
        }
    }

}
