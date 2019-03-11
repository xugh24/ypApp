package com.yuepang.yuepang.activity;

import android.os.Build;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuepang.yuepang.R;
import com.yuepang.yuepang.Util.BindView;
import com.yuepang.yuepang.dialog.PersonalDialog;

import org.w3c.dom.Text;

/**
 * Created by xugh on 2019/3/10.
 */

public class PersonageActivity extends BaseActivity implements PersonalDialog.CallBack {

    public static final int NICK = 0x01;
    public static final int NAME = 0x02;
    public static final int SEX = 0x03;
    public static final int BIR = 0x04;
    public static final int TEL = 0x05;
    public static final int ID = 0x06;
    public static final int COMPANY = 0x07;

    @BindView(id = R.id.iv_head, click = true)
    private ImageView ivHead;

    @BindView(id = R.id.ll_nick, click = true)
    private LinearLayout llnick;//昵称
    @BindView(id = R.id.ll_realname, click = true)
    private LinearLayout llname;//真实姓名
    @BindView(id = R.id.ll_sex, click = true)
    private LinearLayout llsex;//性别
    @BindView(id = R.id.ll_birthday, click = true)
    private LinearLayout llbirthday;//生日
    @BindView(id = R.id.ll_tel, click = true)
    private LinearLayout lltel;//联系方式
    @BindView(id = R.id.ll_id, click = true)
    private LinearLayout llid;//
    @BindView(id = R.id.ll_company, click = true)
    private LinearLayout llcompany;//


    @BindView(id = R.id.tv_nick)
    private TextView tvNick;//
    @BindView(id = R.id.tv_realname)
    private TextView tvName;//
    @BindView(id = R.id.tv_sex)
    private TextView tvsex;//
    @BindView(id = R.id.tv_birthday)
    private TextView tvbirthday;//
    @BindView(id = R.id.tv_tel)
    private TextView tvTel;//
    @BindView(id = R.id.tv_id)
    private TextView tvId;//
    @BindView(id = R.id.tv_company)
    private TextView tvcompany;//


    @Override
    protected String getMyRTitle() {
        return null;
    }

    @Override
    protected String getMyTittle() {
        return "修改资料";
    }

    @Override
    protected int getContentViewId() {
        return R.layout.personage_ly;
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ll_nick:// 昵称
                showInput(NICK);
                break;
            case R.id.ll_realname: // 真实姓名
                showInput(NAME);
                break;
            case R.id.ll_sex: // 性别
                showInput(SEX);
                break;
            case R.id.ll_birthday: // 生日
                showInput(BIR);
                break;
            case R.id.ll_tel: // 电话
                showInput(TEL);
                break;
            case R.id.ll_id: // id
                showInput(ID);
                break;
            case R.id.ll_company: // 公司
                showInput(COMPANY);
                break;
        }
    }

    PersonalDialog personalDialog;

    private void showInput(int type) {
        if (personalDialog == null) {
            personalDialog = new PersonalDialog(this, this);//        }d:\Program Files\Gitd:\Program Files\Git
        }
        personalDialog.setType(type);
        personalDialog.show();
    }

    @Override
    public void callBack(int type, Object obj) {
        if (obj != null && getTargetText(type) != null) {
            String data = (String) obj;
            getTargetText(type).setText(data);
        }
    }

    private TextView getTargetText(int type) {
        switch (type) {
            case PersonageActivity.NICK:
                return tvNick;
            case PersonageActivity.NAME:
                return tvName;
            case PersonageActivity.SEX:
                return tvsex;
            case PersonageActivity.ID:
                return tvId;
            case PersonageActivity.TEL:
                return tvTel;
            case PersonageActivity.COMPANY:
                return tvcompany;
            case PersonageActivity.BIR:
                return tvbirthday;
        }
        return null;
    }
}
