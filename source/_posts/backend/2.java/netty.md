
# Netty

```
入口
ChannelFuture future = serverBootstrap.bind(8080).sync();
bind{
validate():校验：group，channelFactory是否设置
doBind{
    initAndRegister(){
        init(channel){
            设定options attrs
              p.addLast(new ChannelInitializer<Channel>() {
        }
    }
 }
}

00000010-- 1111 1101
           1111 1110
11111110
```
00000011
11111101
=0000 0001
 0000 0001
 1111 1111
 00000001