

#Git 常见操作
[TOC]

## $ 常见命令

### git push 
在使用git commit命令将修改从暂存区提交到本地版本库后，只剩下最后一步将本地版本库的分支推送到远程服务器上对应的分支了，如果不清楚版本库的构成，可以查看我的另一篇，git 仓库的基本结构。
    git push的一般形式为 **git push <远程主机名> <本地分支名>  <远程分支名>**，例如 git push origin master：refs/for/master ，即是将本地的master分支推送到远程主机origin上的对应master分支， origin 是远程主机名，
    第一个master是本地分支名，第二个master是远程分支名。
    1.1 git push origin master
        如果远程分支被省略，如上则表示将本地分支推送到与之存在追踪关系的远程分支（通常两者同名），如果该远程分支不存在，则会被新建

     1.2 git push origin ：refs/for/master 

　　如果省略本地分支名，则表示删除指定的远程分支，因为这等同于推送一个空的本地分支到远程分支，等同于 git push origin --delete master

    1.3 git push origin

　　 如果当前分支与远程分支存在追踪关系，则本地分支和远程分支都可以省略，将当前分支推送到origin主机的对应分支 

	　1.4 git push

　　如果当前分支只有一个远程分支，那么主机名都可以省略，形如 git push，可以使用git branch -r ，查看远程的分支名

	　1.5 git push 的其他命令

　　这几个常见的用法已足以满足我们日常开发的使用了，还有几个扩展的用法，如下：

　　　　（1） git push -u origin master 如果当前分支与多个主机存在追踪关系，则可以使用 -u 参数指定一个默认主机，这样后面就可以不加任何参数使用git push，

　　　　　　不带任何参数的git push，默认只推送当前分支，这叫做simple方式，还有一种matching方式，会推送所有有对应的远程分支的本地分支， Git 2.0之前默认使用matching，现在改为simple方式

　　　　　　如果想更改设置，可以使用git config命令。git config --global push.default matching OR git config --global push.default simple；可以使用git config -l 查看配置

　　　　（2） git push --all origin 当遇到这种情况就是不管是否存在对应的远程分支，将本地的所有分支都推送到远程主机，这时需要 -all 选项

　　　　（3） git push --force origin git push的时候需要本地先git pull更新到跟服务器版本一致，如果本地版本库比远程服务器上的低，那么一般会提示你git pull更新，如果一定要提交，那么可以使用这个命令。 -f

　　　　（4） git push origin --tags //git push 的时候不会推送分支，如果一定要推送标签的话那么可以使用这个命令

	　1.6 关于 refs/for

　　// refs/for 的意义在于我们提交代码到服务器之后是需要经过code review 之后才能进行merge的，而refs/heads 不需要

###.gitignore
\# 此为注释 – 将被 Git 忽略
*.a       # 忽略所有 .a 结尾的文件
!lib.a    # 但 lib.a 除外
/TODO     # 仅仅忽略项目根目录下的 TODO 文件，不包括 subdir/TODO
build/    # 忽略 build/ 目录下的所有文件
doc/*.txt # 会忽略 doc/notes.txt 但不包括 doc/server/arch.txt

在填写忽略文件的过程中，我发现在Android Studio里面，.gitignore中已经标明忽略的文件目录下的文件，当我想git push的时候还会出现在push的目录中，原因是因为在Studio的git忽略目录中，新建的文件在git中会有缓存，如果某些文件已经被纳入了版本管理中，就算是在.gitignore中已经声明了忽略路径也是不起作用的，这时候我们就应该先把本地缓存删除，然后再进行git的push，这样就不会出现忽略的文件了。git清除本地缓存命令如下：
```
git rm -r --cached .
git add .
git commit -m 'update .gitignore'
```



### git checkout 
1. git checkout -- filename的作用是把filename文件在工作区的修改撤销到最近一次git add 或 git commit时的内容

git config --global core.editor vim 

### git diff
git diff：是查看 workspace 与 index 的差别的。 
git diff --cached：是查看 index 与 local repositorty 的差别的。 
git diff HEAD：是查看 workspace 和 local repository 的差别的。（HEAD 指向的是 local repository 中最新提交的版本）
### git log 

```git log --graph --pretty=oneline --abbrev-commit```

### git stash

[Git 工具 - 储藏](https://git-scm.com/book/zh/v1/Git-%E5%B7%A5%E5%85%B7-%E5%82%A8%E8%97%8F%EF%BC%88Stashing%EF%BC%89)


* Q1: I recently did a git stash, then did some work on the branch and committed it, and got these errors when trying to do a git stash apply:

```
CONFLICT (delete/modify): app/controllers/orders_controller.rb deleted in Updated upstream and modified in Stashed changes. Version Stashed changes of app/controllers/orders_controller.rb left in tree.
CONFLICT (content): Merge conflict in app/models/product.rb
```
You have two options:

pop that stash onto the latest commit and resolve the conflicts
pop the stash onto the prior commit.

https://stackoverflow.com/questions/10056165/how-to-resolve-delete-modify-conflict-caused-by-git-stash-apply





## $ 场景

### 1、Git撤销&回滚操作

[三种场景](https://blog.csdn.net/ligang2585116/article/details/71094887)



