package com.example.khangit.project_group3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khangit.project_group3.R;
import com.example.khangit.project_group3.model.ModelProduct;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ModelProductAdapter extends BaseAdapter{
    ArrayList<ModelProduct> arrayListmodelproduct;
    Context context;

    public ModelProductAdapter(ArrayList<ModelProduct> arrayListmodelproduct, Context context) {
        this.arrayListmodelproduct = arrayListmodelproduct;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListmodelproduct.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListmodelproduct.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        TextView txtmodelProduct;
        ImageView imgmodelProduct;

    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_modelproduct, null);
            viewHolder.txtmodelProduct = (TextView) view.findViewById(R.id.tvmodelproduct);
            viewHolder.imgmodelProduct = (ImageView) view.findViewById(R.id.imageviewmodelpro) ;
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();

        }
        ModelProduct modelProduct = (ModelProduct) getItem(i);
        viewHolder.txtmodelProduct.setText(modelProduct.getNameProduct());
        Picasso.with(context).load(modelProduct.getImageProduct())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.errorimg)
                .into(viewHolder.imgmodelProduct);
        return  view;
    }

}
