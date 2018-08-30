# 关于 DialogFragment

DialogFragment 是在 Android 3.0 的时候被引入的，从其名字可以很直观的看出它是一种基于 Fragment 的 Dialog ，可以用来创建对话框，它是用来替代 Dialog 的。一个新事物的出现是为了解决旧事物存在的问题，那不建议使用的 Dialog 存在什么问题呢？下面简单的说下。

## Dialog 存在问题：

- 在手机配置发生变化后（比如：旋屏后），变化之前显示的 Dialog ，变化之后不会显示，更别提 Dialog 状态的恢复了。
- 管理自定义的 Dialog 和系统原生的 Dialog 麻烦

## DialogFragment 怎么解决 Dialog 存在的问题：

- DialogFragment 说到底还是一个 Fragment ，因此它继承了 Fragment 的所有特性。同理 FragmentManager 会管理 DialogFragment。在手机配置发生变化的时候，FragmentManager 可以负责现场的恢复工作。调用 DialogFragment 的 setArguments (bundle) 方法进行数据的设置，可以保证 DialogFragment 的数据也能恢复。
- DialogFragment 里的 onCreateView 和 onCreateDIalog 2个方法，onCreateView 可以用来创建自定义 Dialog ， onCreateDIalog 可以用 Dialog 来创建系统原生 Dialog 。可以在一个类中管理2种不同的 Dialog。

## DialogFragment 使用

DialogFragment 拥有 Fragment 的所有生命周期也有自己的特有的方法先从生命周期开始来看：

### 生命周期

onAttach -> onCreate -> onCreateDialog -> onCreateView -> onActivityCreated -> onStart -> onResume

onDismiss -> onPause -> onStop -> onDestroyView -> onDestroy -> onDetach