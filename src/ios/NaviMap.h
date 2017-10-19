#import <Cordova/CDVPlugin.h>

@interface NaviMap : CDVPlugin

- (void)amapRoute:(CDVInvokedUrlCommand*)command;
- (void)bdmapRoute:(CDVInvokedUrlCommand*)command;

@end
