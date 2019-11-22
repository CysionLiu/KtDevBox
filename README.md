### 一款基于Kotlin+MVP+组件化的app

麻雀虽小，五脏俱全。本app也是如此。


小，即轻量级，一是指app只专注于实现常见app基础的逻辑业务功能，并没有在某个功能点或者UI上做更为细节的实现；二是指app使用了简洁的的Kotlin语言作为实现语言，使用了相对简单的一种MVP实现方式，使用了一种比较轻量级的组件化方案。

全，当然是相对的，一是指app的后端也是本人开发，这能让整个业务逻辑更为全面，也能让感兴趣读者能更为全面的了解此app；二是指app涉及了当前技术趋势下安卓开发的多个技术点，包括kotlin,mvp,组件化,rxjava,retrofit等；三是指本app实际上可以作为一个快速开发框架，这主要得益于组件化的实现，具体怎么使用，后续会提到。


对后端实现感兴趣的，可以看看：

[后端实现-KtDevBox-backend](https://github.com/CysionLiu/KtDevBox-backend)


**app涉及主要功能点**

- 用户注册、登录以及资料管理功能；
- 博客创建、更新、删除和查看等功能；
- 博客的收藏、评论、点赞功能；
- 爬了网易新闻和一些电台的接口以展示，主要做组件化演示。


**项目架构**

项目核心架构如图所示：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190106224258780.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2N5c2lvbjE5ODk=,size_16,color_FFFFFF,t_70)


项目中的shell只含有MyApplication这一个类文件，目前app涉及的业务也仅usercenter和media这两个module，其中usercenter和module并无依赖关系。因此，此项目完全可以作为一个快速开发框架。简单做法：新建几个module编写自身的业务，仅需要被shell依赖，它们并不会受到原业务usercenter和module的影响。然后更改入口Activity之后，就是一个新项目，也不会被打包进apk中。更多组件化的使用可见[Appjoint](https://github.com/PrototypeZ/AppJoint)的介绍。


**主要使用的第三方库**

感谢：

- [Retrofit](https://github.com/square/retrofit)
- [RxJava](https://github.com/ReactiveX/RxJava)
- [glide](https://github.com/bumptech/glide)
- [statusbarutil](https://github.com/laobie/StatusBarUtil)
- [eventbus](https://github.com/greenrobot/EventBus)
- [appjoint](https://github.com/PrototypeZ/AppJoint)
- [logger](https://github.com/orhanobut/logger)
- [ultraviewpager](https://github.com/alibaba/UltraViewPager)
- [multiple-status-view](https://github.com/qyxxjd/MultipleStatusView)
- [SmartRefreshLayout](https://github.com/scwang90/SmartRefreshLayout)
- [DToast](https://github.com/Dovar66/DToast)
- [MultiWaveHeader](https://github.com/scwang90/MultiWaveHeader)
- [xedittext](https://github.com/woxingxiao/XEditText)
- [Targetfun](https://github.com/CysionLiu/kotlin-targetFun)



**License**


Copyright 2019 CysionLiu

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.