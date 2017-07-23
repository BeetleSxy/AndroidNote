# json数据解析
Android 中常用的 json 数据解析有,Gson,Fastjson和jsonObject<br>
那么那一种效率高呢?是jsonObject

一般情况下我们都是用三方框架去解析json数据:但是在特殊情况下我们去使用JsonObject:

1. json数据格式相当复杂的情况下
2. json数据格式相当简单的情况下`{"success":"true"}`

> ## Gson 

主要是javabean的创建

* {} --> 类(object)
* [] --> List
* 无符号 --> String(数据类型)
* 集合没有名称 --> TypeToken

> ### Gson 的基本使用
	
	public void newGson(){
		Gson gson = new Gson();
		gson.fome();
	}


> ## jsonObject



> ## Fastjson