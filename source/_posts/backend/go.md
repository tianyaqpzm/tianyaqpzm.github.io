
## GO
[TOC] 
#log
* log.Fatal 接口，会先将日志内容打印到标准输出，接着调用系统的 os.exit(1) 接口，退出程序并返回状态 1 。但是有一点需要注意，由于是直接调用系统接口退出，defer函数不会被调用，下面是一个Fatal的示例：
* 对于log.Panic接口，该函数把日志内容刷到标准错误后调用 panic 函数，在Panic之后声明的defer是不会执行的。

## itoa
iota在const关键字每次出现时将被重置为0

自定义类型  

```
type Stereotype int 
// 位掩码表达式
IgEggs Stereotype = 1 << iota
//或者
IgEggs Stereotype = 1
// 定义数量级
const (
    _           = iota  // ignore first value by assigning to blank identifier
    KB ByteSize = 1 << (10 * iota)  // 1 << (10*1)
    MB          // 1 << (10*2)
）
// 中间插队
const ( 
    i = iota   // 0
    j = 3.14 
    k = iota    // 2
    l    // 3
)
```
iota 在下一行增长，而不是立即取得它的引用。

## slice

```
// 定义切片 1.
 var identifier []type
 // 2. 
 var slice1 []type = make([]type, len)
//也可以简写为
slice1 := make([]type, len)
// 3. 
make([]T, length, capacity) //  len 是数组的长度并且也是切片的初始长度。

//初始化切片
s :=[] int {1,2,3 } 
s := arr[:] 
s1 := s[startIndex:endIndex]   // s为切片
s :=make([]int,len,cap)  // 通过内置函数
```


