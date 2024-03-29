package com.example.khangit.project_group3.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.example.khangit.project_group3.R;
import com.example.khangit.project_group3.adapter.GiohangAdapter;
import com.example.khangit.project_group3.ultil.CheckConnection;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.List;

public class Giohang extends AppCompatActivity {
    ListView lvgiohang;
    TextView txtthongbao;
    static  TextView txttongtien;
    Button btnthanhtoan, btntieptucmua;
    Toolbar toolbargiohang;
    GiohangAdapter giohangAdapter;
    private Dialog dialog;
    Button btnXoasp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
        initControl();
        ActionToolbar();
        CheckData();
        initEvent();
        CatchchonItemListView();
        EventButton();
    }

    private void EventButton() {
        btntieptucmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.manggiohang.size() > 0){
                    Intent intent = new Intent(getApplicationContext(), Thongtinkhachhang.class);
                    startActivity(intent);
                }
                else{
                    CheckConnection.ShowToast_Short(getApplicationContext(), "Giỏ hàng của bạn chưa có sản phẩm để thanh toán");
                }
            }
        });
    }

    private void CatchchonItemListView() {
        lvgiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Giohang.this);
                builder.setTitle("Xác nhận xóa sản phẩm");
                builder.setMessage("Bạn có chắc muốn xóa sản phẩm");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(MainActivity.manggiohang.size() <= 0){
                            txtthongbao.setVisibility(View.VISIBLE);
                        }
                        else{
                            MainActivity.manggiohang.remove(position);
                            giohangAdapter.notifyDataSetChanged();
                            initEvent();
                            if(MainActivity.manggiohang.size() <= 0){
                                txtthongbao.setVisibility(View.VISIBLE);
                            }else{
                                txtthongbao.setVisibility(View.INVISIBLE);
                                giohangAdapter.notifyDataSetChanged();
                                initEvent();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        giohangAdapter.notifyDataSetChanged();
                        initEvent();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    public static void initEvent() {
        long tongtien = 0;
        for(int i = 0; i < MainActivity.manggiohang.size(); i++){
            tongtien += MainActivity.manggiohang.get(i).getGiasp();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txttongtien.setText(decimalFormat.format(tongtien) + " Đ");
    }


    private void CheckData() {
        if(MainActivity.manggiohang.size() <= 0){
            giohangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.VISIBLE);
            lvgiohang.setVisibility(View.INVISIBLE);
        }else{
            giohangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.INVISIBLE);
            lvgiohang.setVisibility(View.VISIBLE);
        }
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbargiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initControl() {
        lvgiohang = (ListView) findViewById(R.id.listviewgiohang);
        txtthongbao = (TextView) findViewById(R.id.textviewthongbao);
        txttongtien = (TextView) findViewById(R.id.textviewtongtien);
        btnthanhtoan = (Button) findViewById(R.id.buttonthanhtoangiohang);
        btntieptucmua = (Button) findViewById(R.id.buttontieptucmuahang);
        toolbargiohang = (Toolbar) findViewById(R.id.toolbargiohang);
        giohangAdapter = new GiohangAdapter(Giohang.this, MainActivity.manggiohang);
        lvgiohang.setAdapter(giohangAdapter);
    }
}
