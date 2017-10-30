package cn.shield.checkin.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.shield.checkin.R;
import cn.shield.checkin.model.LoginResponse;
import cn.shield.checkin.view.parallax.SensorTranslationUpdater;

/**
 * author: lixin(<a href="mailto:zhuxianlixin@gmail.com">zhuxianlixin@gmail.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2017-10-19 10:02<br/>
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder> {
    private List<LoginResponse.DataBean.CompanyBean> mData;
    private Context mContext;
    private IOnItemClickListener mIOnItemClickListener;

    public CompanyAdapter(IOnItemClickListener iOnItemClickListener, Context context, List<LoginResponse.DataBean.CompanyBean> datas) {
        mData = datas;
        mIOnItemClickListener = iOnItemClickListener;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_company, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (mData.size() <= 0 || mData.size() <= position) return;

        LoginResponse.DataBean.CompanyBean bean = mData.get(position);

        holder.mTextView.setText(bean.getCompany_name());
        if (TextUtils.isEmpty(bean.getCompany_logo())) {
            Glide.with(mContext).load(R.mipmap.ic_launcher_round).into(holder.mImageView);
        } else {
            Glide.with(mContext).load(bean.getCompany_logo()).into(holder.mImageView);
        }
        if (mIOnItemClickListener != null) {
            holder.itemView.setOnClickListener(view -> mIOnItemClickListener.onItemClick(bean, position));
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.pic);
            mTextView = itemView.findViewById(R.id.name);
        }
    }

    public interface IOnItemClickListener {
        void onItemClick(LoginResponse.DataBean.CompanyBean bean, int position);
    }

}
