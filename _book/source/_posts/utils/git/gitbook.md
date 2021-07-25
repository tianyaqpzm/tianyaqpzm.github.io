

`Gitbook` 是什么？其实用一句话就可以概括，它是一个`能将使用 Markown 语法的 md 格式文档，快速制作成各种格式电子书的工具。`。



```undefined
npm install -g gitbook-cli

# 创建笔记文件夹
gitbook init
```





**使用示例：**

```bash
# 在指定的/home/gitbook/目录中初始化一个书籍项目
$ gitbook init --book=/home/gitbook/
# 对应的简写格式
$ gitbook init /home/gitbook/

# 指定书籍项目目录在当前目录，并将编译构建后文件放到指定的当前目录下的mybook目录中
$ gitbook build --book=./  --output=./mybook
# 对应的简写格式
$ gitbook build ./ ./mybook

# 指定书籍项目目录在当前目录，导出 PDF 格式的电子书到指定的/home/pdf/目录中
$ gitbook pdf --book=./  --output=/home/pdf/
# 对应的简写格式
$ gitbook pdf ./ /home/pdf/
```





使用 `git build` 命令来生成静态网页格式的电子书。执行后会生成HTML静态资源输出到当前项目的目录下`_book` 目录中。

```

```





使用 `gitboo serve` 命令开启进行本地网页预览服务，执行后会默认执行 `gitbook build` 命令，然后本地开启一个端口 `4000` 的预览网页服务，此时可以通过浏览器访问本地机器 4000 端口进行电子书的浏览。





使用插件

https://www.cnblogs.com/jiangming-blogs/p/14643136.html

