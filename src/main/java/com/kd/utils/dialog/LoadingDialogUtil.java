package com.kd.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xudeveframe.R;
@Deprecated
public class LoadingDialogUtil {

	private volatile static LoadingDialogUtil mLoadingDialogUtil;
	private  int mLoadingDialogCount;
	private  Dialog mDialog;

	private LoadingDialogUtil(){};
	
	public static  LoadingDialogUtil getInstance(){
		if(mLoadingDialogUtil ==null){
			synchronized (LoadingDialogUtil.class) {
				if(mLoadingDialogUtil==null){
					mLoadingDialogUtil=new LoadingDialogUtil();
				}
			}
			
		}
		return mLoadingDialogUtil;
	}
	
	public synchronized  void showLoadingDialog(Context context, String msg) {
		if (true) {
			LayoutInflater inflater = LayoutInflater.from(context);
			View v = inflater.inflate(R.layout.loading_dialog, null);
			LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);
			ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
			TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);
			Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
					context, R.anim.loading_animation);
			spaceshipImage.startAnimation(hyperspaceJumpAnimation);
			tipTextView.setText(msg);

			mDialog = new Dialog(context, R.style.loading_dialog);

			mDialog.setCancelable(true);
			mDialog.setContentView(layout, new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.FILL_PARENT,
					LinearLayout.LayoutParams.FILL_PARENT));
			mDialog.show();
		}
		mLoadingDialogCount++;
	}

	public synchronized  void cancelDialog() {
		mLoadingDialogCount--;
		if (mDialog!=null){
			mDialog.dismiss();
		}

	}
}
