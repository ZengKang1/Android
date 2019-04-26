package com.example.zengkang;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener{
    //数字0-9
    private Button n_0;
    private Button n_1;
    private Button n_2;
    private Button n_3;
    private Button n_4;
    private Button n_5;
    private Button n_6;
    private Button n_7;
    private Button n_8;
    private Button n_9;

    //运算符
    private Button y_j;//+
    private Button y_jj;//-
    private Button y_c;//*
    private Button y_cc;//除
    private Button y_d;//=
    private Button y_x;//小数点

    //清除
    private  Button det;

    boolean clean;//清空标识


    //结果显示集
    private EditText editText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //数字0——9实例化
        n_0=findViewById(R.id.button26);
        n_1=findViewById(R.id.button21);
        n_2=findViewById(R.id.button22);
        n_3=findViewById(R.id.button23);
        n_4=findViewById(R.id.button16);
        n_5=findViewById(R.id.button17);
        n_6=findViewById(R.id.button18);
        n_7=findViewById(R.id.button13);
        n_8=findViewById(R.id.button12);
        n_9=findViewById(R.id.button11);

        //运算符实例化
        y_j=findViewById(R.id.button28);//加
        y_jj=findViewById(R.id.button24);//减
        y_c=findViewById(R.id.button19);//乘
        y_cc=findViewById(R.id.button14);//除
        y_d=findViewById(R.id.button25);//等
        y_x=findViewById(R.id.button27);//小数点
        det=findViewById(R.id.button8);//清除

        //结果显示集
        editText=findViewById(R.id.ediText);

        //添加时间点击时间
        n_0.setOnClickListener( this);
        n_1.setOnClickListener(this);
        n_2.setOnClickListener( this);
        n_3.setOnClickListener( this);
        n_4.setOnClickListener(this);
        n_5.setOnClickListener( this);
        n_6.setOnClickListener( this);
        n_7.setOnClickListener(this);
        n_8.setOnClickListener( this);
        n_9.setOnClickListener( this);

        y_j.setOnClickListener( this);
        y_jj.setOnClickListener( this);
        y_c.setOnClickListener( this);
        y_cc.setOnClickListener( this);
        y_x.setOnClickListener( this);
        y_d.setOnClickListener( this);
        det.setOnClickListener( this);

    }
    //读取每个按钮内容
    public void onClick(View view){
        //获取文本内容
        String  input=editText.getText().toString();
        switch (view.getId()){
            case R.id.button26:
            case R.id.button21:
            case R.id.button22:
            case R.id.button23:
            case R.id.button16:
            case R.id.button17:
            case R.id.button18:
            case R.id.button13:
            case R.id.button12:
            case R.id.button11:
            case R.id.button27://小数点
                if(clean){
                    clean=false;
                    editText.setText("");//赋值为空
                }
                editText.setText(input+((Button)view).getText()+"");//结果集就是本身
                break;
            case R.id.button28:
            case R.id.button24:
            case R.id.button19:
            case R.id.button14://除
                if(clean){
                    clean=false;
                    input="";
                    editText.setText("");
                }
                editText.setText(input+" "+((Button)view).getText()+" ");
                break;
            case R.id.button8://清除
                if(clean){
                    clean=false;
                    input="";
                    editText.setText("");
                }else  if(input!=null || !input.equals("")){
                    //如果获取内容为空
                    editText.setText(input.substring(0,input.length() - 1 ));//结果集为空
                    break;
                }
                break;
            case  R.id.button25://运算结果=
                getResult();//调用处理结果方法
                break;
        }
    }
    //运算结果方法
    private void getResult(){
        String exp=editText.getText().toString();//获取文本框内容
        if(exp==null||exp.equals("")){
            return;
        }
        if(!exp.contains("")){
            return;
        }
        if(clean){
            clean=false;
            return;
        }
        clean=true;
        double result=0;

        //进行截取
        //运算符前的数字
        String s1=exp.substring(0,exp.indexOf(" "));
        //运算符
        String op=exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2);
        //运算符后的数字
        String s2=exp.substring(exp.indexOf(" ")+3);

        if(!s1.equals("")&&!s2.equals("")){
            //如果包含小数点的运算
            double d1=Double.parseDouble(s1);//则数字都是double类型
            double d2=Double.parseDouble(s2);

            if(op.equals("+")){
                //如果是+
                result=d1+d2;
            }else if(op.equals("-")){
                //如果是-
                result=d1-d2;
            }else if(op.equals("*")){
                //如果是*
                result=d1*d2;
            }else if(op.equals("/")){
                if(d2==0){
                    //如果被除数是0
                    result=0;//则结果为0
                }
                else {
                    //否则执行正常运算
                    result=d1/d2;
                }
            }
            if(!s1.contains(".") &&!s2.contains(".")&&!op.equals("/")){
                //如果是整数类型
                int r=(int)result;//都是整形
                editText.setText(r+"");
            }else {
                editText.setText(result+"");
            }
        }else  if(!s1.equals("")&& s2.equals("")){
            //如果只输入运算符前的数字
            editText.setText(exp);//直接返回当前输入内容
        }else if (s1.equals("")&& !s2.equals("")){
            //如果是只输入运算符后面的数
            double d2 =Double.parseDouble(s2);

            //运算符当前没有输入数字
            if(op.equals("+")){
                result= 0 + d2;
            }else  if(op.equals("-")){
                result= 0 - d2;
            }else if (op.equals("*")){
                result= 0;
            }else  if(op.equals("/")){
                result= 0;
            }
            if(!s1.contains(".")&&!s2.contains(".")){
                int r=(int) result;
                editText.setText(r+"");
            }else {
                editText.setText(result+"");
            }
        }else {
            editText.setText("");
        }
    }
}


