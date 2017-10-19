#import "NaviMap.h"
#import <Cordova/CDVPluginResult.h>

@implementation NaviMap

- (void)amapRoute:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
	NSString *url = [[command.arguments objectAtIndex:0] stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];

    if (url == nil || [url isEqualToString:@""] || [url length] == 0) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"参数不能为空"];
    } else {
        NSURL *urlScheme = [NSURL URLWithString:url];
        NSURL *scheme = [NSURL URLWithString:@"iosamap://"];
        BOOL canOpen = [[UIApplication sharedApplication] canOpenURL:scheme];
        if(canOpen){
            if ([[UIDevice currentDevice].systemVersion integerValue] >= 10) {
                //iOS10以后,使用新API
                [[UIApplication sharedApplication] openURL:urlScheme options:@{} completionHandler:^(BOOL success) {
                    NSLog(@"scheme调用结束");
                }];
            } else { 
                //iOS10以前,使用旧API
                [[UIApplication sharedApplication] openURL:urlScheme];
            }
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"启动成功"];
        }else{
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"未检测到高德地图"];
        }
    }

    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)bdmapRoute:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
	NSString *url = [[command.arguments objectAtIndex:0] stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];

    if (url == nil || [url isEqualToString:@""] || [url length] == 0) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"参数不能为空"];
    } else {
        NSURL *urlScheme = [NSURL URLWithString:url];
        NSURL *scheme = [NSURL URLWithString:@"baidumap://"];
        BOOL canOpen = [[UIApplication sharedApplication] canOpenURL:scheme];
        if(canOpen){
            if ([[UIDevice currentDevice].systemVersion integerValue] >= 10) {
                //iOS10以后,使用新API
                [[UIApplication sharedApplication] openURL:urlScheme options:@{} completionHandler:^(BOOL success) {
                    NSLog(@"scheme调用结束");
                }];
            } else { 
                //iOS10以前,使用旧API
                [[UIApplication sharedApplication] openURL:urlScheme];
            }
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"启动成功"];
        }else{
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"未检测到百度地图"];
        }
    }

    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end