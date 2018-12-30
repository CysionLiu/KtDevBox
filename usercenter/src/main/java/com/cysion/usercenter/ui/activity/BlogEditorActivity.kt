package com.cysion.usercenter.ui.activity

import android.app.Activity
import android.os.Bundle
import com.cysion.ktbox.base.BaseActivity
import com.cysion.ktbox.utils.whiteTextTheme
import com.cysion.other.color
import com.cysion.other.startActivity_ex
import com.cysion.targetfun._addTextChangedListener
import com.cysion.uibox.bar.TopBar
import com.cysion.uibox.dialog.Alert
import com.cysion.uibox.toast.toast
import com.cysion.usercenter.R
import com.cysion.usercenter.constant.BLOG_CONTENT
import com.cysion.usercenter.constant.BLOG_EDIT_TYPE
import com.cysion.usercenter.constant.BLOG_TITLE
import com.cysion.usercenter.constant.BUNDLE_KEY
import com.cysion.usercenter.presenter.BlogEditorPresenter
import com.cysion.usercenter.ui.iview.BlogEditorView
import kotlinx.android.synthetic.main.activity_blog_editor.*

class BlogEditorActivity : BaseActivity(), BlogEditorView {

    /*
    启动该activity
    type=0，创建；1，编辑
     */
    companion object {
        fun start(activity: Activity, title: String, content: String, type: Int = 0) {
            val b = Bundle()
            b.putString(BLOG_TITLE, title)
            b.putString(BLOG_CONTENT, content)
            b.putInt(BLOG_EDIT_TYPE, type)
            activity.startActivity_ex<BlogEditorActivity>(BUNDLE_KEY, b)
        }
    }

    private val presenter by lazy {
        BlogEditorPresenter().apply {
            attach(this@BlogEditorActivity)
        }
    }

    private val title: String by lazy {
        intent.getBundleExtra(BUNDLE_KEY).getString(BLOG_TITLE)
    }
    private val content: String by lazy {
        intent.getBundleExtra(BUNDLE_KEY).getString(BLOG_CONTENT)
    }
    private val type: Int by lazy {
        intent.getBundleExtra(BUNDLE_KEY).getInt(BLOG_EDIT_TYPE)
    }

    override fun getLayoutId(): Int = R.layout.activity_blog_editor

    override fun initView() {
        whiteTextTheme(color(R.color.colorAccent))
        etTitle._addTextChangedListener {
            _afterTextChanged {
                it?.apply {
                    if (length >= 139) {
                        toast("超过140字了")
                    }
                }
            }
        }
        etContent._addTextChangedListener {
            _afterTextChanged {
                it?.apply {
                    if (length >= 2000) {
                        toast("超过最大字数了")
                    }
                }
            }
        }
        initTopBar()
        initEditor()

    }

    private fun initEditor() {
        if (type == 0) {

        } else {
            etTitle.setText(title)
            etContent.setText(content)
        }
    }

    private fun initTopBar() {
        topbar.apply {
            initElements(right = TopBar.ELEMENT.TEXT)
            setTitle("编辑")
            setTexts("发布", TopBar.Pos.RIGHT)
            setOnTopBarClickListener { obj, pos ->
                if (pos == TopBar.Pos.LEFT) {
                    finish()
                } else if (pos == TopBar.Pos.RIGHT) {
                    if (etTitle.text.length < 1) {
                        toast("请输入标题")
                    } else {
                        presenter.createBlog(
                            etTitle.text.toString().trim()
                            , etContent.text.toString().trim()
                        )
                    }
                }
            }
        }
    }

    override fun createDone() {
        toast("创建成功")
        finish()
    }

    override fun updateDone() {
    }

    override fun loading() {
        Alert.loading(self)
    }

    override fun stopLoad() {
        Alert.close()
    }

    override fun error(code: Int, msg: String) {
        toast(msg)
    }

    override fun closeMvp() {
        presenter.detach()
    }
}
