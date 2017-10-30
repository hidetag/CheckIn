package cn.shield.checkin.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.shield.checkin.R;
import cn.shield.checkin.model.CheckInResponse;
import cn.shield.checkin.model.LocationModel;
import cn.shield.checkin.model.LoginResponse;
import cn.shield.checkin.net.API;
import cn.shield.checkin.net.CommonWrap;
import cn.shield.checkin.net.RetrofitHelper;
import cn.shield.checkin.other.Common;
import cn.shield.checkin.other.EncryptPsw;
import cn.shield.checkin.other.KeyboardUtils;
import cn.shield.checkin.other.UserManager;
import cn.shield.checkin.ui.adapter.CompanyAdapter;
import cn.shield.checkin.view.parallax.ParallaxLayerLayout;
import cn.shield.checkin.view.parallax.SensorTranslationUpdater;
import io.reactivex.subscribers.DisposableSubscriber;

public class MainActivity extends AppCompatActivity implements CompanyAdapter.IOnItemClickListener {
    private ParallaxLayerLayout mParallaxLayerLayout1, mParallaxLayerLayout2, mParallaxLayerLayout3, mParallaxLayerLayout4;
    private SensorTranslationUpdater mSensorTranslationUpdater1, mSensorTranslationUpdater2, mSensorTranslationUpdater3, mSensorTranslationUpdater4;
    private EditText mEtName, mEtPsw;
    private RecyclerView mRecyclerView;
    private CompanyAdapter mAdapter;
    private List<LocationModel> mLocationModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initParallax();
        initEt();
        packMockLocationData();
    }

    private void initView() {
        mParallaxLayerLayout1 = (ParallaxLayerLayout) findViewById(R.id.parallax1);
        mParallaxLayerLayout2 = (ParallaxLayerLayout) findViewById(R.id.parallax2);
        mParallaxLayerLayout3 = (ParallaxLayerLayout) findViewById(R.id.parallax3);
        mParallaxLayerLayout4 = (ParallaxLayerLayout) findViewById(R.id.parallax4);
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtPsw = (EditText) findViewById(R.id.et_psw);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
    }

    private void initParallax() {
        mSensorTranslationUpdater1 = new SensorTranslationUpdater(this);
        mSensorTranslationUpdater2 = new SensorTranslationUpdater(this);
        mSensorTranslationUpdater3 = new SensorTranslationUpdater(this);
        mSensorTranslationUpdater4 = new SensorTranslationUpdater(this);
        mParallaxLayerLayout1.setTranslationUpdater(mSensorTranslationUpdater1);
        mParallaxLayerLayout2.setTranslationUpdater(mSensorTranslationUpdater2);
        mParallaxLayerLayout3.setTranslationUpdater(mSensorTranslationUpdater3);
        mParallaxLayerLayout4.setTranslationUpdater(mSensorTranslationUpdater4);
    }

    private void initEt() {
        String name = UserManager.INSTANCE.getInfo(UserManager.sNAME);
        mEtName.setText(name);
        String psw = UserManager.INSTANCE.getInfo(UserManager.sPSW);
        mEtPsw.setText(psw);
        mEtName.setSelection(psw.length());
    }

    private void initRv(List<LoginResponse.DataBean.CompanyBean> company) {
        mAdapter = new CompanyAdapter(this, this, company);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    public void login(View view) {
        KeyboardUtils.closeKeyboard(view);
        if (TextUtils.isEmpty(mEtName.getText().toString().trim())) {
            mEtName.setError("账号不可为空");
            return;
        }
        if (TextUtils.isEmpty(mEtPsw.getText().toString().trim())) {
            mEtPsw.setError("密码不可为空");
            return;
        }
        UserManager.INSTANCE.saveInfo(UserManager.sNAME, mEtName.getText().toString().trim());
        UserManager.INSTANCE.saveInfo(UserManager.sPSW, mEtPsw.getText().toString().trim());
        ProgressDialog dialog = getDialog("登录中");
        RetrofitHelper.<API>createDefault()
                .login(mEtName.getText().toString().trim()
                        , EncryptPsw.encrypt(mEtPsw.getText().toString().trim() + "#35Groups"))
                .compose(CommonWrap.flowableWrap())
                .subscribe(new DisposableSubscriber<LoginResponse>() {
                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        if (loginResponse == null) {
                            Toast.makeText(MainActivity.this, "登录失败，返回体为null", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            return;
                        }
                        if ("TRUE".equals(loginResponse.getResult())) {
                            Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                            initRv(loginResponse.getData().getCompany());
                            UserManager.INSTANCE.saveInfo(UserManager.sID, loginResponse.getData().get_id());
                            UserManager.INSTANCE.saveInfo(UserManager.sTOKEN, loginResponse.getData().getToken());
                        } else {
                            Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable t) {
                        dialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        register();
    }

    @Override
    public void onPause() {
        super.onPause();
        unregister();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregister();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregister();
    }

    private void unregister() {
        mSensorTranslationUpdater1.unregisterSensorManager();
        mSensorTranslationUpdater2.unregisterSensorManager();
        mSensorTranslationUpdater3.unregisterSensorManager();
        mSensorTranslationUpdater4.unregisterSensorManager();
    }

    private void register() {
        mSensorTranslationUpdater1.registerSensorManager();
        mSensorTranslationUpdater2.registerSensorManager();
        mSensorTranslationUpdater3.registerSensorManager();
        mSensorTranslationUpdater4.registerSensorManager();
    }

    @Override
    public void onItemClick(LoginResponse.DataBean.CompanyBean bean, int position) {
        checkIn(bean.get_id());
    }

    /**
     * 选取10个位置进行打卡
     */
    private void packMockLocationData() {
        mLocationModels.add(new LocationModel("打卡地址", 104.000000D, 30.000000D));
        mLocationModels.add(new LocationModel("打卡地址", 104.000000D, 30.000000D));
        mLocationModels.add(new LocationModel("打卡地址", 104.000000D, 30.000000D));
        mLocationModels.add(new LocationModel("打卡地址", 104.000000D, 30.000000D));
        mLocationModels.add(new LocationModel("打卡地址", 104.000000D, 30.000000D));
        mLocationModels.add(new LocationModel("打卡地址", 104.000000D, 30.000000D));
        mLocationModels.add(new LocationModel("打卡地址", 104.000000D, 30.000000D));
        mLocationModels.add(new LocationModel("打卡地址", 104.000000D, 30.000000D));
        mLocationModels.add(new LocationModel("打卡地址", 104.000000D, 30.000000D));
        mLocationModels.add(new LocationModel("打卡地址", 104.000000D, 30.000000D));
    }

    public void checkIn(String companyId) {
        ProgressDialog dialog = getDialog("打卡中");
        int index = new Random().nextInt(mLocationModels.size());
        if (index >= mLocationModels.size()) return;
        RetrofitHelper.<API>createDefault()
                .checkIn(
                        UserManager.INSTANCE.getInfo(UserManager.sID),
                        UserManager.INSTANCE.getInfo(UserManager.sTOKEN),
                        companyId,
                        "on",
                        mLocationModels.get(index).getLocation(),
                        mLocationModels.get(index).getLongitude(),
                        mLocationModels.get(index).getLatitude(),
                        "",
                        "",
                        "",
                        "",
                        0,
                        Common.getPhoneProduct(),
                        Common.getDeviceId(this),
                        Common.getCurrentDate()
                )
                .compose(CommonWrap.flowableWrap())
                .subscribe(new DisposableSubscriber<CheckInResponse>() {
                    @Override
                    public void onNext(CheckInResponse checkInResponse) {
                        if (checkInResponse == null) {
                            Toast.makeText(MainActivity.this, "打卡失败，返回体为null", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            return;
                        }
                        if ("TRUE".equals(checkInResponse.getResult())) {
                            Toast.makeText(MainActivity.this, "打卡成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "打卡失败", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable t) {
                        dialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private ProgressDialog getDialog(String content) {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage(content);
        dialog.show();
        return dialog;
    }
}
