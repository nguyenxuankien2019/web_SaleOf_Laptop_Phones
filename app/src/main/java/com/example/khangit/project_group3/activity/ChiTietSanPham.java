package com.example.khangit.project_group3.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khangit.project_group3.R;
import com.example.khangit.project_group3.model.Giohang;
import com.example.khangit.project_group3.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ChiTietSanPham extends AppCompatActivity {
    Toolbar toolbarchitiet;
    ImageView imageViewchitiet;
    TextView txtten,txtgia,txtmota;
    Spinner spinner;
    Button btndatmua;

    int id = 0;
    String Tenchitiet = "";
    int Giachitiet =0;
    String Hinhanhchitiet = "";
    String Motachitiet = "";
    int Idsanpham=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        Anhxa();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActionToolbar();
        }
        GetInfomation();
        CatchEventSpinner();
        initEvent();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(), com.example.khangit.project_group3.activity.Giohang.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initEvent() {
        btndatmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.manggiohang.size() > 0){
                    int sl= Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exists = false;
                    for(int i = 0; i < MainActivity.manggiohang.size(); i ++){
                        if(MainActivity.manggiohang.get(i).getIdsp() == id){
                            MainActivity.manggiohang.get(i).setSoluongsp(MainActivity.manggiohang.get(i).getSoluongsp() +sl);
                            if(MainActivity.manggiohang.get(i).getSoluongsp() >= 10){
                                MainActivity.manggiohang.get(i).setSoluongsp(10);
                                Toast.makeText(getApplicationContext(), "Quý khách chỉ được mua 10 sản phẩm",
                                        Toast.LENGTH_LONG).show();
                            }
                            MainActivity.manggiohang.get(i).setGiasp(Giachitiet * MainActivity.manggiohang.get(i).getSoluongsp());
                            exists = true;
                        }
                    }
                    if(exists == false){
                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        long Giamoi = soluong * Giachitiet;
                        MainActivity.manggiohang.add(new Giohang(id, Tenchitiet, Giamoi, Hinhanhchitiet, soluong));
                    }
                }else{
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    long Giamoi = soluong * Giachitiet;
                    MainActivity.manggiohang.add(new Giohang(id, Tenchitiet, Giamoi, Hinhanhchitiet, soluong));

                }
                Intent intent = new Intent(getApplicationContext(), com.example.khangit.project_group3.activity.Giohang.class);
                startActivity(intent);
            }
        });

    }

    private void CatchEventSpinner() {
        Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(arrayAdapter);
    }

    private void GetInfomation() {
        Sanpham sanpham = (Sanpham) getIntent().getSerializableExtra("thongtinsanpham");
        id = sanpham.getId();
        Tenchitiet = sanpham.getTensanpham();
        Giachitiet = sanpham.getGiasanpham();
        Hinhanhchitiet = sanpham.getHinhanhsanpham();
        Idsanpham = sanpham.getIDSanpham();
        Motachitiet = sanpham.getMotasanpham();
        txtten.setText(Tenchitiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtgia.setText("Giá : "+decimalFormat.format(Giachitiet)+"Đ");
        txtmota.setText(Motachitiet);
        Picasso.with(getApplicationContext()).load(Hinhanhchitiet)
                .placeholder(R.drawable.noimage)
                .error(R.drawable.errorimg)
                .into(imageViewchitiet);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void ActionToolbar() {
        setSupportActionBar(toolbarchitiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarchitiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void Anhxa() {
        toolbarchitiet = (Toolbar) findViewById(R.id.toolbarchitietsanpham);
        imageViewchitiet = (ImageView) findViewById(R.id.imageviewchitietsanpham);
        txtgia = (TextView) findViewById(R.id.textviewgiachitietsanpham);
        txtten = (TextView) findViewById(R.id.textviewtenchitietsanpham);
        txtmota = (TextView) findViewById(R.id.textviewmotachitietsanpham);
        spinner = (Spinner) findViewById(R.id.spinner);
        btndatmua = (Button) findViewById(R.id.buttondatmua);
    }
}
