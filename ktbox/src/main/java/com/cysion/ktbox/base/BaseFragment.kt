package com.cysion.ktbox.base

import android.app.Application
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cysion.ktbox.utils.logd
import com.orhanobut.logger.Logger
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class BaseFragment : Fragment() {

    var mEverLoaded = false

    val context by lazy {
        getActivity()!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        EventBus.getDefault().register(this)
        context.hasWindowFocus()//此时初始化context指向activity；可避免getActivity的空指针，即使fragment被回收
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        EventBus.getDefault().unregister(this)
        closeMvp()
    }


    //仅用来声明默认接收方法的，实际不应该接收任何消息；子类仿照该方法，自定义事件即可
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun fromEventBus(app: Application) {
    }

    //适用于viewpager添加fragment
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && !mEverLoaded) {
            //首次可见，还未加载过数据
            mEverLoaded = true
            lazyLoad()
        } else if (isVisibleToUser) {
            visibleAgain()
        } else {
            hideAgain()
        }
    }

    //首次可见，还未加载过数据
    protected open fun lazyLoad() {
        logd("懒加载-->" + javaClass.simpleName)
    }

    //再次可见
    protected open fun visibleAgain() {
        logd("再次可见-->" + javaClass.simpleName)
    }

    protected open fun hideAgain() {
        logd("再次隐藏-->" + javaClass.simpleName)
    }

    //针对add方式添加的fragment
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        //为方便调试找寻页面添加,add方式添加
        if (!hidden) {
            Logger.d("可见--->" + javaClass.simpleName)
        } else {
            Logger.d("不可见--->" + javaClass.simpleName)
        }
    }

    abstract fun getLayoutId(): Int

    abstract fun initView()

    abstract fun closeMvp()

    protected open fun initData() {
    }
}