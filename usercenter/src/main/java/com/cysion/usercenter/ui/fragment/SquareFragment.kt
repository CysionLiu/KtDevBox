package com.cysion.usercenter.ui.fragment

import android.graphics.Color
import android.view.Gravity
import com.cysion.ktbox.base.BaseFragment
import com.cysion.other.dp2px
import com.cysion.usercenter.R
import com.cysion.usercenter.adapter.HomeTopPageAdapter
import com.cysion.usercenter.comm.Resolver.mediaActivityApi
import com.cysion.usercenter.entity.Carousel
import com.cysion.usercenter.presenter.SquarePresenter
import com.cysion.usercenter.ui.iview.SquareView
import com.tmall.ultraviewpager.UltraViewPager
import kotlinx.android.synthetic.main.fragment_square.*


class SquareFragment : BaseFragment(), SquareView {

    private val presenter by lazy {
        SquarePresenter().apply {
            attach(this@SquareFragment)
        }
    }
    private lateinit var topAdapter: HomeTopPageAdapter
    private val mCarousels: MutableList<Carousel> = mutableListOf()


    override fun getLayoutId(): Int = R.layout.fragment_square

    override fun initView() {
        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL)
        topAdapter = HomeTopPageAdapter(context, mCarousels)
        ultraViewPager.adapter = topAdapter
        configViewPager()
    }

    private fun configViewPager() {
        ultraViewPager.initIndicator()
        ultraViewPager.getIndicator()
            .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
            .setFocusColor(Color.RED)
            .setNormalColor(Color.WHITE)
            .setRadius(context.dp2px(3).toInt())
        ultraViewPager.apply {
            indicator.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM)
                .setMargin(0, 0, 0, context.dp2px(10).toInt())
                .build()
            setInfiniteLoop(true)
            ultraViewPager.setAutoScroll(2000)
        }

        topAdapter.setItemClickListener {
            if (it.type.equals("news")) {
                mediaActivityApi.startNewsActivity(context,it.title,it.link)
            } else if (it.type.equals("music")) {
                mediaActivityApi.startSongsActivity(context,it.title,it.mediaId)
            }
        }

    }

    override fun lazyLoad() {
        super.lazyLoad()
        presenter.getCarousel()
    }

    override fun closeMvp() {
        presenter.detach()
    }

    override fun setCarousels(carousels: MutableList<Carousel>) {
        mCarousels.clear()
        mCarousels.addAll(carousels)
        ultraViewPager.refresh()
    }

    override fun loading() {
    }

    override fun stopLoad() {
    }

    override fun error(code: Int, msg: String) {
    }
}
