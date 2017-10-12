# cordova-plugin-navi
ionic2 cordova 导航插件，调用高德和百度APP，支持Android和ios
# 使用

高德地图[uri api](http://lbs.amap.com/api/amap-mobile/gettingstarted)
```javascript
  //android平台
  Navi.amapRoute('amapuri://route/plan/?sourceApplication=APP名称&dlat=39.98848272&dlon=116.47560823&dname=中村关&dev=0&t=0'
    , res => { }
    , err => { }
  );
  
  //ios平台
  Navi.amapRoute('iosamap://path?sourceApplication=APP名称&dlat=39.98848272&dlon=116.47560823&dname=中村关&dev=0&t=0'
      , res => { }
      , err => { }
    );
```
百度地图[uri api](http://lbsyun.baidu.com/index.php?title=uri)
```javascript
    Navi.bdmapRoute('baidumap://map/direction?destination=latlng:39.9761,116.3282|name:中关村&mode=driving'
      , res => { }
      , err => { }
    );
```

# 二次封装service
```javascript
import { Injectable } from '@angular/core';
import { FileServ } from '../../providers/common/FileServ';
declare let Navi;

@Injectable()
export class NaviServ {

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
                Navi.amapRoute('amapuri://route/plan/?sourceApplication=APP名称&dlat=' + dlat +
                    '&dlon=' + dlon + '&dname=' + dname + '&dev=0&t=0',
                    res => {
                        resolve(res);
                    },
                    err => {
                        reject(err);
                    });
            } else {
                Navi.amapRoute('iosamap://path?sourceApplication=APP名称&dlat=' + dlat +
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
                Navi.bdmapRoute('baidumap://map/direction?destination=latlng:' + dlat + ',' + dlon + '|name:' + dname+'&mode=driving',
                    res => {
                        resolve(res);
                    },
                    err => {
                        reject(err);
                    });
            } else {
                Navi.bdmapRoute('baidumap://map/direction?destination=latlng:' + dlat + ',' + dlon + '|name:' + dname +'&mode=driving',
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
