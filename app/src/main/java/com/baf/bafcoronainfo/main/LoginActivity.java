package com.baf.bafcoronainfo.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.baf.bafcoronainfo.R;
import com.baf.bafcoronainfo.util.PersistentUser;
import com.baf.bafcoronainfo.util.ToastUtil;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;
import java.util.List;


public class LoginActivity extends Activity  implements View.OnClickListener {
    private String TAG = LoginActivity.class.getSimpleName();
    private Context context;
    private MaterialCardView card_btn_login;
    private MaterialCardView card_btn_canlel;
    private TextInputEditText et_password;
    private ToastUtil toastUtil;

    String[] alphabet = new String[]{
            "dai", "dplans", "dfs", "drect", "dproj", "dac", "dao", "dat", "dad", "dedn",
            "dmet", "dats", "dcwit", "doao", "dengg", "dce", "darmt", "dsup", "dpers", "dwks",
            "dfin", "pm", "dms", "dwc","coas", "pstocoas", "airsecy", "dyairsecy", "ci","acaso",
            "acasp", "acasm", "acasa", "chairmancaab","aocbsr","aocbbd","aoczhr","aocmtr","aocpkp","aocairhq",
            "aocsheikhhasina","vcbsmraau", "comdtbafa","comdtati","ocadminbsr","ocadminbbd","ocadminzhr",
            "ocadminmtr","ocadminpkp" , "ocadminsheikhhasina","ocbru","ocmru","ocssn","oclmh","bafcorona",
            "sdsair","scsngr","ocbsru"
    };
    List<String> list = Arrays.asList(alphabet);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        context = this;
        toastUtil = new ToastUtil(this);
        initUI();

    }

    private void initUI() {
        card_btn_login=(MaterialCardView)findViewById(R.id.card_btn_login);
        card_btn_canlel=(MaterialCardView)findViewById(R.id.card_btn_canlel);
        et_password=(TextInputEditText)findViewById(R.id.et_password);
        card_btn_login.setOnClickListener(this);
        card_btn_canlel.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_btn_login:
                if (list.contains(et_password.getText().toString())) {
                    PersistentUser.setUserpassword(context, et_password.getText().toString());
                    Intent intent = new Intent(getApplicationContext(), CoronaStateActivity.class);
                    startActivity(intent);
                    finish();
                } else if (et_password.getText().toString().equalsIgnoreCase("")) {
                    toastUtil.appSuccessMsg(context, "Please Enter Password");
                } else {
                    toastUtil.appSuccessMsg(context, "Sorry Password Does not match Try Again");
                }
                break;
            case R.id.card_btn_canlel:
                finish();
                break;
        }

    }

    public void BACK(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }


}
