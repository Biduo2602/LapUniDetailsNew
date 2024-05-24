package com.example.appbanlaptop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.appbanlaptop.R;
import com.example.appbanlaptop.modal.LoaiSp;

import java.util.List;

public class LoaiSpAdapter extends BaseAdapter {
   List<LoaiSp> array;
   Context context;

    public LoaiSpAdapter(Context context,List<LoaiSp> array) {
        this.array = array;
        this.context = context;
    }


    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder{
        TextView tvTenSp;
        ImageView imgViewHinhAnh;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder= null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_product, null);
            viewHolder.tvTenSp = convertView.findViewById(R.id.item_name);
            viewHolder.imgViewHinhAnh= convertView.findViewById(R.id.item_img);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.tvTenSp.setText(array.get(position).getTensp());
        String urlHinhAnh = array.get(position).getHinhanh();
        Glide.with(context).load(urlHinhAnh).into(viewHolder.imgViewHinhAnh);
        return convertView;
    }
}
