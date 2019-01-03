package com.cysion.ktbox.base

/**
 * EventBus的消息父类
 * 每个业务组件的消息当由本组件自己发送和接收，不可跨业务组件发送和接收Eventbus消息
 * 业务组件自己定义本组件消息类型，每个组件里的消息类型应是该类的一个子类
 * 为进一步避免EventBus消息满天飞，本消息传递尽量只用在四大组件+Fragment之间通信
 */

abstract class BaseEvent(
    val tag: Int,
    val msg: String
) {
    var arg: Int? = 0
    var obj: Any? = null
    var extend: Map<String, String>? = null
}

//例子
class Tem(tag: Int, str: String) : BaseEvent(tag, str) {

}