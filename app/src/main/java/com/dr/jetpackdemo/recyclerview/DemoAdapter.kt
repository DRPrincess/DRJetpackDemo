package com.dr.jetpackdemo.recyclerview

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import androidx.recyclerview.widget.RecyclerView
import com.dr.jetpackdemo.R
import kotlinx.android.synthetic.main.layout_recycler_item_view.view.*
import java.lang.reflect.Method

/**
 * 功能：
 * 作者： duanrui
 * 时间： 2022/9/26
 */
class DemoAdapter(private val recyclerView: RecyclerView) : RecyclerView.Adapter<DemoHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_recycler_item_view_demo, parent, false)

        return DemoHolder(itemView, recyclerView)
    }

    override fun getItemCount(): Int {
        return 15
    }

    override fun onBindViewHolder(holder: DemoHolder, position: Int) {
        holder.itemView.tv.text = "position:$position"
    }


}

class DemoHolder
    (itemView: View, private val recyclerView: RecyclerView) : RecyclerView.ViewHolder(itemView) {
    init {
        itemView.isFocusable = true
        itemView.setOnClickListener {
            val amount = getScrollAmount(recyclerView, itemView);//计算需要滑动的距离
            //滑动到指定距离
            Log.d(TAG, "position=${bindingAdapterPosition},view.left=${itemView.left},paddingLeft=${recyclerView.paddingLeft},paddingRight=${recyclerView.paddingRight},width= ${recyclerView.width},Itemwidth= ${itemView.width},out=${amount[0]},${amount[1]}")
            scrollToAmount(recyclerView, amount[0], amount[1]);
        }
//        itemView.setFocusable(true)
//        itemView.onFocusChangeListener = object : View.OnFocusChangeListener {
//            override fun onFocusChange(v: View?, hasFocus: Boolean) {
//                if (hasFocus) {
//                    val amount = getScrollAmount(recyclerView, itemView);//计算需要滑动的距离
//                    //滑动到指定距离
//                    scrollToAmount(recyclerView, amount[0], amount[1]);
//                    //itemView.setTranslationZ(20);//阴影
//                    ofFloatAnimator(itemView, 1f, 1.3f);//放大
//                } else {
//                    //itemView.setTranslationZ(0);
//                    ofFloatAnimator(itemView, 1.3f, 1f);
//                }
//            }
//        }
    }

    private val TAG = "DemoHolder"


    //放大动画
    private fun ofFloatAnimator(view: View, start: Float, end: Float) {
        val animatorSet = AnimatorSet()
        animatorSet.duration = 700 //动画时间
        val scaleX = ObjectAnimator.ofFloat(view, "scaleX", start, end)
        val scaleY = ObjectAnimator.ofFloat(view, "scaleY", start, end)
        animatorSet.interpolator = DecelerateInterpolator() //插值器
        animatorSet.play(scaleX).with(scaleY) //组合动画,同时基于x和y轴放大
        animatorSet.start()
    }

    /**
     * 计算需要滑动的距离,使焦点在滑动中始终居中
     * @param recyclerView
     * @param view
     */
    private fun getScrollAmount(recyclerView: RecyclerView, view: View): IntArray {

        val out = IntArray(2)
        val parentLeft = recyclerView.getPaddingLeft();
        val parentTop = recyclerView.getPaddingTop();
        val parentRight = recyclerView.getWidth() - recyclerView.getPaddingRight();
        val childLeft = view.getLeft() + 0 - view.getScrollX();
        val childTop = view.getTop() + 0 - view.getScrollY();

        val dx =
            childLeft - parentLeft - ((parentRight - view.getWidth()) / 2);
        //item左边距减去Recyclerview不在屏幕内的部分，加当前Recyclerview一半的宽度就是居中

        val dy = childTop - parentTop - (parentTop - view.getHeight()) / 2;//同上
        out[0] = dx
        out[1] = dy
        return out
    }

    //根据坐标滑动到指定距离
    private fun scrollToAmount(recyclerView: RecyclerView, dx: Int, dy: Int) {
        //如果没有滑动速度等需求，可以直接调用这个方法，使用默认的速度
//                recyclerView.smoothScrollBy(dx,dy);

        //以下对滑动速度提出定制
        try {
            val recClass: Class<*> = recyclerView.javaClass
            val smoothMethod: Method = recClass.getDeclaredMethod(
                "smoothScrollBy",
                Int::class.javaPrimitiveType,
                Int::class.javaPrimitiveType,
                Interpolator::class.java,
                Int::class.javaPrimitiveType
            )
            smoothMethod.invoke(
                recyclerView,
                dx,
                dy,
                AccelerateDecelerateInterpolator(),
                700
            ) //时间设置为700毫秒，
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}