# 反转链表测试用例

这个目录包含了使用 Jest 测试框架为 LeetCode 206 题"反转链表"编写的完整测试用例。

## 文件结构

- `206.反转链表.ts` - 原始的实现代码
- `206.反转链表.test.ts` - Jest 测试用例
- `package.json` - 项目依赖配置
- `jest.config.js` - Jest 配置文件
- `tsconfig.json` - TypeScript 配置文件

## 安装依赖

```bash
npm install
```

## 运行测试

### 方法1：使用 Jest 测试框架（推荐）
```bash
# 安装依赖
npm install

# 运行所有测试
npm test

# 运行测试并监听文件变化
npm run test:watch

# 运行测试并生成覆盖率报告
npm run test:coverage
```

### 方法2：使用简单的 JavaScript 测试（无需安装依赖）
```bash
# 直接运行 JavaScript 测试文件
node test-simple.js
```

### 方法3：使用 TypeScript 测试（需要 ts-node）
```bash
# 安装 ts-node
npm install -g ts-node

# 运行 TypeScript 测试
ts-node 206.反转链表.simple-test.ts
```

## 测试用例覆盖

### 基础功能测试
1. **正常链表反转** - `[1,2,3,4,5]` → `[5,4,3,2,1]`
2. **空链表处理** - `null` → `null`
3. **单节点链表** - `[1]` → `[1]`
4. **两个节点链表** - `[1,2]` → `[2,1]`
5. **三个节点链表** - `[1,2,3]` → `[3,2,1]`

### 边界条件测试
6. **包含重复元素** - `[1,1,2,2,3]` → `[3,2,2,1,1]`
7. **大链表处理** - `[1,2,3,4,5,6,7,8,9,10]` → `[10,9,8,7,6,5,4,3,2,1]`
8. **负数链表** - `[-1,-2,-3]` → `[-3,-2,-1]`
9. **混合正负数** - `[1,-2,3,-4,5]` → `[5,-4,3,-2,1]`
10. **零值链表** - `[0,0,0]` → `[0,0,0]`

### 性能测试
11. **大型链表性能** - 10000个节点的链表反转，确保在1秒内完成

### 边界值测试
12. **最大整数值** - 测试 `Number.MAX_SAFE_INTEGER` 和 `Number.MIN_SAFE_INTEGER`
13. **浮点数处理** - 测试浮点数值的处理（虽然题目要求整数）

## 测试输出示例

```
=== 反转链表测试用例 ===

测试用例1: [1,2,3,4,5]
原始链表: 1 -> 2 -> 3 -> 4 -> 5
反转后: 5 -> 4 -> 3 -> 2 -> 1
期望结果: 5 -> 4 -> 3 -> 2 -> 1
测试结果: ✅ 通过

测试用例2: 空链表
原始链表: null
反转后: null
测试结果: ✅ 通过
```

## 辅助函数

测试文件中包含了以下辅助函数：

- `createLinkedList(arr: number[])` - 从数组创建链表
- `linkedListToArray(head: ListNode | null)` - 将链表转换为数组
- `compareLinkedLists(list1, list2)` - 比较两个链表是否相等

## 注意事项

1. 确保已安装 Node.js 和 npm
2. 首次运行前需要安装依赖：`npm install`
3. 如果遇到 TypeScript 类型错误，可能需要安装类型定义：`npm install --save-dev @types/node`
4. 测试覆盖率报告会生成在 `coverage/` 目录中 