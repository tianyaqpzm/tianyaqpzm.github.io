---
title: n8n
date: 2025-10-19 10:07:58
tags: [AI,工作流]
categories:
  -backend
description: xx
top:true
---
# N8N

[TOC]





## 安装软件

### 端口使用

|                  | 占用端口                                                     |                         |
| ---------------- | ------------------------------------------------------------ | ----------------------- |
| Shadesock-python | 1080 <br />10000<br />10010<br />10010<br />10020<br />40000<br /> | /usr/local/bin/ssserver |
| Shadesock-go     | 30000                                                        |                         |
| Dante            | 15505                                                        |                         |
| Nginx            | 443->5678                                                    |                         |
| N8n              | 5678                                                         |                         |



### 基础软件

```bash
apt-get -y update
apt-get install python3
apt-get -y install gettext build-essential unzip gzip curl openssl libssl-dev autoconf automake libtool gcc make perl cpio libpcre3 libpcre3-dev zlib1g-dev libev-dev libc-ares-dev git qrencode
apt-get install netstat
# nginx
tail -f /var/log/nginx/access.log

# 配置ssl
cat pei.work.gd.cer > /etc/nginx/ssl/pei.work.gd_full.pem
YOUR_DOMAIN="pei.work.gd"
openssl s_client -connect "$YOUR_DOMAIN":443 -servername "$YOUR_DOMAIN" < /dev/null


# 构建 full.pem 三个月有效 需续期
# 1. 将您的域名证书（Leaf Certificate）放在最前面
cat pei.work.gd.cer > /etc/nginx/n8n/pei.work.gd/full.pem
#2. 将中间证书（Intermediate Certificate(s)）追加在其后
cat ca.cer >> /etc/nginx/n8n/pei.work.gd/full.pem

# /Users/pei/blog/blog/source/_posts/schedule/software/n8n.conf
cat /etc/nginx/nginx.conf

sudo systemctl reload nginx
systemctl restart nginx
cat /etc/nginx/conf.d/n8x.conf


# 测试语法是否正确
nginx -t
sudo systemctl start nginx
systemctl restart nginx
sudo systemctl reload nginx
sudo service nginx reload

tail -f /var/log/nginx/access.log

```

n8x.conf

```
/Users/pei/blog/blog/source/_posts/schedule/software/n8n.conf
/etc/nginx/conf.d/n8x.conf
# ----------------------------------------------------
# 2. 监听 443 端口 (HTTPS) 并反向代理到 n8n (5678)
# ----------------------------------------------------
server {
    # 监听 443 端口并启用 SSL
    listen 443 ssl;
    http2 on;
    server_name pei.work.gd;

    # =======================================================
    # SSL 证书配置 - 请根据您的证书路径进行修改
    # (如果是用 Certbot 或 Nginx Proxy Manager，路径可能不同)
    # =======================================================
    ssl_certificate /etc/nginx/n8n/pei.work.gd/full.pem;
    ssl_certificate_key /etc/nginx/n8n/pei.work.gd/pei.work.gd.key;

    # 基础 SSL 设置（推荐）
    ssl_session_cache shared:SSL:10m;
    ssl_protocols TLSv1.2 TLSv1.3;
    ssl_ciphers 'ECDHE-ECDSA-AES128-GCM-SHA256:ECDHE-RSA-AES128-GCM-SHA256:ECDHE-ECDSA-AES256-GCM-SHA384:ECDHE-RSA-AES256-GCM-SHA384:ECDHE-ECDSA-CHACHA20-POLY1305:ECDHE-RSA-CHACHA20-POLY1305:DHE-RSA-AES128-GCM-SHA256:DHE-RSA-AES256-GCM-SHA384';
    ssl_prefer_server_ciphers on;

    # 允许上传文件大小限制（根据需要调整）
    client_max_body_size 50M;

    # =======================================================
    # 反向代理配置
    # =======================================================
    location / {
        # 将请求转发到 n8n 的内部 HTTP 端口
        proxy_pass http://127.0.0.1:5678/;

        # 告诉 n8n 原始的域名
        proxy_set_header Host $host;

        # 告诉 n8n 原始的客户端 IP
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;

        # **关键**：告诉 n8n 外部使用的是 HTTPS 协议，以便其生成正确的 Webhook URL
        proxy_set_header X-Forwarded-Proto $scheme;

        # WebSocket 支持（n8n 需要）
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection "upgrade";
    }
}

```



##### SSL三个月需证书续期：

   ssl_certificate /etc/nginx/n8n/pei.work.gd/full.pem;
    ssl_certificate_key /etc/nginx/n8n/pei.work.gd/pei.work.gd.key;

###### 1、在网站上生成 证书文件, 下载ssl文件

https://freedomain.one/Direct.sv?cmd=userSSLMgm&domain=pei.work.gd

![image-20251019170536747](n8n/image-20251019170536747.png)

![image-20251019170429118](n8n/image-20251019170429118.png)

2、拼接生成full.cer 将其放到指定位置

```
cat pei.work.gd.cer > /etc/nginx/n8n/pei.work.gd/full.pem
cat ca.cer >> /etc/nginx/n8n/pei.work.gd/full.pem

```



#### forever

```
命令	描述
forever start [script]	以后台守护进程模式启动脚本。
forever list	列出所有由 forever 管理的正在运行的进程。
forever stop [uid/pid/index]	停止指定的进程（可以使用 ID、PID 或列表中的索引）。
forever stopall	停止所有由 forever 管理的进程。
forever restart [uid/pid/index]	重启指定的进程。
forever restartall	重启所有由 forever 管理的进程。
forever logs [uid/pid/index]	查看指定进程的日志。
```



### shadowsocks

root        8992           1  0 Jan11 ?        00:19:55 /usr/bin/shadowsocks-server -u -c /etc/shadowsocks-go/config.json

root     4114708       1  0 Jun21 ?     00:24:48 /bin/python3 /usr/local/bin/ssserver -c /etc/shadowsocks-python/config.json -d start

```bash
# 安装脚本
/Users/pei/blog/blog/source/_posts/schedule/software/shadowsocks-all.sh
/usr/local/
/usr/local/bin/ssserver
# 配置文件

# 测试验证
ss -tuln | grep 10000
netstat -anp|grep 10000
ps -ef|grep ss

"12340":"Wwzz@1102"

chacha20-ietf-poly1305
wget --no-check-certificate -O shadowsocks-all.sh https://raw.githubusercontent.com/teddysun/shadowsocks_install/master/shadowsocks-all.sh

chmod +x shadowsocks-all.sh ./shadowsocks-all.sh 2>&1 | tee shadowsocks-all.log 原文链接：https://idealclover.top/archives/543/

{
    "server":"0.0.0.0",
    "local_port":1099,
    "port_password":{
    "30000":"@Qq19062525",
    "12345":"Wwzz@1102"
    },
    "method":"chacha20-ietf-poly1305",
    "timeout":300
}


root@ubuntu-pei:~# cat /etc/shadowsocks-go/config.json
{
    "server":"0.0.0.0",
    "server_port":30000,
    "local_port":1099,
    "password":"****",
    "method":"chacha20",
    "timeout":300
}

root@ubuntu-pei:~# cat /etc/shadowsocks-python/config.json
{
    "server":"0.0.0.0",
    "server_port":10000,
    "local_address":"127.0.0.1",
    "local_port":1080,
    "port_password":{
	"10000":"****",
	"40000":"****",
        "10010":"****",
        "10020":"****"
    },
    "password":"****",
    "timeout":300,
    "method":"chacha20",
    "fast_open":false
}

```





### dante

lgp_ss5  2536459       1  0 Sep10 ?        00:00:17 /usr/sbin/danted

```bash
# 安装
apt-get install dante-server
# 创建用户 密码为使用的连接密码
sudo useradd -r -s /bin/false lgp_ss5
# 待确认
sudo passwd lgp_ss5  

# 配置文件
vim /etc/danted.conf

#启动
sudo systemctl start danted.service
#验证
curl -v -x socks5://lgp_ss5:*******@134.199.140.250:15505 http://www.google.com/
# 查看日志
journalctl -u danted -f
```



配置文件（/etc/danted.conf）

```ini
# 基础配置
logoutput: syslog
user.privileged: root
user.unprivileged: lgp_ss5

# 网络设置
internal: 0.0.0.0 port = 15505
# 使用`ip addr`确认实际网卡名
external: eth0

# 认证方式（二选一）
socksmethod: username # 用户名认证
# 访问控制规则
client pass {
    from: 0.0.0.0/0 to: 0.0.0.0/0
    log: connect disconnect error
}
socks pass {
    from: 0.0.0.0/0 to: 0.0.0.0/0
    log: connect disconnect error
}
```

### frp

nobody    651475       1  0 Jul20 ?        00:03:32 /usr/bin/frps -c /etc/frp/frps.ini

```bash
# 安装路径
/Users/pei/blog/blog/source/_posts/schedule/software/frp/

/usr/bin/frps
/root/frp_0.61.2_linux_amd64/frps

root@ubuntu-pei:~/frp_0.61.2_linux_amd64# cat /etc/frp/frps.ini
[common]
bind_port = 7000
token = raqhec-4mymku-Xaqduw
dashboard_port = 7500
dashboard_user = admin
dashboard_pwd = *****
#vhost_http_port = 80
#vhost_https_port = 443

```



客户端frpc.ini

```
# 与服务端建立连接，跟上面的配置要对应
serverAddr = "pei.work.gd"
serverPort = 7000

# 配置Token鉴权，要与服务端一致
auth.method = "token"
auth.token = "*****"

# 配置日志信息
log.level = "info"
log.to = "frpc.log"

# 该内网穿透起名为SSH，annotations中随便写了一些备注，基于TCP协议将本机的22端口映射到公网的8001
[[proxies]]
name = "Mongodb"
annotations = {title = "Mongodb远程连接", fuck = "test", desc = "annotations是该连接的备注信息，里面的key和val是随便写的，在服务端控制面板可以看到"}
type = "tcp"
localIP = "127.0.0.1"
localPort = 36213
remotePort = 50001
```





###  n8n

root     3764231 3764082  0 Oct18 ?  00:00:37 /usr/bin/node /mnt/volume_add_01/n8x/node_modules/.bin/n8n

https://pei.work.gd/

```bash
# 安装前依赖：
# nodejs  sqlite3  nginx
curl -fsSL https://deb.nodesource.com/setup_22.x | sudo bash -
sudo apt-get install -y nodejs
npm install sqlite3 --save

npm uninstall forever -g
npm install forever -g

# 安装
cd /mnt/volume_add_01/n8x
npm install n8n
NODE_OPTIONS="--max-old-space-size=2048" npm install n8n --force --maxsockets=1

# 执行
/root/n8x/node_modules/.bin/n8n
# 在/root/n8x 位置执行下面命令
forever start n8n-config.json

# docker版本
docker run -it --rm --name n8n -p 5678:8080 -v n8n_data:/home/node/.n8n n8nio/n8n

# 设定后台执行
forever  -e app-err.log start /mnt/volume_add_01/n8x/node_modules/.bin/n8n
forever start n8n-config.json

# 其他操作
# 重置用户 谨慎操作
./node_modules/.bin/n8n user-management:reset

```



#### 对接telegram的机器人

1、在telegram新建机器人，获取token

https://t.me/TimnyBot

2、将 <YOUR_HTTPS_URL> 替换为您实际的 Webhook 地址，该地址必须是 HTTPS，前面是n8n工作流的webhook地址

curl -F "url=https://pei.work.gd/webhook/55acc711-c248-4ac9-b6cd-e295c2d33f4b/webhook" \
"https://api.telegram.org/bot<YOUR_HTTPS_URL>/setWebhook"



#### n8n工作流：

[劝退：n8n等 AI工作流不要学](https://www.woshipm.com/ai/6280845.html)

b站视频----

【我用n8n把YouTube热点变成“自动情报“，全自动 AI 热搜报告，日更、群发、上表！-哔哩哔哩】 https://b23.tv/GgfvtD9



#### 对接Google Auth

地址： https://console.cloud.google.com/auth/overview?hl=zh-cn

 https://aistudio.google.com/api-keys

[来自B站视频连接，不可用，参考下面步骤](https://acngml2kgfja.feishu.cn/docx/BLb9dPcYdo2jtax4oZWckv0knyf)

![af0b40c74c9753d603ff8a766393f6c9](n8n/af0b40c74c9753d603ff8a766393f6c9.jpg)





#### 对接飞书

注意点： 对接飞书多维表格，需要在右上角更多-》添加文档应用   否则不报错且n8n更新不成功

![image-20251020225818993](n8n/image-20251020225818993.png)

接受者id:  

YouTube视频分析：

oc_86f06915167927194714cb427b88ed4a





新闻记录：

https://acnpim97lb45.feishu.cn/wiki/PPn1wnBk4iF9zykJBMMceJfcn6c?table=tblKdHI9JjL6GTMU&view=vewBWxJ7hc

?table=tblKdHI9JjL6GTMU&view=vewBWxJ7hc

PPn1wnBk4iF9zykJBMMceJfcn6c

https://acnpim97lb45.feishu.cn/base/Wx9jbWEnEaVwmis3t0PcUPmdnHc?table=tblKdHI9JjL6GTMU&view=vewBWxJ7hc





#### Forever启动的配置文件

```json
cat /mnt/volume_add_01/n8x/n8n-config.json  --- 废弃，内存不足时 临时增加的存储卷
cat /root/n8n/n8n-config.json
[
  {
    "uid": "n8n",
    "script": "./node_modules/.bin/n8n",
    "options": ["start"],
    "append": true,
    "logFile": "/var/log/n8n/n8n.log",
    "outFile": "/var/log/n8n/n8n-out.log",
    "errFile": "/var/log/n8n/n8n-err.log",
    "env": {
      "WEBHOOK_URL": "https://pei.work.gd/",
      "N8N_PROTOCOL": "https",
      "N8N_EDITOR_BASE_URL": "https://pei.work.gd/",
      "N8N_PROXY_HOPS":1,
      "NODE_OPTIONS":"--max-old-space-size=2048",
      "NODE_ENV": "production"
    }
  }
]
```

环境变量

```
#export N8N_SECURE_COOKIE=false
export WEBHOOK_URL=https://pei.work.gd/
export N8N_HOST=pei.work.gd
export N8N_PROTOCOL=https
export N8N_PROXY_HOPS=1
```



### 2. 虚拟内存--交换内存

##### 创建

```bash
sudo fallocate -l 2G /swapfile

创建一个 4GB 的文件（根据需要调整大小）

sudo fallocate -l 4G /swapfile
sudo fallocate -l 768MB /swapfile

sudo chmod 600 /swapfile
sudo mkswap /swapfile
sudo swapon /swapfile
```

##### 开机自启

```bash
# 将其添加到 /etc/fstab 以便在重启后保留， tee命令从标准输入读取数据
echo '/swapfile none swap sw 0 0' | sudo tee -a /etc/fstab

```



##### 删除

```bash
# 删除 /swapfile
sudo swapoff /swapfile
sudo rm /swapfile
sudo nano /etc/fstab
# 注释掉开机启动
vi /etc/fstab
```







### 问题：

![image-20251019104937509](n8n/image-20251019104937509.png)





#### 切换挂载盘（当前盘已删除）

##### 快照新建容器：

 512 MB  Memory  / 10 GB  Disk  + 5 GB / SFO3  - Ubuntu 24.10 x64

San Francisco Datacenter3 SFO3

```shell
ssh root@209.38.78.253
ssh root@143.198.140.5
# Create a mount point for your volume:
$ mkdir -p /mnt/volume_add_01

# Mount your volume at the newly-created mount point:
$ mount -o discard,defaults,noatime /dev/disk/by-id/scsi-0DO_Volume_volume-add-01 /mnt/volume_add_01

# Change fstab so the volume will be mounted after a reboot
$ echo '/dev/disk/by-id/scsi-0DO_Volume_volume-add-01 /mnt/volume_add_01 ext4 defaults,nofail,discard 0 0' | sudo tee -a /etc/fstab
```



![image-20251019152151961](n8n/image-20251019152151961.png)



##### Pei Mac

smb://[2409:8a20:c92:35c0:1464:d16e:bd33:e861]

![image-20251019231645755](n8n/image-20251019231645755.png)



##### nas:

smb://[2409:8a20:c50:8511::6c0]





![image-20251020001121187](n8n/image-20251020001121187.png)
