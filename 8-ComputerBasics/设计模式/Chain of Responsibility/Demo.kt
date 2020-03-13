class ChainOfResponsibilityPattern {
    fun main(args: Array<String>) {
        //组装责任链 
        Handler handler1 = ConcreteHandler1 ()
        Handler handler2 = ConcreteHandler2 ()
        handler1.setNext(handler2)
        //提交请求 
        handler1.handleRequest("two")
    }
}

//抽象处理者角色
abstract class Handler {
    private var next: Handler? = null
    //处理请求的方法
    abstract fun handleRequest(request: String)
}

//具体处理者角色1
class ConcreteHandler1 : Handler() {
    override fun handleRequest(request: String) {
        if (request.equals("one")) {
            System.out.println("具体处理者1负责处理该请求！");
        } else {
            if (next != null) {
                next.handleRequest(request);
            } else {
                System.out.println("没有人处理该请求！");
            }
        }
    }
}

//具体处理者角色2
class ConcreteHandler2 : Handler() {
    override fun handleRequest(request: String) {
        if (request.equals("two")) {
            System.out.println("具体处理者2负责处理该请求！");
        } else {
            if (next != null) {
                next.handleRequest(request);
            } else {
                System.out.println("没有人处理该请求！");
            }
        }
    }
}