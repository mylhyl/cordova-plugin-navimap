#import <Cordova/CDVPlugin.h>

@interface Navi : CDVPlugin

- (void)amapRoute:(CDVInvokedUrlCommand*)command;
- (void)bdmapRoute:(CDVInvokedUrlCommand*)command;

@end
