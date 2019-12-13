package com.atguigu.gmall0715.mock;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by DaDi on 2019/12/13
 */
public class JsonMocker {

    int startupNum=100000;
    int eventNum=200000 ;

    com.atguigu.gmall.mock.util.RandomDate logDateUtil= null;


    com.atguigu.gmall.mock.util.RanOpt[] osOpts= {new com.atguigu.gmall.mock.util.RanOpt("ios",3),new com.atguigu.gmall.mock.util.RanOpt("andriod",7) };
    com.atguigu.gmall.mock.util.RandomOptionGroup<String> osOptionGroup= new com.atguigu.gmall.mock.util.RandomOptionGroup(osOpts);
    Date startTime= null;
    Date endTime= null;

    com.atguigu.gmall.mock.util.RanOpt[] areaOpts= {new com.atguigu.gmall.mock.util.RanOpt("beijing",10),
            new com.atguigu.gmall.mock.util.RanOpt("shanghai",10),new com.atguigu.gmall.mock.util.RanOpt("guangdong",20),new com.atguigu.gmall.mock.util.RanOpt("hebei",5),
            new com.atguigu.gmall.mock.util.RanOpt("heilongjiang",5),new com.atguigu.gmall.mock.util.RanOpt("shandong",5),new com.atguigu.gmall.mock.util.RanOpt("tianjin",5),
            new com.atguigu.gmall.mock.util.RanOpt("shan3xi",5),new com.atguigu.gmall.mock.util.RanOpt("shan1xi",5),new com.atguigu.gmall.mock.util.RanOpt("sichuan",5)
    };
    com.atguigu.gmall.mock.util.RandomOptionGroup<String> areaOptionGroup= new com.atguigu.gmall.mock.util.RandomOptionGroup(areaOpts);

    String appId="gmall2019";

    com.atguigu.gmall.mock.util.RanOpt[] vsOpts= {new com.atguigu.gmall.mock.util.RanOpt("1.2.0",50),new com.atguigu.gmall.mock.util.RanOpt("1.1.2",15),
            new com.atguigu.gmall.mock.util.RanOpt("1.1.3",30),
            new com.atguigu.gmall.mock.util.RanOpt("1.1.1",5)
    };

    com.atguigu.gmall.mock.util.RandomOptionGroup<String> vsOptionGroup= new com.atguigu.gmall.mock.util.RandomOptionGroup(vsOpts);

    com.atguigu.gmall.mock.util.RanOpt[] eventOpts= {new com.atguigu.gmall.mock.util.RanOpt("addFavor",10),new com.atguigu.gmall.mock.util.RanOpt("addComment",30),
            new com.atguigu.gmall.mock.util.RanOpt("addCart",20), new com.atguigu.gmall.mock.util.RanOpt("clickItem",40)
    };

    com.atguigu.gmall.mock.util.RandomOptionGroup<String> eventOptionGroup= new com.atguigu.gmall.mock.util.RandomOptionGroup(eventOpts);

    com.atguigu.gmall.mock.util.RanOpt[] channelOpts= {new com.atguigu.gmall.mock.util.RanOpt("xiaomi",10),new com.atguigu.gmall.mock.util.RanOpt("huawei",20),
            new com.atguigu.gmall.mock.util.RanOpt("wandoujia",30), new com.atguigu.gmall.mock.util.RanOpt("360",20), new com.atguigu.gmall.mock.util.RanOpt("tencent",20)
            , new com.atguigu.gmall.mock.util.RanOpt("baidu",10), new com.atguigu.gmall.mock.util.RanOpt("website",10)
    };

    com.atguigu.gmall.mock.util.RandomOptionGroup<String> channelOptionGroup= new com.atguigu.gmall.mock.util.RandomOptionGroup(channelOpts);

    com.atguigu.gmall.mock.util.RanOpt[] quitOpts= {   new com.atguigu.gmall.mock.util.RanOpt(true,20),new com.atguigu.gmall.mock.util.RanOpt(false,80)};

    com.atguigu.gmall.mock.util.RandomOptionGroup<Boolean> isQuitGroup= new com.atguigu.gmall.mock.util.RandomOptionGroup(quitOpts);

    public JsonMocker( )  {

    }

    public JsonMocker(String startTimeString ,String endTimeString,int startupNum,int eventNum) {
        try {
            startTime= new SimpleDateFormat("yyyy-MM-dd").parse(startTimeString);
            endTime= new SimpleDateFormat("yyyy-MM-dd").parse(endTimeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        logDateUtil= new com.atguigu.gmall.mock.util.RandomDate(startTime,endTime,startupNum+eventNum);
    }

    String initEventLog(String startLogJson){
            /*`type` string   COMMENT '日志类型',
             `mid` string COMMENT '设备唯一 表示',
            `uid` string COMMENT '用户标识',
            `os` string COMMENT '操作系统',
            `appid` string COMMENT '应用id',
            `area` string COMMENT '地区' ,
            `evid` string COMMENT '事件id',
            `pgid` string COMMENT '当前页',
            `npgid` string COMMENT '跳转页',
            `itemid` string COMMENT '商品编号',
            `ts` bigint COMMENT '时间',*/

        JSONObject startLog = JSON.parseObject(startLogJson);
        String mid= startLog.getString("mid");
        String uid=  startLog.getString("uid");
        String os= startLog.getString("os");
        String appid=this.appId;
        String area=startLog.getString("area");
        String evid = eventOptionGroup.getRandomOpt().getValue();
        int pgid = new Random().nextInt(50)+1;
        int npgid = new Random().nextInt(50)+1;
        int itemid = new Random().nextInt(50);
        //  long ts= logDateUtil.getRandomDate().getTime();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type","event");
        jsonObject.put("mid",mid);
        jsonObject.put("uid",uid);
        jsonObject.put("os",os);
        jsonObject.put("appid",appid);
        jsonObject.put("area",area);
        jsonObject.put("evid",evid);
        jsonObject.put("pgid",pgid);
        jsonObject.put("npgid",npgid);
        jsonObject.put("itemid",itemid);
        return  jsonObject.toJSONString();
    }


    String initStartupLog( ){
            /*`type` string   COMMENT '日志类型',
             `mid` string COMMENT '设备唯一标识',
      `uid` string COMMENT '用户标识',
      `os` string COMMENT '操作系统', ,
      `appId` string COMMENT '应用id', ,
     `vs` string COMMENT '版本号',
     `ts` bigint COMMENT '启动时间', ,
     `area` string COMMENT '城市' */


        String mid= "mid_"+ com.atguigu.gmall.mock.util.RandomNum.getRandInt(1,500);
        String uid=""+ com.atguigu.gmall.mock.util.RandomNum.getRandInt(1,500);
        String os=osOptionGroup.getRandomOpt().getValue();
        String appid=this.appId;
        String area=areaOptionGroup.getRandomOpt().getValue();
        String vs = vsOptionGroup.getRandomOpt().getValue();
        //long ts= logDateUtil.getRandomDate().getTime();
        String ch=os.equals("ios")?"appstore": channelOptionGroup.getRandomOpt().getValue();



        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type","startup");
        jsonObject.put("mid",mid);
        jsonObject.put("uid",uid);
        jsonObject.put("os",os);
        jsonObject.put("appid",appid);
        jsonObject.put("area",area);
        jsonObject.put("ch",ch);
        jsonObject.put("vs",vs);
        return  jsonObject.toJSONString();
    }

    public static void genLog() {
        JsonMocker jsonMocker = new JsonMocker();
        jsonMocker.startupNum = 1000000;
        for (int i = 0; i < jsonMocker.startupNum; i++) {
            String startupLog = jsonMocker.initStartupLog();
            jsonMocker.sendLog(startupLog);
            while (!jsonMocker.isQuitGroup.getRandomOpt().getValue()) {
                String eventLog = jsonMocker.initEventLog(startupLog);
                jsonMocker.sendLog(eventLog);
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public void sendLog(String log)   {
        com.atguigu.gmall.mock.util.LogUploader.sendLogStream(log);
    }

    public static void main(String[] args)  {
        genLog();
    }

}
