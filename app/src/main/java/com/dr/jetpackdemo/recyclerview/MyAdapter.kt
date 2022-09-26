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
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dr.jetpackdemo.R
import kotlinx.android.synthetic.main.layout_recycler_item_view.view.*
import java.lang.reflect.Method

/**
 * 功能：
 * 作者： duanrui
 * 时间： 2022/9/26
 */
class MyAdapter(private val recyclerView: RecyclerView) : RecyclerView.Adapter<MyHolder>() {
    private var selectedPosition = -1
    private var oldPosition = -1

    private  val UPDATE_REASON_LIKE = "99999dr"
    fun setSelectPosition(position: Int) {
        oldPosition = selectedPosition;
        selectedPosition = position
        //通知上次选中的viewHolder，取消选中
        notifyItemChanged(oldPosition,UPDATE_REASON_LIKE)
        //通知本地选中的viewHolder设置选中效果
        notifyItemChanged(position,UPDATE_REASON_LIKE)

//        //通知上次选中的viewHolder，取消选中
//        notifyItemChanged(oldPosition)
//        //通知本地选中的viewHolder设置选中效果
//        notifyItemChanged(position)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_recycler_item_view, parent, false)
        return MyHolder(itemView, recyclerView)
    }

    override fun getItemCount(): Int {
        return 15
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.setView(position, selectedPosition == position)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int, payloads: MutableList<Any>) {
        val updateByPayload = bindPosViewWithPayLoads(holder, position, payloads)
        if (!updateByPayload) {
            onBindViewHolder(holder, position)
        }
    }
    private fun bindPosViewWithPayLoads(holder: MyHolder, item: Int, payloads: MutableList<Any>): Boolean {
        var updateByPayload = false
        for (payload in payloads) {
            if (payload == UPDATE_REASON_LIKE) {
                holder.setView(item,item == selectedPosition,item>oldPosition)
                updateByPayload = true
            }
        }
        return updateByPayload
    }

}

class MyHolder(itemView: View, private val recyclerView: RecyclerView) : RecyclerView.ViewHolder(itemView) {
    init {
        itemView.tv.setOnClickListener {
            (recyclerView.adapter as MyAdapter).setSelectPosition(this.bindingAdapterPosition)
        }
    }

    private  val TAG = "MyHolder"


    fun setView(position: Int, isSelected: Boolean,isLeft:Boolean =  false) {
//        val layoutParams = itemView.layoutParams as LinearLayout.LayoutParams
//        if(position ==0){
//            layoutParams.setMargins(12,0,6,0)
//        }else if(position==14){
//            layoutParams.setMargins(6,0,12,0)
//        }
//        itemView.layoutParams =layoutParams

        if (isSelected) {
            itemView.tv.visibility = View.GONE
            itemView.tvBig.visibility = View.VISIBLE
            itemView.tvBig.text = "position：$position"
        } else {
            itemView.tvBig.visibility = View.GONE
            itemView.tv.visibility = View.VISIBLE
            itemView.tv.text = "position：$position"
        }
        itemView.tv.text = "position：$position"
        if (isSelected) {
            var leftOffset = 0
            if(isLeft){
                leftOffset = 263
            }
            val amount = getScrollAmount(recyclerView, itemView,leftOffset);//计算需要滑动的距离
            Log.e(TAG, "position=${bindingAdapterPosition},view.left=${itemView.left},paddingLeft=${recyclerView.paddingLeft},paddingRight=${recyclerView.paddingRight},width= ${recyclerView.width},Itemwidth= ${itemView.width},out=${amount[0]},${amount[1]}")
            //滑动到指定距离
            scrollToAmount(recyclerView, amount[0], amount[1])
        }
    }

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
    private fun getScrollAmount(recyclerView: RecyclerView, view: View, leftOffset:Int): IntArray {

        val parentWidth = recyclerView.width //在屏幕上展示的宽度
        val itemWidth = view.width+263 //item宽度
        val out = IntArray(2)
        //recyclerView 左侧的边界
        val parentLeft = recyclerView.paddingLeft;
        val parentTop = recyclerView.paddingTop;
        val parentRight = parentWidth - recyclerView.paddingRight;
        //item 距离屏幕上左侧可见边界的距离
        val childLeft = view.left + 0 - view.scrollX-leftOffset
        val childTop = view.top + 0 - view.scrollY

        //item左边距减去 Recyclerview不在屏幕内的部分，加当前Recyclerview一半的宽度就是居中
        val dx =
            childLeft - parentLeft - ((parentRight - itemWidth) / 2);

        val dy = childTop - parentTop - (parentTop - view.getHeight()) / 2;//同上
        out[0] = dx
        out[1] = dy
        return out
    }

    /**
     * dx和dy表示滑动的距离，
     * 当dx为正值，View内容会向左边滑动，反之向右边滑动；
     * 当dy为正值，View内容会向上边滑动，反之向下边滑动。
     *
     */
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
