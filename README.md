# cordova-plugin-navimap
ionic cordova 导航插件，URI方式调用高德和百度APP，支持Android和ios

# 安装

npm
```bash
cordova plugin add cordova-plugin-navimap
```
github
```bash
cordova plugin add https://github.com/mylhyl/cordova-plugin-navimap.git
```

# cordova使用

高德地图[uri api](http://lbs.amap.com/api/amap-mobile/gettingstarted)
```javascript
    //引用导航插件
    declare let cordovaNaviMap;
    //android平台
    cordovaNaviMap.amapRoute('amapuri://route/plan/?sourceApplication=APP名称'+
    '&dlat=39.98848272&dlon=116.47560823&dname=中村关&dev=0&t=0'
        , res => {
            //成功
        }
        , err => {
            //失败
        }
    );

    //ios平台
    cordovaNaviMap.amapRoute('iosamap://path?sourceApplication=APP名称'+
    '&dlat=39.98848272&dlon=116.47560823&dname=中村关&dev=0&t=0'
        , res => {
            //成功
         }
        , err => {
            //失败
        }
    );
```
百度地图[uri api](http://lbsyun.baidu.com/index.php?title=uri)
```javascript
    //
    cordovaNaviMap.bdmapRoute('baidumap://map/direction?'+
    'destination=latlng:39.9761,116.3282|name:中关村&mode=driving'
      , res => {
          //成功
       }
      , err => {
          //失败
      }
    );
```

# 二次封装service
```javascript
import { Injectable } from '@angular/core';
import { FileServ } from '../../providers/common/FileServ';
declare let cordovaNaviMap;

@Injectable()
export class NaviMapServ {

    constructor(private fileServ: FileServ) {

    }

    /**
     * 高德地图
     * @param dlat 终点纬度
     * @param dlon 终点经度
     * @param dname 终点名称
     */
    public amapRoute(dlat: string, dlon: string, dname: string): Promise<string> {
        return new Promise<string>((resolve, reject) => {

            if (this.fileServ.isAndroid()) {
                cordovaNaviMap.amapRoute('amapuri://route/plan/?sourceApplication=APP名称&dlat=' + dlat + 
                '&dlon=' + dlon + '&dname=' + dname + '&dev=0&t=0',
                    res => {
                        resolve(res);
                    },
                    err => {
                        reject(err);
                    });
            } else {
                cordova.naviMap.amapRoute('iosamap://path?sourceApplication=APP名称&dlat=' + dlat +
                    '&dlon=' + dlon + '&dname=' + dname + '&dev=0&t=0',
                    res => {
                        resolve(res);
                    }, err => {
                        reject(err);
                    });
            }
        });
    }

    /**
     * 百度地图
     * @param dlat 终点纬度
     * @param dlon 终点经度
     * @param dname 终点名称
     */
    public bdmapRoute(dlat: string, dlon: string, dname: string): Promise<string> {
        return new Promise<string>((resolve, reject) => {

            if (this.fileServ.isAndroid()) {
                cordovaNaviMap.bdmapRoute('baidumap://map/direction?destination=latlng:' + dlat + 
                ',' + dlon + '|name:' + dname+'&mode=driving',
                    res => {
                        resolve(res);
                    },
                    err => {
                        reject(err);
                    });
            } else {
                cordovaNaviMap.bdmapRoute('baidumap://map/direction?destination=latlng:' + dlat + 
                ',' + dlon + '|name:' + dname +'&mode=driving',
                    res => {
                        resolve(res);
                    }, err => {
                        reject(err);
                    });
            }
        });
    }
}
```
