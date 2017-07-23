##Retrofit2
这里有一段代码，是Retrofit最简单的应用

	public class Example01 {
		public interface BlogService {
	        @GET("blog/{id}") //这里的{id} 表示是一个变量
	        Call<ResponseBody> getBlog(/** 这里的id表示的是上面的{id} */@Path("id") int id);
		}
	
	    public static void main(String[] args) throws IOException {
	        Retrofit retrofit = new Retrofit.Builder()
	                .baseUrl("http://localhost:4567/")
	                .build();
	
	        BlogService service = retrofit.create(BlogService.class);
	        Call<ResponseBody> call = service.getBlog(2);
	        // 用法和OkHttp的call如出一辙
	        // 不同的是如果是Android系统回调方法执行在主线程
	        call.enqueue(new Callback<ResponseBody>() {
	            @Override
	            public void onResponse(
	                    Call<ResponseBody> call, Response<ResponseBody> response) {
	                try {
	                    System.out.println(response.body().string());
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	
	            @Override
	            public void onFailure(Call<ResponseBody> call, Throwable t) {
	                t.printStackTrace();
	            }
	        });
	    }
	}

这段代码简单的使用了OkHttp3去做了网络求中的个中操作，使用前要去创建一个接口去用来定义使用的是GIT还是post请求，使用前需要定义一个主域名，使用的是baseUrl方法。然后将网络访问的过程交给OkHttp去实现。使用的的方法和OkHttp是一样的。