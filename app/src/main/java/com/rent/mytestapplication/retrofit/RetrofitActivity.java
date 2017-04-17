package com.rent.mytestapplication.retrofit;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.rent.mytestapplication.R;
import com.rent.mytestapplication.common.view.BaseView;
import com.rent.mytestapplication.mvp.BaseActivity;
import com.rent.mytestapplication.retrofit.adapter.RxJava2CallAdapterFactory;
import com.rent.mytestapplication.retrofit.bean.JsonResult;
import com.rent.mytestapplication.retrofit.bean.ListResult;
import com.rent.mytestapplication.retrofit.bean.MapResult;
import com.rent.mytestapplication.retrofit.bean.Result;
import com.rent.mytestapplication.retrofit.converter.JsonConverterFactory;
import com.rent.mytestapplication.retrofit.observable.CallObservable;
import com.rent.mytestapplication.retrofit.observer.DialogObserver;
import com.rent.mytestapplication.upload.bean.UploadBean;
import com.rent.mytestapplication.upload.functions.FileConverterFunction;
import com.rent.mytestapplication.upload.provider.ImageUploadProvider;
import com.rent.mytestapplication.upload.provider.impl.ImageUploadProviderImpl;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends BaseActivity implements BaseView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

//        Observable.interval(1, TimeUnit.SECONDS)
//                .doOnDispose(new Action() {
//                    @Override
//                    public void run() throws Exception {
//                        System.out.println("dispose onCreate()");
//                    }
//                })
//                .compose(this.<Long>bindUntilEvent(ActivityEvent.PAUSE))
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        System.out.println("Started in onCreate(), running until onPause(): " + aLong);
//                    }
//                });
    }

    public void testGet(View v) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpClient.getHttpClient())
                .build();
        Test2Service service = retrofit.create(Test2Service.class);
        Call<List<Repo>> call = service.list("feveren", "111");
        call.enqueue(new Callback<List<Repo>>() {

            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                List<Repo> list = response.body();
                for (Repo repo : list) {
                    System.out.println(repo);
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {

            }
        });
    }

    public void postPost(View v) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpClient.getHttpClient())
                .build();
        Test2Service service = retrofit.create(Test2Service.class);
        Call<List<Repo>> call = service.post("feveren", "111", "aa@163.com");
        call.enqueue(new Callback<List<Repo>>() {

            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {

            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {

            }
        });
    }

    public void list(View v) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TestService service = retrofit.create(TestService.class);
        Call<List<Repo>> call = service.listRepos("feveren");
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                List<Repo> list = response.body();
                for (Repo repo : list) {
                    System.out.println(repo);
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {

            }
        });
    }


    public void testPost(View v) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpClient.getHttpClient())
                .build();
        TestService service = retrofit.create(TestService.class);
        Call<List<Repo>> call = service.post("feveren", "嗷嗷啊", "胜多负少");
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                List<Repo> list = response.body();
                if (list != null) {
                    for (Repo repo : list) {
                        System.out.println(repo);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {

            }
        });
    }

    public void getList(View v) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://t.zm.gaiay.cn/")
                .addConverterFactory(new JsonConverterFactory())
                .client(HttpClient.getHttpClient())
                .build();
        TestService service = retrofit.create(TestService.class);
        Call<ListResult<Ad>> call = service.getADList(1483939981359L);
        call.enqueue(new Callback<ListResult<Ad>>() {
            @Override
            public void onResponse(Call<ListResult<Ad>> call, Response<ListResult<Ad>> response) {
                ListResult<RetrofitActivity.Ad> bean = response.body();
                System.out.println("code = " + bean.code);
                System.out.println("message = " + bean.message);
                List<RetrofitActivity.Ad> data = bean.data;
                System.out.println("data = " + data);
                if (data != null) {
                    for (RetrofitActivity.Ad ad : data) {
                        System.out.println(ad);
                    }
                }
            }

            @Override
            public void onFailure(Call<ListResult<Ad>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getBean(View v) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://t.zm.gaiay.cn/")
                .addConverterFactory(new JsonConverterFactory())
                .client(HttpClient.getHttpClient())
                .build();
        TestService service = retrofit.create(TestService.class);
        Call<Result<Detail>> call = service.getCardDetail("46f1c9142d255075c-220e");
        call.enqueue(new Callback<Result<Detail>>() {
            @Override
            public void onResponse(Call<Result<Detail>> call, Response<Result<Detail>> response) {
                Result<Detail> bean = response.body();
                System.out.println("code = " + bean.code);
                System.out.println("message = " + bean.message);
                System.out.println("data = " + bean.data);
            }

            @Override
            public void onFailure(Call<Result<Detail>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getMap(View v) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://t.zm.gaiay.cn/")
                .addConverterFactory(new JsonConverterFactory())
                .client(HttpClient.getHttpClient())
                .build();
        TestService service = retrofit.create(TestService.class);
        Call<MapResult> call = service.getCardDetailMap("46f1c9142d255075c-220e");
        call.enqueue(new Callback<MapResult>() {
            @Override
            public void onResponse(Call<MapResult> call, Response<MapResult> response) {
                MapResult bean = response.body();
                System.out.println("code = " + bean.code);
                System.out.println("message = " + bean.message);
                Map<String, Object> data = bean.data;
                System.out.println("data = " + data);
                if (data != null) {
                    for (Map.Entry<String, Object> entry : data.entrySet()) {
                        System.out.println(entry.getKey() + " = " + entry.getValue());
                    }
                }
            }

            @Override
            public void onFailure(Call<MapResult> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getJson(View v) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://t.zm.gaiay.cn/")
                .addConverterFactory(new JsonConverterFactory())
                .client(HttpClient.getHttpClient())
                .build();
        TestService service = retrofit.create(TestService.class);
        Call<JsonResult> call = service.getCardDetailJson("46f1c9142d255075c-220e");
        call.enqueue(new Callback<JsonResult>() {
            @Override
            public void onResponse(Call<JsonResult> call, Response<JsonResult> response) {
                JsonResult bean = response.body();
                System.out.println("code = " + bean.code);
                System.out.println("message = " + bean.message);
                System.out.println("data = " + bean.data);
            }

            @Override
            public void onFailure(Call<JsonResult> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getRxList(View v) {
        TestService service = Requester.get().create(TestService.class);
        CallObservable<ListResult<Ad>> obz = service.getRxADList(1483939981359L);
        obz.compose(this.<ListResult<Ad>>bindUntilEvent(ActivityEvent.DESTROY));
        obz.subscribe(this, new DialogObserver<ListResult<Ad>>(this, "加载中") {

            @Override
            protected void onSuccess(ListResult<Ad> result) {
                List<RetrofitActivity.Ad> data = result.data;
                System.out.println("data = " + data);
                if (data != null) {
                    for (RetrofitActivity.Ad ad : data) {
                        System.out.println(ad);
                    }
                }
            }
        });
    }


    public void getRxBean(View v) {
        TestService service = Requester.get().create(TestService.class);
        service.getRxCardDetail("46f1c9142d255075c-220e").subscribe(this, new Observer<Result<Detail>>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(Result<Detail> bean) {
                System.out.println("code = " + bean.code);
                System.out.println("message = " + bean.message);
                System.out.println("data = " + bean.data);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError");
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }

    public void getRxMap(View v) {
        TestService service = Requester.get().create(TestService.class);
        Observable<MapResult> obz = service.getRxCardDetailMap("46f1c9142d255075c-220e");
        obz.subscribeOn(Schedulers.io()).subscribe(new Observer<MapResult>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(MapResult bean) {
                System.out.println("code = " + bean.code);
                System.out.println("message = " + bean.message);
                Map<String, Object> data = bean.data;
                System.out.println("data = " + data);
                if (data != null) {
                    for (Map.Entry<String, Object> entry : data.entrySet()) {
                        System.out.println(entry.getKey() + " = " + entry.getValue());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }

    public void getRxJson(View v) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://t.zm.gaiay.cn/")
                .addConverterFactory(new JsonConverterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(HttpClient.getHttpClient())
                .build();
        TestService service = retrofit.create(TestService.class);
        Observable<JsonResult> obz = service.getRxCardDetailJson("46f1c9142d255075c-220e");
        obz.subscribe(new Observer<JsonResult>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(JsonResult bean) {
                System.out.println("code = " + bean.code);
                System.out.println("message = " + bean.message);
                Map<String, Object> data = bean.data;
                System.out.println("data = " + data);
                if (data != null) {
                    for (Map.Entry<String, Object> entry : data.entrySet()) {
                        System.out.println(entry.getKey() + " = " + entry.getValue());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }

    public void testNetwork(View v) {
        Map<String, String> map = new HashMap<>();
        map.put("lastQueryTime", 1483939981359L + "");
        HttpClient.get("https://t.zm.gaiay.cn/api/zm/ad-scene/", map, new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                System.out.println(response.body().string());
            }
        });
    }

    private MultipartBody.Part addPart(String upload) {
        File file = new File(upload);
        return MultipartBody.Part.createFormData("FILE", file.getName(), RequestBody.create(null, file));
    }

    public void uploadFile(View v) {
//        String clientId = "ae32c42e64434648af5cccb25ee1e906";
//        String domain = "t.zm.gaiay.cn";
//
//        List<MultipartBody.Part> list = new ArrayList<>();
//        list.add(addPart(Environment.getExternalStorageDirectory() + "/upload1.jpg"));
////        list.add(addPart(Environment.getExternalStorageDirectory() + "/upload2.jpg"));
//
//        Requester.get().create(TestService.class)
//                .uploadFile(RequestBody.create(null, clientId), RequestBody.create(null, domain), list)
//                .setConverter(new FileConverterFunction())
//                .subscribe(this, new ResponseObserver<Result<List<UploadBean>>>() {
//
//                    @Override
//                    protected void onSuccess(Result<List<UploadBean>> result) {
//                        System.out.println("Thread name: " + Thread.currentThread());
//                        System.out.println(result.code);
//                        System.out.println(result.message);
//                        for (UploadBean upload : result.data) {
//                            System.out.println(upload.fileName + " " + upload.url);
//                        }
//                    }
//                });

        ImageUploadProvider provider = new ImageUploadProviderImpl();
        provider.upload(new UploadBean(Environment.getExternalStorageDirectory() + "/upload1.jpg"))
                .composeCommon(this)
                .map(new FileConverterFunction())
                .subscribe(new DialogObserver<Result<List<UploadBean>>>(this, "上传中") {

                        @Override
                        protected void onSuccess(Result<List<UploadBean>> result) {
                            System.out.println("Thread name: " + Thread.currentThread());
                            for (UploadBean upload : result.data) {
                                System.out.println(upload.fileName + " " + upload.url);
                            }
                        }
                });
    }

    public static class Repo {
        long id;
        String name;
        String full_name;

        @Override
        public String toString() {
            return "Repo{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", full_name='" + full_name + '\'' +
                    '}';
        }
    }

    public static class Ad {
        public int id;
        public String pic;
        public String link;

        @Override
        public String toString() {
            return "Ad{" +
                    "id=" + id +
                    ", pic='" + pic + '\'' +
                    ", link='" + link + '\'' +
                    '}';
        }
    }

    public static class Detail {
        String id;
        String linkName;
        String linkUrl;
        String createTime;
        String updateTime;

        @Override
        public String toString() {
            return "Detail{" +
                    "id='" + id + '\'' +
                    ", linkName='" + linkName + '\'' +
                    ", linkUrl='" + linkUrl + '\'' +
                    ", createTime='" + createTime + '\'' +
                    ", updateTime='" + updateTime + '\'' +
                    '}';
        }
    }
}
