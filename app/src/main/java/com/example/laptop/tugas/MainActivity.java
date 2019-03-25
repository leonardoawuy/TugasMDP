package com.example.laptop.tugas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edName;
    RadioGroup rgType;
    RadioButton rButton,rbTea,rbCoffee,rbSmoothies;
    CheckBox cbPearl,cbPudding,cbRedBean,cbCoconut;
    Button btnMinus,btnPlus,btnAdd,btnDel,btnReset;
    TextView txtQty,txtTotal,txtName;
    int qty = 1,harga,index=-1,total = 0;
    RecyclerView rvOrder;
    ArrayList<Order> arrOrder = new ArrayList<>();
    OrderAdapter adapter;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edName = (EditText) findViewById(R.id.editText_name);
        rgType = findViewById(R.id.radioGroup);
        rbTea = findViewById(R.id.radioButton);
        rbCoffee = findViewById(R.id.radioButton2);
        rbSmoothies = findViewById(R.id.radioButton3);
        txtQty = findViewById(R.id.textView4);
        txtName = findViewById(R.id.textView6);
        txtTotal = findViewById(R.id.textView8);
        btnMinus = findViewById(R.id.button);
        btnPlus = findViewById(R.id.button2);
        btnAdd = findViewById(R.id.button3);
        btnDel = findViewById(R.id.button4);
        btnReset = findViewById(R.id.button5);
        cbPearl = findViewById(R.id.checkBox);
        cbRedBean = findViewById(R.id.checkBox2);
        cbPudding = findViewById(R.id.checkBox3);
        cbCoconut = findViewById(R.id.checkBox4);
        rvOrder =findViewById(R.id.recyclerView);

        adapter= new OrderAdapter(arrOrder, new RVClickListener() {
            @Override
            public void recyclerViewListClicked(View v, int posisi) {
                index=posisi;
                txtQty.setText(arrOrder.get(posisi).getQty()+"");
                if (arrOrder.get(posisi).getType().equals("Tea"))rbTea.setChecked(true);
                if (arrOrder.get(posisi).getType().equals("Coffee"))rbCoffee.setChecked(true);
                if (arrOrder.get(posisi).getType().equals("Smoothies"))rbSmoothies.setChecked(true);

                cbPearl.setChecked(false);
                cbCoconut.setChecked(false);
                cbPudding.setChecked(false);
                cbRedBean.setChecked(false);
                for (String s : arrOrder.get(posisi).getToping()) {
                    if (s.equals("Pearl"))cbPearl.setChecked(true);
                    if (s.equals("Coconut Jelly"))cbCoconut.setChecked(true);
                    if (s.equals("Pudding"))cbPudding.setChecked(true);
                    if (s.equals("Red Bean"))cbRedBean.setChecked(true);
                }
            }
        });
        RecyclerView.LayoutManager lm = new LinearLayoutManager(MainActivity.this);
        rvOrder.setLayoutManager(lm);
        rvOrder.setAdapter(adapter);

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qty >1) {
                    qty = qty - 1;
                    String tmp = String.valueOf(qty);
                    txtQty.setText(tmp);
                }
                else{

                }
            }
        });
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty = qty+1;
                String tmp = String.valueOf(qty);
                txtQty.setText(tmp);
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = rgType.getCheckedRadioButtonId();
                ArrayList<String> toping = new ArrayList<String>();
                rButton = (RadioButton) findViewById(selectedId);
                String inputNama=edName.getText().toString();
                if (inputNama.equalsIgnoreCase("")){
                    Toast.makeText(MainActivity.this, "Field name cannot be empty", Toast.LENGTH_SHORT).show();
                }else {
                    txtName.setText(inputNama);
                    String type=rButton.getText().toString();
                    if (type.equalsIgnoreCase("Tea")){
                        type="Tea";
                        harga=harga+23000;
                    } if (type.equalsIgnoreCase("Coffe")){
                        type="Coffe";
                        harga=harga+25000;
                    } if (type.equalsIgnoreCase("Smoothies")){
                        type="Smoothies";
                        harga=harga+30000;
                    }
                    if (cbPearl.isChecked()){
                        toping.add("Pearl");
                        harga=harga+3000;
                    }if (cbRedBean.isChecked()){
                        toping.add("Red Bean");
                        harga=harga+4000;
                    }if (cbCoconut.isChecked()){
                        toping.add("Coconut Jelly");
                        harga=harga+4000;
                    }if (cbPudding.isChecked()){
                        toping.add("Pudding");
                        harga=harga+3000;
                    }
                    arrOrder.add(new Order(type.toString(),toping,qty,harga) );
                    adapter.notifyDataSetChanged();
                }
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rgType.setSelected(rbTea.isSelected());
                if(cbPudding.isChecked()==true){
                    cbPudding.setChecked(false);
                }
                if(cbCoconut.isChecked()==true){
                    cbCoconut.setChecked(false);
                }
                if(cbPearl.isChecked()==true){
                    cbPearl.setChecked(false);
                }
                if(cbRedBean.isChecked()==true){
                    cbRedBean.setChecked(false);
                }
                edName.setText("");
                txtQty.setText("1");
                qty=1;
                arrOrder.remove(index);
                adapter.notifyDataSetChanged();
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rgType.setSelected(rbTea.isSelected());
                if(cbPudding.isChecked()==true){
                    cbPudding.setChecked(false);
                }
                if(cbCoconut.isChecked()==true){
                    cbCoconut.setChecked(false);
                }
                if(cbPearl.isChecked()==true){
                    cbPearl.setChecked(false);
                }
                if(cbRedBean.isChecked()==true){
                    cbRedBean.setChecked(false);
                }
                edName.setText("");
                txtQty.setText("1");
                qty=1;
                txtName.setText("");
                arrOrder.clear();
                adapter.notifyDataSetChanged();
            }
        });
    }
}
