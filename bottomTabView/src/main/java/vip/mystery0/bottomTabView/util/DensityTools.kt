package vip.mystery0.bottomTabView.util

import android.content.Context

object DensityTools {
	/**
	 * dp转px
	 * @param context 上下文
	 * @param dpValue dp值
	 * @return 转换之后的px值
	 */
	fun dp2px(context: Context, dpValue: Float): Float {
		return (dpValue * context.resources.displayMetrics.density + 0.5).toFloat()
	}

	/**
	 * px转dp
	 * @param context 上下文
	 * @param pxValue px值
	 * @return 转换之后的dp值
	 */
	fun px2dp(context: Context, pxValue: Float): Float {
		return (pxValue / context.resources.displayMetrics.density + 0.5).toFloat()
	}

	/**
	 * 获取屏幕宽度
	 * @param context 上下文
	 * @return px值
	 */
	fun getScreenWidth(context: Context): Int {
		return context.resources.displayMetrics.widthPixels
	}


	/**
	 * 获取屏幕高度
	 * @param context 上下文
	 * @return px值
	 */
	fun getScreenHeight(context: Context): Int {
		return context.resources.displayMetrics.heightPixels
	}
}