package com.winhex.wys.wys.Utils;

//import android.os.SystemClock;
//import android.view.View;
//
//public class ClickUtils {
//    /** 快速点击设置的间隔 */
//    private final static long INTERVAL_FAST_CLICK = 200;
//    /** 记录时间 */
//    private static long sClickTimeMills = 0;
//    /** 连续点击3次 */
//    private static long[] mHits03 = new long[3];
//    /** 连续点击5次 */
//    private static long[] mHits05 = new long[5];
//
//    /**
//     * 判断是否是快速点击
//     *
//     * @return true - 快速点击 false - 正常逻辑
//     */
//    public static boolean isFastClick() {
//        long nowTimeMills = TimeUtils.getNowMills();
//        if (nowTimeMills - sClickTimeMills < INTERVAL_FAST_CLICK) {
//            return true;
//        }
//        sClickTimeMills = TimeUtils.getNowMills();
//        return false;
//    }
//
//    /**
//     * 连续点击3次
//     *
//     * @param view 视图
//     */
//    public static void clickThreeTimes(View view, View.OnClickListener onClickListener) {
//        System.arraycopy(mHits03, 1, mHits03, 0, mHits03.length - 1);
//        mHits03[mHits03.length - 1] = SystemClock.uptimeMillis();
//        if (mHits03[0] >= (SystemClock.uptimeMillis() - 1000)) {
//            onClickListener.onClick(view);
//        }
//    }
//
//    /**
//     * 连续点击5次
//     *
//     * @param view 视图
//     */
//    public static void clickFiveTimes(View view, View.OnClickListener onClickListener) {
//        System.arraycopy(mHits05, 1, mHits05, 0, mHits05.length - 1);
//        mHits05[mHits05.length - 1] = SystemClock.uptimeMillis();
//        if (mHits05[0] >= (SystemClock.uptimeMillis() - 1000)) {
//            onClickListener.onClick(view);
//        }
//    }
//}
