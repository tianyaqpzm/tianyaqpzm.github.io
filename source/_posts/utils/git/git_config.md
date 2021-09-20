Invalid username or password

![image-20191202001350552](git_config/image-20191202001350552.png)

git config --global user.name "tianyaqpzm"

git config --global user.email "tianyaqpzm@163.com"

![image-20191202001949386](git_config/image-20191202001949386.png)

### 设置 Git 提交注释模版

git config --global commit.template ~/project/utils/commit.template

【问题单号 Defect】
【修改说明 Modification】
【自测结果】自测通过
【二进制 or 第三方来源】不涉及

避免每次输入密码：

.gitconfig 配置文件。用文档编辑工具打开该文件

添加：

```
[user]
	name = wb-ly409739  //你的用户名
	email = wb-ly409739@alibaba-inc.com  //你的git邮箱账号

[credential]
    helper = store
```

git config credential.helper store

git config --global credential.helper store

如果还没有解决：

并没有解决我的问题。后来几经搜索各大网友的答案以后，才发现是自己的密码设置错误。

执行命令后，git 会弹出一个 GitHub 登陆的小界面，你登录成功后要求你输入用户名和密码。这里的密码并不是你的 GitHub 的密码或者本地 git 的密码。

**而是 GitHub 的 Personal access tokens**

![img](git_config/1084615-20180104155748534-1934463441.png)

密码重新填写后。完美解决问题
