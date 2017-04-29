package core.utils;

import blog.pojo.blog;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonUitl {

    private static Gson gson = new Gson();

    public static void convert() {
        // 读取nameID.txt文件中的NAMEID字段（key）对应值（value）并存储
        ArrayList<String> list = new ArrayList<String>();
        BufferedReader brname;
        try {
            brname = new BufferedReader(new FileReader("D:\\test\\jsonTest\\blog.j"));// 读取NAMEID对应值
            String sname = null;
            while ((sname = brname.readLine()) != null) {
                // System.out.println(sname);
                list.add(sname);// 将对应value添加到链表存储
            }
            brname.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        // 读取原始json文件并进行操作和输出
        try {
            BufferedReader br = new BufferedReader(new FileReader(
                    "D:\\test\\jsonTest\\blog.j"));// 读取原始json文件
            BufferedWriter bw = new BufferedWriter(new FileWriter(
                    "D:\\test\\jsonTest\\blog.j"));// 输出新的json文件
            String s = null, ws = null;
            while ((s = br.readLine()) != null) {

                try {

                    new JsonParser();

                    JsonObject dataJson = new JsonParser().parse(s).getAsJsonObject();

//                    JSONObject dataJson = new JSONObject(s);// 创建一个包含原始json串的json对象

//                    JSONArray stars = dataJson.getJSONArray("stars");// 找到features json数组

                    JsonArray j = dataJson.getAsJsonObject().getAsJsonArray("stars");



//                    System.out.println(stars.toString());


//                    for (int i = 0; i < features.length(); i++) {
//                        JSONObject info = features.getJSONObject(i);// 获取features数组的第i个json对象
//                        JSONObject properties = info.getJSONObject("properties");// 找到properties的json对象
//                        String name = properties.getString("name");// 读取properties对象里的name字段值
//                        System.out.println(name);
//                        properties.put("NAMEID", list.get(i));// 添加NAMEID字段
//                        // properties.append("name", list.get(i));
//                        System.out.println(properties.getString("NAMEID"));
//                        properties.remove("ISO");// 删除ISO字段
//                    }



//                    ws = dataJson.toString();
//
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

//            bw.write(ws);
            // bw.newLine();

//            bw.flush();
//            br.close();
//            bw.close();

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public static blog fetch(String title){

        blog oneglg=null;

        try {
            BufferedReader br = new BufferedReader(new FileReader("D:\\test\\jsonTest\\blog.j"));// 读取原始json文件

            String s = null, ws = null;
            while ((s = br.readLine()) != null) {
                try {


                    JsonObject dataJson = new JsonParser().parse(s).getAsJsonObject();

                    String str = dataJson.get("stars").getAsString();

//                    JsonArray stars = dataJson.getAsJsonObject().getAsJsonArray("stars");
                    JsonArray stars = new JsonParser().parse(str).getAsJsonArray();

                    for (int j=0;j<stars.size();j++){

                        blog blog = gson.fromJson(stars.get(j), blog.class);

                        if(blog.getTitle().equals(title)){
                            oneglg=new blog();
                            oneglg.setAuthor(blog.getAuthor());
                            oneglg.setTitle(blog.getTitle());
                            oneglg.setContent(blog.getContent());
                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            br.close();


        } catch (IOException e) {

            e.printStackTrace();

        }

        return oneglg;

    }

    public static void edit(blog blogtoedit){

        blog oneglg=null;
        String filepath = "D:\\test\\jsonTest\\blog.j";

        List<blog> list = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filepath));// 读取原始json文件
            BufferedWriter bw = null;

            String s = null, ws = null;

            while ((s = br.readLine()) != null) {
                try {

                    JsonObject dataJson = new JsonParser().parse(s).getAsJsonObject();

                    String str = dataJson.get("stars").getAsString();


//                    JsonArray stars = dataJson.getAsJsonObject().getAsJsonArray("stars");
                    JsonArray stars = new JsonParser().parse(str).getAsJsonArray();

                    for (int j=0;j<stars.size();j++){

                        JsonElement el = stars.get(j);
                        blog blog = gson.fromJson(el, blog.class);

                        if(!blog.getTitle().equals(blogtoedit.getTitle())){

                            oneglg=new blog();
                            oneglg.setTitle(blog.getTitle());
                            oneglg.setAuthor(blog.getAuthor());
                            oneglg.setContent(blog.getContent());
                            list.add(oneglg);

                        }

                    }

                    list.add(blogtoedit);

                    JsonArray jsonArray = new Gson().toJsonTree(list, new TypeToken<List<blog>>() {}.getType()).getAsJsonArray();

                    JSONObject jb = new JSONObject();

                    jb.put("stars",jsonArray);

                    ws = jb.toString();

                    bw = new BufferedWriter(new FileWriter(filepath,false));// 输出新的json文件
                    bw.write(ws);
                    bw.newLine();
                    bw.flush();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


//            bw.close();
//            br.close();



        } catch (IOException e) {

            e.printStackTrace();
        }


    }

    public static void write(blog innblog){
        blog oneglg=null;
        String filepath = "D:\\test\\jsonTest\\blog.j";

        List<blog> list = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filepath));// 读取原始json文件
            BufferedWriter bw = null;

            String s = null, ws = null;

            while ((s = br.readLine()) != null) {

                try {

                    JsonObject dataJson = new JsonParser().parse(s).getAsJsonObject();

                    String str = dataJson.get("stars").getAsString();

//                    JsonArray stars = dataJson.getAsJsonObject().getAsJsonArray("stars");
                    JsonArray stars = new JsonParser().parse(str).getAsJsonArray();

                    for (int j=0;j<stars.size();j++){

                        JsonElement el = stars.get(j);
                        blog blog = gson.fromJson(el, blog.class);

                        oneglg=new blog();
                        oneglg.setTitle(blog.getTitle());
                        oneglg.setAuthor(blog.getAuthor());
                        oneglg.setContent(blog.getContent());
                        list.add(oneglg);


                    }

                    list.add(innblog);

                    JsonArray jsonArray = new Gson().toJsonTree(list, new TypeToken<List<blog>>() {}.getType()).getAsJsonArray();

                    JSONObject jb = new JSONObject();

                    jb.put("stars",jsonArray);

                    ws = jb.toString();

                    bw = new BufferedWriter(new FileWriter(filepath,false));// 输出新的json文件
                    bw.write(ws);
                    bw.newLine();
                    bw.flush();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


//            bw.close();
//            br.close();



        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public static List<blog> read(){

        ArrayList<blog> list = new ArrayList<>();

        blog blogs=null;
//        JSONObject info=null;

        try {
            BufferedReader br = new BufferedReader(new FileReader("D:\\test\\jsonTest\\blog.j"));// 读取原始json文件

            String s = null, ws = null;
            while ((s = br.readLine()) != null) {
                try {

                    //                    JSONObject jsonObject = new JSONObject(jsonstr);
                    //先通过字符串的方式得到,转义字符自然会被转化掉
//                    String jsonstrtemp = jsonObject.getString("message");

//                    JSONObject dataJson = new JSONObject(s);// 创建一个包含原始json串的json对象
//                    JSONArray stars = dataJson.getJSONArray("stars");// 找到features json数组

                    JsonObject dataJson = new JsonParser().parse(s).getAsJsonObject();

                    String str = dataJson.get("stars").getAsString();

//                    JsonArray stars = dataJson.get("stars").getAsJsonArray();
//                    JsonArray stars = dataJson.getAsJsonArray("stars");
                    JsonArray stars = new JsonParser().parse(str).getAsJsonArray();


                    //获取第i个数组元素
//                    JsonElement el = stars.get(2);
//                    //映射为类实例
//                    blog data = gson.fromJson(el, blog.class);
//

//                    System.out.println("作者"+data.getAuthor());
//                    System.out.println("标题"+data.getTitle());
//                    System.out.println("内容"+data.getContent());


                    for (int j=0;j<stars.size();j++){
//                        info = stars.getJSONObject(j);// 获取features数组的第i个json对象
//                         info = stars.getAsJsonObject();
                        JsonElement el = stars.get(j);
                        blog blog = gson.fromJson(el, blog.class);

//                        info = (new JsonParser()).parse(stars[j]).getAsJsonObject();
//                        String author = info.getString("author");// 找到properties的json对象
//                        String title = info.getString("title");// 读取properties对象里的name字段值
//                        String content =info.getString("content");
                        blogs=new blog();
                        blogs.setAuthor(blog.getAuthor());
                        blogs.setTitle(blog.getTitle());
                        blogs.setContent(blog.getContent());

                        list.add(blogs);

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            br.close();


        } catch (IOException e) {

            e.printStackTrace();
        }

        return  list;
    }

}
